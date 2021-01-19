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

package com.footyandsweep.apiauthenticationservice.service;

import com.footyandsweep.apiauthenticationservice.exception.SignUpException;
import com.footyandsweep.apiauthenticationservice.payload.SignUpRequest;
import com.footyandsweep.apicommonlibrary.model.sweepstake.SweepstakeCommon;

public interface UserService {

  void addOwnerToSweepstake(SweepstakeCommon sweepstake);

  void checkSignUpRequestIsValid(SignUpRequest request) throws SignUpException;
}
