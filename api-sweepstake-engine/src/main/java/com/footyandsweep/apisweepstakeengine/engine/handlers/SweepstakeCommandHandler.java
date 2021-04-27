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

package com.footyandsweep.apisweepstakeengine.engine.handlers;

import com.footyandsweep.apicommonlibrary.cqrs.sweepstake.update.SweepstakeStatusUpdated;
import com.footyandsweep.apicommonlibrary.cqrs.sweepstake.update.UpdateSweepstakeStatusCommand;
import com.footyandsweep.apicommonlibrary.cqrs.sweepstake.update.UpdateSweepstakeStatusFailure;
import com.footyandsweep.apicommonlibrary.exceptions.SweepstakeDoesNotExistException;
import com.footyandsweep.apisweepstakeengine.engine.SweepstakeEngine;
import io.eventuate.tram.commands.consumer.CommandHandlers;
import io.eventuate.tram.commands.consumer.CommandMessage;
import io.eventuate.tram.messaging.common.Message;
import io.eventuate.tram.sagas.participant.SagaCommandHandlersBuilder;
import lombok.RequiredArgsConstructor;

import static io.eventuate.tram.commands.consumer.CommandHandlerReplyBuilder.withFailure;
import static io.eventuate.tram.commands.consumer.CommandHandlerReplyBuilder.withSuccess;

@RequiredArgsConstructor
public class SweepstakeCommandHandler {

  private final SweepstakeEngine sweepstakeEngine;

  public CommandHandlers commandHandlerDefinitions() {
    return SagaCommandHandlersBuilder.fromChannel("sweepstake-engine-events")
        .onMessage(UpdateSweepstakeStatusCommand.class, this::updateSweepstakeStatus)
        .build();
  }

  private Message updateSweepstakeStatus(
      CommandMessage<UpdateSweepstakeStatusCommand> updateSweepstakeStatusCommand) {
    try {
      UpdateSweepstakeStatusCommand command = updateSweepstakeStatusCommand.getCommand();
      sweepstakeEngine.updateSweepstakeStatus(command.getSweepstakeId(), command.getStatus());

      return withSuccess(new SweepstakeStatusUpdated());
    } catch (SweepstakeDoesNotExistException e) {
      return withFailure(new UpdateSweepstakeStatusFailure());
    }
  }
}
