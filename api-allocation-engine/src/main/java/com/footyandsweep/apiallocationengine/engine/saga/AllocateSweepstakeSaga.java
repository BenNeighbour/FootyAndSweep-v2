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

package com.footyandsweep.apiallocationengine.engine.saga;

import com.footyandsweep.apiallocationengine.engine.AllocationEngine;
import com.footyandsweep.apicommonlibrary.model.sweepstake.SweepstakeCommon;
import io.eventuate.tram.sagas.orchestration.SagaDefinition;
import io.eventuate.tram.sagas.simpledsl.SimpleSaga;
import org.springframework.stereotype.Component;

@Component
public class AllocateSweepstakeSaga implements SimpleSaga<AllocateSweepstakeSagaData> {

    private final AllocationEngine allocationEngine;

    public AllocateSweepstakeSaga(AllocationEngine allocationEngine) {
        this.allocationEngine = allocationEngine;
    }

    @Override
    public SagaDefinition<AllocateSweepstakeSagaData> getSagaDefinition() {
        return step()
                .invokeParticipant(sagaData -> allocationEngine.updateSweepstakeStatus(sagaData.getSweepstake().getId(), SweepstakeCommon.SweepstakeStatus.ALLOCATED))
                .withCompensation(sagaData -> allocationEngine.updateSweepstakeStatus(sagaData.getSweepstake().getId(), SweepstakeCommon.SweepstakeStatus.OPEN))
                .step()
                .invokeLocal(allocationEngine::allocateSweepstakeTickets)
                .withCompensation(sagaData -> {
                })
                .step()
                .invokeParticipant(sagaData -> allocationEngine.allocateTickets(sagaData.getTickets()))
                .build();
    }

    @Override
    public void onSagaCompletedSuccessfully(String sagaId, AllocateSweepstakeSagaData sagaData) {
        System.out.println("Allocate Sweepstake Saga: " + sagaId + " has been completed successfully");
    }
}
