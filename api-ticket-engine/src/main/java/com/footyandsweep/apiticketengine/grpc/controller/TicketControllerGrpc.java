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

package com.footyandsweep.apiticketengine.grpc.controller;

import com.footyandsweep.Common;
import com.footyandsweep.TicketServiceGrpc;
import com.footyandsweep.TicketServiceOuterClass;
import com.footyandsweep.apicommonlibrary.helper.ProtoConverterUtils;
import com.footyandsweep.apiticketengine.dao.TicketDao;
import com.footyandsweep.apiticketengine.grpc.util.GrpcService;
import com.footyandsweep.apiticketengine.model.Ticket;
import io.grpc.stub.StreamObserver;

import java.util.List;

@GrpcService
public class TicketControllerGrpc extends TicketServiceGrpc.TicketServiceImplBase {

  private final TicketDao ticketDao;

  public TicketControllerGrpc(TicketDao ticketDao) {
    this.ticketDao = ticketDao;
  }

  @Override
  public void findTicketById(
      Common.Id request, StreamObserver<TicketServiceOuterClass.Ticket> responseObserver) {
    TicketServiceOuterClass.Ticket.Builder ticketBuilder =
        TicketServiceOuterClass.Ticket.newBuilder();
    ProtoConverterUtils.convertToProto(ticketBuilder, ticketDao.findTicketById(request.getId()));

    responseObserver.onNext(ticketBuilder.build());
    responseObserver.onCompleted();
  }

  @Override
  public void getAllTicketsBySweepstakeId(
      Common.Id request, StreamObserver<TicketServiceOuterClass.TicketList> responseObserver) {
    TicketServiceOuterClass.TicketList.Builder ticketListBuilder =
            TicketServiceOuterClass.TicketList.newBuilder();

    List<Ticket> tickets = ticketDao.findAllTicketsBySweepstakeId(request.getId()).get();
    tickets.forEach(ticket -> {
      TicketServiceOuterClass.Ticket.Builder current = TicketServiceOuterClass.Ticket.newBuilder();
      ProtoConverterUtils.convertToProto(current, ticket);

      ticketListBuilder.addTicket(current.build());
    });

    responseObserver.onNext(ticketListBuilder.build());
    responseObserver.onCompleted();
  }
}
