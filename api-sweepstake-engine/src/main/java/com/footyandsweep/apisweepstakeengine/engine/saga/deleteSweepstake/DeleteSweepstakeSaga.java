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

package com.footyandsweep.apisweepstakeengine.engine.saga.deleteSweepstake;

import com.footyandsweep.apisweepstakeengine.engine.SweepstakeEngine;
import io.eventuate.tram.sagas.orchestration.SagaDefinition;
import io.eventuate.tram.sagas.simpledsl.SimpleSaga;
import org.springframework.stereotype.Component;

@Component
public class DeleteSweepstakeSaga implements SimpleSaga<DeleteSweepstakeSagaData> {

  private final SweepstakeEngine sweepstakeEngine;

  public DeleteSweepstakeSaga(SweepstakeEngine sweepstakeEngine) {
    this.sweepstakeEngine = sweepstakeEngine;
  }

  @Override
  public SagaDefinition<DeleteSweepstakeSagaData> getSagaDefinition() {
    // @formatter:off
    return
            step()
            .invokeLocal(sagaData -> sweepstakeEngine.deleteSweepstakeById(sagaData.getSweepstake().getId()))
            .withCompensation(sagaData -> {})
            .step()
            .invokeParticipant(sweepstakeEngine::deleteRemoteSweepstakeRelation)
            .step()
            .invokeLocal(sagaData -> sweepstakeEngine.deleteAllSweepstakeRelationsBySweepstakeId(sagaData.getSweepstake().getId()))
            .withCompensation(sagaData -> {})
            .build();
    // @formatter:on
  }
}
