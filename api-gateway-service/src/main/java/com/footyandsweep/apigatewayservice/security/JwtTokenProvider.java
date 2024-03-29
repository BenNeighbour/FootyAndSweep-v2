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

import com.footyandsweep.apigatewayservice.config.AppProperties;
import com.footyandsweep.apigatewayservice.dao.UserDao;
import com.footyandsweep.apigatewayservice.model.UserPrincipal;
import com.google.gson.Gson;
import io.jsonwebtoken.*;
import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.stereotype.Service;

import java.lang.reflect.InvocationTargetException;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;

@Service
public class JwtTokenProvider {

  private static final Logger logger = LoggerFactory.getLogger(JwtTokenProvider.class);

  private final UserDao userDao;
  private AppProperties appProperties;

  public JwtTokenProvider(UserDao userDao, AppProperties appProperties) {
    this.userDao = userDao;
    this.appProperties = appProperties;
  }

  public String createToken(Authentication authentication) {
    try {
      UserPrincipal userPrincipal = new UserPrincipal();
      BeanUtils.copyProperties(userPrincipal, authentication.getPrincipal());
      BeanUtils.copyProperties(userPrincipal, userDao.findUserByEmail(userPrincipal.getAttributes().get("email").toString()));

      Date now = new Date();
      Date expiryDate = new Date(now.getTime() + appProperties.getAuth().getTokenExpirationMsec());

      return Jwts.builder()
          .setIssuer("footyandsweep")
          .claim("metadata", userPrincipal.getAttributes())
          .setId(userPrincipal.getId())
          .setIssuedAt(new Date())
          .setExpiration(expiryDate)
          .signWith(SignatureAlgorithm.HS256, appProperties.getAuth().getTokenSecret())
          .compact();
    } catch (IllegalAccessException | InvocationTargetException e) {
      e.printStackTrace();
    }

    return "";
  }

  public String getUserIdFromToken(String token) {
    Claims claims = Jwts.parser()
            .setSigningKey(appProperties.getAuth().getTokenSecret())
            .parseClaimsJws(token)
            .getBody();

    return claims.getId();
  }

  public Authentication getAuthentication(String token) {
    Claims claims =
        Jwts.parser()
            .setSigningKey(appProperties.getAuth().getTokenSecret())
            .parseClaimsJws(token)
            .getBody();

    Collection<? extends GrantedAuthority> authorities =
        claims == null
            ? AuthorityUtils.NO_AUTHORITIES
            : AuthorityUtils.commaSeparatedStringToAuthorityList(claims.toString());

    UserPrincipal principal = new UserPrincipal(claims.getSubject(), "", "", authorities);

    return new UsernamePasswordAuthenticationToken(principal, token, authorities);
  }

  public boolean validateToken(String authToken) {
    try {
      Jwts.parser()
          .setSigningKey(appProperties.getAuth().getTokenSecret())
          .parseClaimsJws(authToken);

      return true;
    } catch (SignatureException ex) {
      logger.error("Invalid JWT signature");
    } catch (MalformedJwtException ex) {
      logger.error("Invalid JWT token");
    } catch (ExpiredJwtException ex) {
      logger.error("Expired JWT token");
    } catch (UnsupportedJwtException ex) {
      logger.error("Unsupported JWT token");
    } catch (IllegalArgumentException ex) {
      logger.error("JWT claims string is empty.");
    }
    return false;
  }
}
