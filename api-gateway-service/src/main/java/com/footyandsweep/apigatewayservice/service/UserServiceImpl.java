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

import com.footyandsweep.apicommonlibrary.events.SweepstakeRelationDeleted;
import com.footyandsweep.apicommonlibrary.model.sweepstake.SweepstakeCommon;
import com.footyandsweep.apigatewayservice.dao.SweepstakeIdDao;
import com.footyandsweep.apigatewayservice.dao.UserDao;
import com.footyandsweep.apigatewayservice.model.User;
import com.footyandsweep.apigatewayservice.relation.SweepstakeIds;
import io.eventuate.tram.events.publisher.DomainEventPublisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

import static java.util.Collections.singletonList;

@Service
@Transactional
public class UserServiceImpl implements UserService {

  private final UserDao userDao;

  private final DomainEventPublisher domainEventPublisher;

  @Autowired private SweepstakeIdDao sweepstakeIdDao;

  public UserServiceImpl(final UserDao userDao, final DomainEventPublisher domainEventPublisher) {
    this.userDao = userDao;
    this.domainEventPublisher = domainEventPublisher;
  }

  @Override
  @Transactional
  public void addUserToSweepstake(SweepstakeCommon sweepstake) {
    User addingParticipant = userDao.findUserByUserId(sweepstake.getOwnerId());

    if (addingParticipant != null) {
      sweepstakeIdDao.save(new SweepstakeIds(sweepstake.getOwnerId(), sweepstake.getId()));
    } else {
      // Dispatch a sweepstake relation deleted event
      SweepstakeRelationDeleted relationDeleted =
          new SweepstakeRelationDeleted(sweepstake.getId(), "Owner is invalid!");

      /* The sweepstake engine will consume this broadcast and delete it's relation with this
      sweepstake, then it will remove the sweepstake with the message string given by the event
      above */
      domainEventPublisher.publish(
          SweepstakeCommon.class, sweepstake.getId(), singletonList(relationDeleted));
    }
  }
}
