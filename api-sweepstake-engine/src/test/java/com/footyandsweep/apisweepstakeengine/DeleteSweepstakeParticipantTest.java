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

package com.footyandsweep.apisweepstakeengine;

import com.footyandsweep.apicommonlibrary.model.sweepstake.SweepstakeCommon;
import com.footyandsweep.apicommonlibrary.model.sweepstake.SweepstakeTypeCommon;
import com.footyandsweep.apisweepstakeengine.dao.ParticipantIdDao;
import com.footyandsweep.apisweepstakeengine.engine.SweepstakeEngineImpl;
import com.footyandsweep.apisweepstakeengine.model.Sweepstake;
import com.footyandsweep.apisweepstakeengine.relation.ParticipantIds;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RunWith(MockitoJUnitRunner.class)
public class DeleteSweepstakeParticipantTest {

  private static Sweepstake sweepstake = new Sweepstake();

  @Mock private ParticipantIdDao participantIdDao;

  @InjectMocks private SweepstakeEngineImpl sweepstakeEngine;

  @BeforeEach
  public void setUp() {
    sweepstake.setId(UUID.randomUUID().toString());
    sweepstake.setName("Test");
    sweepstake.setOwnerId(UUID.randomUUID().toString());
    sweepstake.setSweepstakeEventId(UUID.randomUUID().toString());
    sweepstake.setIsPrivate(false);
    sweepstake.setStake(new BigDecimal("3.00"));
    sweepstake.setSweepstakeType(SweepstakeTypeCommon.Correct_Score_FT);
    sweepstake.setStatus(SweepstakeCommon.SweepstakeStatus.OPEN);
    sweepstake.setMinimumPlayers(2);
    sweepstake.setMaximumPlayerTickets(4);
    sweepstake.setTotalNumberOfTickets(4);

    MockitoAnnotations.initMocks(this);
  }

  @Test
  public void deleteAllSweepstakeRelationsBySweepstakeId() {
    /* Save the participantId */
    Mockito.when(participantIdDao.save(Mockito.any(ParticipantIds.class)))
        .thenAnswer(i -> i.getArguments()[0]);
    ParticipantIds owner = new ParticipantIds();
    owner.setSweepstakeId(sweepstake.getId());
    owner.setParticipantId(UUID.randomUUID().toString());

    owner = participantIdDao.save(owner);

    List<ParticipantIds> ids = new ArrayList<>();
    ids.add(owner);

    Mockito.when(participantIdDao.findAllParticipantIdsBySweepstakeId(ArgumentMatchers.anyString()))
        .thenReturn(ids);

    sweepstakeEngine.deleteAllSweepstakeRelationsBySweepstakeId(sweepstake.getId());

    Mockito.verify(participantIdDao).delete(Mockito.any(ParticipantIds.class));
  }
}
