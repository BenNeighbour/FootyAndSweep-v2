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
import org.springframework.security.config.annotation.method.configuration.EnableReactiveMethodSecurity;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.SecurityWebFiltersOrder;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.security.web.server.context.NoOpServerSecurityContextRepository;
import org.springframework.security.web.server.savedrequest.NoOpServerRequestCache;
import reactor.core.publisher.Mono;

@Configuration
@EnableWebFluxSecurity
@EnableReactiveMethodSecurity
public class SecurityConfig {

  private final OAuth2SuccessHandler oAuth2SuccessHandler;
  private final OAuth2FailureHandler oAuth2FailureHandler;
  private final CustomAuthorizationRequestRepository authorizationRequestRepository;
  private final JwtAuthenticationRepository jwtAuthenticationRepository;

  public SecurityConfig(
      OAuth2SuccessHandler oAuth2SuccessHandler,
      OAuth2FailureHandler oAuth2FailureHandler,
      CustomAuthorizationRequestRepository authorizationRequestRepository,
      JwtAuthenticationRepository jwtAuthenticationRepository) {
    this.oAuth2SuccessHandler = oAuth2SuccessHandler;
    this.oAuth2FailureHandler = oAuth2FailureHandler;
    this.authorizationRequestRepository = authorizationRequestRepository;
    this.jwtAuthenticationRepository = jwtAuthenticationRepository;
  }

  @Bean
  public JwtAuthenticationRepository tokenAuthenticationFilter() {
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

    http.addFilterAfter(tokenAuthenticationFilter(), SecurityWebFiltersOrder.AUTHENTICATION);

    return http.build();
  }
}
