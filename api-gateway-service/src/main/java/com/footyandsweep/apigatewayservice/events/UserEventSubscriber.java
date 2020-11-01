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

import com.footyandsweep.apicommonlibrary.events.SweepstakeCreated;
import com.footyandsweep.apigatewayservice.service.UserServiceImpl;
import io.eventuate.tram.events.subscriber.DomainEventEnvelope;
import io.eventuate.tram.events.subscriber.DomainEventHandlers;
import io.eventuate.tram.events.subscriber.DomainEventHandlersBuilder;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

@NoArgsConstructor
public class UserEventSubscriber {

  @Autowired private UserServiceImpl userService;

  public DomainEventHandlers domainEventHandlers() {
    return DomainEventHandlersBuilder.forAggregateType(
            "com.footyandsweep.apicommonlibrary.model.sweepstake.SweepstakeCommon")
        .onEvent(SweepstakeCreated.class, this::handleNewSweepstakeCreated)
        .build();
  }

  private void handleNewSweepstakeCreated(
      DomainEventEnvelope<SweepstakeCreated> domainEventEnvelope) {
    // Handle the sweepstake and user ids to be updated
    userService.addUserToSweepstake(domainEventEnvelope.getEvent().getSweepstake());
  }
}
