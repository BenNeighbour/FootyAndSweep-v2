package com.footyandsweep.apigatewayservice.events;

import com.footyandsweep.apicommonlibrary.events.SweepstakeCreated;
import com.footyandsweep.apigatewayservice.service.UserService;
import io.eventuate.tram.events.subscriber.DomainEventEnvelope;
import io.eventuate.tram.events.subscriber.DomainEventHandlers;
import io.eventuate.tram.events.subscriber.DomainEventHandlersBuilder;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

@NoArgsConstructor
public class UserEventSubscriber {

  @Autowired private UserService userService;

  public DomainEventHandlers domainEventHandlers() {
    return DomainEventHandlersBuilder.forAggregateType(
            "com.footyandsweep.apicommonlibrary.model.SweepstakeCommon")
        .onEvent(SweepstakeCreated.class, this::handleNewSweepstakeCreated)
        .build();
  }

  private void handleNewSweepstakeCreated(
      DomainEventEnvelope<SweepstakeCreated> domainEventEnvelope) {
    // Handle the sweepstake and user ids to be updated
    userService.addUserToSweepstake(
        domainEventEnvelope.getEvent().getOwnerId(), domainEventEnvelope.getEvent());
  }
}
