/*
 *   Copyright 2021 FootyAndSweep
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

package com.footyandsweep.apigatewayservice.config;

import com.footyandsweep.apigatewayservice.oauth2.CustomAuthorizationRequestRepository;
import com.footyandsweep.apigatewayservice.oauth2.OAuth2FailureHandler;
import com.footyandsweep.apigatewayservice.oauth2.OAuth2SuccessHandler;
import com.footyandsweep.apigatewayservice.security.JwtAuthenticationRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.authentication.UserDetailsRepositoryReactiveAuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableReactiveMethodSecurity;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.SecurityWebFiltersOrder;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.security.web.server.context.NoOpServerSecurityContextRepository;
import org.springframework.security.web.server.savedrequest.NoOpServerRequestCache;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsConfigurationSource;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;
import reactor.core.publisher.Mono;

import java.util.Collections;

@Configuration
@EnableWebFluxSecurity
@EnableReactiveMethodSecurity
public class SecurityConfig {

  private final OAuth2SuccessHandler oAuth2SuccessHandler;
  private final OAuth2FailureHandler oAuth2FailureHandler;
  private final CustomAuthorizationRequestRepository authorizationRequestRepository;
  private final ReactiveUserDetailsService userDetailsService;

  public SecurityConfig(
      OAuth2SuccessHandler oAuth2SuccessHandler,
      OAuth2FailureHandler oAuth2FailureHandler,
      CustomAuthorizationRequestRepository authorizationRequestRepository,
      ReactiveUserDetailsService userDetailsService) {
    this.oAuth2SuccessHandler = oAuth2SuccessHandler;
    this.oAuth2FailureHandler = oAuth2FailureHandler;
    this.authorizationRequestRepository = authorizationRequestRepository;
    this.userDetailsService = userDetailsService;
  }

  @Bean
  public ReactiveAuthenticationManager authenticationManager() {
    UserDetailsRepositoryReactiveAuthenticationManager authenticationManager =
        new UserDetailsRepositoryReactiveAuthenticationManager(userDetailsService);

    authenticationManager.setPasswordEncoder(passwordEncoder());

    return authenticationManager;
  }

  @Bean
  public JwtAuthenticationRepository jwtAuthenticationRepository() {
    return new JwtAuthenticationRepository();
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Bean
  public SecurityWebFilterChain springWebFilterChain(ServerHttpSecurity http) {
    http.requestCache()
        .requestCache(NoOpServerRequestCache.getInstance())
        .and()
        .cors()
        .configurationSource(createCorsConfigSource())
        .and()
        .csrf()
        .disable()
        .securityContextRepository(NoOpServerSecurityContextRepository.getInstance())
        .authorizeExchange()
        .pathMatchers(HttpMethod.OPTIONS)
        .permitAll()
        .pathMatchers(
            "/",
            "/error",
            "/favicon.ico",
            "/*/*.png",
            "/*/*.gif",
            "/*/*.svg",
            "/*/*.jpg",
            "/*/*.html",
            "/*/*.css",
            "/*/*.js")
        .permitAll()
        .pathMatchers("/login/*", "/auth/*", "/oauth2/*")
        .permitAll()
        .anyExchange()
        .authenticated()
        .and()
        .oauth2Login()
        .authorizationRequestRepository(authorizationRequestRepository)
        .authenticationSuccessHandler(oAuth2SuccessHandler)
        .authenticationFailureHandler(oAuth2FailureHandler)
        .and()
        .formLogin()
        .disable()
        .httpBasic()
        .disable()
        .exceptionHandling()
        .authenticationEntryPoint(
            (exchange, e) -> {
              return Mono.fromRunnable(
                  () -> exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED));
            })
        .accessDeniedHandler(
            (exchange, e) -> {
              return Mono.fromRunnable(
                  () -> exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED));
            })
        .and()
        .oauth2Client();

    http.addFilterAfter(jwtAuthenticationRepository(), SecurityWebFiltersOrder.AUTHENTICATION);

    return http.build();
  }

  public CorsConfigurationSource createCorsConfigSource() {
    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
    CorsConfiguration config = new CorsConfiguration();

    config.addAllowedOrigin("http://www.footyandsweep-dev.com:3000");
    config.addAllowedMethod("OPTIONS");
    config.addAllowedMethod("GET");
    config.addAllowedMethod("PUT");
    config.addAllowedMethod("POST");
    config.addAllowedMethod("DELETE");

    config.setAllowedHeaders(Collections.singletonList("*"));

    config.setAllowCredentials(true);

    source.registerCorsConfiguration("/**", config);
    return source;
  }
}
