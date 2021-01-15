/*
 *   Copyright 2020 FootyAndSweep
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

package com.footyandsweep.apiauthenticationservice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/com.footyandsweep.AuthenticationService")
public class AuthenticationController {

    @GetMapping("/amIAuthenticated")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<Object> amIAuthenticated() {
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    //  @PostMapping("/login")
//  public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
//
//    Authentication authentication =
//        authenticationManager.authenticate(
//            new UsernamePasswordAuthenticationToken(
//                loginRequest.getEmail(), loginRequest.getPassword()));
//
//    SecurityContextHolder.getContext().setAuthentication(authentication);
//
//    String token = tokenProvider.createToken(authentication);
//    return ResponseEntity.ok(new AuthResponse(token));
//  }
//
//  @PostMapping("/signup")
//  public ResponseEntity<?> registerUser(@Valid @RequestBody SignUpRequest signUpRequest) {
//    if (userDao.existsByEmail(signUpRequest.getEmail())) {
//      throw new BadRequestException("Email address already in use.");
//    }
//
//    User user = new User();
//    user.setName(signUpRequest.getName());
//    user.setEmail(signUpRequest.getEmail());
//    user.setPassword(signUpRequest.getPassword());
//    user.setProvider(AuthProvider.local);
//
//    user.setPassword(passwordEncoder.encode(user.getPassword()));
//
//    User result = userDao.save(user);
//
//    URI location =
//        ServletUriComponentsBuilder.fromCurrentContextPath()
//            .path("/user/me")
//            .buildAndExpand(result.getId())
//            .toUri();
//
//    return ResponseEntity.created(location)
//        .body(new ApiResponse(true, "User registered successfully"));
//  }

// @GetMapping("/AuthenticateMe")
// @PreAuthorize("hasRole('USER')")
// public User authenticateMe(@CurrentUser UserPrincipal userPrincipal) {
//   return userDao.findById(userPrincipal.getId())
//           .orElseThrow(() -> new ResourceNotFoundException("User", "id", userPrincipal.getId()));
// }

}
