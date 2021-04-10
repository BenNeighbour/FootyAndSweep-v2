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

package com.footyandsweep.apigatewayservice.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpCookie;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.ReactiveSecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Optional;

@Component
public class JwtAuthenticationRepository implements WebFilter {

  private static final Logger logger = LoggerFactory.getLogger(JwtAuthenticationRepository.class);

  @Autowired private JwtTokenProvider tokenProvider;

  @Override
  public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
    try {
      /* Get the token from the token cookie */
      Optional<String> token = getTokenFromCookie(exchange.getRequest());

      if (token.isPresent() && tokenProvider.validateToken(token.get())) {
        Authentication authentication = this.tokenProvider.getAuthentication(token.get());
        return chain.filter(exchange)
                .subscriberContext(ReactiveSecurityContextHolder.withAuthentication(authentication));
      }
    } catch (Exception e) {
      e.printStackTrace();
    }

    /* Do the filter */
    return chain.filter(exchange);
  }

  private Optional<String> getTokenFromCookie(ServerHttpRequest request) {
    /* Find the cookie with the name of token */
    List<HttpCookie> cookie = request.getCookies().get("token");

    if (cookie == null || cookie.isEmpty() || cookie.get(0) == null) return Optional.empty();

    return Optional.of(cookie.get(0).getValue());
  }
}
