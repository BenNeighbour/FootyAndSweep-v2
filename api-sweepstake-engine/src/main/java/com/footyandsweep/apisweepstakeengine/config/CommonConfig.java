package com.footyandsweep.apisweepstakeengine.config;

import com.footyandsweep.apisweepstakeengine.dao.SweepstakeDao;
import com.footyandsweep.apisweepstakeengine.engine.SweepstakeEngine;
import com.footyandsweep.apisweepstakeengine.engine.SweepstakeEngineImpl;
import com.footyandsweep.apisweepstakeengine.events.SweepstakeEventSubscriber;
import io.eventuate.tram.events.publisher.DomainEventPublisher;
import io.eventuate.tram.events.subscriber.DomainEventDispatcher;
import io.eventuate.tram.events.subscriber.DomainEventDispatcherFactory;
import io.eventuate.tram.spring.events.publisher.TramEventsPublisherConfiguration;
import io.eventuate.tram.spring.events.subscriber.TramEventSubscriberConfiguration;
import io.eventuate.tram.spring.jdbckafka.TramJdbcKafkaConfiguration;
import io.eventuate.tram.spring.optimisticlocking.OptimisticLockingDecoratorConfiguration;
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
        OptimisticLockingDecoratorConfiguration.class})
public class CommonConfig {

    @Bean
    public SweepstakeEngine sweepstakeEngine(DomainEventPublisher domainEventPublisher, SweepstakeDao sweepstakeDao) {
        return new SweepstakeEngineImpl(sweepstakeDao, domainEventPublisher);
    }

    @Bean
    public SweepstakeEventSubscriber sweepstakeEventSubscriber() {
        return new SweepstakeEventSubscriber();
    }

    @Bean
    public DomainEventDispatcher domainEventDispatcher(SweepstakeEventSubscriber sweepstakeEventSubscriber, DomainEventDispatcherFactory domainEventDispatcherFactory) {
        return domainEventDispatcherFactory.make("customerServiceEvents", sweepstakeEventSubscriber.domainEventHandlers());
    }
}
