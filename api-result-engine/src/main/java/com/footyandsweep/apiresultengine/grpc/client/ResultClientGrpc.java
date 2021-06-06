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

package com.footyandsweep.apiresultengine.grpc.client;

import com.footyandsweep.*;
import com.footyandsweep.apicommonlibrary.helper.ProtoConverterUtils;
import com.footyandsweep.apicommonlibrary.model.ticket.AllocationCommon;
import com.footyandsweep.apicommonlibrary.model.ticket.TicketCommon;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ResultClientGrpc {

  private final SweepstakeServiceGrpc.SweepstakeServiceBlockingStub sweepstakeEngineChannel;
  private final TicketServiceGrpc.TicketServiceBlockingStub ticketEngineChannel;
  private final AllocationServiceGrpc.AllocationServiceBlockingStub allocationEngineChannel;

  public SweepstakeServiceOuterClass.SweepstakeList getSweepstakeByFootballMatchId(
      String footballMatchId) {
    return sweepstakeEngineChannel.findSweepstakeByFootballMatchId(
        Common.Id.newBuilder().setId(footballMatchId).build());
  }

  public List<TicketCommon> findAllTicketsBySweepstakeId(String sweepstakeId) {
    List<TicketCommon> returnList = new ArrayList<>();
    TicketServiceOuterClass.TicketList tickets =
        ticketEngineChannel.getAllTicketsBySweepstakeId(
            Common.Id.newBuilder().setId(sweepstakeId).build());
    tickets
        .getTicketList()
        .forEach(
            ticket ->
                returnList.add(ProtoConverterUtils.convertToPojo(TicketCommon.class, ticket)));

    return returnList;
  }

  public AllocationCommon getTicketAllocation(String ticketId) {
    String allocationId =
        ticketEngineChannel
            .findTicketById(Common.Id.newBuilder().setId(ticketId).build())
            .getAllocationId();

    return ProtoConverterUtils.convertToPojo(AllocationCommon.class, allocationEngineChannel.getAllocationById(Common.Id.newBuilder().setId(allocationId).build()));
  }
}
