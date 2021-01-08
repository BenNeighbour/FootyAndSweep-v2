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

package com.footyandsweep.apiticketengine.event;

import com.footyandsweep.apicommonlibrary.BaseEvent;
import com.footyandsweep.apicommonlibrary.events.EventType;
import com.footyandsweep.apicommonlibrary.events.SweepstakeEvent;
import com.footyandsweep.apicommonlibrary.events.TicketEvent;
import com.footyandsweep.apicommonlibrary.model.sweepstake.SweepstakeCommon;
import com.footyandsweep.apiticketengine.dao.TicketDao;
import com.footyandsweep.apiticketengine.engine.TicketEngine;
import com.footyandsweep.apiticketengine.model.Ticket;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class TicketMessageListener {

  private final TicketEngine ticketEngine;
  private final TicketDao ticketDao;
  private final TicketMessageDispatcher ticketMessageDispatcher;

  public TicketMessageListener(
      TicketEngine ticketEngine,
      TicketDao ticketDao,
      TicketMessageDispatcher ticketMessageDispatcher) {
    this.ticketEngine = ticketEngine;
    this.ticketDao = ticketDao;
    this.ticketMessageDispatcher = ticketMessageDispatcher;
  }

  @KafkaListener(
      id = "ticketTicketListener",
      topics = "api-ticket-events-topic",
      groupId = "ticketConsumerGroup",
      containerFactory = "TicketEventKafkaListenerContainerFactory")
  public void ticketEventListener(BaseEvent message) {
    try {
      /* Use JSON Object Mapper to read the message and reflect it into an object */
      TicketEvent event = (TicketEvent) message;

      /* Use relevant helper functions depending on the different event types */
      if (event.getEvent().equals(EventType.ALLOCATED)
          && !event
              .getTicket()
              .getSweepstake()
              .getStatus()
              .equals(SweepstakeCommon.SweepstakeStatus.ALLOCATED)) {
        Ticket ticket = new Ticket();
        BeanUtils.copyProperties(ticket, event.getTicket());

        /* Update the ticket in the database */
        ticketDao.saveAndFlush(ticket);

        if (event.isLastTicket()) {
          /* Setting the status */
          ticket.getSweepstake().setStatus(SweepstakeCommon.SweepstakeStatus.ALLOCATED);

          SweepstakeEvent sweepstakeEvent =
              new SweepstakeEvent(ticket.getSweepstake(), EventType.STATUS_UPDATED);

          /* Publish message to sweepstake to sweepstake */
          ticketMessageDispatcher.publishEvent(sweepstakeEvent, "api-sweepstake-events-topic");
        }
      }
    } catch (Exception e) {
      /* TODO: Log or handle the exception here */
    }
  }
}
