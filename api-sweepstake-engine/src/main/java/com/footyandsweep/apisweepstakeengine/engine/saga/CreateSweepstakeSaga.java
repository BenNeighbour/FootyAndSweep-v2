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

package com.footyandsweep.apisweepstakeengine.engine.saga;

import com.footyandsweep.apicommonlibrary.cqrs.user.LinkParticipantToSweepstakeCommand;
import com.footyandsweep.apicommonlibrary.cqrs.user.LinkParticipantToSweepstakeFailure;
import com.footyandsweep.apicommonlibrary.cqrs.user.ParticipantNotFound;
import com.footyandsweep.apisweepstakeengine.dao.ParticipantIdDao;
import com.footyandsweep.apisweepstakeengine.dao.SweepstakeDao;
import com.footyandsweep.apisweepstakeengine.model.Sweepstake;
import com.footyandsweep.apisweepstakeengine.relation.ParticipantIds;
import io.eventuate.tram.commands.consumer.CommandWithDestination;
import io.eventuate.tram.sagas.orchestration.SagaDefinition;
import io.eventuate.tram.sagas.simpledsl.SimpleSaga;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.UUID;

import static io.eventuate.tram.commands.consumer.CommandWithDestinationBuilder.send;

public class CreateSweepstakeSaga implements SimpleSaga<CreateSweepstakeSagaData> {

  @Autowired
  private ParticipantIdDao participantIdDao;

  private final SweepstakeDao sweepstakeDao;
  private SagaDefinition<CreateSweepstakeSagaData> sagaDefinition =
          /* New step in the saga */
          step()
                  /* Invoking a method with transactions LOCAL to this service */
                  .invokeLocal(this::createSweepstake)
                  /* Run this method to compensate if it gets rejected */
                  .withCompensation(this::handleSweepstakeRejected)

                  .step()
                  .invokeLocal(this::createSweepstakeRelation)
                  .withCompensation(this::handleOwnerRelationRejected)
                  /* New step in the saga */
                  .step()
                  /* Invoking a cross-service call */
                  .invokeParticipant(this::linkOwnerToSweepstake)
                  /* 'Catch' exceptions here and handle them with methods */
                  .onReply(ParticipantNotFound.class, this::handleParticipantNotFound)
                  .onReply(LinkParticipantToSweepstakeFailure.class, this::handleLinkOwnerToSweepstakeFailure)
                  .build();

  private void handleLinkOwnerToSweepstakeFailure(CreateSweepstakeSagaData data, LinkParticipantToSweepstakeFailure reply) {

  }

  private void handleParticipantNotFound(CreateSweepstakeSagaData data, ParticipantNotFound reply) {
    /* TODO: Do something when handling the exception */
  }

  public CreateSweepstakeSaga(SweepstakeDao sweepstakeDao) {
    this.sweepstakeDao = sweepstakeDao;
  }

  private CommandWithDestination linkOwnerToSweepstake(
          CreateSweepstakeSagaData createSweepstakeSagaData) {
    UUID sweepstakeId = createSweepstakeSagaData.getSweepstake().getId();
    UUID ownerId = createSweepstakeSagaData.getSweepstake().getOwnerId();

    /* Do the remote service invocation here */
    return send(new LinkParticipantToSweepstakeCommand(ownerId, sweepstakeId)).to("user-service-events").build();
  }

  private void handleSweepstakeRejected(CreateSweepstakeSagaData createSweepstakeSagaData) {
    /* Rollback here */
    sweepstakeDao.deleteById(createSweepstakeSagaData.getSweepstake().getId());
  }

  private void handleOwnerRelationRejected(CreateSweepstakeSagaData createSweepstakeSagaData) {
    /* Rollback here */
    participantIdDao.deleteById(createSweepstakeSagaData.getOwnerIdObject().getId());
  }

  private void createSweepstake(CreateSweepstakeSagaData createSweepstakeSagaData) {
    Sweepstake sweepstake = (Sweepstake) createSweepstakeSagaData.getSweepstake();

    /* Saving the sweepstake */
    sweepstake = sweepstakeDao.save(sweepstake);

    createSweepstakeSagaData.getSweepstake().setId(sweepstake.getId());
  }

  private void createSweepstakeRelation(CreateSweepstakeSagaData createSweepstakeSagaData) {
    ParticipantIds participantId = new ParticipantIds(createSweepstakeSagaData.getSweepstake().getId(), createSweepstakeSagaData.getSweepstake().getOwnerId());
    participantIdDao.save(participantId);

    createSweepstakeSagaData.setOwnerIdObject(participantId);
  }

  @Override
  public SagaDefinition<CreateSweepstakeSagaData> getSagaDefinition() {
    return this.sagaDefinition;
  }
}
