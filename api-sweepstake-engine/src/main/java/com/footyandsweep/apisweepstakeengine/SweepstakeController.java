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

import com.footyandsweep.apicommonlibrary.cqrs.SagaResponse;
import com.footyandsweep.apisweepstakeengine.dao.ParticipantIdDao;
import com.footyandsweep.apisweepstakeengine.dao.SweepstakeDao;
import com.footyandsweep.apisweepstakeengine.engine.SweepstakeEngine;
import com.footyandsweep.apisweepstakeengine.engine.saga.createSweepstake.CreateSweepstakeSaga;
import com.footyandsweep.apisweepstakeengine.engine.saga.createSweepstake.CreateSweepstakeSagaData;
import com.footyandsweep.apisweepstakeengine.engine.saga.deleteSweepstake.DeleteSweepstakeSaga;
import com.footyandsweep.apisweepstakeengine.engine.saga.deleteSweepstake.DeleteSweepstakeSagaData;
import com.footyandsweep.apisweepstakeengine.engine.saga.joinSweepstake.JoinSweepstakeSaga;
import com.footyandsweep.apisweepstakeengine.engine.saga.joinSweepstake.JoinSweepstakeSagaData;
import com.footyandsweep.apisweepstakeengine.helper.ResultHelper;
import com.footyandsweep.apisweepstakeengine.model.Sweepstake;
import io.eventuate.tram.sagas.orchestration.SagaInstanceFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class SweepstakeController {

  private final SweepstakeDao sweepstakeDao;
  private final SweepstakeEngine sweepstakeEngine;

  private final CreateSweepstakeSaga createSweepstakeSaga;
  private final DeleteSweepstakeSaga deleteSweepstakeSaga;
  private final JoinSweepstakeSaga joinSweepstakeSaga;

  private final SagaInstanceFactory sagaInstanceFactory;
  private final SimpMessagingTemplate messagingTemplate;

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

  @Transactional
  @MessageMapping("/join")
  public void join(JoinSweepstakeSagaData request) {
    if (sweepstakeDao.findSweepstakeByJoinCode(request.getSweepstakeJoinCode()) == null) {
      SagaResponse<String> sweepstakeSagaError =
          new SagaResponse<>(SagaResponse.Status.FAILED, "Invalid Join Code!", "Invalid Join Code!");

      messagingTemplate.convertAndSend("/sweepstake-topic/join", sweepstakeSagaError);
    } else {
      sagaInstanceFactory.create(joinSweepstakeSaga, request);
    }
  }

  @GetMapping("/sweepstakes/{userId}")
  public ResponseEntity<List<Sweepstake>> getMySweepstakes(@PathVariable("userId") String userId) {
    return ResponseEntity.ok(sweepstakeEngine.getAllSweepstakesByUser(userId));
  }
}
