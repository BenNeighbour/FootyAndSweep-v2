package com.footyandsweep.apisweepstakeengine.config;

import com.footyandsweep.apisweepstakeengine.engine.SweepstakeEngine;
import com.footyandsweep.apisweepstakeengine.engine.SweepstakeEngineImpl;
import com.footyandsweep.apisweepstakeengine.events.SweepstakeEventSubscriber;
import io.eventuate.tram.events.subscriber.DomainEventDispatcher;
import io.eventuate.tram.events.subscriber.DomainEventDispatcherFactory;
import io.eventuate.tram.spring.events.publisher.TramEventsPublisherConfiguration;
import io.eventuate.tram.spring.events.subscriber.TramEventSubscriberConfiguration;
import io.eventuate.tram.spring.messaging.producer.jdbc.TramMessageProducerJdbcConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories
@EnableAutoConfiguration
@Import({TramEventsPublisherConfiguration.class,
        TramMessageProducerJdbcConfiguration.class,
        TramEventSubscriberConfiguration.class})
public class CommonConfig {

    @Bean
    public SweepstakeEngine sweepstakeEngine() {
        return new SweepstakeEngineImpl();
    }

    @Bean
    public SweepstakeEventSubscriber sweepstakeEventSubscriber() {
        return new SweepstakeEventSubscriber();
    }

    @Bean
    public DomainEventDispatcher domainEventDispatcher(SweepstakeEventSubscriber eventSubscriber,
                                                       DomainEventDispatcherFactory eventDispatcher) {
        return eventDispatcher.make("sweepstakeEngineEventSubscriber",
                eventSubscriber.domainEventHandlers());
    }

}
