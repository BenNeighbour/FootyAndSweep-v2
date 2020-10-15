package com.footyandsweep.apisweepstakeengine.dao;

import com.footyandsweep.apisweepstakeengine.model.Sweepstake;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface SweepstakeDao extends JpaRepository<Sweepstake, UUID> {

    Sweepstake findSweepstakeBySweepstakeId(UUID sweepstakeId);

}
