/*
 *   Copyright 2020 FootyAndSweep
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

import com.footyandsweep.apisweepstakeengine.dao.FootballMatchDao;
import com.footyandsweep.apisweepstakeengine.dao.ParticipantIdDao;
import com.footyandsweep.apisweepstakeengine.dao.SweepstakeDao;
import com.footyandsweep.apisweepstakeengine.engine.SweepstakeEngineImpl;
import com.footyandsweep.apisweepstakeengine.helper.ResultHelper;
import com.footyandsweep.apisweepstakeengine.model.FootballMatchSweepstake;
import com.footyandsweep.apisweepstakeengine.model.Sweepstake;
import com.footyandsweep.apisweepstakeengine.relation.ParticipantIds;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/internal/sweepstake")
public class SweepstakeController {

  private final SweepstakeEngineImpl sweepstakeEngine;
  private final SweepstakeDao sweepstakeDao;
  private final ParticipantIdDao participantIdDao;
  private final ResultHelper resultHelper;
  private final FootballMatchDao footballMatchDao;

  public SweepstakeController(
      final SweepstakeEngineImpl sweepstakeEngine,
      final SweepstakeDao sweepstakeDao,
      final ParticipantIdDao participantIdDao,
      final ResultHelper resultHelper,
      final FootballMatchDao footballMatchDao) {
    this.sweepstakeEngine = sweepstakeEngine;
    this.sweepstakeDao = sweepstakeDao;
    this.participantIdDao = participantIdDao;
    this.resultHelper = resultHelper;
    this.footballMatchDao = footballMatchDao;
  }

  @PostMapping("/save")
  public ResponseEntity<Sweepstake> createSweepstake(
      @RequestBody FootballMatchSweepstake sweepstake) {
    return ResponseEntity.ok(sweepstakeEngine.saveSweepstake(sweepstake.getOwnerId(), sweepstake));
  }

  @GetMapping("/by/joinCode/{joinCode}")
  public Sweepstake findSweepstakeByJoinCode(@PathVariable("joinCode") String joinCode) {
    return sweepstakeDao.findSweepstakeByJoinCode(joinCode);
  }

  @GetMapping("/by/id/{id}")
  public Sweepstake findSweepstakeById(@PathVariable("id") UUID id) {
    return sweepstakeDao.findSweepstakeById(id);
  }

  @GetMapping("/by/footballMatch/{id}")
  public List<Sweepstake> findSweepstakeByFootballMatchId(@PathVariable("id") UUID id) {
    return sweepstakeDao.findAll().stream()
        .filter(sweepstake -> sweepstake instanceof FootballMatchSweepstake)
        .filter(
            sweepstake -> ((FootballMatchSweepstake) sweepstake).getFootballMatchId().equals(id))
        .filter(
            sweepstake ->
                footballMatchDao
                    .findFootballMatchById(sweepstake.getSweepstakeEventId())
                    .equals(footballMatchDao.findFootballMatchById(id)))
        .collect(Collectors.toList());
  }

  @GetMapping("/by/{sweepstakeId}/participants")
  public HashMap<UUID, UUID> findAllSweepstakeParticipantRelations(
      @PathVariable("sweepstakeId") UUID id) {

    Optional<List<ParticipantIds>> participantsInSweepstake =
        participantIdDao.findAllParticipantIdsBySweepstakeId(id);

    if (!participantsInSweepstake.isPresent()) return new HashMap<>();

    HashMap<UUID, UUID> returnHashMap = new HashMap<>();

    participantsInSweepstake
        .get()
        .forEach(
            participantIds ->
                returnHashMap.put(
                    participantIds.getParticipantId(), participantIds.getSweepstakeId()));

    return returnHashMap;
  }

  @PostMapping("/result")
  public Map<Integer, String> resultHelper(@RequestBody FootballMatchSweepstake sweepstake) {
    return resultHelper.buildResultsForSweepstakeType(sweepstake.getSweepstakeType(), sweepstake);
  }
}
