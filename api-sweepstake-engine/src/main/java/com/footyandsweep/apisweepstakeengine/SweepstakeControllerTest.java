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
import com.footyandsweep.apicommonlibrary.other.CustomMap;
import com.footyandsweep.apisweepstakeengine.dao.ParticipantIdDao;
import com.footyandsweep.apisweepstakeengine.dao.SweepstakeDao;
import com.footyandsweep.apisweepstakeengine.engine.saga.createSweepstake.CreateSweepstakeSaga;
import com.footyandsweep.apisweepstakeengine.engine.saga.createSweepstake.CreateSweepstakeSagaData;
import com.footyandsweep.apisweepstakeengine.engine.saga.deleteSweepstake.DeleteSweepstakeSaga;
import com.footyandsweep.apisweepstakeengine.engine.saga.deleteSweepstake.DeleteSweepstakeSagaData;
import com.footyandsweep.apisweepstakeengine.engine.saga.joinSweepstake.JoinSweepstakeSaga;
import com.footyandsweep.apisweepstakeengine.engine.saga.joinSweepstake.JoinSweepstakeSagaData;
import com.footyandsweep.apisweepstakeengine.helper.ResultHelper;
import com.footyandsweep.apisweepstakeengine.model.FootballMatchSweepstake;
import com.footyandsweep.apisweepstakeengine.model.Sweepstake;
import com.footyandsweep.apisweepstakeengine.relation.ParticipantIds;
import io.eventuate.tram.sagas.orchestration.SagaInstanceFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/sweepstake/test")
public class SweepstakeControllerTest {

  private final ResultHelper resultHelper;
  private final SweepstakeDao sweepstakeDao;
  private final ParticipantIdDao participantIdDao;

  private final CreateSweepstakeSaga createSweepstakeSaga;
  private final DeleteSweepstakeSaga deleteSweepstakeSaga;
  private final JoinSweepstakeSaga joinSweepstakeSaga;

  private final SagaInstanceFactory sagaInstanceFactory;

  public SweepstakeControllerTest(ResultHelper resultHelper, SweepstakeDao sweepstakeDao, ParticipantIdDao participantIdDao, CreateSweepstakeSaga createSweepstakeSaga, DeleteSweepstakeSaga deleteSweepstakeSaga, JoinSweepstakeSaga joinSweepstakeSaga, SagaInstanceFactory sagaInstanceFactory) {
    this.resultHelper = resultHelper;
    this.sweepstakeDao = sweepstakeDao;
    this.participantIdDao = participantIdDao;
    this.createSweepstakeSaga = createSweepstakeSaga;
    this.deleteSweepstakeSaga = deleteSweepstakeSaga;
    this.joinSweepstakeSaga = joinSweepstakeSaga;
    this.sagaInstanceFactory = sagaInstanceFactory;
  }

  @PostMapping("/save")
  @Transactional
  public Sweepstake save(@RequestBody Sweepstake sweepstake) {
    CreateSweepstakeSagaData data = new CreateSweepstakeSagaData(sweepstake);
    sagaInstanceFactory.create(createSweepstakeSaga, data);

    return sweepstake;
  }

  @PostMapping("/delete")
  @Transactional
  public Sweepstake delete(@RequestBody Sweepstake sweepstake) {
    DeleteSweepstakeSagaData data = new DeleteSweepstakeSagaData(sweepstake);
    sagaInstanceFactory.create(deleteSweepstakeSaga, data);

    return sweepstake;
  }

  @GetMapping("/by/joinCode/{joinCode}")
  public Sweepstake findSweepstakeByJoinCode(@PathVariable("joinCode") String joinCode) {
    return sweepstakeDao.findSweepstakeByJoinCode(joinCode);
  }

  @PostMapping("/join")
  @Transactional
  public ResponseEntity<String> join(@RequestParam("participantId") String participantId, @RequestParam("joinCode") String joinCode) {
    JoinSweepstakeSagaData data = new JoinSweepstakeSagaData();
    data.setSweepstakeJoinCode(joinCode);
    data.setParticipantId(participantId);

    sagaInstanceFactory.create(joinSweepstakeSaga, data);

    return ResponseEntity.ok("Joined Successfully");
  }

  @GetMapping("/by/{sweepstakeId}/participants")
  public List<String> findAllSweepstakeParticipantRelations(
          @PathVariable("sweepstakeId") String id) {

    List<ParticipantIds> participantsInSweepstake =
            participantIdDao.findAllParticipantIdsBySweepstakeId(id);

    List<String> participantIds = new ArrayList<>();
    participantsInSweepstake.forEach(curr -> participantIds.add(curr.getParticipantId()));

    return participantIds;
  }

  @PostMapping("/result")
  public List<CustomMap> resultHelper(@RequestBody SweepstakeCommon sweepstake) {
    List<CustomMap> customMap = new ArrayList<>();

    FootballMatchSweepstake footballMatchSweepstake = sweepstakeDao.findFootballMatchSweepstakeById(sweepstake.getId());

    resultHelper.buildResultsForSweepstakeType(footballMatchSweepstake.getSweepstakeType(), footballMatchSweepstake).forEach((integer, s) -> {
      customMap.add(new CustomMap(integer, s));
    });

    return customMap;
  }

  @GetMapping("/by/id/{sweepstakeId}")
  public SweepstakeCommon findSweepstakeById(@PathVariable("sweepstakeId") String sweepstakeId) {
    return sweepstakeDao.findSweepstakeById(sweepstakeId);
  }
}