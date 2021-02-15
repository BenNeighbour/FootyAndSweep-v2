/*
 *   Copyright 2021 FootyAndSweep
 *
 *   Licensed under the Apache License, Version 2.0 (the "License");
 *   you may not use this file except in compliance with the License.
 *   You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 *   Unless required by applicable law or agreed to in writing, software
 *   distributed under the License is distributed on an "AS IS" BASIS,
 *   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *   See the License for the specific language governing permissions and
 *   limitations under the License.
 */

package com.footyandsweep.apiresultengine.engine;

import com.footyandsweep.Common;
import com.footyandsweep.SweepstakeServiceGrpc;
import com.footyandsweep.SweepstakeServiceOuterClass;
import com.footyandsweep.apicommonlibrary.cqrs.user.UpdateUserBalanceCommand;
import com.footyandsweep.apicommonlibrary.model.sweepstake.SweepstakeCommon;
import com.footyandsweep.apicommonlibrary.model.sweepstake.SweepstakeTypeCommon;
import com.footyandsweep.apicommonlibrary.model.ticket.AllocationCommon;
import com.footyandsweep.apicommonlibrary.model.ticket.TicketCommon;
import com.footyandsweep.apiresultengine.dao.ResultDao;
import com.footyandsweep.apiresultengine.engine.saga.ProcessSweepstakeResultSagaData;
import com.footyandsweep.apiresultengine.model.FootballMatchResult;
import io.eventuate.tram.commands.consumer.CommandWithDestination;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.SimpleDateFormat;
import java.util.*;

import static io.eventuate.tram.commands.consumer.CommandWithDestinationBuilder.send;

@Service
public class ResultEngineImpl implements ResultEngine {

  private static final Logger log = LoggerFactory.getLogger(ResultEngineImpl.class);
  private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ssZ");

  private final ResultDao resultDao;
  private final RestTemplate restTemplate;

  public ResultEngineImpl(ResultDao resultDao, RestTemplate restTemplate) {
    this.resultDao = resultDao;
    this.restTemplate = restTemplate;
  }

  @Override
  public void processSweepstakeResult(ProcessSweepstakeResultSagaData sagaData) {
    /* Cast the result to a Football Match Result */
    FootballMatchResult footballMatchResult = (FootballMatchResult) sagaData.getResult();

    /* TODO: CREATE A BEAN FOR THIS FOR EACH SERVICE IN EVERY SERVICE */
    ManagedChannel channel =
        ManagedChannelBuilder.forAddress("api-sweepstake-engine", 9090).usePlaintext().build();

    SweepstakeServiceGrpc.SweepstakeServiceBlockingStub clientStub =
        SweepstakeServiceGrpc.newBlockingStub(channel);

    SweepstakeServiceOuterClass.SweepstakeList sweepstakesWithResult =
        clientStub.findSweepstakeByFootballMatchId(
            Common.Id.newBuilder()
                .setId(footballMatchResult.getFootballMatchId().toString())
                .build());

    channel.shutdown();

    /* For each sweepstake, process the tickets for it */
    sweepstakesWithResult
        .getSweepstakesList()
        .forEach(
            sweepstake -> {
              SweepstakeCommon sweepstakeCommon = new SweepstakeCommon();
              try {
                BeanUtils.copyProperties(sweepstakeCommon, sweepstake);
              } catch (IllegalAccessException | InvocationTargetException e) {
                e.printStackTrace();
              }
              sagaData.setSweepstake(sweepstakeCommon);
              this.processTickets(sagaData);
            });

    /* Make this result processed so it doesn't get attempted again , and persist it */
    sagaData.getResult().setProcessed(true);
    sagaData.setResult(resultDao.saveAndFlush(sagaData.getResult()));

    /* Log the event */
    log.info(
        "Result {} for football match {} has been created! {}",
        sagaData.getResult().getId(),
        footballMatchResult.getFootballMatchId(),
        dateFormat.format(new Date()));
  }

  private void processTickets(ProcessSweepstakeResultSagaData sagaData) {
    /* Get all of the winning codes */
    Map<Integer, String> winningResultMap = this.getSweepstakeResultMap(sagaData.getSweepstake());

    /* Get a list of tickets that belong to the sweepstake */
    Optional<List<TicketCommon>> ticketList =
        Optional.of(
            Arrays.asList(
                Objects.requireNonNull(
                    restTemplate.getForObject(
                        "http://api-ticket-engine/internal/ticket/by/sweepstake/"
                            + sagaData.getSweepstake().getId(),
                        TicketCommon[].class))));

    /* Seeing if the winning ticket is present */
    boolean isWinningTicketPresent =
        ticketList.get().stream()
            .anyMatch(
                ticket ->
                    winningResultMap.containsKey(
                        this.getTicketAllocation(ticket.getId()).getCode()));

    ticketList
        .get()
        .forEach(
            ticket -> {
              if (isWinningTicketPresent) {
                if (winningResultMap.containsKey(
                    this.getTicketAllocation(ticket.getId()).getCode())) {
                  ticket.setStatus(TicketCommon.TicketStatus.WON);
                  BigDecimal totalPot =
                      sagaData
                          .getSweepstake()
                          .getStake()
                          .multiply(new BigDecimal(ticketList.get().size()));

                  if (winningResultMap.values().size() > 1) {
                    totalPot =
                        totalPot.divide(
                            new BigDecimal(winningResultMap.values().size()), RoundingMode.DOWN);
                    totalPot = totalPot.setScale(2, RoundingMode.DOWN);
                  }

                  /* Update user balance */
                  sagaData.setUserBalanceMap(new ImmutablePair<>(ticket.getUserId(), totalPot));

                } else {
                  ticket.setStatus(TicketCommon.TicketStatus.LOST);
                }
              } else {
                ticket.setStatus(TicketCommon.TicketStatus.REFUNDED);
                /* Update the user balance */
                //                sagaData.setUserBalanceMap(ticket.getUserId(),
                // sagaData.getSweepstake().getStake());
                sagaData.setUserBalanceMap(
                    new ImmutablePair<>(ticket.getUserId(), sagaData.getSweepstake().getStake()));
              }
            });
  }

  private Map<Integer, String> getSweepstakeResultMap(SweepstakeCommon sweepstake) {
    /* Defining the result map so it can be modified in the iteration */
    Optional<Map<Integer, String>> resultMap = Optional.empty();

    /* Going over all of the sweepstake types in the enum in order to programmatically determine what method will be invoked on the result helper */
    for (SweepstakeTypeCommon i : SweepstakeTypeCommon.values()) {
      /* Call result helper to get the field and return a function that returns the right maps to back */
      if (sweepstake.getSweepstakeType().equals(i))
        resultMap =
            Optional.ofNullable(
                restTemplate
                    .postForEntity(
                        "http://api-sweepstake-engine:8080/internal/sweepstake/result",
                        sweepstake,
                        Map.class)
                    .getBody());
    }

    return resultMap.orElse(new HashMap<>());
  }

  private AllocationCommon getTicketAllocation(String ticketId) {
    return restTemplate
        .getForEntity(
            "http://api-allocation-engine:8080/internal/allocation/by/ticket/" + ticketId,
            AllocationCommon.class)
        .getBody();
  }

  @Override
  public CommandWithDestination updateUserBalance(ProcessSweepstakeResultSagaData sagaData) {
    return send(new UpdateUserBalanceCommand(
            sagaData.getUserBalanceMap().getKey(), sagaData.getUserBalanceMap().getValue()))
        .to("user-service-events")
        .build();
  }
}
