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

package com.footyandsweep.apiticketengine.engine.saga;

import com.footyandsweep.apicommonlibrary.cqrs.SagaResponse;
import com.footyandsweep.apiticketengine.engine.TicketEngine;
import com.footyandsweep.apiticketengine.model.Ticket;
import io.eventuate.tram.sagas.orchestration.SagaDefinition;
import io.eventuate.tram.sagas.simpledsl.SimpleSaga;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class BuyTicketSaga implements SimpleSaga<BuyTicketSagaData> {

  private final TicketEngine ticketEngine;
  private final SimpMessagingTemplate messagingTemplate;

  @Override
  public SagaDefinition<BuyTicketSagaData> getSagaDefinition() {
    // @formatter:off
    return step()
        .invokeLocal(ticketEngine::getParentSweepstakeAndParticipant)
        .withCompensation(sagaData -> {})
        .step()
        .invokeLocal(ticketEngine::buyTickets)
        .withCompensation(
            sagaData ->
                sagaData
                    .getSavedTicketIds()
                    .forEach(ticketEngine::deleteTicket))
        .step()
        .invokeParticipant(ticketEngine::updateUserBalance)
        .build();
    // @formatter:on
  }

  @Override
  public void onSagaCompletedSuccessfully(String sagaId, BuyTicketSagaData sagaData) {
    SagaResponse<List<String>> ticketSagaComplete =
            new SagaResponse<>(
                    SagaResponse.Status.COMPLETED, "Ticket(s) Bought!", sagaData.getSavedTicketIds());

    messagingTemplate.convertAndSend("/ticket-topic/buy", ticketSagaComplete);
  }

  @Override
  public void onStarting(String sagaId, BuyTicketSagaData sagaData) {
    SagaResponse<BuyTicketSagaData> ticketSagaPending =
            new SagaResponse<>(
                    SagaResponse.Status.PENDING,
                    "Buying Tickets...",
                    sagaData);

    messagingTemplate.convertAndSend("/ticket-topic/buy", ticketSagaPending);
  }

  @Override
  public void onSagaRolledBack(String sagaId, BuyTicketSagaData sagaData) {
    SagaResponse<String> ticketSagaError =
            new SagaResponse<>(
                    SagaResponse.Status.FAILED,
                    "Buying Ticket Failed!",
                    "");

    messagingTemplate.convertAndSend("/ticket-topic/buy", ticketSagaError);
  }
}
