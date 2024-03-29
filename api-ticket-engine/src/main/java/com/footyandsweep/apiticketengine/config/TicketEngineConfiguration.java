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

package com.footyandsweep.apiticketengine.config;

import com.footyandsweep.SweepstakeServiceGrpc;
import com.footyandsweep.apiticketengine.dao.TicketDao;
import com.footyandsweep.apiticketengine.engine.TicketEngine;
import com.footyandsweep.apiticketengine.engine.TicketEngineImpl;
import com.footyandsweep.apiticketengine.engine.handlers.TicketCommandHandler;
import com.footyandsweep.apiticketengine.grpc.client.TicketClientGrpc;
import io.eventuate.tram.commands.consumer.CommandDispatcher;
import io.eventuate.tram.sagas.participant.SagaCommandDispatcherFactory;
import io.eventuate.tram.sagas.spring.orchestration.SagaOrchestratorConfiguration;
import io.eventuate.tram.sagas.spring.participant.SagaParticipantConfiguration;
import io.eventuate.tram.spring.optimisticlocking.OptimisticLockingDecoratorConfiguration;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@EnableAutoConfiguration
@Import({
  SagaOrchestratorConfiguration.class,
  OptimisticLockingDecoratorConfiguration.class,
  SagaParticipantConfiguration.class
})
public class TicketEngineConfiguration {

  @Bean
  public SweepstakeServiceGrpc.SweepstakeServiceBlockingStub sweepstakeServiceChannel() {
    ManagedChannel channel =
        ManagedChannelBuilder.forAddress("api-sweepstake-engine", 9090).usePlaintext().build();

    return SweepstakeServiceGrpc.newBlockingStub(channel);
  }

  @Bean
  public TicketEngine ticketEngine(TicketDao ticketDao, TicketClientGrpc ticketClientGrpc) {
    return new TicketEngineImpl(ticketDao, ticketClientGrpc);
  }

  @Bean
  public TicketCommandHandler ticketCommandHandler(TicketEngine ticketEngine) {
    return new TicketCommandHandler(ticketEngine);
  }

  @Bean
  public CommandDispatcher consumerCommandDispatcher(
      TicketCommandHandler target, SagaCommandDispatcherFactory sagaCommandDispatcherFactory) {
    return sagaCommandDispatcherFactory.make(
        "ticket-engine-events-dispatcher", target.commandHandlerDefinitions());
  }
}
