package com.footyandsweep.apiallocationengine;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.core.Ordered;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.filter.ForwardedHeaderFilter;

@SpringBootApplication
public class ApiAllocationEngineApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiAllocationEngineApplication.class, args);
	}

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

}
