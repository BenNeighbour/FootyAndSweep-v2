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

import com.footyandsweep.apisweepstakeengine.dao.SweepstakeDao;
import com.footyandsweep.apisweepstakeengine.engine.SweepstakeEngineImpl;
import com.footyandsweep.apisweepstakeengine.model.Sweepstake;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/internal/sweepstake")
public class SweepstakeController {

    private final SweepstakeEngineImpl sweepstakeEngine;
    private final SweepstakeDao sweepstakeDao;

    public SweepstakeController(SweepstakeEngineImpl sweepstakeEngine, SweepstakeDao sweepstakeDao) {
        this.sweepstakeEngine = sweepstakeEngine;
        this.sweepstakeDao = sweepstakeDao;
    }

    @PostMapping("/save")
    public ResponseEntity<Sweepstake> createSweepstake(@RequestBody Sweepstake sweepstake) {
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

}
