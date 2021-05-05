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
import com.footyandsweep.apigatewayservice.exception.OAuth2AuthenticationProcessingException;
import com.footyandsweep.apigatewayservice.model.AuthProvider;
import com.footyandsweep.apigatewayservice.model.User;
import com.footyandsweep.apigatewayservice.model.UserPrincipal;
import com.footyandsweep.apigatewayservice.oauth2.user.GoogleOAuth2UserInfo;
import com.footyandsweep.apigatewayservice.oauth2.user.OAuth2UserInfo;
import com.footyandsweep.apigatewayservice.oauth2.user.OAuth2UserInfoFactory;
import com.footyandsweep.apigatewayservice.security.JwtTokenProvider;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.protobuf.util.JsonFormat;
import lombok.RequiredArgsConstructor;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.server.WebFilterExchange;
import org.springframework.security.web.server.authentication.ServerAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Mono;

import java.lang.reflect.InvocationTargetException;
import java.net.URI;
import java.util.Arrays;

@Component
@RequiredArgsConstructor
public class OAuth2SuccessHandler implements ServerAuthenticationSuccessHandler {

  private static final Gson gson =
      new GsonBuilder().serializeNulls().generateNonExecutableJson().create();
  private static final JsonFormat.Parser jsonParser = JsonFormat.parser().ignoringUnknownFields();
  private static final JsonFormat.Printer jsonPrinter =
      JsonFormat.printer().includingDefaultValueFields();

  private final JwtTokenProvider tokenProvider;
  private final UserDao userDao;

  @Override
  public Mono<Void> onAuthenticationSuccess(
      WebFilterExchange webFilterExchange, Authentication authentication) {
    String targetUrl = "http://www.footyandsweep-dev.com:3000/oauth/login";

    if (webFilterExchange.getExchange().getResponse().isCommitted()) return Mono.empty();

    boolean isSigningUp = false;

    /* Access the params cookie here */
    String paramsString =
        webFilterExchange.getExchange().getRequest().getCookies().get("params").get(0).getValue();

    /* Split the params string */
    for (String s : paramsString.split("\\?")) {
      /* Get the signup param (if there is one) */
      if (s.contains("signup")) {
        /* Get the param value */
        if (Arrays.asList(s.split("=")).get(1).equals("true")) isSigningUp = true;
      }
    }

    try {
      UserPrincipal userPrincipal = new UserPrincipal();
      BeanUtils.copyProperties(userPrincipal, authentication.getPrincipal());

      /* Check that the user exists */
      User user = userDao.findUserByEmail(userPrincipal.getAttributes().get("email").toString());

      /* Get the registration id */
      String registrationId =
          webFilterExchange.getExchange().getRequest().getPath().toString().split("/")[4];

      /* If the user does not exist, they need to sign up first */
      if (user == null) throw new OAuth2AuthenticationProcessingException("You must sign up!");

      /* Check the signup query param  */
      if (!isSigningUp) {

        updateExistingUser(
            user,
            OAuth2UserInfoFactory.getOAuth2UserInfo(registrationId, userPrincipal.getAttributes()));
      } else {
        /* The user is signing up */
        user = registerNewUser(OAuth2UserInfoFactory.getOAuth2UserInfo(registrationId, userPrincipal.getAttributes()));
      }

      /* Set the cookie */
      String token = tokenProvider.createToken(authentication);
      webFilterExchange
          .getExchange()
          .getResponse()
          .addCookie(
              ResponseCookie.from("token", token)
                  .domain("footyandsweep-dev.com")
                  .maxAge(36000)
                  .secure(false)
                  .httpOnly(true)
                  .path("/")
                  .build());

      targetUrl =
          UriComponentsBuilder.fromUriString(targetUrl)
              .queryParam("user_id", user.getId())
              .build()
              .toUriString();

    } catch (IllegalAccessException | InvocationTargetException e) {
      e.printStackTrace();
    }

    /* Redirect to the redirect uri */
    webFilterExchange.getExchange().getResponse().setStatusCode(HttpStatus.TEMPORARY_REDIRECT);
    webFilterExchange.getExchange().getResponse().getHeaders().setLocation(URI.create(targetUrl));
    return webFilterExchange.getExchange().getResponse().setComplete();
  }

  private User registerNewUser(OAuth2UserInfo userInfo) {
    User user = new User();

    user.setProvider(
        userInfo instanceof GoogleOAuth2UserInfo
            ? AuthProvider.valueOf("google")
            : AuthProvider.valueOf("facebook"));
    user.setProviderId(userInfo.getId());
    user.setUsername(userInfo.getName());
    user.setEmail(userInfo.getEmail());
    user.setProfilePicture(userInfo.getImageUrl());

    return userDao.save(user);
  }

  private User updateExistingUser(User existingUser, OAuth2UserInfo oAuth2UserInfo) {
    existingUser.setUsername(oAuth2UserInfo.getName());
    existingUser.setProfilePicture(oAuth2UserInfo.getImageUrl());

    return userDao.saveAndFlush(existingUser);
  }
}
