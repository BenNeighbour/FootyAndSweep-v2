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

package com.footyandsweep.apigatewayservice.dao;

import com.footyandsweep.apigatewayservice.relation.SweepstakeIds;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
public interface SweepstakeIdDao extends JpaRepository<SweepstakeIds, String> {

  @Transactional
  @Cacheable(value = "sweepstakeParticipantCache", key = "#sweepstakeId")
  Optional<List<SweepstakeIds>> findAllSweepstakeIdsBySweepstakeId(String sweepstakeId);

  @Transactional
  @Cacheable(value = "sweepstakeParticipantCache", key = "#result.get().getSweepstakeId()")
  Optional<SweepstakeIds> findSweepstakeIdsByParticipantId(String participantId);

  @Transactional
  @CacheEvict(value = "sweepstakeParticipantCache", key = "#sweepstakeIds.getSweepstakeId()")
  SweepstakeIds save(SweepstakeIds sweepstakeIds);

  @Transactional
  @CacheEvict(value = "sweepstakeParticipantCache", key = "#sweepstakeIds.getSweepstakeId()")
  void delete(SweepstakeIds sweepstakeIds);
}
