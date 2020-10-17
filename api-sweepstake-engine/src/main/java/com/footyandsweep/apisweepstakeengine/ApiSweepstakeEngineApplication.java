package com.footyandsweep.apisweepstakeengine;

import com.footyandsweep.apisweepstakeengine.config.RedisConfiguration;
import com.footyandsweep.apisweepstakeengine.config.WebConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.core.Ordered;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.web.client.RestTemplate;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.filter.ForwardedHeaderFilter;

@SpringBootApplication
@EnableJpaRepositories
@Import({WebConfiguration.class, RedisConfiguration.class})
@EnableDiscoveryClient
public class ApiSweepstakeEngineApplication {

  @Bean
  @LoadBalanced
  public RestTemplate restTemplate() {
    return new RestTemplate();
  }

  @Bean
  public FilterRegistrationBean forwardHeadersFilterBean() {
    FilterRegistrationBean bean = new FilterRegistrationBean(new ForwardedHeaderFilter());
    bean.setOrder(Ordered.HIGHEST_PRECEDENCE);
    return bean;
  }

  public static void main(String[] args) {
    SpringApplication.run(ApiSweepstakeEngineApplication.class, args);
  }
}
