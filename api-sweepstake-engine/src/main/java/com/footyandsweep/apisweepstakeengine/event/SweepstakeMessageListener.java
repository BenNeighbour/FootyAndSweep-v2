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

package com.footyandsweep.apisweepstakeengine.event;

import com.footyandsweep.apicommonlibrary.BaseEvent;
import com.footyandsweep.apicommonlibrary.events.EventType;
import com.footyandsweep.apicommonlibrary.events.ProcessStatus;
import com.footyandsweep.apicommonlibrary.events.SweepstakeEvent;
import com.footyandsweep.apisweepstakeengine.dao.SweepstakeDao;
import com.footyandsweep.apisweepstakeengine.engine.SweepstakeEngine;
import com.footyandsweep.apisweepstakeengine.model.Sweepstake;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class SweepstakeMessageListener {

  private final SweepstakeEngine sweepstakeEngine;
  private final SweepstakeDao sweepstakeDao;

  public SweepstakeMessageListener(SweepstakeEngine sweepstakeEngine, SweepstakeDao sweepstakeDao) {
    this.sweepstakeEngine = sweepstakeEngine;
    this.sweepstakeDao = sweepstakeDao;
  }

  @KafkaListener(
      id = "sweepstakeSweepstakeListener",
      topics = "api-sweepstake-events-topic",
      groupId = "sweepstakeConsumerGroup",
      containerFactory = "SweepstakeEventKafkaListenerContainerFactory")
  public void sweepstakeEventListener(BaseEvent message) {
    try {
      /* Use JSON Object Mapper to read the message and reflect it into an object */
      SweepstakeEvent event = (SweepstakeEvent) message;

      Sweepstake sweepstake = new Sweepstake();
      BeanUtils.copyProperties(sweepstake, event.getSweepstake());

      /* Use relevant helper functions depending on the different event types */
      if (event.getEvent().equals(EventType.RELATION_DELETED)) {
        sweepstakeEngine.deleteParticipantRelation(event.getSweepstake().getId());
        sweepstakeEngine.deleteSweepstake(event.getSweepstake().getId());
        sweepstake.setProcessStatus(ProcessStatus.INVALID);

        /* Invoke process ended event */
        event.setSweepstake(sweepstake);
        event.setEvent(EventType.PROCESS_ENDED);
        this.sweepstakeEventListener(event);
      }
      if (event.getEvent().equals(EventType.STATUS_UPDATED)) {
        sweepstakeDao.saveAndFlush(sweepstake);
      }
      /* Set the root entities status to persisted */
      if (event.getEvent().equals(EventType.PROCESS_ENDED)) {
        if (sweepstake.getProcessStatus().equals(ProcessStatus.RELATIONS_PENDING)) {
          sweepstake.setProcessStatus(ProcessStatus.FULLY_PERSISTED);
          sweepstakeDao.saveAndFlush(sweepstake);
        }
      }
    } catch (Exception e) {
      /* TODO: Log or handle the exception here */
    }
  }
}
