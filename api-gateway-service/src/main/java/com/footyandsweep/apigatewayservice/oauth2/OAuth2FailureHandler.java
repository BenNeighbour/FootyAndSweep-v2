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

import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.server.WebFilterExchange;
import org.springframework.security.web.server.authentication.ServerAuthenticationFailureHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.net.URLEncoder;

@Component
public class OAuth2FailureHandler implements ServerAuthenticationFailureHandler {

  @Override
  public Mono<Void> onAuthenticationFailure(
      WebFilterExchange webFilterExchange, AuthenticationException exception) {
    /* Make the target url have the error parameter on it */
    String targetUrl;

    /* If this is an OAuth 2.0 request, do the following */
    if (webFilterExchange.getExchange().getRequest().getPath().toString().contains("oauth")) {
      try {
        targetUrl =
            UriComponentsBuilder.fromUriString("http://www.footyandsweep-dev.com:3000/oauth/login")
                .queryParam(
                    "error",
                    URLEncoder.encode(exception.getLocalizedMessage(), "UTF-8").replace("+", "%20"))
                .build()
                .toUriString();
      } catch (Exception e) {
        /* If something went wrong, just make the error code */
        targetUrl =
            UriComponentsBuilder.fromUriString("http://www.footyandsweep-dev.com:3000/oauth/login")
                .queryParam("error", "Hmmm...%20Somethings%20not%20right...")
                .build()
                .toUriString();
      }

      /* Redirect to the redirect uri */
      webFilterExchange.getExchange().getResponse().setStatusCode(HttpStatus.TEMPORARY_REDIRECT);
      webFilterExchange.getExchange().getResponse().getHeaders().setLocation(URI.create(targetUrl));
    } else {
      /* Otherwise, just return Unauthorized */
      webFilterExchange.getExchange().getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
    }

    return webFilterExchange.getExchange().getResponse().setComplete();
  }
}
