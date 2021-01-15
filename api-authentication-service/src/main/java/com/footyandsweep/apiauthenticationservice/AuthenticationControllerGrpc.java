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

import com.footyandsweep.AuthenticationServiceGrpc;
import com.footyandsweep.AuthenticationServiceOuterClass;
import com.footyandsweep.apiauthenticationservice.grpc.GrpcService;
import io.grpc.stub.StreamObserver;

@GrpcService
public class AuthenticationControllerGrpc extends AuthenticationServiceGrpc.AuthenticationServiceImplBase {

    @Override
    public void findUserByUserId(AuthenticationServiceOuterClass.findUserByIdRequest request, StreamObserver<AuthenticationServiceOuterClass.User> responseObserver) {
       /* TODO: Call the dao method here! */
    }
}
