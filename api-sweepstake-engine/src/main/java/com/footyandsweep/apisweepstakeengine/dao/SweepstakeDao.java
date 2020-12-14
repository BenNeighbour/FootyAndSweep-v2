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

package com.footyandsweep.apisweepstakeengine.dao;

import com.footyandsweep.apicommonlibrary.model.sweepstake.SweepstakeCommon;
import com.footyandsweep.apisweepstakeengine.model.FootballMatchSweepstake;
import com.footyandsweep.apisweepstakeengine.model.Sweepstake;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;

@Repository
public interface SweepstakeDao extends JpaRepository<Sweepstake, UUID> {

  @Transactional
  @CachePut(value = "sweepstakeCache", key = "#id")
  Sweepstake findSweepstakeById(UUID id);

  @Transactional
  @CachePut(value = "sweepstakeCache", key = "#id")
  FootballMatchSweepstake findFootballMatchSweepstakeById(UUID id);

  @Transactional
  @CachePut(value = "sweepstakeCache", key = "#result.getId()")
  Sweepstake findSweepstakeByJoinCode(String joinCode);

  @Transactional
  List<Sweepstake> findAllSweepstakesByStatus(SweepstakeCommon.SweepstakeStatus status);

  @Transactional
  @CacheEvict(value = "sweepstakeCache", key = "#sweepstake.getId()")
  Sweepstake save(Sweepstake sweepstake);

  @Transactional
  @CacheEvict(value = "sweepstakeCache", key = "#sweepstake.getId()")
  void delete(Sweepstake sweepstake);
}
