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
import com.footyandsweep.apicommonlibrary.events.TicketAllocated;
import com.footyandsweep.apisweepstakeengine.dao.SweepstakeDao;
import com.footyandsweep.apisweepstakeengine.engine.SweepstakeEngine;
import com.footyandsweep.apisweepstakeengine.model.Sweepstake;
import io.eventuate.tram.events.subscriber.DomainEventEnvelope;
import io.eventuate.tram.events.subscriber.DomainEventHandlers;
import io.eventuate.tram.events.subscriber.DomainEventHandlersBuilder;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

@NoArgsConstructor
public class SweepstakeEventSubscriber {

  @Autowired private SweepstakeEngine sweepstakeEngine;
  @Autowired private SweepstakeDao sweepstakeDao;

  public DomainEventHandlers domainEventHandlers() {
    return DomainEventHandlersBuilder.forAggregateType(
            "com.footyandsweep.apicommonlibrary.model.sweepstake.SweepstakeCommon")
        .onEvent(SweepstakeRelationDeleted.class, this::handleSweepstakeRelationDeleted)
        .build();
  }

  private void handleTicketAllocatedEvent(
      DomainEventEnvelope<TicketAllocated> domainEventEnvelope) {
    sweepstakeDao.saveAndFlush((Sweepstake) domainEventEnvelope.getEvent().getSweepstake());
  }

  private void handleSweepstakeRelationDeleted(
      DomainEventEnvelope<SweepstakeRelationDeleted> domainEventEnvelope) {
    // Handle this so that the sweepstake is deleted
    sweepstakeEngine.deleteSweepstake(
        domainEventEnvelope.getEvent().getSweepstake().getId());
  }
}
