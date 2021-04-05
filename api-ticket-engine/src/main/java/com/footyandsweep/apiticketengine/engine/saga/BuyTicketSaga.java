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

import com.footyandsweep.apiticketengine.engine.TicketEngine;
import io.eventuate.tram.sagas.orchestration.SagaDefinition;
import io.eventuate.tram.sagas.simpledsl.SimpleSaga;
import org.springframework.stereotype.Component;

@Component
public class BuyTicketSaga implements SimpleSaga<BuyTicketSagaData> {

  private final TicketEngine ticketEngine;

  public BuyTicketSaga(TicketEngine ticketEngine) {
    this.ticketEngine = ticketEngine;
  }

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
                    .getSavedTickets()
                    .forEach(currentTicket -> ticketEngine.deleteTicket(currentTicket.getId())))
        .step()
        .invokeParticipant(ticketEngine::updateUserBalance)
        .build();
    // @formatter:on
  }
}
