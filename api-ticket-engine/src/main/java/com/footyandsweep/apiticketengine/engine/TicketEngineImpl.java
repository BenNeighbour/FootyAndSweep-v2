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

package com.footyandsweep.apiticketengine.engine;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.footyandsweep.apicommonlibrary.events.EventType;
import com.footyandsweep.apicommonlibrary.events.SweepstakeEvent;
import com.footyandsweep.apicommonlibrary.events.TicketEvent;
import com.footyandsweep.apicommonlibrary.helper.SweepstakeLock;
import com.footyandsweep.apicommonlibrary.model.sweepstake.SweepstakeCommon;
import com.footyandsweep.apicommonlibrary.model.ticket.TicketCommon;
import com.footyandsweep.apicommonlibrary.model.user.UserCommon;
import com.footyandsweep.apiticketengine.dao.TicketDao;
import com.footyandsweep.apiticketengine.event.TicketMessageDispatcher;
import com.footyandsweep.apiticketengine.model.Ticket;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Transactional
public class TicketEngineImpl implements TicketEngine {

  private final TicketDao ticketDao;
  private final RestTemplate restTemplate;
  private final TicketMessageDispatcher ticketMessageDispatcher;

  public TicketEngineImpl(final TicketDao ticketDao, final RestTemplate restTemplate, final TicketMessageDispatcher ticketMessageDispatcher) {
    this.ticketDao = ticketDao;
    this.restTemplate = restTemplate;
    this.ticketMessageDispatcher = ticketMessageDispatcher;
  }

  @Override
  public void buyTickets(UUID userId, int numberOfTickets, String joinCode) {
    try {
      /* Get the user object by the id provided */
      Optional<UserCommon> user =
          Optional.ofNullable(
              restTemplate.getForObject(
                  "http://api-gateway-service:8080/internal/user/by/id/" + userId,
                  UserCommon.class));

      /* Check if the user sent back is not malformed or null */
      if (!user.isPresent()) throw new Exception();

      /* Get the sweepstake object that has the joinCode */
      Optional<SweepstakeCommon> parentSweepstake =
          Optional.ofNullable(
              restTemplate.getForObject(
                  "http://api-sweepstake-engine:8080/internal/sweepstake/by/joinCode/" + joinCode,
                  SweepstakeCommon.class));

      /* Check if the sweepstake sent back is not malformed or null - meaning it's an invalid join code */
      if (!parentSweepstake.isPresent()) throw new Exception();

      /* Validate the sweepstake status */
      if (!parentSweepstake.get().getStatus().equals(SweepstakeCommon.SweepstakeStatus.OPEN))
        throw new Exception();

      /* TODO: Validate whether the user is actually part of the sweepstake */

      /* Locking condition */
      boolean isSweepstakeLocked = false;

      try {
        /* Lock the user */
        SweepstakeLock.userLock(user.get().getId());

        /* If the user cannot afford tickets, throw an error/send error message to client via WebSocket */
        if (!this.canUserAffordTickets(
            user.get().getBalance(), numberOfTickets, parentSweepstake.get().getStake()))
          throw new Exception();

        /* Lock the sweepstake - and all of the other participants*/
        SweepstakeLock.lockSweepstake(parentSweepstake.get().getJoinCode());
        isSweepstakeLocked = true;

        /* Invoking the helper function to deal with database iterations/transactions */
        this.ticketIteratorHelper(numberOfTickets, userId, parentSweepstake.get());

        /* Fetching all of the tickets with this sweepstake id from the database */
        Optional<List<Ticket>> allSweepstakeTickets =
            ticketDao.findAllTicketsBySweepstakeId(parentSweepstake.get().getId());

        /* Checking if the tickets can be allocated already */
        if (allSweepstakeTickets.isPresent()) {
          if (allSweepstakeTickets.get().size()
              >= parentSweepstake.get().getTotalNumberOfTickets()) {
            /* Creating the sweepstake sold out object for the other services to react to */
            SweepstakeEvent sweepstakeSoldOut = new SweepstakeEvent(parentSweepstake.get(), EventType.SOLD_OUT);

            /* Dispatch the sweepstake sold out event */
            ticketMessageDispatcher.publishEvent(sweepstakeSoldOut, "api-sweepstake-events-topic");
          }
        }
      } catch (InterruptedException ignored) {
      } finally {
        if (isSweepstakeLocked) {
          SweepstakeLock.unlockSweepstake(parentSweepstake.get().getJoinCode());
        }

        SweepstakeLock.userUnlock(userId);
      }

    } catch (Exception e) {
      /* Get the error message and ping it back to the client */
    }
  }

  private void ticketIteratorHelper(
      int numberOfTickets, UUID userId, SweepstakeCommon parentSweepstake) {
    try{
      /* When valid, buy each of the tickets for the user */
      for (int i = 0; i < numberOfTickets; i++) {
        Ticket ticket = new Ticket();

        /* Fill in those properties in the new ticket object */
        ticket.setUserId(userId);
        ticket.setStatus(TicketCommon.TicketStatus.PENDING);
        ticket.setSweepstakeId(parentSweepstake.getId());

        /* Persist that ticket while adding it onto the list of bought tickets for that user */
        ticket = ticketDao.save(ticket);

        /* Creating the sweepstake created object for the other services to react to */
        TicketEvent ticketBought = new TicketEvent(ticket, EventType.PURCHASED);

        /* Dispatch tickets bought event */
        ticketMessageDispatcher.publishEvent(ticketBought, "api-ticket-event-topic");
      }
    } catch (Exception e) {
      /* Get the error message and ping it back to the client */
    }
  }

  private boolean canUserAffordTickets(
      BigDecimal userBalance, int numberOfTickets, BigDecimal stake) {
    /* Multiply the number of tickets by the stake */
    BigDecimal total = stake.multiply(new BigDecimal(numberOfTickets));

    /* Validate whether the user balance is greater than the total cost and return */
    return userBalance.compareTo(total) >= 0;
  }
}
