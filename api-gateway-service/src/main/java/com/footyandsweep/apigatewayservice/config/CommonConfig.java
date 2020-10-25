/*
 * Copyright 2020 FootyAndSweep
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.footyandsweep.apigatewayservice.config;

import com.footyandsweep.apigatewayservice.dao.UserDao;
import com.footyandsweep.apigatewayservice.events.UserEventSubscriber;
import com.footyandsweep.apigatewayservice.service.UserService;
import com.footyandsweep.apigatewayservice.service.UserServiceImpl;
import io.eventuate.tram.events.publisher.DomainEventPublisher;
import io.eventuate.tram.events.subscriber.DomainEventDispatcher;
import io.eventuate.tram.events.subscriber.DomainEventDispatcherFactory;
import io.eventuate.tram.spring.events.publisher.TramEventsPublisherConfiguration;
import io.eventuate.tram.spring.events.subscriber.TramEventSubscriberConfiguration;
import io.eventuate.tram.spring.jdbckafka.TramJdbcKafkaConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories
@EnableAutoConfiguration
@Import({TramJdbcKafkaConfiguration.class,
        TramEventsPublisherConfiguration.class,
        TramEventSubscriberConfiguration.class})
public class CommonConfig {

    @Bean
    public UserService userService(DomainEventPublisher domainEventPublisher, UserDao userDao) {
        return new UserServiceImpl(userDao, domainEventPublisher);
    }

    @Bean
    public UserEventSubscriber userEventSubscriber() {
        return new UserEventSubscriber();
    }

    @Bean
    public DomainEventDispatcher domainEventDispatcher(UserEventSubscriber userEventSubscriber, DomainEventDispatcherFactory domainEventDispatcherFactory) {
        return domainEventDispatcherFactory.make("customerServiceEvents", userEventSubscriber.domainEventHandlers());
    }
}
