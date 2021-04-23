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

package com.footyandsweep.apisweepstakeengine.engine.saga.joinSweepstake;

import com.footyandsweep.apicommonlibrary.cqrs.SagaResponse;
import com.footyandsweep.apicommonlibrary.cqrs.user.LinkParticipantToSweepstakeFailure;
import com.footyandsweep.apicommonlibrary.cqrs.user.ParticipantNotFound;
import com.footyandsweep.apicommonlibrary.model.sweepstake.SweepstakeCommon;
import com.footyandsweep.apisweepstakeengine.engine.SweepstakeEngine;
import com.footyandsweep.apisweepstakeengine.engine.saga.createSweepstake.CreateSweepstakeSagaData;
import com.footyandsweep.apisweepstakeengine.relation.ParticipantIds;
import io.eventuate.tram.sagas.orchestration.SagaDefinition;
import io.eventuate.tram.sagas.simpledsl.SimpleSaga;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

@Component
public class JoinSweepstakeSaga implements SimpleSaga<JoinSweepstakeSagaData> {

  private final SweepstakeEngine sweepstakeEngine;
  private final SimpMessagingTemplate messagingTemplate;

  public JoinSweepstakeSaga(
      SweepstakeEngine sweepstakeEngine, SimpMessagingTemplate messagingTemplate) {
    this.sweepstakeEngine = sweepstakeEngine;
    this.messagingTemplate = messagingTemplate;
  }

  @Override
  public SagaDefinition<JoinSweepstakeSagaData> getSagaDefinition() {
    // @formatter:off
    return step()
        .invokeLocal(
            sagaData -> {
              ParticipantIds participantId =
                  sweepstakeEngine.createSweepstakeParticipantRelation(
                      sagaData.getSweepstakeJoinCode(), sagaData.getParticipantId());

              sagaData.setSweepstakeParticipantId(participantId.getId());
              sagaData.setSweepstakeId(participantId.getSweepstakeId());
            })
        /* Delete the sweepstake and the relation */
        .withCompensation(
            sagaData ->
                sweepstakeEngine.deleteSweepstakeRelationById(
                    sagaData.getSweepstakeParticipantId()))
        .step()
        .invokeParticipant(
            sagaData ->
                sweepstakeEngine.linkParticipantToSweepstake(
                    sagaData.getSweepstakeId(), sagaData.getParticipantId()))
        .onReply(ParticipantNotFound.class, (sagaData, e) -> {})
        .onReply(LinkParticipantToSweepstakeFailure.class, (sagaData, e) -> {})
        .build();
    // @formatter:on
  }

  @Override
  public void onSagaCompletedSuccessfully(String sagaId, JoinSweepstakeSagaData sagaData) {
    SagaResponse<String> sweepstakeSagaComplete =
        new SagaResponse<>(
            sagaId, SagaResponse.Status.COMPLETED, "Sweepstake joined!", sagaData.getSweepstakeId());

    messagingTemplate.convertAndSend("/sweepstake-topic/join", sweepstakeSagaComplete);
  }

  @Override
  public void onStarting(String sagaId, JoinSweepstakeSagaData sagaData) {
    SagaResponse<String> sweepstakeSagaPending =
        new SagaResponse<>(
            sagaId,
            SagaResponse.Status.PENDING,
            "Joining Sweepstake...", "Joining Sweepstake");

    messagingTemplate.convertAndSend("/sweepstake-topic/join", sweepstakeSagaPending);
  }

  @Override
  public void onSagaRolledBack(String sagaId, JoinSweepstakeSagaData sagaData) {
    SagaResponse<String> sweepstakeSagaError =
        new SagaResponse<>(
            sagaId,
            SagaResponse.Status.FAILED,
            "Join Sweepstake Failed!",
            "");

    messagingTemplate.convertAndSend("/sweepstake-topic/join", sweepstakeSagaError);
  }
}
