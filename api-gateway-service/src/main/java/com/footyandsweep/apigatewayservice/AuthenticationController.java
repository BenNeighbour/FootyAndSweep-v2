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

import com.footyandsweep.apigatewayservice.model.User;
import com.footyandsweep.apigatewayservice.payload.LoginRequest;
import com.footyandsweep.apigatewayservice.payload.SignUpRequest;
import com.footyandsweep.apigatewayservice.security.JwtTokenProvider;
import com.footyandsweep.apigatewayservice.service.UserService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

  private final UserService userService;

  private final JwtTokenProvider tokenProvider;
  private final ReactiveAuthenticationManager authenticationManager;

  public AuthenticationController(
      UserService userService,
      JwtTokenProvider tokenProvider,
      ReactiveAuthenticationManager authenticationManager) {
    this.userService = userService;
    this.tokenProvider = tokenProvider;
    this.authenticationManager = authenticationManager;
  }

  @PostMapping("/signup")
  public Mono<ResponseEntity> signup(@Valid @RequestBody Mono<SignUpRequest> authRequest) {
    return authRequest
        .flatMap(
            signUpRequest -> {
              /* Sign the user up */
              Optional<User> user = userService.signupUser(signUpRequest);

              if (user.isPresent()) {
                /* Now, log the user in */
                return loginHelper(
                    Mono.just(
                        new LoginRequest(signUpRequest.getEmail(), signUpRequest.getPassword())));
              }

              return Mono.empty();
            })
        .map(
            o -> {
              if (o == null)
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Something's not quite right...");
              return ResponseEntity.ok(o);
            });
  }

  @PostMapping("/login")
  public Mono<ResponseEntity> login(@Valid @RequestBody Mono<LoginRequest> authRequest) {
    return loginHelper(authRequest);
  }

  private Mono<ResponseEntity> loginHelper(Mono<LoginRequest> authRequest) {
    return authRequest
        .flatMap(
            login ->
                this.authenticationManager
                    .authenticate(
                        new UsernamePasswordAuthenticationToken(
                            login.getEmail(), login.getPassword()))
                    .map(this.tokenProvider::createToken))
        .map(
            jwt -> {
              HttpHeaders responseHeaders = new HttpHeaders();
              responseHeaders.add(
                  HttpHeaders.SET_COOKIE,
                  ResponseCookie.from("token", jwt)
                      .httpOnly(true)
                      .path("/")
                      .sameSite("Strict")
                      .domain("footyandsweep-dev.com")
                      .build()
                      .toString());

              return ResponseEntity.ok().headers(responseHeaders).build();
            });
  }
}
