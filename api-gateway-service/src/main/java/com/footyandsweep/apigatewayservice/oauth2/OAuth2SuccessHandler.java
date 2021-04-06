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

import com.footyandsweep.apigatewayservice.dao.UserDao;
import com.footyandsweep.apigatewayservice.security.JwtTokenProvider;
import okhttp3.Cookie;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.server.WebFilterExchange;
import org.springframework.security.web.server.authentication.ServerAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.net.URI;

@Component
public class OAuth2SuccessHandler implements ServerAuthenticationSuccessHandler {

  private final JwtTokenProvider tokenProvider;
  private final UserDao userDao;

  public OAuth2SuccessHandler(JwtTokenProvider tokenProvider, UserDao userDao) {
    this.tokenProvider = tokenProvider;
    this.userDao = userDao;
  }

  @Override
  public Mono<Void> onAuthenticationSuccess(
      WebFilterExchange webFilterExchange, Authentication authentication) {
    String targetUrl = "http://www.footyandsweep-dev.com:3000/oauth/login";

    if (webFilterExchange.getExchange().getResponse().isCommitted()) return Mono.empty();

    /* Set the cookie */
    webFilterExchange
        .getExchange()
        .getResponse()
        .addCookie(
            ResponseCookie.from("token", tokenProvider.createToken(authentication))
                .domain("footyandsweep-dev.com")
                .secure(false)
                .httpOnly(true)
                .path("/")
                .build());

    /* Redirect to the redirect uri */
    webFilterExchange.getExchange().getResponse().setStatusCode(HttpStatus.TEMPORARY_REDIRECT);
    webFilterExchange.getExchange().getResponse().getHeaders().setLocation(URI.create(targetUrl));
    return webFilterExchange.getExchange().getResponse().setComplete();
  }
}
