/*
 *   Copyright 2020 FootyAndSweep
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

package com.footyandsweep.apiresultengine.config;

import com.footyandsweep.apiresultengine.dao.ResultDao;
import com.footyandsweep.apiresultengine.engine.ResultEngine;
import com.footyandsweep.apiresultengine.engine.ResultEngineImpl;
import com.footyandsweep.apiresultengine.events.ResultEventSubscriber;
import io.eventuate.tram.events.publisher.DomainEventPublisher;
import io.eventuate.tram.events.subscriber.DomainEventDispatcher;
import io.eventuate.tram.events.subscriber.DomainEventDispatcherFactory;
import io.eventuate.tram.spring.events.publisher.TramEventsPublisherConfiguration;
import io.eventuate.tram.spring.events.subscriber.TramEventSubscriberConfiguration;
import io.eventuate.tram.spring.jdbckafka.TramJdbcKafkaConfiguration;
import io.eventuate.tram.spring.optimisticlocking.OptimisticLockingDecoratorConfiguration;
import io.eventuate.tram.viewsupport.rebuild.SnapshotConfiguration;
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
        TramEventSubscriberConfiguration.class,
        OptimisticLockingDecoratorConfiguration.class,
        SnapshotConfiguration.class})
public class CommonConfig {

    @Bean
    public ResultEngine resultEngine(DomainEventPublisher domainEventPublisher, ResultDao resultDao) {
        return new ResultEngineImpl(resultDao, domainEventPublisher);
    }

    @Bean
    public ResultEventSubscriber resultEventSubscriber() {
        return new ResultEventSubscriber();
    }

    @Bean
    public DomainEventDispatcher domainEventDispatcher(ResultEventSubscriber resultEventSubscriber, DomainEventDispatcherFactory domainEventDispatcherFactory) {
        return domainEventDispatcherFactory.make("resultEngineEvents", resultEventSubscriber.domainEventHandlers());
    }
}