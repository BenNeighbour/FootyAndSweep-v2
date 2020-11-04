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

package com.footyandsweep.apiallocationengine.engine;

import com.footyandsweep.apiallocationengine.dao.AllocationDao;
import com.footyandsweep.apicommonlibrary.model.sweepstake.SweepstakeCommon;
import com.footyandsweep.apicommonlibrary.model.ticket.TicketCommon;
import io.eventuate.tram.events.publisher.DomainEventPublisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.transaction.Transactional;
import java.util.*;

@Transactional
@Service
public class AllocationEngineImpl implements AllocationEngine {

  private final AllocationDao allocationDao;
  private final DomainEventPublisher domainEventPublisher;

  @Autowired private RestTemplate restTemplate;

  public AllocationEngineImpl(
      final AllocationDao allocationDao, final DomainEventPublisher domainEventPublisher) {
    this.allocationDao = allocationDao;
    this.domainEventPublisher = domainEventPublisher;
  }

  /*
    This method id called by either the sweepstake sold out event listener or by the cron job batch-processor
  */
  @Override
  public void allocateSweepstakeTickets(SweepstakeCommon sweepstake) {
    try {
      /* Get a unique shuffled list of unique user ids */
      List<UUID> uniqueUserList = this.sweepstakeParticipantsHelper(sweepstake.getId());

      /* Get the built possible result maps from the sweepstake/result methods */
      Optional<Map<Integer, String>> sweepstakeResultMap =
          Optional.ofNullable(sweepstake.getSweepstakeResultMap());

      /* If the result map is not valid, then throw an error */
      if (!sweepstakeResultMap.isPresent()) throw new Exception();

      /* Build a randomized list of possible results */
      List<Integer> sweepstakeResultIdList = getSweepstakeResultIdList(sweepstakeResultMap.get());

      /* Get a list of tickets that belong to this sweepstake */
      List<TicketCommon> allPurchasedTickets = this.getSweepstakeTicketsHelper(sweepstake.getId());

      /* Process each of the user bought tickets */
      this.processAllTickets(
          allPurchasedTickets, uniqueUserList, sweepstakeResultMap.get(), sweepstakeResultIdList);
    } catch (Exception e) {
      /* Throw error to WebSocket client */
    }
  }

  private List<Integer> getSweepstakeResultIdList(Map<Integer, String> sweepstakeResultMap) {
    List<Integer> sweepstakeResultIdList = new ArrayList<>(sweepstakeResultMap.keySet());
    Collections.shuffle(sweepstakeResultIdList);

    return sweepstakeResultIdList;
  }

  private List<UUID> sweepstakeParticipantsHelper(UUID sweepstakeId) {
    /* Get a list of participants that might have purchased tickets */
    Optional<HashMap<UUID, UUID>> relationIdList =
        Optional.ofNullable(
            restTemplate.getForObject(
                "http://api-sweepstake-engine/internal/sweepstake/by/"
                    + sweepstakeId
                    + "/participants",
                HashMap.class));

    /* Put the result of those returned relations in an array */
    assert relationIdList.isPresent();
    List<UUID> participantIds = new ArrayList<>(relationIdList.get().keySet());

    /* Shuffle the user list and return it back to caller */
    Collections.shuffle(participantIds);
    return participantIds;
  }

  private List<TicketCommon> getSweepstakeTicketsHelper(UUID sweepstakeId) {
    /* Get a list of tickets that belong to the requested sweepstake */
    Optional<List<TicketCommon>> ticketList =
        Optional.ofNullable(
            restTemplate.getForObject(
                "http://api-ticket-engine/internal/ticket/by/sweepstake/" + sweepstakeId,
                List.class));

    /* Return those tickets if they are valid, otherwise an empty array */
    return ticketList.orElseGet(ArrayList::new);
  }

  private void processAllTickets(
      List<TicketCommon> tickets,
      List<UUID> participantIds,
      Map<Integer, String> sweepstakeResultMap,
      List<Integer> sweepstakeResultIdList) {}

  private void allocateTicket() {}
}
