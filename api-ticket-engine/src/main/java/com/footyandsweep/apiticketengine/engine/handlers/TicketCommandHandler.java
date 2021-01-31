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

package com.footyandsweep.apiticketengine.engine.handlers;

import com.footyandsweep.apicommonlibrary.cqrs.ticket.AllocateTicketsCommand;
import com.footyandsweep.apicommonlibrary.cqrs.ticket.TicketsAllocated;
import com.footyandsweep.apicommonlibrary.cqrs.user.UpdateUserBalanceFailure;
import com.footyandsweep.apicommonlibrary.exceptions.InsufficientCreditsException;
import com.footyandsweep.apicommonlibrary.exceptions.UserDoesNotExistException;
import com.footyandsweep.apiticketengine.engine.TicketEngine;
import com.footyandsweep.apiticketengine.model.Ticket;
import io.eventuate.tram.commands.consumer.CommandHandlers;
import io.eventuate.tram.commands.consumer.CommandMessage;
import io.eventuate.tram.messaging.common.Message;
import io.eventuate.tram.sagas.participant.SagaCommandHandlersBuilder;

import static io.eventuate.tram.commands.consumer.CommandHandlerReplyBuilder.withFailure;
import static io.eventuate.tram.commands.consumer.CommandHandlerReplyBuilder.withSuccess;

public class TicketCommandHandler {

  private final TicketEngine ticketEngine;

  public TicketCommandHandler(TicketEngine ticketEngine) {
    this.ticketEngine = ticketEngine;
  }

  public CommandHandlers commandHandlerDefinitions() {
    return SagaCommandHandlersBuilder.fromChannel("ticket-engine-events")
        .onMessage(AllocateTicketsCommand.class, this::allocateTickets)
        .build();
  }

  private Message allocateTickets(CommandMessage<AllocateTicketsCommand> allocateTicketsCommand) {
    try {
      AllocateTicketsCommand command = allocateTicketsCommand.getCommand();
      command.getTickets().forEach(ticket -> ticketEngine.modifyTickets((Ticket) ticket));

      return withSuccess(new TicketsAllocated());
    } catch (UserDoesNotExistException | InsufficientCreditsException e) {
      return withFailure(new UpdateUserBalanceFailure());
    }
  }
}
