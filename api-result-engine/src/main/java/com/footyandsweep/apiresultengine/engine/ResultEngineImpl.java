/*
 *   Copyright 2020 FootyAndSweep
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

import com.footyandsweep.apicommonlibrary.events.EventType;
import com.footyandsweep.apicommonlibrary.events.UserEvent;
import com.footyandsweep.apicommonlibrary.helper.SweepstakeLock;
import com.footyandsweep.apicommonlibrary.model.sweepstake.SweepstakeCommon;
import com.footyandsweep.apicommonlibrary.model.sweepstake.SweepstakeTypeCommon;
import com.footyandsweep.apicommonlibrary.model.ticket.AllocationCommon;
import com.footyandsweep.apicommonlibrary.model.ticket.TicketCommon;
import com.footyandsweep.apicommonlibrary.model.user.UserCommon;
import com.footyandsweep.apiresultengine.dao.ResultDao;
import com.footyandsweep.apiresultengine.event.ResultMessageDispatcher;
import com.footyandsweep.apiresultengine.model.FootballMatchResult;
import com.footyandsweep.apiresultengine.model.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class ResultEngineImpl implements ResultEngine {

  private static final Logger log = LoggerFactory.getLogger(ResultEngineImpl.class);
  private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ssZ");

  private final ResultDao resultDao;
  private final RestTemplate restTemplate;
  private final ResultMessageDispatcher resultMessageDispatcher;

  public ResultEngineImpl(
      ResultDao resultDao,
      RestTemplate restTemplate,
      ResultMessageDispatcher resultMessageDispatcher) {
    this.resultDao = resultDao;
    this.restTemplate = restTemplate;
    this.resultMessageDispatcher = resultMessageDispatcher;
  }

  @Override
  @Transactional
  public void processWinningTickets() {
    /* For each result that is not processed, process it! */
    resultDao.findAll().stream()
        .filter(result -> !result.isProcessed())
        .forEach(this::processSweepstakeResult);
  }

  private void processSweepstakeResult(Result result) {
    /* TODO: CHANGE THIS: Cast the result to a Football Match Result */
    FootballMatchResult footballMatchResult = (FootballMatchResult) result;

    /* Get a list of sweepstakes that are linked to the result event, and are of the right type */
    List<SweepstakeCommon> sweepstakesWithResult =
        Arrays.asList(
            Objects.requireNonNull(
                restTemplate.getForObject(
                    "http://api-sweepstake-engine:8080/internal/sweepstake/by/footballMatch/"
                        + footballMatchResult.getFootballMatchId(),
                    SweepstakeCommon[].class)));

    /* For each sweepstake, process the tickets for it */
    sweepstakesWithResult.forEach(
        sweepstake -> this.processTickets(sweepstake.getStake(), sweepstake));

    /* Make this result processed so it doesn't get attempted again , and persist it */
    result.setProcessed(true);
    resultDao.saveAndFlush(result);
  }

  private void processTickets(BigDecimal stake, SweepstakeCommon sweepstake) {
    /* Get all of the winning codes */
    Map<Integer, String> winningResultMap = this.getSweepstakeResultMap(sweepstake);

    /* Get a list of tickets that belong to the sweepstake */
    Optional<List<TicketCommon>> ticketList =
        Optional.of(
            Arrays.asList(
                Objects.requireNonNull(
                    restTemplate.getForObject(
                        "http://api-ticket-engine/internal/ticket/by/sweepstake/"
                            + sweepstake.getId(),
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
                      sweepstake.getStake().multiply(new BigDecimal(ticketList.get().size()));

                  if (winningResultMap.values().size() > 1) {
                    totalPot =
                        totalPot.divide(
                            new BigDecimal(winningResultMap.values().size()), RoundingMode.DOWN);
                    totalPot = totalPot.setScale(2, RoundingMode.DOWN);
                  }

                  /* Update user balance */
                  this.updateUserBalance(ticket.getUserId(), totalPot);
                } else {
                  ticket.setStatus(TicketCommon.TicketStatus.LOST);
                }
              } else {
                ticket.setStatus(TicketCommon.TicketStatus.REFUNDED);
                /* Update the user balance */
                this.updateUserBalance(ticket.getUserId(), sweepstake.getStake());
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

  private AllocationCommon getTicketAllocation(UUID ticketId) {
    return restTemplate
        .getForEntity(
            "http://api-allocation-engine:8080/internal/allocation/by/ticket/" + ticketId,
            AllocationCommon.class)
        .getBody();
  }

  private void updateUserBalance(UUID userId, BigDecimal creditAmount) {
    try {
      /* Locking the user from buying more tickets */
      SweepstakeLock.userLock(userId);

      /* Get the user and set it's new balance */
      UserCommon user =
          restTemplate.getForObject(
              "http://api-gateway-service:8080/internal/user/by/id/" + userId, UserCommon.class);

      assert user != null;
      user.setBalance(user.getBalance().subtract(creditAmount));

      /* Creating the event object to be sent to the kafka stream */
      UserEvent userEvent = new UserEvent(user, EventType.UPDATED);

      /* Dispatch message to gateway service to set the user's balance to itself + the credit amount */
      resultMessageDispatcher.publishEvent(userEvent, "api-user-events-topic");
    } catch (InterruptedException ie) {
      ie.getMessage();
    } finally {
      /* Unlocking the lock afterwards */
      SweepstakeLock.userUnlock(userId);
    }
  }
}
