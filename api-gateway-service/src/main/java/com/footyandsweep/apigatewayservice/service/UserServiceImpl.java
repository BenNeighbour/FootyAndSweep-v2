/*
 * Copyright 2020 FootyAndSweep
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.footyandsweep.apigatewayservice.service;

import com.footyandsweep.apicommonlibrary.model.SweepstakeCommon;
import com.footyandsweep.apigatewayservice.dao.SweepstakeIdDao;
import com.footyandsweep.apigatewayservice.dao.UserDao;
import com.footyandsweep.apigatewayservice.model.User;
import com.footyandsweep.apigatewayservice.relation.SweepstakeIds;
import io.eventuate.tram.events.publisher.DomainEventPublisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

  private final UserDao userDao;

  private final DomainEventPublisher domainEventPublisher;

  @Autowired private SweepstakeIdDao sweepstakeIdDao;

  public UserServiceImpl(final UserDao userDao, final DomainEventPublisher domainEventPublisher) {
    this.userDao = userDao;
    this.domainEventPublisher = domainEventPublisher;
  }

  @Override
  public User addUserToSweepstake(UUID userId, SweepstakeCommon sweepstake) {
    try {
      User addingParticipant = userDao.findUserByUserId(userId);

      if (addingParticipant != null) {
        if (sweepstakeIdDao.findSweepstakeIdsByParticipantId(userId) == null) {
          sweepstakeIdDao.save(new SweepstakeIds(userId, sweepstake.getSweepstakeId()));
          return addingParticipant;
        }
      }
    } catch (Exception e) {
      // TODO: THROW BACK ERROR MESSGAE!!
      return null;
    }

    return null;
  }
}
