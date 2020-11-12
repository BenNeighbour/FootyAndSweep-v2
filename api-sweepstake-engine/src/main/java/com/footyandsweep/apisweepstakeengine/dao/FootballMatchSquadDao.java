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

import com.footyandsweep.apisweepstakeengine.model.FootballMatchSquad;
import com.footyandsweep.apisweepstakeengine.relation.ParticipantIds;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.UUID;

@Repository
public interface FootballMatchSquadDao extends JpaRepository<FootballMatchSquad, UUID> {

    @Transactional
    @Cacheable(value = "footballMatchSquadCache", key = "#id")
    FootballMatchSquad findFootballMatchSquadById(UUID id);

    @Transactional
    @CacheEvict(value = "footballMatchSquadCache", key = "#footballMatchSquad.getId()")
    FootballMatchSquad save(FootballMatchSquad footballMatchSquad);

    @Transactional
    @CacheEvict(value = "footballMatchSquadCache", key = "#footballMatchSquad.getId()")
    void delete(FootballMatchSquad footballMatchSquad);

}
