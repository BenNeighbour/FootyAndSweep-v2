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

package com.footyandsweep.apiticketengine.engine;

import com.footyandsweep.apicommonlibrary.cqrs.user.UpdateUserBalanceCommand;
import com.footyandsweep.apicommonlibrary.helper.SweepstakeLock;
import com.footyandsweep.apicommonlibrary.model.sweepstake.SweepstakeCommon;
import com.footyandsweep.apicommonlibrary.model.ticket.TicketCommon;
import com.footyandsweep.apiticketengine.dao.TicketDao;
import com.footyandsweep.apiticketengine.engine.saga.BuyTicketSagaData;
import com.footyandsweep.apiticketengine.model.Ticket;
import io.eventuate.tram.commands.consumer.CommandWithDestination;
import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static io.eventuate.tram.commands.consumer.CommandWithDestinationBuilder.send;

@Service
public class TicketEngineImpl implements TicketEngine {

  private static final Logger log = LoggerFactory.getLogger(TicketEngineImpl.class);
  private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ssZ");

  private final TicketDao ticketDao;
  private final RestTemplate restTemplate;

  public TicketEngineImpl(TicketDao ticketDao, RestTemplate restTemplate) {
    this.ticketDao = ticketDao;
    this.restTemplate = restTemplate;
  }

  /* TODO: REPLACE WITH GRPC CLIENTS */
  @Override
  public void getParentSweepstakeAndParticipant(BuyTicketSagaData sagaData) {
    /* Get the sweepstake object that has the joinCode */
    Optional<SweepstakeCommon> parentSweepstake =
            Optional.ofNullable(
                    restTemplate.getForObject(
                            "http://api-sweepstake-engine:8080/sweepstake/test/by/joinCode/" + sagaData.getParentSweepstake().getJoinCode(),
                            SweepstakeCommon.class));

    assert parentSweepstake.isPresent();
    sagaData.setParentSweepstake(parentSweepstake.get());
  }

  @Override
  public void buyTickets(BuyTicketSagaData sagaData) {
    try {
      /* Validate the sweepstake status */
      if (!sagaData.getParentSweepstake().getStatus().equals(SweepstakeCommon.SweepstakeStatus.OPEN))
        throw new Exception();

      /* Locking condition */
      boolean isSweepstakeLocked = false;

      try {
        /* Lock the user */
        SweepstakeLock.userLock(sagaData.getParticipant().getId());

        /* Lock the sweepstake - and all of the other participants*/
        SweepstakeLock.lockSweepstake(sagaData.getParentSweepstake().getJoinCode());
        isSweepstakeLocked = true;

        /* Invoking the helper function to deal with database iterations/transactions */
        this.ticketIteratorHelper(sagaData);

        /* Fetching all of the tickets with this sweepstake id from the database */
        Optional<List<Ticket>> allSweepstakeTickets =
                ticketDao.findAllTicketsBySweepstakeId(sagaData.getParentSweepstake().getId());

        /* Checking if the tickets can be allocated already */
        if (allSweepstakeTickets.isPresent()) {
          if (allSweepstakeTickets.get().size()
                  >= sagaData.getParentSweepstake().getTotalNumberOfTickets()) {
            /* TODO: Sold out! */

          }
        }

      } catch (InterruptedException ignored) {
      } finally {
        if (isSweepstakeLocked) {
          SweepstakeLock.unlockSweepstake(sagaData.getParentSweepstake().getJoinCode());
        }

        SweepstakeLock.userUnlock(sagaData.getParticipant().getId());
      }

    } catch (Exception e) {
      /* Get the error message and ping it back to the client */
    }
  }

  private void ticketIteratorHelper(BuyTicketSagaData sagaData) {
    sagaData.setSavedTickets(new ArrayList<>());

    try {
      /* When valid, buy each of the tickets for the user */
      for (int i = 0; i < sagaData.getNumberOfTickets(); i++) {
        Ticket ticket = new Ticket();

        /* Fill in those properties in the new ticket object */
        ticket.setUserId(sagaData.getParticipant().getId());
        ticket.setStatus(TicketCommon.TicketStatus.PENDING);
        ticket.setSweepstakeId(sagaData.getParentSweepstake().getId());

        /* Persist that ticket while adding it onto the list of bought tickets for that user */
        ticket = ticketDao.save(ticket);
        sagaData.getSavedTickets().add(ticket);
      }
    } catch (Exception e) {
      /* Get the error message and ping it back to the client */
    }
  }

  @Override
  public CommandWithDestination updateUserBalance(BuyTicketSagaData sagaData) {
    return send(new UpdateUserBalanceCommand(sagaData.getParticipant().getId(),
            sagaData.getParentSweepstake().getStake().multiply(new BigDecimal(sagaData.getNumberOfTickets())).multiply(new BigDecimal(-1))))
            .to("user-service-events")
            .build();
  }

  @Override
  public void modifyTickets(TicketCommon ticket) throws InvocationTargetException, IllegalAccessException {
    Ticket persistingTicket = new Ticket();

    BeanUtils.copyProperties(persistingTicket, ticket);
    ticketDao.saveAndFlush(persistingTicket);
  }

  @Override
  public void deleteTicket(String ticketId) {
    ticketDao.deleteById(ticketId);
  }
}
