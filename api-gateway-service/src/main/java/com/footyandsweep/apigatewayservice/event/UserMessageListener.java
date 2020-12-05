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

package com.footyandsweep.apigatewayservice.event;

import com.footyandsweep.apicommonlibrary.BaseEvent;
import com.footyandsweep.apicommonlibrary.events.EventType;
import com.footyandsweep.apicommonlibrary.events.SweepstakeEvent;
import com.footyandsweep.apicommonlibrary.events.TicketEvent;
import com.footyandsweep.apicommonlibrary.events.UserEvent;
import com.footyandsweep.apigatewayservice.dao.UserDao;
import com.footyandsweep.apigatewayservice.model.User;
import com.footyandsweep.apigatewayservice.service.UserService;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class UserMessageListener {

  private final UserService userService;
  private final UserDao userDao;

  public UserMessageListener(UserService userService, UserDao userDao) {
    this.userService = userService;
    this.userDao = userDao;
  }

  @KafkaListener(
      id = "userSweepstakeListener",
      topics = "api-sweepstake-events-topic",
      groupId = "userConsumerGroup",
      containerFactory = "UserEventKafkaListenerContainerFactory")
  public void sweepstakeEventListener(BaseEvent message) {
    try {
      /* Use JSON Object Mapper to read the message and reflect it into an object */
      SweepstakeEvent event = (SweepstakeEvent) message;

      /* Use relevant helper functions depending on the different event types */
      if (event.getEvent().equals(EventType.CREATED))
        userService.addOwnerToSweepstake(event.getSweepstake());
    } catch (Exception e) {
      /* TODO: Log or handle the exception here */
      System.out.println("Error sending or receiving a valid message!");
    }
  }

  @KafkaListener(
      id = "userTicketListener",
      topics = "api-ticket-events-topic",
      groupId = "userConsumerGroup",
      containerFactory = "UserEventKafkaListenerContainerFactory")
  public void ticketEventListener(BaseEvent message) {
    try {
      /* Use JSON Object Mapper to read the message and reflect it into an object */
      TicketEvent event = (TicketEvent) message;

      /* Use relevant helper functions depending on the different event types */
      if (event.getEvent().equals(EventType.PURCHASED)) {
        /* Deduct credits from user account */
        User user = userDao.findUserById(event.getTicket().getUserId());
        user.setBalance(user.getBalance().subtract(event.getTicket().getSweepstake().getStake()));

        /* Update that user */
        userDao.saveAndFlush(user);
      }
    } catch (Exception e) {
      /* TODO: Log or handle the exception here */
      System.out.println("Error sending or receiving a valid message!");
    }
  }

  @KafkaListener(
      id = "userUserListener",
      topics = "api-user-events-topic",
      groupId = "userConsumerGroup",
      containerFactory = "UserEventKafkaListenerContainerFactory")
  public void userEventListener(BaseEvent message) {
    try {
      /* Use JSON Object Mapper to read the message and reflect it into an object */
      UserEvent event = (UserEvent) message;

      /* Use relevant helper functions depending on the different event types */
      if (event.getEvent().equals(EventType.UPDATED)) userDao.saveAndFlush((User) event.getUser());
    } catch (Exception e) {
      /* TODO: Log or handle the exception here */
      System.out.println("Error sending or receiving a valid message!");
    }
  }
}