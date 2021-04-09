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

import com.footyandsweep.apigatewayservice.service.UserDetailsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpCookie;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

import java.util.Optional;

@Component
public class TokenAuthenticationFilter implements WebFilter {

    private static final Logger logger = LoggerFactory.getLogger(TokenAuthenticationFilter.class);

    @Autowired private JwtTokenProvider tokenProvider;
    @Autowired private UserDetailsService customUserDetailsService;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
        try {
            /* Get the token from the token cookie */
            Optional<String> token = getTokenFromCookie(exchange.getRequest());

            if (token.isPresent() && tokenProvider.validateToken(token.get())) {
                /* Get the user id */
                String userId = tokenProvider.getUserIdFromToken(token.get());

                /* Load the user itself */
                UserDetails userDetails = customUserDetailsService.loadUserById(userId);

                /* Create a token from those details */
                UsernamePasswordAuthenticationToken authentication =
                        new UsernamePasswordAuthenticationToken(
                                userDetails, null, userDetails.getAuthorities());

                SecurityContextHolder.getContext().setAuthentication(authentication);
            }

        } catch (Exception ex) {
            logger.error("Could not set user authentication in security context", ex);
        }

        /* Do the filter */
        return chain.filter(exchange);
    }

    private Optional<String> getTokenFromCookie(ServerHttpRequest request) {
        /* Find the cookie with the name of token */
        HttpCookie cookie = request.getCookies().get("token").get(0);

        return Optional.of(cookie.getValue());
    }
}
