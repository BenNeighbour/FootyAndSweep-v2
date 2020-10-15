package com.footyandsweep.apigatewayservice.dao;

import com.footyandsweep.apigatewayservice.relation.SweepstakeIds;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface SweepstakeIdDao extends JpaRepository<SweepstakeIds, UUID> {

  SweepstakeIds findSweepstakeIdsBySweepstakeId(UUID sweepstakeId);

  SweepstakeIds findSweepstakeIdsByParticipantId(UUID participantId);
}
