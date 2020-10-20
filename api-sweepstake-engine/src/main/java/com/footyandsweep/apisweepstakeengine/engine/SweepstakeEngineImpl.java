package com.footyandsweep.apisweepstakeengine.engine;

import com.footyandsweep.apicommonlibrary.events.SweepstakeCreated;
import com.footyandsweep.apicommonlibrary.model.SweepstakeCommon;
import com.footyandsweep.apicommonlibrary.model.TicketCommon;
import com.footyandsweep.apisweepstakeengine.dao.ParticipantIdDao;
import com.footyandsweep.apisweepstakeengine.dao.SweepstakeDao;
import com.footyandsweep.apisweepstakeengine.model.Sweepstake;
import com.footyandsweep.apisweepstakeengine.relation.ParticipantIds;
import io.eventuate.tram.events.publisher.DomainEventPublisher;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;

import static java.util.Collections.singletonList;

@Service
public class SweepstakeEngineImpl implements SweepstakeEngine {

  private final SweepstakeDao sweepstakeDao;

  @Autowired
  private ParticipantIdDao participantIdDao;

  private final DomainEventPublisher domainEventPublisher;

  public SweepstakeEngineImpl(SweepstakeDao sweepstakeDao, DomainEventPublisher domainEventPublisher) {
    this.sweepstakeDao = sweepstakeDao;
    this.domainEventPublisher = domainEventPublisher;
  }

  @Override
  public Sweepstake saveProcessedTickets(UUID sweepstakeId, List<TicketCommon> tickets) {
    // TODO: Process those tickets and save them, linked to the sweepstake
    return new Sweepstake();
  }

  @Override
  @Transactional
  public Sweepstake saveSweepstake(UUID ownerId, Sweepstake sweepstake) {
    try {
      sweepstake.setOwnerId(ownerId);

      Sweepstake savedSweepstake = sweepstakeDao.save(sweepstake);
      participantIdDao.save(new ParticipantIds(savedSweepstake.getSweepstakeId(), ownerId));

      SweepstakeCreated sweepstakeCreated = new SweepstakeCreated();;
      BeanUtils.copyProperties(sweepstakeCreated, sweepstake);

      /* TODO: This gets received by the gateway service, then that service adds the sweepstake and
          user id into it's SweepstakeIds Junction Table */
      domainEventPublisher.publish(
          SweepstakeCommon.class,
          savedSweepstake.getSweepstakeId(),
          singletonList(sweepstakeCreated));

      return savedSweepstake;
    } catch (Exception e) {
      // TODO: FIX THIS!
      return null;
    }
  }
}
