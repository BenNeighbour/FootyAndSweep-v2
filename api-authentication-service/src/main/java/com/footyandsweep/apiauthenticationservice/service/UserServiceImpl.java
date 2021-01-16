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

package com.footyandsweep.apiauthenticationservice.service;

import com.footyandsweep.AuthenticationServiceOuterClass;
import com.footyandsweep.apiauthenticationservice.dao.SweepstakeIdDao;
import com.footyandsweep.apiauthenticationservice.dao.UserDao;
import com.footyandsweep.apiauthenticationservice.event.UserMessageDispatcher;
import com.footyandsweep.apiauthenticationservice.exception.SignUpException;
import com.footyandsweep.apiauthenticationservice.model.User;
import com.footyandsweep.apiauthenticationservice.relation.SweepstakeIds;
import com.footyandsweep.apicommonlibrary.events.EventType;
import com.footyandsweep.apicommonlibrary.events.SweepstakeEvent;
import com.footyandsweep.apicommonlibrary.model.sweepstake.SweepstakeCommon;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class UserServiceImpl implements UserService {

  private static final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);
  private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ssZ");

  private final UserDao userDao;
  private final SweepstakeIdDao sweepstakeIdDao;
  private final UserMessageDispatcher userMessageDispatcher;

  public UserServiceImpl(
      final UserDao userDao,
      final SweepstakeIdDao sweepstakeIdDao,
      UserMessageDispatcher userMessageDispatcher) {
    this.userDao = userDao;
    this.sweepstakeIdDao = sweepstakeIdDao;
    this.userMessageDispatcher = userMessageDispatcher;
  }

  @Override
  public void checkSignUpRequestIsValid(AuthenticationServiceOuterClass.SignUpRequest request) throws SignUpException {
    /* Firstly, check if the confirmPassword string and password string are the same, or they are not already signed up */
    if (!request.getPassword().equals(request.getConfirmPassword())) {
      throw new SignUpException("Password and Confirm Password do not match up!");
    } else if (userDao.existsByEmail(request.getEmail())) {
      throw new SignUpException("Another account is using this email address. Please log into your account.");
    }
  }

  @Override
  public void addOwnerToSweepstake(SweepstakeCommon sweepstake) {
    try {
      User addingParticipant = userDao.findUserById(sweepstake.getOwnerId());

      if (addingParticipant != null) {
        sweepstakeIdDao.save(new SweepstakeIds(sweepstake.getOwnerId(), sweepstake.getId()));

        /* Inform the sweepstake engine that the process has completed */
        SweepstakeEvent processCompletedEvent =
            new SweepstakeEvent(sweepstake, EventType.PROCESS_ENDED);
        userMessageDispatcher.publishEvent(processCompletedEvent, "api-sweepstake-events-topic");
      } else {
        /* Dispatch a sweepstake relation deleted event */
        SweepstakeEvent relationDeleted =
            new SweepstakeEvent(sweepstake, EventType.RELATION_DELETED);

        /* The sweepstake engine will consume this broadcast and delete it's relation with this
        sweepstake, then it will remove the sweepstake with the message string given by the event
        above */
        userMessageDispatcher.publishEvent(relationDeleted, "api-sweepstake-events-topic");

        /* Log the event */
        log.info(
            "Sweepstake relation {} has been purged! {}",
            relationDeleted.getSweepstake().getId(),
            dateFormat.format(new Date()));
      }
    } catch (Exception e) {
      /* Get the error message and ping it back to the client */
    }


  }
}
