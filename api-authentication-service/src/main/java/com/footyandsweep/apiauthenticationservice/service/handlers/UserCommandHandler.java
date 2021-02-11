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

package com.footyandsweep.apiauthenticationservice.service.handlers;

import com.footyandsweep.apiauthenticationservice.service.UserService;
import com.footyandsweep.apicommonlibrary.cqrs.sweepstake.delete.AllSweepstakeRelationsDeleted;
import com.footyandsweep.apicommonlibrary.cqrs.sweepstake.delete.DeleteAllSweepstakeRelationsCommand;
import com.footyandsweep.apicommonlibrary.cqrs.user.*;
import com.footyandsweep.apicommonlibrary.exceptions.InsufficientCreditsException;
import com.footyandsweep.apicommonlibrary.exceptions.SomethingWentWrongException;
import com.footyandsweep.apicommonlibrary.exceptions.UserDoesNotExistException;
import io.eventuate.tram.commands.consumer.CommandHandlers;
import io.eventuate.tram.commands.consumer.CommandMessage;
import io.eventuate.tram.messaging.common.Message;
import io.eventuate.tram.sagas.participant.SagaCommandHandlersBuilder;

import static io.eventuate.tram.commands.consumer.CommandHandlerReplyBuilder.withFailure;
import static io.eventuate.tram.commands.consumer.CommandHandlerReplyBuilder.withSuccess;

public class UserCommandHandler {

  private final UserService userService;

  public UserCommandHandler(UserService userService) {
    this.userService = userService;
  }

  public CommandHandlers commandHandlerDefinitions() {
    return SagaCommandHandlersBuilder.fromChannel("user-service-events")
        .onMessage(LinkParticipantToSweepstakeCommand.class, this::linkParticipantToSweepstake)
        .onMessage(DeleteAllSweepstakeRelationsCommand.class, this::deleteAllSweepstakeRelations)
        .onMessage(LinkParticipantToSweepstakeCommand.class, this::linkParticipantToSweepstake)
        .onMessage(UpdateUserBalanceCommand.class, this::updateUserBalance)
        .build();
  }

  private Message updateUserBalance(CommandMessage<UpdateUserBalanceCommand> updateUserBalanceCommandCommand) {
    try {
      UpdateUserBalanceCommand command = updateUserBalanceCommandCommand.getCommand();
      userService.updateUserBalance(command.getUserId(), command.getAmountAdded());

      return withSuccess(new UserBalanceUpdated());
    } catch (UserDoesNotExistException | InsufficientCreditsException e) {
      return withFailure(new UpdateUserBalanceFailure());
    }
  }

  private Message deleteAllSweepstakeRelations(
      CommandMessage<DeleteAllSweepstakeRelationsCommand> deleteAllSweepstakeRelationsCommand) {
    DeleteAllSweepstakeRelationsCommand command = deleteAllSweepstakeRelationsCommand.getCommand();
    userService.deleteAllSweepstakeRelations(command.getSweepstakeId());

    return withSuccess(new AllSweepstakeRelationsDeleted());
  }

  private Message linkParticipantToSweepstake(
      CommandMessage<LinkParticipantToSweepstakeCommand> linkOwnerToSweepstakeCommand) {
    try {
      LinkParticipantToSweepstakeCommand command = linkOwnerToSweepstakeCommand.getCommand();
      userService.addOwnerToSweepstake(command.getSweepstakeId(), command.getParticipantId());

      return withSuccess(new ParticipantLinkedToSweepstake());
    } catch (UserDoesNotExistException e) {
      return withFailure(new ParticipantNotFound());
    } catch (SomethingWentWrongException e) {
      return withFailure(new LinkParticipantToSweepstakeFailure());
    }
  }
}
