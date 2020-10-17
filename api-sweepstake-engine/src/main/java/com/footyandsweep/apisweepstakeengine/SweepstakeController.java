package com.footyandsweep.apisweepstakeengine;

import com.footyandsweep.apisweepstakeengine.engine.SweepstakeEngineImpl;
import com.footyandsweep.apisweepstakeengine.model.Sweepstake;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sweepstake")
public class SweepstakeController {

    @Autowired
    private SweepstakeEngineImpl sweepstakeEngine;

    @PostMapping("/save")
    public ResponseEntity<Sweepstake> createSweepstake(@RequestBody Sweepstake sweepstake) {
        return ResponseEntity.ok(sweepstakeEngine.saveSweepstake(sweepstake.getOwnerId(), sweepstake));
    }

}
