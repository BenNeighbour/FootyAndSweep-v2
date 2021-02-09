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

import com.footyandsweep.Common;
import com.footyandsweep.SweepstakeServiceGrpc;
import com.footyandsweep.TicketServiceGrpc;
import com.footyandsweep.TicketServiceOuterClass;
import com.footyandsweep.apicommonlibrary.helper.ProtoConverterUtils;
import com.footyandsweep.apicommonlibrary.model.ticket.TicketCommon;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class AllocationClientGrpc {

  private final SweepstakeServiceGrpc.SweepstakeServiceBlockingStub sweepstakeEngineChannel;
  private final TicketServiceGrpc.TicketServiceBlockingStub ticketEgineChannel;

  public AllocationClientGrpc(SweepstakeServiceGrpc.SweepstakeServiceBlockingStub sweepstakeEngineChannel, TicketServiceGrpc.TicketServiceBlockingStub ticketEgineChannel) {
    this.sweepstakeEngineChannel = sweepstakeEngineChannel;
    this.ticketEgineChannel = ticketEgineChannel;
  }

  public List<String> getAllSweepstakeParticipants(String sweepstakeId) {
    Common.Id id = Common.Id.newBuilder().setId(sweepstakeId).build();

    Common.Ids participantIdProto =
            sweepstakeEngineChannel.getAllSweepstakeParticipants(id);

    return Arrays.asList(ProtoConverterUtils.convertToPojo(String[].class, participantIdProto));
  }

  public List<TicketCommon> getSweepstakeTickets(String sweepstakeId) {
    Common.Id id = Common.Id.newBuilder().setId(sweepstakeId).build();

    TicketServiceOuterClass.TicketList ticketsProto = ticketEgineChannel.getAllTicketsBySweepstakeId(id);

    return Arrays.asList(ProtoConverterUtils.convertToPojo(TicketCommon[].class, ticketsProto));
  }
}
