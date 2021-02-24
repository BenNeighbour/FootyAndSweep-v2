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

import com.footyandsweep.apisweepstakeengine.dao.ParticipantIdDao;
import com.footyandsweep.apisweepstakeengine.dao.SweepstakeDao;
import com.footyandsweep.apisweepstakeengine.engine.saga.createSweepstake.CreateSweepstakeSaga;
import com.footyandsweep.apisweepstakeengine.engine.saga.createSweepstake.CreateSweepstakeSagaData;
import com.footyandsweep.apisweepstakeengine.engine.saga.deleteSweepstake.DeleteSweepstakeSaga;
import com.footyandsweep.apisweepstakeengine.engine.saga.deleteSweepstake.DeleteSweepstakeSagaData;
import com.footyandsweep.apisweepstakeengine.engine.saga.joinSweepstake.JoinSweepstakeSaga;
import com.footyandsweep.apisweepstakeengine.engine.saga.joinSweepstake.JoinSweepstakeSagaData;
import com.footyandsweep.apisweepstakeengine.helper.ResultHelper;
import com.footyandsweep.apisweepstakeengine.model.Sweepstake;
import io.eventuate.tram.sagas.orchestration.SagaInstanceFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@Controller
public class SweepstakeController {

  private final ResultHelper resultHelper;
  private final SweepstakeDao sweepstakeDao;
  private final ParticipantIdDao participantIdDao;

  private final CreateSweepstakeSaga createSweepstakeSaga;
  private final DeleteSweepstakeSaga deleteSweepstakeSaga;
  private final JoinSweepstakeSaga joinSweepstakeSaga;

  private final SagaInstanceFactory sagaInstanceFactory;

  public SweepstakeController(ResultHelper resultHelper, SweepstakeDao sweepstakeDao, ParticipantIdDao participantIdDao, CreateSweepstakeSaga createSweepstakeSaga, DeleteSweepstakeSaga deleteSweepstakeSaga, JoinSweepstakeSaga joinSweepstakeSaga, SagaInstanceFactory sagaInstanceFactory) {
    this.resultHelper = resultHelper;
    this.sweepstakeDao = sweepstakeDao;
    this.participantIdDao = participantIdDao;
    this.createSweepstakeSaga = createSweepstakeSaga;
    this.deleteSweepstakeSaga = deleteSweepstakeSaga;
    this.joinSweepstakeSaga = joinSweepstakeSaga;
    this.sagaInstanceFactory = sagaInstanceFactory;
  }

  @Transactional
  @MessageMapping("/save")
  public Sweepstake createSweepstake(Sweepstake sweepstake) {
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
  public ResponseEntity<String> join(
          @RequestParam("participantId") String participantId,
          @RequestParam("joinCode") String joinCode) {
    JoinSweepstakeSagaData data = new JoinSweepstakeSagaData();
    data.setSweepstakeJoinCode(joinCode);
    data.setParticipantId(participantId);

    sagaInstanceFactory.create(joinSweepstakeSaga, data);

    return ResponseEntity.ok("Joined Successfully");
  }
}
