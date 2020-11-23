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

package com.footyandsweep.apiallocationengine.event;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.footyandsweep.apiallocationengine.dao.AllocationDao;
import com.footyandsweep.apiallocationengine.engine.AllocationEngine;
import com.footyandsweep.apicommonlibrary.events.EventType;
import com.footyandsweep.apicommonlibrary.events.SweepstakeEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class AllocationMessageListener {

  @Autowired private ObjectMapper objectMapper;
  @Autowired private AllocationEngine allocationEngine;
  @Autowired private AllocationDao allocationDao;

  @KafkaListener(
      topics = "api-sweepstake-events-topic",
      containerFactory = "AllocationEventKafkaListenerContainerFactory")
  public void sweepstakeEventListener(String serializedMessage) {
    try {
      /* Use JSON Object Mapper to read the message and reflect it into an object */
      SweepstakeEvent event = objectMapper.readValue(serializedMessage, SweepstakeEvent.class);

      /* Use relevant helper functions depending on the different event types */
      if (event.getEvent().equals(EventType.SOLD_OUT))
        allocationEngine.allocateSweepstakeTickets(event.getSweepstake());
    } catch (JsonProcessingException e) {
      /* TODO: Log or handle the exception here */
      System.out.println("Error sending or receiving a valid message!");
    }
  }
}
