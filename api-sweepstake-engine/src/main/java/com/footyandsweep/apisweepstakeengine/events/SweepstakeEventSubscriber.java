package com.footyandsweep.apisweepstakeengine.events;

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
            "com.footyandsweep.apicommonlibrary.model.SweepstakeCommon")
        .onEvent(TicketDecisioningSuccess.class, this::handleTicketDecisioningSuccess)
        .build();
  }

  private void handleTicketDecisioningSuccess(
      DomainEventEnvelope<TicketDecisioningSuccess> domainEventEnvelope) {
    // Handle the bunch of tickets that have been changed
    sweepstakeEngine.saveProcessedTickets(
        domainEventEnvelope.getEvent().getSweepstakeId(),
        domainEventEnvelope.getEvent().getTickets());
  }
}
