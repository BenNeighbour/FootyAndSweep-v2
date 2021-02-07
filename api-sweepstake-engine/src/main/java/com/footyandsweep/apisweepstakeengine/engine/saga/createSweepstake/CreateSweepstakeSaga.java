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

package com.footyandsweep.apisweepstakeengine.engine.saga.createSweepstake;

import com.footyandsweep.apicommonlibrary.cqrs.user.LinkParticipantToSweepstakeFailure;
import com.footyandsweep.apicommonlibrary.cqrs.user.ParticipantNotFound;
import com.footyandsweep.apisweepstakeengine.dao.ParticipantIdDao;
import com.footyandsweep.apisweepstakeengine.engine.SweepstakeEngine;
import com.footyandsweep.apisweepstakeengine.relation.ParticipantIds;
import io.eventuate.tram.sagas.orchestration.SagaDefinition;
import io.eventuate.tram.sagas.simpledsl.SimpleSaga;
import org.springframework.stereotype.Component;

@Component
public class CreateSweepstakeSaga implements SimpleSaga<CreateSweepstakeSagaData> {

  private final SweepstakeEngine sweepstakeEngine;
  private final ParticipantIdDao participantIdDao;

  public CreateSweepstakeSaga(
      SweepstakeEngine sweepstakeEngine, ParticipantIdDao participantIdDao) {
    this.sweepstakeEngine = sweepstakeEngine;
    this.participantIdDao = participantIdDao;
  }

  @Override
  public SagaDefinition<CreateSweepstakeSagaData> getSagaDefinition() {

    return

            step()
        .invokeLocal(sweepstakeEngine::saveSweepstake)
        .withCompensation(
            sagaData -> sweepstakeEngine.deleteSweepstakeById(sagaData.getSweepstake().getId()))


        .step()
        .invokeLocal(
            sagaData -> {
              ParticipantIds participantIds =
                  sweepstakeEngine.createSweepstakeParticipantRelation(
                      sagaData.getSweepstake().getJoinCode(),
                      sagaData.getSweepstake().getOwnerId());
              sagaData.setOwnerIdObject(participantIds);
            })
        .withCompensation(
            sagaData ->
                sweepstakeEngine.deleteSweepstakeRelationById(sagaData.getOwnerIdObject().getId()))


        .step()
        .invokeParticipant(
            sagaData ->
                sweepstakeEngine.linkParticipantToSweepstake(
                    sagaData.getSweepstake().getId(),
                    sagaData.getOwnerIdObject().getParticipantId()))
        .onReply(
            ParticipantNotFound.class, (sagaData, participantNotFound) -> sagaData.getSweepstake())
        .onReply(
            LinkParticipantToSweepstakeFailure.class,
            (sagaData, linkFailure) -> sagaData.getSweepstake())


        .build();
  }

    @Override
    public void onSagaCompletedSuccessfully(String sagaId, CreateSweepstakeSagaData sagaData) {
        System.out.println("Create Sweepstake Saga: " + sagaId + " has been completed successfully");
    }
}
