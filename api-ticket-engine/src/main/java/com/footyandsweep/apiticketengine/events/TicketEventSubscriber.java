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

import com.footyandsweep.apicommonlibrary.events.TicketDecisioningSuccess;
import com.footyandsweep.apiticketengine.engine.TicketEngine;
import io.eventuate.tram.events.subscriber.DomainEventEnvelope;
import io.eventuate.tram.events.subscriber.DomainEventHandlers;
import io.eventuate.tram.events.subscriber.DomainEventHandlersBuilder;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

@NoArgsConstructor
public class TicketEventSubscriber {

  @Autowired private TicketEngine ticketEngine;

  public DomainEventHandlers domainEventHandlers() {
    return DomainEventHandlersBuilder.forAggregateType(
            "com.footyandsweep.apicommonlibrary.model.ticket.TicketCommon")
        .onEvent(TicketDecisioningSuccess.class, this::handleTicketDecisioningSuccess)
        .build();
  }

  private void handleTicketDecisioningSuccess(
      DomainEventEnvelope<TicketDecisioningSuccess> domainEventEnvelope) {
    // Handle the bunch of tickets that have been changed
//    ticketEngine.saveProcessedTickets(
//        domainEventEnvelope.getEvent().getSweepstakeId(),
//        domainEventEnvelope.getEvent().getTickets());
  }
}
