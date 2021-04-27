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

package com.footyandsweep.apigatewayservice.service;

import com.footyandsweep.apigatewayservice.exception.SignUpException;
import com.footyandsweep.apigatewayservice.model.User;
import com.footyandsweep.apigatewayservice.payload.SignUpRequest;

import java.math.BigDecimal;
import java.util.Optional;

public interface UserService {

  void addOwnerToSweepstake(String sweepstakeId, String ownerId);

  void checkSignUpRequestIsValid(SignUpRequest request) throws SignUpException;

  void deleteAllSweepstakeRelations(String sweepstakeId);

  void saveSweepstakeId(String sweepstakeId, String participantId);

  void updateUserBalance(String userId, BigDecimal amountDeducted);

  Optional<User> signupUser(SignUpRequest request);
}
