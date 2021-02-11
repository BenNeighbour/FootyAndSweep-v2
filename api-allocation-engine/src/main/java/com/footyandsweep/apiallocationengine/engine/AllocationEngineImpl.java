/*
 *   Copyright 2021 FootyAndSweep
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
import com.footyandsweep.apiallocationengine.engine.saga.AllocateSweepstakeSagaData;
import com.footyandsweep.apiallocationengine.grpc.client.AllocationClientGrpc;
import com.footyandsweep.apiallocationengine.model.Allocation;
import com.footyandsweep.apicommonlibrary.cqrs.sweepstake.update.UpdateSweepstakeStatusCommand;
import com.footyandsweep.apicommonlibrary.cqrs.ticket.AllocateTicketsCommand;
import com.footyandsweep.apicommonlibrary.model.sweepstake.SweepstakeCommon;
import com.footyandsweep.apicommonlibrary.model.sweepstake.SweepstakeTypeCommon;
import com.footyandsweep.apicommonlibrary.model.ticket.TicketCommon;
import io.eventuate.tram.commands.consumer.CommandWithDestination;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

import static io.eventuate.tram.commands.consumer.CommandWithDestinationBuilder.send;

@Service
public class AllocationEngineImpl implements AllocationEngine {

  private static final Logger log = LoggerFactory.getLogger(AllocationEngineImpl.class);
  private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ssZ");

  private final AllocationDao allocationDao;
  private final RestTemplate restTemplate;
  private final AllocationClientGrpc allocationClient;

  public AllocationEngineImpl(AllocationDao allocationDao, RestTemplate restTemplate, AllocationClientGrpc allocationClient) {
    this.allocationDao = allocationDao;
    this.restTemplate = restTemplate;
    this.allocationClient = allocationClient;
  }

  /*
      This method is called by either the sweepstake sold out event listener or by the cron job batch-processor
    */
  @Override
  public void allocateSweepstakeTickets(AllocateSweepstakeSagaData sagaData) {
    try {
      /* Get a unique shuffled list of unique user ids */
      List<String> uniqueUserList = this.sweepstakeParticipantsHelper(sagaData.getSweepstake().getId());

      /* Get the built possible result maps from the sweepstake/result methods */
      Optional<Map<Integer, String>> sweepstakeResultMap =
          Optional.of(this.getSweepstakeResultMap(sagaData.getSweepstake()));

      /* Build a randomized list of possible results */
      List<Integer> sweepstakeResultIdList = getSweepstakeResultIdList(sweepstakeResultMap.get());

      /* Get a list of tickets that belong to this sweepstake */
      List<TicketCommon> allPurchasedTickets = this.getSweepstakeTicketsHelper(sagaData.getSweepstake().getId());
      sagaData.setTickets(allPurchasedTickets);

      /* Process each of the user bought tickets */
      this.processAllTickets(
          sagaData, uniqueUserList, sweepstakeResultMap.get(), sweepstakeResultIdList);
    } catch (Exception e) {
      /* Throw error to */
      e.getCause();
    }
  }

  private List<Integer> getSweepstakeResultIdList(Map<Integer, String> sweepstakeResultMap) {
    List<Integer> sweepstakeResultIdList = new ArrayList<>(sweepstakeResultMap.keySet());

    Collections.shuffle(sweepstakeResultIdList);

    return sweepstakeResultIdList;
  }

  private List<String> sweepstakeParticipantsHelper(String sweepstakeId) {
    /* Get a list of participants that might have purchased tickets */
    List<String> participantIds = new LinkedList<>(allocationClient.getAllSweepstakeParticipants(sweepstakeId));

    Collections.shuffle(participantIds);
    return participantIds;
  }

  private List<TicketCommon> getSweepstakeTicketsHelper(String sweepstakeId) {
    /* Return all sweepstake tickets */
    return allocationClient.getSweepstakeTickets(sweepstakeId);
  }

  private void processAllTickets(
          AllocateSweepstakeSagaData sagaData,
      List<String> participantIds,
      Map<Integer, String> sweepstakeResultMap,
      List<Integer> sweepstakeResultIdList) {
    /* Creating a ticket map that will be iterated over to allocate each ticket */
    Map<String, List<TicketCommon>> userTicketsMap = new HashMap<>();

    /* Adding the list of tickets that are linked to the user id and putting them into the user tickets map */
    for (int i = 0; i < participantIds.size(); i++) {
      final int finalI = i;
      List<TicketCommon> userTickets =
          sagaData.getTickets().stream()
              .filter(ticketCommon -> ticketCommon.getUserId().equals(participantIds.get(finalI)))
              .collect(Collectors.toList());

      userTicketsMap.put(participantIds.get(finalI), userTickets);
    }

    /* List that stores user ids to keep track of what user we are on - it's ordered and gets refilled */
    List<String> userIdAllocationList = new ArrayList<>();

    /* Modified Tickets */
    List<TicketCommon> modifiedTickets = new ArrayList<>();

    /* Iterating over the user tickets map */
    while (!userTicketsMap.isEmpty()) {
      /* Refill the user allocation list */
      userIdAllocationList.addAll(participantIds);

      /* Meanwhile there are still users left to allocate */
      while (!userIdAllocationList.isEmpty()) {

        /* Get the first user id of the list, then remove so it does not get allocated again during this round */
        String userId = userIdAllocationList.remove(0);

        /* Obtain the tickets for that user in the user tickets map */
        List<TicketCommon> userTickets = userTicketsMap.get(userId);

        /* Checking if the tickets are void/null */
        if (userTickets == null || userTickets.isEmpty()) {
          /* Remove those tickets from their maps */
          userTicketsMap.remove(userId);
          participantIds.remove(userId);

          /* Logs here */
          System.out.println("User: " + userId + " has no more tickets to allocate");
        } else {
          /* Otherwise, get the current ticket (popping each one off each time), and run it through the allocateTicket function */
          TicketCommon ticket = userTickets.remove(0);

          System.out.println("Allocate User: " + userId + " Ticket: " + ticket);

          modifiedTickets.add(this.allocateTicket(
              sweepstakeResultMap, sweepstakeResultIdList, ticket));
        }
      }

      sagaData.setTickets(modifiedTickets);

      /* Reverse the order of the 'random' shuffled user id list and go over it all again until there is nothing left to allocate */
      Collections.reverse(participantIds);
    }
  }

  private TicketCommon allocateTicket(
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

      /* Getting sweepstake by the id so that it can be modified here */
      Optional<SweepstakeCommon> sweepstake =
          Optional.ofNullable(
              restTemplate.getForObject(
                  "http://api-sweepstake-engine:8080/sweepstake/test/by/id/"
                      + ticket.getSweepstakeId(),
                  SweepstakeCommon.class));

      /* If the sweepstake is not valid, then throw an error */
      if (!sweepstake.isPresent()) throw new Exception();

      /* Persisting the allocation and setting to itself so the generated id is filled in */
      allocation = allocationDao.save(allocation);

      /* Setting the transient aggregate  */
      ticket.setAllocationCommon(allocation);
      ticket.setSweepstake(sweepstake.get());

      /* Join the allocation id with the ticket */
      ticket.setAllocationId(allocation.getId());

      return ticket;
    } catch (Exception e) {
      /* Throw error to WebSocket client */
      e.getCause();
    }

    return null;
  }

  private Map<Integer, String> getSweepstakeResultMap(SweepstakeCommon sweepstake) {
    HashMap<Integer, String> resultMap = new HashMap<>();

    /* Going over all of the sweepstake types in the enum in order to programmatically determine what method will be invoked on the result helper */
    for (SweepstakeTypeCommon i : SweepstakeTypeCommon.values()) {

      /* Call result helper to get the field and return a function that returns the right maps to back */
      if (sweepstake.getSweepstakeType().equals(i)) {
        resultMap =
                new HashMap<>(allocationClient.resultHelper(sweepstake));
      }
    }

    return resultMap;
  }

  @Override
  public CommandWithDestination allocateTickets(List<TicketCommon> tickets) {
    HashMap<String, String> ticketAllocationIds = new HashMap<>();
    tickets.forEach(ticket -> ticketAllocationIds.put(ticket.getId(), ticket.getAllocationId()));

    return send(new AllocateTicketsCommand(ticketAllocationIds))
            .to("ticket-engine-events")
            .build();
  }

  @Override
  public CommandWithDestination updateSweepstakeStatus(String sweepstakeId, SweepstakeCommon.SweepstakeStatus status) {
    return send(new UpdateSweepstakeStatusCommand(sweepstakeId, status))
            .to("sweepstake-engine-events")
            .build();
  }
}