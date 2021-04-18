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

package com.footyandsweep.apisweepstakeengine.grpc.client;

import com.footyandsweep.AllocationServiceGrpc;
import com.footyandsweep.Common;
import com.footyandsweep.SweepstakeServiceOuterClass;
import com.footyandsweep.TicketServiceGrpc;
import com.footyandsweep.apicommonlibrary.helper.ProtoConverterUtils;
import com.footyandsweep.apicommonlibrary.model.ticket.TicketCommon;
import com.footyandsweep.apisweepstakeengine.model.Sweepstake;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SweepstakeClientGrpc {

  private final AllocationServiceGrpc.AllocationServiceBlockingStub allocationEngineChannel;
  private final TicketServiceGrpc.TicketServiceBlockingStub ticketServiceChannel;

  public SweepstakeClientGrpc(AllocationServiceGrpc.AllocationServiceBlockingStub allocationEngineChannel, TicketServiceGrpc.TicketServiceBlockingStub ticketServiceChannel) {
    this.allocationEngineChannel = allocationEngineChannel;
    this.ticketServiceChannel = ticketServiceChannel;
  }

  public void allocateSweepstake(Sweepstake sweepstake) {
    SweepstakeServiceOuterClass.Sweepstake.Builder grpcSweepstake =
        SweepstakeServiceOuterClass.Sweepstake.newBuilder();
    ProtoConverterUtils.convertToProto(grpcSweepstake, sweepstake);

    allocationEngineChannel.allocateSweepstake(grpcSweepstake.build());
  }

  public List<TicketCommon> getSweepstakeTickets(String sweepstakeId) {
    List<TicketCommon> ticketCommonList = new ArrayList<>();
    ticketServiceChannel.getAllTicketsBySweepstakeId(Common.Id.newBuilder().setId(sweepstakeId).build()).getTicketList().forEach(ticket -> {
      ticketCommonList.add(ProtoConverterUtils.convertToPojo(TicketCommon.class, ticket));
    });

    return ticketCommonList;
  }
}
