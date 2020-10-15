package com.footyandsweep.apigatewayservice.config;

import com.footyandsweep.apigatewayservice.events.UserEventSubscriber;
import com.footyandsweep.apigatewayservice.service.UserService;
import com.footyandsweep.apigatewayservice.service.UserServiceImpl;
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
    public UserService userService() {
        return new UserServiceImpl();
    }

    @Bean
    public UserEventSubscriber userEventSubscriber() {
        return new UserEventSubscriber();
    }

    @Bean
    public DomainEventDispatcher domainEventDispatcher(UserEventSubscriber eventSubscriber,
                                                       DomainEventDispatcherFactory eventDispatcher) {
        return eventDispatcher.make("sweepstakeEngineEventSubscriber",
                eventSubscriber.domainEventHandlers());
    }

}
