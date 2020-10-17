package com.footyandsweep.apigatewayservice;

import com.footyandsweep.apigatewayservice.config.RedisConfiguration;
import com.footyandsweep.apigatewayservice.config.WebConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
@Import({WebConfiguration.class, RedisConfiguration.class})
public class ApiGatewayServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiGatewayServiceApplication.class, args);
	}

}
