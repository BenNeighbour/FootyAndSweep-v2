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

package com.footyandsweep.apigatewayservice.service;

import com.footyandsweep.apicommonlibrary.events.EventType;
import com.footyandsweep.apicommonlibrary.events.SweepstakeEvent;
import com.footyandsweep.apicommonlibrary.model.sweepstake.SweepstakeCommon;
import com.footyandsweep.apigatewayservice.dao.SweepstakeIdDao;
import com.footyandsweep.apigatewayservice.dao.UserDao;
import com.footyandsweep.apigatewayservice.event.UserMessageDispatcher;
import com.footyandsweep.apigatewayservice.model.User;
import com.footyandsweep.apigatewayservice.relation.SweepstakeIds;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class UserServiceImpl implements UserService {

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
  public void addOwnerToSweepstake(SweepstakeCommon sweepstake) {
    try {
      User addingParticipant = userDao.findUserById(sweepstake.getOwnerId());

      if (addingParticipant != null) {
        sweepstakeIdDao.save(new SweepstakeIds(sweepstake.getOwnerId(), sweepstake.getId()));
      } else {
        /* Dispatch a sweepstake relation deleted event */
        SweepstakeEvent relationDeleted =
            new SweepstakeEvent(sweepstake, EventType.RELATION_DELETED);

        /* The sweepstake engine will consume this broadcast and delete it's relation with this
        sweepstake, then it will remove the sweepstake with the message string given by the event
        above */
        userMessageDispatcher.publishEvent(relationDeleted, "api-sweepstake-events-topic");
      }
    } catch (Exception e) {
      /* Get the error message and ping it back to the client */
    }
  }
}
