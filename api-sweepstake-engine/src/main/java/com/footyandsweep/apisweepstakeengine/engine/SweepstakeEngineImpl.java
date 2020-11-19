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

package com.footyandsweep.apisweepstakeengine.engine;

import com.footyandsweep.apicommonlibrary.events.EventType;
import com.footyandsweep.apicommonlibrary.events.SweepstakeEvent;
import com.footyandsweep.apisweepstakeengine.dao.ParticipantIdDao;
import com.footyandsweep.apisweepstakeengine.dao.SweepstakeDao;
import com.footyandsweep.apisweepstakeengine.event.SweepstakeMessageDispatcher;
import com.footyandsweep.apisweepstakeengine.model.Sweepstake;
import com.footyandsweep.apisweepstakeengine.relation.ParticipantIds;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.UUID;

@Service
@Transactional
public class SweepstakeEngineImpl implements SweepstakeEngine {

  private final SweepstakeDao sweepstakeDao;
  private final ParticipantIdDao participantIdDao;
  private final SweepstakeMessageDispatcher sweepstakeMessageDispatcher;

  public SweepstakeEngineImpl(final SweepstakeDao sweepstakeDao, final ParticipantIdDao participantIdDao, final SweepstakeMessageDispatcher sweepstakeMessageDispatcher) {
    this.sweepstakeDao = sweepstakeDao;
    this.participantIdDao = participantIdDao;
    this.sweepstakeMessageDispatcher = sweepstakeMessageDispatcher;
  }

  @Override
  public Sweepstake saveSweepstake(UUID ownerId, Sweepstake sweepstake) {
    try {
      sweepstake.setOwnerId(ownerId);

      /* Persist the sweepstake and it's participant junction table */
      sweepstake = sweepstakeDao.save(sweepstake);
      participantIdDao.save(new ParticipantIds(sweepstake.getId(), ownerId));

      /* Creating the sweepstake created object for other services to react to */
      SweepstakeEvent sweepstakeCreated = new SweepstakeEvent(sweepstake, EventType.CREATED);

      /* This gets received by the gateway service, then that service adds the sweepstake and
      user id into it's SweepstakeIds Junction Table */
      sweepstakeMessageDispatcher.publishEvent(sweepstakeCreated, "api-sweepstake-event-topic");

      return sweepstake;
    } catch (Exception e) {
      // TODO: FIX THIS!
      return null;
    }
  }

  @Override
  public void deleteParticipantRelation(UUID sweepstakeId) {
    participantIdDao.delete(
        participantIdDao.findAllParticipantIdsBySweepstakeId(sweepstakeId).get().stream()
            .filter(participantIds -> participantIds.getSweepstakeId().equals(sweepstakeId))
            .findFirst()
            .get());
  }

  @Override
  public Sweepstake deleteSweepstake(UUID sweepstakeId) {
    Sweepstake sweepstake = sweepstakeDao.findSweepstakeById(sweepstakeId);
    this.deleteParticipantRelation(sweepstakeId);
    sweepstakeDao.delete(sweepstake);

    // TODO: Broadcast websockets error message with the reason in it

    return sweepstake;
  }
}
