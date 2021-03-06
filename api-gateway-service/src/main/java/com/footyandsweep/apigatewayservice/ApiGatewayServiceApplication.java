package com.footyandsweep.apigatewayservice;

import com.footyandsweep.apigatewayservice.config.AppProperties;
import com.footyandsweep.apigatewayservice.config.WebConfiguration;
import io.eventuate.tram.spring.consumer.kafka.EventuateTramKafkaMessageConsumerConfiguration;
import io.eventuate.tram.spring.messaging.producer.jdbc.TramMessageProducerJdbcConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.core.Ordered;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.filter.ForwardedHeaderFilter;

@EnableCaching
@SpringBootApplication
@EnableDiscoveryClient
@EnableScheduling
@EnableJpaRepositories("com.footyandsweep.apigatewayservice.dao")
@Import({
		WebConfiguration.class,
		TramMessageProducerJdbcConfiguration.class,
		EventuateTramKafkaMessageConsumerConfiguration.class
})
@EnableConfigurationProperties(AppProperties.class)
public class ApiGatewayServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiGatewayServiceApplication.class, args);
	}

	@Bean
	public FilterRegistrationBean forwardHeadersFilterBean() {
		FilterRegistrationBean bean = new FilterRegistrationBean(new ForwardedHeaderFilter());
		bean.setOrder(Ordered.HIGHEST_PRECEDENCE);
		return bean;
	}

}
