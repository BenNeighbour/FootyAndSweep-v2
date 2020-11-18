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
import com.footyandsweep.apiallocationengine.model.Allocation;
import com.footyandsweep.apicommonlibrary.model.sweepstake.SweepstakeCommon;
import com.footyandsweep.apicommonlibrary.model.sweepstake.SweepstakeTypeCommon;
import com.footyandsweep.apicommonlibrary.model.ticket.TicketCommon;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.transaction.Transactional;
import java.util.*;
import java.util.stream.Collectors;

@Transactional
@Service
public class AllocationEngineImpl implements AllocationEngine {

  private final AllocationDao allocationDao;

  @Autowired private RestTemplate restTemplate;

  public AllocationEngineImpl(final AllocationDao allocationDao) {
    this.allocationDao = allocationDao;
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
          Optional.ofNullable(this.getSweepstakeResultMap(sweepstake));

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
                "http://api-sweepstake-engine:8080/internal/sweepstake/by/"
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
      List<Integer> sweepstakeResultIdList) {
    /* Creating a ticket map that will be iterated over to allocate each ticket */
    Map<UUID, List<TicketCommon>> userTicketsMap = new HashMap<>();

    /* Adding the list of tickets that are linked to the user id and putting them into the user tickets ma[ */
    participantIds.forEach(
        uuid -> {
          userTicketsMap.put(
              uuid,
              tickets.stream()
                  .filter(ticket -> ticket.getUserId().equals(uuid))
                  .collect(Collectors.toList()));
        });

    /* List that stores user ids to keep track of what user we are on - it's ordered and gets refilled */
    ArrayList<UUID> userIdAllocationList = new ArrayList<>();

    /* Iterating over the user tickets map */
    while (!userTicketsMap.isEmpty()) {
      /* Refill the user allocation list */
      userIdAllocationList.addAll(participantIds);

      /* Meanwhile there are still users left to allocate */
      while (!userIdAllocationList.isEmpty()) {
        /* Get the first user id of the list, then remove so it does not get allocated again during this round */
        UUID userId = userIdAllocationList.remove(0);

        /* Obtain the tickets for that user in the user tickets map */
        Optional<List<TicketCommon>> userTickets = Optional.ofNullable(userTicketsMap.get(userId));

        /* Checking if the tickets are void/null */
        if (!userTickets.isPresent() || userTickets.get().isEmpty()) {
          /* Remove those tickets from their maps */
          userTicketsMap.remove(userId);
          participantIds.remove(userId);

          /* Logs here */
          System.out.println("User: " + userId + " has no more tickets to allocate");
        } else {
          /* Otherwise, get the current ticket (popping each one off each time), and run it through the allocateTicket function */
          TicketCommon ticket = userTickets.get().remove(0);

          /* Logs here */
          System.out.println("Allocate User: " + userId + " Ticket: " + ticket);
          this.allocateTicket(sweepstakeResultMap, sweepstakeResultIdList, ticket);
        }
      }

      /* Reverse the order of the 'random' shuffled user id list and go over it all again until there is nothing left to allocate */
      Collections.reverse(participantIds);
    }
  }

  private void allocateTicket(
      Map<Integer, String> sweepstakeResultMap,
      List<Integer> sweepstakeResultIdList,
      TicketCommon ticket) {
    try {
      /* Creating a new instance of the allocation object that is about to be persisted */
      Allocation allocation = new Allocation();

      /* Filling in fields of the allocation object based on the parameters passed into this function */
      allocation.setCode(sweepstakeResultIdList.remove(0));
      allocation.setDescription(sweepstakeResultMap.remove(allocation.getCode()));
      allocation.setTicketId(ticket.getId());

      /* Setting the ticket fields before broadcasting the event */
      ticket.setStatus(TicketCommon.TicketStatus.INPLAY);
      ticket.setAllocationId(allocation.getId());

      /* Getting sweepstake by the id so that it can be modified here */
      Optional<SweepstakeCommon> sweepstake =
          Optional.ofNullable(
              restTemplate.getForObject(
                  "http://api-sweepstake-engine:8080/internal/sweepstake/by/"
                      + ticket.getSweepstakeId()
                      + "/participants",
                  SweepstakeCommon.class));

      /* If the sweepstake is not valid, then throw an error */
      if (!sweepstake.isPresent()) throw new Exception();

      /* Persisting the allocation and setting to itself so the generated id is filled in */
      allocation = allocationDao.save(allocation);

      /* Setting the sweepstake status to allocated */
      sweepstake.get().setStatus(SweepstakeCommon.SweepstakeStatus.ALLOCATED);

      /* Creating the ticket allocated event with the right metadata inside to be put into the message to the other services */
      //      TicketAllocated ticketAllocated = new TicketAllocated(ticket, allocation,
      // sweepstake.get());

      /* Publish ticket allocated event */
      //      domainEventPublisher.publish(
      //          TicketCommon.class, ticket.getId(), singletonList(ticketAllocated));
    } catch (Exception e) {
      /* Throw error to WebSocket client */
    }
  }

  private Map<Integer, String> getSweepstakeResultMap(SweepstakeCommon sweepstake) {
    /* Defining the result map so it can be modified in the iteration */
    Optional<Map<Integer, String>> resultMap = Optional.empty();

    /* Going over all of the sweepstake types in the enum in order to programmatically determine what method will be invoked on the result helper */
    for (SweepstakeTypeCommon i : SweepstakeTypeCommon.values()) {
      /* Call result helper to get the field and return a function that returns the right maps to back */
      if (sweepstake.getSweepstakeType().equals(i))
        resultMap =
            Optional.ofNullable(
                restTemplate
                    .postForEntity(
                        "http://api-sweepstake-engine:8080/internal/sweepstake/result",
                        sweepstake,
                        Map.class)
                    .getBody());
    }

    return resultMap.orElse(new HashMap<>());
  }
}
