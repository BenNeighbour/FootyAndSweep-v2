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
import com.footyandsweep.apigatewayservice.oauth2.user.OAuth2UserInfo;
import com.footyandsweep.apigatewayservice.oauth2.user.OAuth2UserInfoFactory;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class OAuth2UserService extends DefaultOAuth2UserService {

  private final UserDao userDao;

  public OAuth2UserService(UserDao userDao) {
    this.userDao = userDao;
  }

  @Override
  public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
    OAuth2User oAuth2User = super.loadUser(userRequest);

    OAuth2UserInfo oAuth2UserInfo =
            OAuth2UserInfoFactory.getOAuth2UserInfo(
                    userRequest.getClientRegistration().getRegistrationId(), oAuth2User.getAttributes());

    if (StringUtils.isEmpty(oAuth2UserInfo.getEmail())) {
      throw new OAuth2AuthenticationProcessingException("Email not found from any providers");
    }

    /* Find the user by email */
    User user = userDao.findUserByEmail(oAuth2UserInfo.getEmail());

    try {
      if (user == null) throw new OAuth2AuthenticationProcessingException("You must sign up!");

      if (!user.getProvider().equals(AuthProvider.valueOf(userRequest.getClientRegistration().getRegistrationId()))) {
        throw new OAuth2AuthenticationProcessingException(
            "Woah! Looks like you're already signed up with your "
                + user.getProvider().getValue()
                + ". Please use your "
                + user.getProvider().getValue()
                + " account to login.");
      }

      user = registerNewUser(userRequest, oAuth2UserInfo);
    } catch (Exception e) {

    }

    return UserPrincipal.create(user, oAuth2User.getAttributes());
  }

  private User registerNewUser(OAuth2UserRequest oAuth2UserRequest, OAuth2UserInfo oAuth2UserInfo) {
    User user = new User();

    user.setProvider(
            AuthProvider.valueOf(oAuth2UserRequest.getClientRegistration().getRegistrationId()));
    user.setProviderId(oAuth2UserInfo.getId());
    user.setUsername(oAuth2UserInfo.getName());
    user.setEmail(oAuth2UserInfo.getEmail());
    user.setProfilePicture(oAuth2UserInfo.getImageUrl());

    return userDao.save(user);
  }
}
