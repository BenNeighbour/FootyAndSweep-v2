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

package com.footyandsweep.apiauthenticationservice.config;

import com.footyandsweep.apiauthenticationservice.dao.SweepstakeIdDao;
import com.footyandsweep.apiauthenticationservice.dao.UserDao;
import com.footyandsweep.apiauthenticationservice.service.UserService;
import com.footyandsweep.apiauthenticationservice.service.UserServiceImpl;
import com.footyandsweep.apiauthenticationservice.service.handlers.UserCommandHandler;
import io.eventuate.tram.commands.consumer.CommandDispatcher;
import io.eventuate.tram.sagas.participant.SagaCommandDispatcherFactory;
import io.eventuate.tram.sagas.spring.participant.SagaParticipantConfiguration;
import io.eventuate.tram.spring.optimisticlocking.OptimisticLockingDecoratorConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({SagaParticipantConfiguration.class, OptimisticLockingDecoratorConfiguration.class})
@EnableAutoConfiguration
public class UserServiceConfiguration {

  @Bean
  public UserService userService(UserDao userDao, SweepstakeIdDao sweepstakeIdDao) {
    return new UserServiceImpl(userDao, sweepstakeIdDao);
  }

  @Bean
  public UserCommandHandler userCommandHandler(UserService userService) {
    return new UserCommandHandler(userService);
  }

  @Bean
  public CommandDispatcher consumerCommandDispatcher(
      UserCommandHandler target, SagaCommandDispatcherFactory sagaCommandDispatcherFactory) {
    return sagaCommandDispatcherFactory.make(
        "user-service-events-dispatcher", target.commandHandlerDefinitions());
  }
}
