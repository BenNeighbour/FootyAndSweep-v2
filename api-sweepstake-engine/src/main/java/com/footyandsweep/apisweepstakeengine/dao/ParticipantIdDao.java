package com.footyandsweep.apisweepstakeengine.dao;

import com.footyandsweep.apisweepstakeengine.relation.ParticipantIds;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ParticipantIdDao extends JpaRepository<ParticipantIds, UUID> {
}
