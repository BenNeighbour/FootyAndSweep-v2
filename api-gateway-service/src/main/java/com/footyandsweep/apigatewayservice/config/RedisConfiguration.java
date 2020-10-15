package com.footyandsweep.apigatewayservice.config;

import io.eventuate.tram.jdbcredis.TramJdbcRedisConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({TramJdbcRedisConfiguration.class})
public class RedisConfiguration {
}
