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

import com.footyandsweep.apicommonlibrary.exceptions.ParticipantAlreadyJoinedException;
import com.footyandsweep.apicommonlibrary.model.sweepstake.SweepstakeCommon;
import com.footyandsweep.apicommonlibrary.model.sweepstake.SweepstakeTypeCommon;
import com.footyandsweep.apisweepstakeengine.dao.ParticipantIdDao;
import com.footyandsweep.apisweepstakeengine.dao.SweepstakeDao;
import com.footyandsweep.apisweepstakeengine.engine.SweepstakeEngineImpl;
import com.footyandsweep.apisweepstakeengine.engine.saga.createSweepstake.CreateSweepstakeSagaData;
import com.footyandsweep.apisweepstakeengine.model.Sweepstake;
import com.footyandsweep.apisweepstakeengine.relation.ParticipantIds;
import org.junit.Rule;
import org.junit.jupiter.api.Assertions;
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
public class SweepstakeEngineTest {

    @Mock
    private SweepstakeDao sweepstakeDao;

    @Mock
    private ParticipantIdDao participantIdDao;

    @InjectMocks
    private SweepstakeEngineImpl sweepstakeEngine;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void shouldSaveSweepstake() {
        Sweepstake sweepstake = new Sweepstake();
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

        CreateSweepstakeSagaData data = new CreateSweepstakeSagaData();
        data.setSweepstake(sweepstake);

        Mockito.when(sweepstakeDao.save(Mockito.any(Sweepstake.class))).thenAnswer(i -> i.getArguments()[0]);
        Mockito.when(sweepstakeDao.findSweepstakeById(ArgumentMatchers.anyString())).thenReturn(sweepstake);

        sweepstakeEngine.saveSweepstake(data);

        /* Actually verify that it's been saved */
        Mockito.verify(sweepstakeDao).save(Mockito.any(Sweepstake.class));

        /* Check that it's in the  */
        Assertions.assertNotNull(sweepstakeDao.findSweepstakeById(data.getSweepstake().getId()));
    }

    @Test
    public void shouldNotCreateSweepstakeParticipantRelation() {
        String participantId = UUID.randomUUID().toString();

        Sweepstake sweepstake = new Sweepstake();
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

        Mockito.when(sweepstakeDao.save(Mockito.any(Sweepstake.class))).thenAnswer(i -> i.getArguments()[0]);

        sweepstake = sweepstakeDao.save(sweepstake);

        Mockito.when(participantIdDao.save(Mockito.any(ParticipantIds.class))).thenAnswer(i -> i.getArguments()[0]);

        ParticipantIds owner = new ParticipantIds();
        owner.setSweepstakeId(sweepstake.getId());
        owner.setParticipantId(participantId);

        owner = participantIdDao.save(owner);

        List<ParticipantIds> ids = new ArrayList<>();
        ids.add(owner);

        Mockito.when(sweepstakeDao.findSweepstakeById(ArgumentMatchers.anyString())).thenReturn(sweepstake);
        Mockito.when(sweepstakeDao.findSweepstakeByJoinCode(ArgumentMatchers.anyString())).thenReturn(sweepstake);
        Mockito.when(participantIdDao.findAllParticipantIdsBySweepstakeId(ArgumentMatchers.anyString())).thenReturn(ids);

        Sweepstake finalSweepstake = sweepstake;
        org.assertj.core.api.Assertions.assertThatThrownBy(() -> sweepstakeEngine.createSweepstakeParticipantRelation(finalSweepstake.getJoinCode(), participantId))
                .isInstanceOf(ParticipantAlreadyJoinedException.class)
                .hasMessage("You are already part of this sweepstake!");
    }
}
