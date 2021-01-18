/*
 *   Copyright 2021 FootyAndSweep
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

package com.footyandsweep.apiallocationengine.event;

import com.footyandsweep.apiallocationengine.dao.AllocationDao;
import com.footyandsweep.apiallocationengine.engine.AllocationEngine;
import com.footyandsweep.apicommonlibrary.BaseEvent;
import com.footyandsweep.apicommonlibrary.events.EventType;
import com.footyandsweep.apicommonlibrary.events.SweepstakeEvent;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class AllocationMessageListener {

  private final AllocationEngine allocationEngine;
  private final AllocationDao allocationDao;

  public AllocationMessageListener(AllocationEngine allocationEngine, AllocationDao allocationDao) {
    this.allocationEngine = allocationEngine;
    this.allocationDao = allocationDao;
  }

  @KafkaListener(
      id = "allocationSweepstakeListener",
      topics = "api-sweepstake-events-topic",
      groupId = "allocationConsumerGroup",
      containerFactory = "AllocationEventKafkaListenerContainerFactory")
  public void sweepstakeEventListener(BaseEvent message) {
    try {
      /* Use JSON Object Mapper to read the message and reflect it into an object */
      SweepstakeEvent event = (SweepstakeEvent) message;

      /* Use relevant helper functions depending on the different event types */
      if (event.getEvent().equals(EventType.SOLD_OUT)
          || event.getEvent().equals(EventType.NEEDS_ALLOCATING))
        allocationEngine.allocateSweepstakeTickets(event.getSweepstake());
    } catch (Exception e) {
      /* TODO: Log or handle the exception here */
      System.out.println("Error sending or receiving a valid message!");
    }
  }
}
