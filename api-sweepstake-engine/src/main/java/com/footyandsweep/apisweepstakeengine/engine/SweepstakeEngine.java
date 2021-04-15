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

import com.footyandsweep.apicommonlibrary.model.sweepstake.SweepstakeCommon;
import com.footyandsweep.apisweepstakeengine.engine.saga.createSweepstake.CreateSweepstakeSagaData;
import com.footyandsweep.apisweepstakeengine.engine.saga.deleteSweepstake.DeleteSweepstakeSagaData;
import com.footyandsweep.apisweepstakeengine.model.Sweepstake;
import com.footyandsweep.apisweepstakeengine.relation.ParticipantIds;
import io.eventuate.tram.commands.consumer.CommandWithDestination;

import java.util.List;

public interface SweepstakeEngine {

  void saveSweepstake(CreateSweepstakeSagaData sagaData);

  CommandWithDestination deleteRemoteSweepstakeRelation(DeleteSweepstakeSagaData sagaData);

  ParticipantIds createSweepstakeParticipantRelation(String joinCode, String participantId);

  void deleteSweepstakeById(String sweepstakeId);

  void deleteSweepstake(Sweepstake sweepstake);

  void deleteSweepstakeRelationById(String relationId);

  void deleteAllSweepstakeRelationsBySweepstakeId(String sweepstakeId);

  CommandWithDestination linkParticipantToSweepstake(String sweepstakeId, String participantId);

  void updateSweepstakeStatus(String sweepstakeId, SweepstakeCommon.SweepstakeStatus status);

  List<Sweepstake> getAllSweepstakesByUser(String userId);
}
