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

package com.footyandsweep.apiallocationengine;

import com.footyandsweep.apiallocationengine.dao.AllocationDao;
import com.footyandsweep.apiallocationengine.engine.AllocationEngine;
import com.footyandsweep.apiallocationengine.model.Allocation;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/internal/allocation")
public class AllocationController {

  private final AllocationDao allocationDao;
  private final AllocationEngine allocationEngine;

  public AllocationController(AllocationDao allocationDao, AllocationEngine allocationEngine) {
    this.allocationDao = allocationDao;
    this.allocationEngine = allocationEngine;
  }

  @GetMapping("/by/ticket/{id}")
  public Allocation findAllocationByTicketId(@PathVariable("id") UUID id) {
    return allocationDao.findAllocationByTicketId(id);
  }

  @PostMapping("/save")
  public Allocation createAllocation(@RequestBody Allocation allocation) {
    return allocationDao.save(allocation);
  }
}
