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

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.footyandsweep.apicommonlibrary.BaseEvent;
import com.footyandsweep.apicommonlibrary.events.EventType;
import com.footyandsweep.apicommonlibrary.events.SweepstakeEvent;
import com.footyandsweep.apicommonlibrary.events.TicketEvent;
import com.footyandsweep.apisweepstakeengine.dao.SweepstakeDao;
import com.footyandsweep.apisweepstakeengine.engine.SweepstakeEngine;
import com.footyandsweep.apisweepstakeengine.model.Sweepstake;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class SweepstakeMessageListener {

  private final ObjectMapper objectMapper;
  private final SweepstakeEngine sweepstakeEngine;
  private final SweepstakeDao sweepstakeDao;

  public SweepstakeMessageListener(
      ObjectMapper objectMapper, SweepstakeEngine sweepstakeEngine, SweepstakeDao sweepstakeDao) {
    this.objectMapper = objectMapper;
    this.sweepstakeEngine = sweepstakeEngine;
    this.sweepstakeDao = sweepstakeDao;
  }

//  @KafkaListener(
//          id = "sweepstakeTicketListener",
//          topics = "api-ticket-events-topic",
//          groupId = "sweepstakeConsumerGroup",
//          containerFactory = "SweepstakeEventKafkaListenerContainerFactory")
//  public void ticketEventListener(BaseEvent message) {
//    try {
//      /* Use JSON Object Mapper to read the message and reflect it into an object */
//      TicketEvent event = (TicketEvent) message;
//
//      /* Use relevant helper functions depending on the different event types */
//      if (event.getEvent().equals(EventType.ALLOCATED))
//        sweepstakeDao.saveAndFlush((Sweepstake) event.getTicket().getSweepstake());
//    } catch (Exception e) {
//      /* TODO: Log or handle the exception here */
//      System.out.println("Error sending or receiving a valid message!");
//    }
//  }

  @KafkaListener(
      id = "sweepstakeSweepstakeListener",
      topics = "api-sweepstake-events-topic",
      groupId = "sweepstakeConsumerGroup",
      containerFactory = "SweepstakeEventKafkaListenerContainerFactory")
  public void sweepstakeEventListener(BaseEvent message) {
    try {
      /* Use JSON Object Mapper to read the message and reflect it into an object */
      SweepstakeEvent event = (SweepstakeEvent) message;

      /* Use relevant helper functions depending on the different event types */
      if (event.getEvent().equals(EventType.RELATION_DELETED)) {
        sweepstakeEngine.deleteParticipantRelation(event.getSweepstake().getId());
        sweepstakeEngine.deleteSweepstake(event.getSweepstake().getId());
      }
    } catch (Exception e) {
      /* TODO: Log or handle the exception here */
      System.out.println("Error sending or receiving a valid message!");
    }
  }
}
