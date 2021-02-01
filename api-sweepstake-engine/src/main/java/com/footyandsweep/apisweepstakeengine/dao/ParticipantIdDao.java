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

package com.footyandsweep.apisweepstakeengine.dao;

import com.footyandsweep.apisweepstakeengine.relation.ParticipantIds;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
public interface ParticipantIdDao extends JpaRepository<ParticipantIds, String> {

  @Transactional
  @Cacheable(value = "sweepstakeParticipantCache", key = "#sweepstakeId")
  List<ParticipantIds> findAllParticipantIdsBySweepstakeId(String sweepstakeId);

  @Transactional
  @Cacheable(value = "sweepstakeParticipantCache", key = "#result.get().getSweepstakeId()")
  Optional<List<ParticipantIds>> findAllParticipantIdsByParticipantId(String participantId);

  @Transactional
  @CacheEvict(value = "sweepstakeParticipantCache", key = "#participantIds.getSweepstakeId()")
  ParticipantIds save(ParticipantIds participantIds);

  @Transactional
  @CacheEvict(value = "sweepstakeParticipantCache", key = "#participantIds.getSweepstakeId()")
  void delete(ParticipantIds participantIds);

  @Transactional
  @CacheEvict(value = "sweepstakeParticipantCache", key = "#participantIds.getSweepstakeId()")
  void deleteBySweepstakeId(String sweepstakeId);
}
