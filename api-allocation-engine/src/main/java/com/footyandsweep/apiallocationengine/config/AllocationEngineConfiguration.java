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

package com.footyandsweep.apiallocationengine.config;

import com.footyandsweep.SweepstakeServiceGrpc;
import com.footyandsweep.apiallocationengine.dao.AllocationDao;
import com.footyandsweep.apiallocationengine.engine.AllocationEngine;
import com.footyandsweep.apiallocationengine.engine.AllocationEngineImpl;
import com.footyandsweep.apiallocationengine.grpc.client.AllocationClientGrpc;
import io.eventuate.tram.sagas.spring.orchestration.SagaOrchestratorConfiguration;
import io.eventuate.tram.spring.optimisticlocking.OptimisticLockingDecoratorConfiguration;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.client.RestTemplate;

@Configuration
@EnableAutoConfiguration
@Import({SagaOrchestratorConfiguration.class, OptimisticLockingDecoratorConfiguration.class})
public class AllocationEngineConfiguration {

  @Bean
  public SweepstakeServiceGrpc.SweepstakeServiceBlockingStub sweepstakeEngineChannel() {
    ManagedChannel channel = ManagedChannelBuilder.forAddress("api-sweepstake-engine", 9090)
            .usePlaintext()
            .build();

    return SweepstakeServiceGrpc.newBlockingStub(channel);
  }

  @Bean
  public AllocationEngine allocationEngine(AllocationDao allocationDao, RestTemplate restTemplate, AllocationClientGrpc allocationClientGrpc) {
    return new AllocationEngineImpl(allocationDao, restTemplate, allocationClientGrpc);
  }
}
