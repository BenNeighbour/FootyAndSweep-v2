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

package com.footyandsweep.apiauthenticationservice.controller;

import com.footyandsweep.apiauthenticationservice.dao.UserDao;
import com.footyandsweep.apiauthenticationservice.exception.ResourceNotFoundException;
import com.footyandsweep.apiauthenticationservice.model.AuthProvider;
import com.footyandsweep.apiauthenticationservice.model.User;
import com.footyandsweep.apiauthenticationservice.payload.LoginRequest;
import com.footyandsweep.apiauthenticationservice.payload.SignUpRequest;
import com.footyandsweep.apiauthenticationservice.security.CurrentUser;
import com.footyandsweep.apiauthenticationservice.security.TokenProvider;
import com.footyandsweep.apiauthenticationservice.security.UserPrincipal;
import com.footyandsweep.apiauthenticationservice.service.UserService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/com.footyandsweep.AuthenticationService")
public class AuthenticationController {

  private final TokenProvider tokenProvider;
  private final AuthenticationManager authenticationManager;
  private final PasswordEncoder passwordEncoder;
  private final UserService userService;
  private final UserDao userDao;

  public AuthenticationController(
      TokenProvider tokenProvider,
      AuthenticationManager authenticationManager,
      PasswordEncoder passwordEncoder,
      UserService userService,
      UserDao userDao) {
    this.tokenProvider = tokenProvider;
    this.authenticationManager = authenticationManager;
    this.passwordEncoder = passwordEncoder;
    this.userService = userService;
    this.userDao = userDao;
  }

  @GetMapping("/amIAuthenticated")
  @PreAuthorize("hasRole('USER')")
  public ResponseEntity<Object> amIAuthenticated() {
    return ResponseEntity.status(HttpStatus.OK).build();
  }

  @GetMapping("/user/me")
  @PreAuthorize("hasRole('USER')")
  public User getCurrentUser(@CurrentUser UserPrincipal userPrincipal) {
    return userDao
        .findById(userPrincipal.getId())
        .orElseThrow(() -> new ResourceNotFoundException("User", "id", userPrincipal.getId()));
  }

  @PostMapping("/login")
  public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
    Authentication authentication =
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginRequest.getEmail(), loginRequest.getPassword()));

    SecurityContextHolder.getContext().setAuthentication(authentication);

    String token = tokenProvider.createToken(authentication);
    HttpHeaders responseHeaders = new HttpHeaders();
    responseHeaders.add(HttpHeaders.SET_COOKIE, ResponseCookie.from("X-AUTH-TOKEN", token)
            .httpOnly(true)
            .path("/")
            .sameSite("Strict")
            .domain("footyandsweep-dev.com")
            .build().toString());

    return ResponseEntity.ok().headers(responseHeaders).build();
  }

  @PostMapping("/signup")
  public ResponseEntity<?> registerUser(@Valid @RequestBody SignUpRequest signUpRequest) {
    userService.checkSignUpRequestIsValid(signUpRequest);

    User user = new User();
    user.setUsername(signUpRequest.getUsername());
    user.setEmail(signUpRequest.getEmail());
    user.setPassword(signUpRequest.getPassword());
    user.setProvider(AuthProvider.email_address);

    user.setPassword(passwordEncoder.encode(user.getPassword()));

    userDao.save(user);

    return ResponseEntity.ok().body("User registered successfully");
  }

  @GetMapping("/by/id/{userId}")
  public User findUserById(@PathVariable("userId") String userId) {
    return userDao.findUserById(userId);
  }
}
