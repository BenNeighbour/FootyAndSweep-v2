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

package com.footyandsweep.apisweepstakeengine.events;

import com.footyandsweep.apicommonlibrary.events.SweepstakeRelationDeleted;
import com.footyandsweep.apicommonlibrary.events.TicketDecisioningSuccess;
import com.footyandsweep.apisweepstakeengine.engine.SweepstakeEngine;
import io.eventuate.tram.events.subscriber.DomainEventEnvelope;
import io.eventuate.tram.events.subscriber.DomainEventHandlers;
import io.eventuate.tram.events.subscriber.DomainEventHandlersBuilder;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

@NoArgsConstructor
public class SweepstakeEventSubscriber {

  @Autowired private SweepstakeEngine sweepstakeEngine;

  public DomainEventHandlers domainEventHandlers() {
    return DomainEventHandlersBuilder.forAggregateType(
            "com.footyandsweep.apicommonlibrary.model.ticket.TicketCommon")
        .onEvent(TicketDecisioningSuccess.class, this::handleTicketDecisioningSuccess)
        .andForAggregateType("com.footyandsweep.apicommonlibrary.model.sweepstake.SweepstakeCommon")
        .onEvent(SweepstakeRelationDeleted.class, this::handleSweepstakeRelationDeleted)
        .build();
  }

  private void handleTicketDecisioningSuccess(
      DomainEventEnvelope<TicketDecisioningSuccess> domainEventEnvelope) {
    // Handle the bunch of tickets that have been changed
    sweepstakeEngine.saveProcessedTickets(
        domainEventEnvelope.getEvent().getSweepstakeId(),
        domainEventEnvelope.getEvent().getTickets());
  }

  private void handleSweepstakeRelationDeleted(
      DomainEventEnvelope<SweepstakeRelationDeleted> domainEventEnvelope) {
    // Handle this so that the sweepstake is deleted
    sweepstakeEngine.deleteSweepstake(
        domainEventEnvelope.getEvent().getSweepstakeId(),
        domainEventEnvelope.getEvent().getErrorReason());
  }
}
