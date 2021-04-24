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

package com.footyandsweep.apisweepstakeengine.engine;

import com.footyandsweep.apicommonlibrary.cqrs.sweepstake.delete.DeleteAllSweepstakeRelationsCommand;
import com.footyandsweep.apicommonlibrary.cqrs.user.LinkParticipantToSweepstakeCommand;
import com.footyandsweep.apicommonlibrary.exceptions.ParticipantAlreadyJoinedException;
import com.footyandsweep.apicommonlibrary.exceptions.SweepstakeDoesNotExistException;
import com.footyandsweep.apicommonlibrary.model.sweepstake.SweepstakeCommon;
import com.footyandsweep.apisweepstakeengine.dao.ParticipantIdDao;
import com.footyandsweep.apisweepstakeengine.dao.SweepstakeDao;
import com.footyandsweep.apisweepstakeengine.engine.saga.createSweepstake.CreateSweepstakeSagaData;
import com.footyandsweep.apisweepstakeengine.engine.saga.deleteSweepstake.DeleteSweepstakeSagaData;
import com.footyandsweep.apisweepstakeengine.grpc.client.SweepstakeClientGrpc;
import com.footyandsweep.apisweepstakeengine.model.Sweepstake;
import com.footyandsweep.apisweepstakeengine.relation.ParticipantIds;
import io.eventuate.tram.commands.consumer.CommandWithDestination;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static io.eventuate.tram.commands.consumer.CommandWithDestinationBuilder.send;

@Service
@RequiredArgsConstructor
public class SweepstakeEngineImpl implements SweepstakeEngine {

  private static final Logger log = LoggerFactory.getLogger(SweepstakeEngineImpl.class);
  private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ssZ");

  private final SweepstakeDao sweepstakeDao;
  private final ParticipantIdDao participantIdDao;
  private final SweepstakeClientGrpc sweepstakeClient;

  @Override
  public void saveSweepstake(CreateSweepstakeSagaData sagaData) {
    Sweepstake sweepstake = (Sweepstake) sagaData.getSweepstake();

    /* Saving the sweepstake */
    sweepstake = sweepstakeDao.save(sweepstake);

    sagaData.getSweepstake().setId(sweepstake.getId());
  }

  @Override
  public ParticipantIds createSweepstakeParticipantRelation(String joinCode, String participantId) {
    /* Find the sweepstake */
    Sweepstake sweepstake = sweepstakeDao.findSweepstakeByJoinCode(joinCode);

    if (sweepstake != null) {

      /* Check if the user is already in the sweepstake */
      List<ParticipantIds> sweepstakeParticipantIds =
          participantIdDao.findAllParticipantIdsBySweepstakeId(sweepstake.getId());

      sweepstakeParticipantIds =
          sweepstakeParticipantIds.stream()
              .filter(participantIds -> participantIds.getParticipantId().equals(participantId))
              .collect(Collectors.toList());

      if (sweepstakeParticipantIds.isEmpty()) {
        /* If the user isn't in the sweepstake, then add it to the participant list */
        ParticipantIds newParticipant = new ParticipantIds(sweepstake.getId(), participantId);

        newParticipant = participantIdDao.save(newParticipant);
        return newParticipant;
      } else {
        throw new ParticipantAlreadyJoinedException("You are already part of this sweepstake!");
      }
    } else {
      throw new RuntimeException("Invalid Join Code!");
    }
  }

  @Override
  public void updateSweepstakeStatus(
      String sweepstakeId, SweepstakeCommon.SweepstakeStatus status) {
    try {
      Sweepstake sweepstake = sweepstakeDao.findSweepstakeById(sweepstakeId);
      sweepstake.setStatus(status);

      sweepstakeDao.saveAndFlush(sweepstake);
    } catch (NullPointerException e) {
      throw new SweepstakeDoesNotExistException();
    }
  }

  @Override
  public CommandWithDestination linkParticipantToSweepstake(
      String sweepstakeId, String participantId) {
    /* Do the remote service invocation here */
    return send(new LinkParticipantToSweepstakeCommand(participantId, sweepstakeId))
        .to("user-service-events")
        .build();
  }

  @Override
  public CommandWithDestination deleteRemoteSweepstakeRelation(DeleteSweepstakeSagaData sagaData) {
    return send(new DeleteAllSweepstakeRelationsCommand(sagaData.getSweepstake().getId()))
        .to("user-service-events")
        .build();
  }

  @Override
  public void deleteSweepstakeById(String sweepstakeId) {
    sweepstakeDao.deleteById(sweepstakeId);
  }

  @Override
  public void deleteSweepstake(Sweepstake sweepstake) {
    sweepstakeDao.delete(sweepstake);
  }

  @Override
  public void deleteSweepstakeRelationById(String relationId) {
    participantIdDao.deleteById(relationId);
  }

  @Override
  public void deleteAllSweepstakeRelationsBySweepstakeId(String sweepstakeId) {
    List<ParticipantIds> participantIds =
        participantIdDao.findAllParticipantIdsBySweepstakeId(sweepstakeId);

    assert participantIds != null;
    participantIds.forEach(participantIdDao::delete);
  }

  @Override
  public List<Sweepstake> getAllSweepstakesByUser(String userId) {
    /* Get all the sweepstake relations by the id */
    Optional<List<ParticipantIds>> sweepstakeUserRelations =
        participantIdDao.findAllByParticipantId(userId);

    if (sweepstakeUserRelations.isPresent()) {
      List<Sweepstake> participantSweepstakes = new ArrayList<>();

      /* For each one, get the sweepstake for it and put it in the return array */
      sweepstakeUserRelations
          .get()
          .forEach(
              participantIds -> {
                Sweepstake sweepstake =
                    sweepstakeDao.findSweepstakeById(participantIds.getSweepstakeId());

                /* Get the tickets for this sweepstake */
                sweepstake.setTickets(sweepstakeClient.getSweepstakeTickets(sweepstake.getId()));
                if (sweepstake != null) participantSweepstakes.add(sweepstake);
              });

      return participantSweepstakes;
    }

    return new ArrayList<>();
  }
}
