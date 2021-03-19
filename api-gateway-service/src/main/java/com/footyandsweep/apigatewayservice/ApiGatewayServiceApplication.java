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

import io.netty.handler.codec.http.HttpResponseStatus;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.buffer.NettyDataBufferFactory;
import org.springframework.http.HttpCookie;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.web.reactive.config.EnableWebFlux;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.socket.HandshakeInfo;
import org.springframework.web.reactive.socket.WebSocketHandler;
import org.springframework.web.reactive.socket.adapter.ReactorNettyWebSocketSession;
import org.springframework.web.reactive.socket.server.WebSocketService;
import org.springframework.web.reactive.socket.server.support.HandshakeWebSocketService;
import org.springframework.web.reactive.socket.server.upgrade.ReactorNettyRequestUpgradeStrategy;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;
import reactor.netty.http.server.HttpServerResponse;

import java.util.Optional;
import java.util.function.Supplier;

@EnableWebFlux
@SpringBootApplication
@EnableDiscoveryClient
public class ApiGatewayServiceApplication {

  public static void main(String[] args) {
    SpringApplication.run(ApiGatewayServiceApplication.class, args);
  }

  @Bean
  public WebSocketService webSocketService(UpgradeStrategy upgradeStrategy) {
    return new HandshakeWebSocketService(upgradeStrategy);
  }

  public static class UpgradeStrategy extends ReactorNettyRequestUpgradeStrategy {

    private static final WebClient webClient = WebClient.create();

    @Override
    public Mono<Void> upgrade(
        ServerWebExchange exchange,
        WebSocketHandler handler,
        String subProtocol,
        Supplier<HandshakeInfo> handshakeInfoFactory) {
      ServerHttpResponse response = exchange.getResponse();
      HttpServerResponse reactorResponse = (HttpServerResponse) response;
      HandshakeInfo handshakeInfo = handshakeInfoFactory.get();
      NettyDataBufferFactory bufferFactory = (NettyDataBufferFactory) response.bufferFactory();

      boolean authResult = validateAuth(exchange);

      if (!authResult)
        return Mono.just(reactorResponse.status(HttpResponseStatus.UNAUTHORIZED))
            .flatMap(HttpServerResponse::send);
      else
        return reactorResponse.sendWebsocket(
            subProtocol,
            //              this.maxFramePayloadLength,
            (in, out) -> {
              ReactorNettyWebSocketSession session =
                  new ReactorNettyWebSocketSession(in, out, handshakeInfo, bufferFactory);
              //                        this.maxFramePayloadLength);
              return handler.handle(session);
            });
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
}
