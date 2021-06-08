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

package com.footyandsweep.apiallocationengine.grpc.client;

import com.footyandsweep.*;
import com.footyandsweep.apicommonlibrary.helper.ProtoConverterUtils;
import com.footyandsweep.apicommonlibrary.model.sweepstake.SweepstakeCommon;
import com.footyandsweep.apicommonlibrary.model.ticket.TicketCommon;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class AllocationClientGrpc {

  private final SweepstakeServiceGrpc.SweepstakeServiceBlockingStub sweepstakeEngineChannel;
  private final TicketServiceGrpc.TicketServiceBlockingStub ticketEngineChannel;

  public AllocationClientGrpc(
      SweepstakeServiceGrpc.SweepstakeServiceBlockingStub sweepstakeEngineChannel,
      TicketServiceGrpc.TicketServiceBlockingStub ticketEgineChannel) {
    this.sweepstakeEngineChannel = sweepstakeEngineChannel;
    this.ticketEngineChannel = ticketEgineChannel;
  }

  public List<String> getAllSweepstakeParticipants(String sweepstakeId) {
    Common.Id id = Common.Id.newBuilder().setId(sweepstakeId).build();

    Common.Ids participantIdProto = sweepstakeEngineChannel.getAllSweepstakeParticipants(id);

    return participantIdProto.getIdList();
  }

  public List<TicketCommon> getSweepstakeTickets(String sweepstakeId) {
    Common.Id id = Common.Id.newBuilder().setId(sweepstakeId).build();

    TicketServiceOuterClass.TicketList ticketsProto =
        ticketEngineChannel.getAllTicketsBySweepstakeId(id);

    /* Convert proto "list" object to a POJO */
    List<TicketCommon> tickets = new ArrayList<>();
    ticketsProto
        .getTicketList()
        .forEach(
            ticket -> {
              TicketCommon ticketCommon =
                  ProtoConverterUtils.convertToPojo(TicketCommon.class, ticket);
              tickets.add(ticketCommon);
            });

    return tickets;
  }

  public SweepstakeCommon findSweepstakeById(String id) {
    SweepstakeServiceOuterClass.Sweepstake sweepstake =
        sweepstakeEngineChannel.findSweepstakeById(Common.Id.newBuilder().setId(id).build());
    return ProtoConverterUtils.convertToPojo(SweepstakeCommon.class, sweepstake);
  }

  public HashMap<Integer, String> resultHelper(SweepstakeCommon sweepstake) {
    HashMap<Integer, String> returnMap = new HashMap<>();
    sweepstakeEngineChannel
        .resultHelper(Common.Id.newBuilder().setId(sweepstake.getId()).build())
        .getPairsList()
        .forEach(
            pair -> {
              returnMap.put(pair.getKey(), pair.getValue());
            });

    return returnMap;
  }
}
