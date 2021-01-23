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

import com.footyandsweep.apisweepstakeengine.dao.ParticipantIdDao;
import com.footyandsweep.apisweepstakeengine.dao.SweepstakeDao;
import com.footyandsweep.apisweepstakeengine.engine.SweepstakeEngine;
import com.footyandsweep.apisweepstakeengine.engine.SweepstakeEngineImpl;
import io.eventuate.tram.sagas.spring.orchestration.SagaOrchestratorConfiguration;
import io.eventuate.tram.spring.optimisticlocking.OptimisticLockingDecoratorConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@EnableAutoConfiguration
@Import({SagaOrchestratorConfiguration.class, OptimisticLockingDecoratorConfiguration.class})
public class SweepstakeEngineConfiguration {

  @Bean
  public SweepstakeEngine sweepstakeEngine(
      SweepstakeDao sweepstakeDao,
      ParticipantIdDao participantIdDao) {
//      SagaInstanceFactory sagaInstanceFactory,
//      CreateSweepstakeSaga createSweepstakeSaga) {
    return new SweepstakeEngineImpl(sweepstakeDao, participantIdDao);
  }

//  @Bean
//  public CreateSweepstakeSaga createSweepstakeSaga() {
//    return new CreateSweepstakeSaga();
//  }

}
