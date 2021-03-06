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

import com.footyandsweep.apigatewayservice.dao.UserDao;
import com.footyandsweep.apigatewayservice.exception.ResourceNotFoundException;
import com.footyandsweep.apigatewayservice.model.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CustomUserDetailsService implements UserDetailsService {

  private final UserDao userDao;

  public CustomUserDetailsService(UserDao userDao) {
    this.userDao = userDao;
  }

  @Override
  @Transactional
  public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
    User user =
        userDao
            .findUserByEmail(email)
            .orElseThrow(
                () -> new UsernameNotFoundException("User not found with email : " + email));

    return UserPrincipal.create(user);
  }

  @Transactional
  public UserDetails loadUserById(String id) {
    User user =
        userDao.findById(id).orElseThrow(() -> new ResourceNotFoundException("User", "id", id));

    return UserPrincipal.create(user);
  }
}
