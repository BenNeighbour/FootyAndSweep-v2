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

package com.footyandsweep.apigatewayservice.oauth2;

import org.apache.commons.lang.SerializationUtils;
import org.springframework.http.ResponseCookie;
import org.springframework.security.oauth2.client.web.server.ServerAuthorizationRequestRepository;
import org.springframework.security.oauth2.client.web.server.WebSessionOAuth2ServerAuthorizationRequestRepository;
import org.springframework.security.oauth2.core.endpoint.OAuth2AuthorizationRequest;
import org.springframework.security.oauth2.core.endpoint.OAuth2ParameterNames;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebSession;
import reactor.core.publisher.Mono;

import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

@Component
public class CustomAuthorizationRequestRepository
    implements ServerAuthorizationRequestRepository<OAuth2AuthorizationRequest> {

  private static final String DEFAULT_AUTHORIZATION_REQUEST_ATTR_NAME =
      WebSessionOAuth2ServerAuthorizationRequestRepository.class.getName()
          + ".AUTHORIZATION_REQUEST";

  @Override
  public Mono<OAuth2AuthorizationRequest> loadAuthorizationRequest(ServerWebExchange exchange) {
    /* Get the authorization request from the query parameter */
    String state = getStateParameter(exchange);
    if (state == null) {
      return Mono.empty();
    }

    return getStateToAuthorizationRequest(exchange)
        .filter((stateToAuthorizationRequest) -> stateToAuthorizationRequest.containsKey(state))
        .map((stateToAuthorizationRequest) -> stateToAuthorizationRequest.get(state));
  }

  @Override
  public Mono<Void> saveAuthorizationRequest(
      OAuth2AuthorizationRequest authorizationRequest, ServerWebExchange exchange) {
      /* Put query params in cookie */
      exchange.getResponse().addCookie(ResponseCookie.from("params", exchange.getRequest().getURI().getQuery())
              .domain("footyandsweep-dev.com")
              .secure(false)
              .path("/")
              .build());

      return saveStateToAuthorizationRequest(exchange)
              .doOnNext(
                      (stateToAuthorizationRequest) ->
                              stateToAuthorizationRequest.put(
                                      authorizationRequest.getState(), authorizationRequest))
              .then();
  }

  @Override
  public Mono<OAuth2AuthorizationRequest> removeAuthorizationRequest(ServerWebExchange exchange) {
    String state = getStateParameter(exchange);

    if (state == null) {
      return Mono.empty();
    }

    return exchange
        .getSession()
        .map(WebSession::getAttributes)
        .handle(
            (sessionAttrs, sink) -> {
                Map<String, OAuth2AuthorizationRequest> stateToAuthRequest =
                        (Map<String, OAuth2AuthorizationRequest>)
                                sessionAttrs.get(OAuth2ParameterNames.STATE);

                if (stateToAuthRequest == null) {
                    sink.complete();
                    return;
                }

                OAuth2AuthorizationRequest removedValue = stateToAuthRequest.remove(state);
                if (stateToAuthRequest.isEmpty()) sessionAttrs.remove(OAuth2ParameterNames.STATE);

                    /* Overwrite the existing request */
                else if (removedValue != null)
                    sessionAttrs.put(OAuth2ParameterNames.STATE, stateToAuthRequest);
                if (removedValue == null) sink.complete();
                else sink.next(removedValue);
            });
  }

  private String getStateParameter(ServerWebExchange exchange) {
    return exchange.getRequest().getQueryParams().getFirst(OAuth2ParameterNames.STATE);
  }

  private Mono<Map<String, OAuth2AuthorizationRequest>> getStateToAuthorizationRequest(
      ServerWebExchange exchange) {
    return exchange
        .getSession()
        .map(WebSession::getAttributes)
        .flatMap(
            (sessionAttrs) ->
                Mono.justOrEmpty(
                    (Map<String, OAuth2AuthorizationRequest>)
                        sessionAttrs.get(DEFAULT_AUTHORIZATION_REQUEST_ATTR_NAME)));
  }

  private Mono<Map<String, OAuth2AuthorizationRequest>> saveStateToAuthorizationRequest(
      ServerWebExchange exchange) {
    return exchange
        .getSession()
        .map(WebSession::getAttributes)
        .doOnNext(
            (sessionAttrs) -> {
              Object stateToAuthzRequest = sessionAttrs.get(OAuth2ParameterNames.STATE);

              if (stateToAuthzRequest == null) {
                stateToAuthzRequest = new HashMap<>();
              }

              sessionAttrs.put(OAuth2ParameterNames.STATE, stateToAuthzRequest);
            })
        .flatMap(
            (sessionAttrs) ->
                Mono.justOrEmpty(
                    (Map<String, OAuth2AuthorizationRequest>)
                        sessionAttrs.get(OAuth2ParameterNames.STATE)));
  }
}
