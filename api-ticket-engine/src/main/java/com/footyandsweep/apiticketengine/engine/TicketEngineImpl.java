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

import com.footyandsweep.apicommonlibrary.events.SweepstakeSoldOut;
import com.footyandsweep.apicommonlibrary.events.TicketBought;
import com.footyandsweep.apicommonlibrary.model.sweepstake.SweepstakeCommon;
import com.footyandsweep.apicommonlibrary.model.ticket.TicketCommon;
import com.footyandsweep.apicommonlibrary.model.user.UserCommon;
import com.footyandsweep.apiticketengine.dao.TicketDao;
import com.footyandsweep.apiticketengine.model.Ticket;
import io.eventuate.tram.events.publisher.DomainEventPublisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static java.util.Collections.singletonList;

@Service
@Transactional
public class TicketEngineImpl implements TicketEngine {

  private final TicketDao ticketDao;
  private final DomainEventPublisher domainEventPublisher;

  @Autowired private RestTemplate restTemplate;

  /* Constructor for injecting spring beans/fields */
  public TicketEngineImpl(
      final TicketDao ticketDao, final DomainEventPublisher domainEventPublisher) {
    this.ticketDao = ticketDao;
    this.domainEventPublisher = domainEventPublisher;
  }

  @Override
  public void buyTickets(UUID userId, int numberOfTickets, String joinCode) {
    try {
      /* Get the user object by the id provided */
      Optional<UserCommon> user =
          Optional.ofNullable(
              restTemplate.getForObject(
                  "http://api-gateway-service:8080/internal/user/by/id/" + userId, UserCommon.class));

      /* Check if the user sent back is not malformed or null */
      if (!user.isPresent()) throw new Exception();

      /* Get the sweepstake object that has the joinCode */
      Optional<SweepstakeCommon> parentSweepstake =
          Optional.ofNullable(
              restTemplate.getForObject(
                  "http://api-sweepstake-engine:8080/internal/sweepstake/by/joinCode/" + joinCode,
                  SweepstakeCommon.class));

      /* Check if the sweepstake sent back is not malformed or null */
      if (!parentSweepstake.isPresent()) throw new Exception();

      /* Force a lock this thread */
      sweepstakeLockHelper();

      /* If the user cannot afford tickets, throw an error/send error message to client via WebSocket */
      if (!this.canUserAffordTickets(
          user.get().getBalance(), numberOfTickets, parentSweepstake.get().getStake()))
        throw new Exception();

      /* Invoking the helper function to deal with database iterations/transactions */
      this.ticketIteratorHelper(numberOfTickets, userId, parentSweepstake.get());

      /* Fetching all of the tickets with this sweepstake id from the database */
      Optional<List<Ticket>> allSweepstakeTickets =
          ticketDao.findAllTicketsBySweepstakeId(parentSweepstake.get().getId());

      /* Checking if the tickets can be allocated already */
      if (allSweepstakeTickets.isPresent()) {
        if (allSweepstakeTickets.get().size() >= parentSweepstake.get().getTotalNumberOfTickets()) {
          /* Creating the sweepstake sold out object for the other services to react to */
          SweepstakeSoldOut sweepstakeSoldOut = new SweepstakeSoldOut(parentSweepstake.get());

          /* Dispatch the sweepstake sold out event */
          domainEventPublisher.publish(
              SweepstakeCommon.class,
              parentSweepstake.get().getId(),
              singletonList(sweepstakeSoldOut));
        }
      }

    } catch (Exception e) {
      /* Get the error message and ping it back to the client */
    }
  }

  private void ticketIteratorHelper(
      int numberOfTickets, UUID userId, SweepstakeCommon parentSweepstake) {
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
      TicketBought ticketBought = new TicketBought(ticket, parentSweepstake.getStake());

      /* Dispatch tickets bought event */
      domainEventPublisher.publish(TicketCommon.class, ticket.getId(), singletonList(ticketBought));
    }
  }

  private boolean canUserAffordTickets(
      BigDecimal userBalance, int numberOfTickets, BigDecimal stake) {
    /* Multiply the number of tickets by the stake */
    BigDecimal total = stake.multiply(new BigDecimal(numberOfTickets));

    /* Validate whether the user balance is greater than the total cost and return */
    return userBalance.compareTo(total) >= 0;
  }

  private void sweepstakeLockHelper() {
    // TODO: Somehow leverage the distributed locks in the sweepstake engine
    // Validate that the user has enough credits
    // Validate that the sweepstake has enough
  }
}
