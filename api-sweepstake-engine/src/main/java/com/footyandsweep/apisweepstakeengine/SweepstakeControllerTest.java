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

import com.footyandsweep.apisweepstakeengine.engine.saga.createSweepstake.CreateSweepstakeSaga;
import com.footyandsweep.apisweepstakeengine.engine.saga.createSweepstake.CreateSweepstakeSagaData;
import com.footyandsweep.apisweepstakeengine.engine.saga.deleteSweepstake.DeleteSweepstakeSaga;
import com.footyandsweep.apisweepstakeengine.engine.saga.deleteSweepstake.DeleteSweepstakeSagaData;
import com.footyandsweep.apisweepstakeengine.model.Sweepstake;
import io.eventuate.tram.sagas.orchestration.SagaInstanceFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;

@RestController
@RequestMapping("/sweepstake/test")
public class SweepstakeControllerTest {

  private final CreateSweepstakeSaga createSweepstakeSaga;
  private final DeleteSweepstakeSaga deleteSweepstakeSaga;
  private final SagaInstanceFactory sagaInstanceFactory;

  public SweepstakeControllerTest(CreateSweepstakeSaga createSweepstakeSaga, DeleteSweepstakeSaga deleteSweepstakeSaga, SagaInstanceFactory sagaInstanceFactory) {
    this.createSweepstakeSaga = createSweepstakeSaga;
    this.deleteSweepstakeSaga = deleteSweepstakeSaga;
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
}
