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

package com.footyandsweep.apiallocationengine.dao;

import com.footyandsweep.apiallocationengine.model.Allocation;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.UUID;

@Repository
public interface AllocationDao extends JpaRepository<Allocation, UUID> {

  @Transactional
  @CacheEvict(value = "allocationCache", key = "#id")
  Allocation findAllocationById(UUID id);

  @Transactional
  @CacheEvict(value = "allocationCache", key = "#id")
  Allocation findAllocationByTicketId(UUID ticketId);

  @Transactional
  @CacheEvict(value = "allocationCache", key = "#allocation.getId()")
  Allocation save(Allocation allocation);

  @Transactional
  @CacheEvict(value = "allocationCache", key = "#allocation.getId()")
  void delete(Allocation allocation);
}
