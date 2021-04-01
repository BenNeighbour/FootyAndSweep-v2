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

package com.footyandsweep.apigatewayservice;

import org.springframework.cloud.gateway.filter.factory.GatewayFilterFactory;
import org.springframework.http.HttpCookie;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.server.ServerWebExchange;

import java.util.Optional;

@Component
public class GatewayFilter implements GatewayFilterFactory<com.footyandsweep.apigatewayservice.GatewayFilter.Config> {

    private static final WebClient webClient = WebClient.create();

    @Override
    public org.springframework.cloud.gateway.filter.GatewayFilter apply(Config config) {
        return (exchange, chain) -> {
//            URI requestUrl = exchange.getRequiredAttribute(GATEWAY_ORIGINAL_REQUEST_URL_ATTR);
            String scheme = exchange.getRequest().getURI().getScheme();

            if (!"ws".equals(scheme) && !"wss".equals(scheme)) return chain.filter(exchange);

            else {
//                String wsScheme = "ws".equals(scheme) ? "http" : "https";
//                URI wsRequestUrl = UriComponentsBuilder.fromUri(requestUrl).scheme(wsScheme).build().toUri();

                /* Validate authentication here */
                boolean authResult = validateAuth(exchange);

                /* Set the response status to Unauthorized */
                if (!authResult) exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);

//                exchange.getAttributes().put(GATEWAY_ORIGINAL_REQUEST_URL_ATTR, wsRequestUrl);

                return chain.filter(exchange);
            }
        };
    }

    @Override
    public Class<Config> getConfigClass() {
        return Config.class;
    }

    @Override
    public Config newConfig() {
        return new Config("GatewayFilter");
    }

    public static class Config {

        private String name;

        public Config(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    public boolean validateAuth(ServerWebExchange exchange) {
        try {
            /* Obtain cookies from request */
            Optional<HttpCookie> token =
                    exchange.getRequest().getCookies().get("X-AUTH-TOKEN").stream().findFirst();
            if (!token.isPresent()) {
                /* Throw error */
                throw new Exception();
            }

            webClient
                    .get()
                    .uri("http://api-authentication-service:8080/auth/amIAuthenticated")
                    .cookie("X-AUTH-TOKEN", token.get().getValue())
                    .exchange()
                    .map(
                            clientResponse -> {
                                return exchange.getResponse().setStatusCode(clientResponse.statusCode());
                            });

            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
