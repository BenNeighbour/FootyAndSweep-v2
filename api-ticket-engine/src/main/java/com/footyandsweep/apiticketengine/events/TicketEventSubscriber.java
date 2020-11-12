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

package com.footyandsweep.apiticketengine.events;

import com.footyandsweep.apicommonlibrary.events.TicketAllocated;
import com.footyandsweep.apiticketengine.dao.TicketDao;
import com.footyandsweep.apiticketengine.engine.TicketEngine;
import com.footyandsweep.apiticketengine.model.Ticket;
import io.eventuate.tram.events.subscriber.DomainEventEnvelope;
import io.eventuate.tram.events.subscriber.DomainEventHandlers;
import io.eventuate.tram.events.subscriber.DomainEventHandlersBuilder;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

@NoArgsConstructor
public class TicketEventSubscriber {

  @Autowired private TicketEngine ticketEngine;

  @Autowired private TicketDao ticketDao;

  public DomainEventHandlers domainEventHandlers() {
    return DomainEventHandlersBuilder.forAggregateType(
            "com.footyandsweep.apicommonlibrary.model.ticket.TicketCommon")
        .onEvent(TicketAllocated.class, this::handleTicketAllocatedEvent)
        .build();
  }

  private void handleTicketAllocatedEvent(
      DomainEventEnvelope<TicketAllocated> domainEventEnvelope) {
    ticketDao.saveAndFlush((Ticket) domainEventEnvelope.getEvent().getTicket());
  }
}
