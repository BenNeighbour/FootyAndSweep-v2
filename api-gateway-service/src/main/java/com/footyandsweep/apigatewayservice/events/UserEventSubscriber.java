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

package com.footyandsweep.apigatewayservice.events;

import com.footyandsweep.apigatewayservice.dao.UserDao;
import com.footyandsweep.apigatewayservice.model.User;
import com.footyandsweep.apigatewayservice.service.UserServiceImpl;
import io.eventuate.tram.events.subscriber.DomainEventEnvelope;
import io.eventuate.tram.events.subscriber.DomainEventHandlers;
import io.eventuate.tram.events.subscriber.DomainEventHandlersBuilder;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

@NoArgsConstructor
public class UserEventSubscriber {

  @Autowired private UserServiceImpl userService;
  @Autowired private UserDao userDao;

  public DomainEventHandlers domainEventHandlers() {
    return DomainEventHandlersBuilder.forAggregateType(
            "com.footyandsweep.apicommonlibrary.model.sweepstake.SweepstakeCommon")
        .onEvent(SweepstakeCreated.class, this::handleNewSweepstakeCreated)
        .andForAggregateType("com.footyandsweep.apicommonlibrary.model.ticket.TicketCommon")
        .onEvent(TicketBought.class, this::handleTicketsBought)
        .build();
  }

  private void handleNewSweepstakeCreated(
      DomainEventEnvelope<SweepstakeCreated> domainEventEnvelope) {
    /* Handle the sweepstake and user ids to be updated */
    userService.addOwnerToSweepstake(domainEventEnvelope.getEvent().getSweepstake());
  }

  private void handleTicketsBought(DomainEventEnvelope<TicketBought> domainEventEnvelope) {
    /* Update the user's balance accordingly */
    Optional<User> ticketOwner =
        Optional.ofNullable(
            userDao.findUserById(domainEventEnvelope.getEvent().getTicket().getUserId()));

    if (ticketOwner.isPresent()) {
      ticketOwner
          .get()
          .setBalance(
              ticketOwner.get().getBalance().subtract(domainEventEnvelope.getEvent().getPrice()));
      userDao.saveAndFlush(ticketOwner.get());
    } else {
      /* TODO: SOMETHING ISN'T RIGHT! */
    }
  }
}
