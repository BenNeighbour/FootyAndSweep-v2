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

package com.footyandsweep.apisweepstakeengine.config;

import com.footyandsweep.AllocationServiceGrpc;
import com.footyandsweep.TicketServiceGrpc;
import com.footyandsweep.apisweepstakeengine.dao.ParticipantIdDao;
import com.footyandsweep.apisweepstakeengine.dao.SweepstakeDao;
import com.footyandsweep.apisweepstakeengine.engine.SweepstakeEngine;
import com.footyandsweep.apisweepstakeengine.engine.SweepstakeEngineImpl;
import com.footyandsweep.apisweepstakeengine.engine.handlers.SweepstakeCommandHandler;
import com.footyandsweep.apisweepstakeengine.grpc.client.SweepstakeClientGrpc;
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
public class SweepstakeEngineConfiguration {

  @Bean
  public AllocationServiceGrpc.AllocationServiceBlockingStub allocationEngineChannel() {
    ManagedChannel channel =
        ManagedChannelBuilder.forAddress("api-allocation-engine", 9090).usePlaintext().build();

    return AllocationServiceGrpc.newBlockingStub(channel);
  }

  @Bean
  public TicketServiceGrpc.TicketServiceBlockingStub ticketServiceChannel() {
    ManagedChannel channel =
            ManagedChannelBuilder.forAddress("api-ticket-engine", 9090).usePlaintext().build();

    return TicketServiceGrpc.newBlockingStub(channel);
  }

  @Bean
  public SweepstakeEngine sweepstakeEngine(
          SweepstakeDao sweepstakeDao, ParticipantIdDao participantIdDao) {
    return new SweepstakeEngineImpl(sweepstakeDao, participantIdDao);
  }

  @Bean
  public SweepstakeCommandHandler sweepstakeCommandHandler(SweepstakeEngine sweepstakeEngine) {
    return new SweepstakeCommandHandler(sweepstakeEngine);
  }

  @Bean
  public CommandDispatcher consumerCommandDispatcher(
      SweepstakeCommandHandler target, SagaCommandDispatcherFactory sagaCommandDispatcherFactory) {
    return sagaCommandDispatcherFactory.make(
        "sweepstake-engine-events-dispatcher", target.commandHandlerDefinitions());
  }
}
