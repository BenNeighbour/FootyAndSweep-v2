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

import com.footyandsweep.apicommonlibrary.cqrs.SagaResponse;
import com.footyandsweep.apicommonlibrary.cqrs.user.LinkParticipantToSweepstakeFailure;
import com.footyandsweep.apicommonlibrary.cqrs.user.ParticipantNotFound;
import com.footyandsweep.apicommonlibrary.model.sweepstake.SweepstakeCommon;
import com.footyandsweep.apisweepstakeengine.engine.SweepstakeEngine;
import io.eventuate.tram.sagas.orchestration.SagaDefinition;
import io.eventuate.tram.sagas.simpledsl.SimpleSaga;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

@Component
public class CreateSweepstakeSaga implements SimpleSaga<CreateSweepstakeSagaData> {

  private final SweepstakeEngine sweepstakeEngine;
  private final SimpMessagingTemplate messagingTemplate;

  public CreateSweepstakeSaga(
      SweepstakeEngine sweepstakeEngine, SimpMessagingTemplate messagingTemplate) {
    this.sweepstakeEngine = sweepstakeEngine;
    this.messagingTemplate = messagingTemplate;
  }

  @Override
  public SagaDefinition<CreateSweepstakeSagaData> getSagaDefinition() {
    // @formatter:off
    return step()
        .invokeLocal(sweepstakeEngine::saveSweepstake)
        .withCompensation(
            sagaData -> sweepstakeEngine.deleteSweepstakeById(sagaData.getSweepstake().getId()))
        .step()
        .invokeLocal(
            sagaData ->
                sagaData.setOwner(
                    sweepstakeEngine.createSweepstakeParticipantRelation(
                        sagaData.getSweepstake().getJoinCode(),
                        sagaData.getSweepstake().getOwnerId())))
        .withCompensation(
            sagaData -> sweepstakeEngine.deleteSweepstakeRelationById(sagaData.getOwner().getId()))
        .step()
        .invokeParticipant(
            sagaData ->
                sweepstakeEngine.linkParticipantToSweepstake(
                    sagaData.getSweepstake().getId(), sagaData.getOwner().getParticipantId()))
        .onReply(ParticipantNotFound.class, (sagaData, participantNotFound) -> {})
        .onReply(LinkParticipantToSweepstakeFailure.class, (sagaData, linkFailure) -> {})
        .build();
    // @formatter:on
  }

  @Override
  public void onSagaCompletedSuccessfully(String sagaId, CreateSweepstakeSagaData sagaData) {
    SagaResponse<SweepstakeCommon> sweepstakeSagaComplete =
        new SagaResponse<>(
            sagaId, SagaResponse.Status.COMPLETED, "Sweepstake created!", sagaData.getSweepstake());

    messagingTemplate.convertAndSend("/sweepstake-topic/save", sweepstakeSagaComplete);
  }

  @Override
  public void onStarting(String sagaId, CreateSweepstakeSagaData sagaData) {
    SagaResponse<SweepstakeCommon> sweepstakeSagaPending =
        new SagaResponse<>(
            sagaId,
            SagaResponse.Status.PENDING,
            "Creating Sweepstake...",
            sagaData.getSweepstake());

    messagingTemplate.convertAndSend("/sweepstake-topic/save", sweepstakeSagaPending);
  }

  @Override
  public void onSagaRolledBack(String sagaId, CreateSweepstakeSagaData sagaData) {
    SagaResponse<SweepstakeCommon> sweepstakeSagaError =
        new SagaResponse<>(
            sagaId,
            SagaResponse.Status.FAILED,
            "Create Sweepstake Failed!",
            sagaData.getSweepstake());

    messagingTemplate.convertAndSend("/sweepstake-topic/save", sweepstakeSagaError);
  }
}
