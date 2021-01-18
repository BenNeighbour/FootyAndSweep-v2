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

package com.footyandsweep.apiauthenticationservice;

import com.footyandsweep.AuthenticationServiceGrpc;
import com.footyandsweep.AuthenticationServiceOuterClass;
import com.footyandsweep.apiauthenticationservice.dao.UserDao;
import com.footyandsweep.apiauthenticationservice.exception.SignUpException;
import com.footyandsweep.apiauthenticationservice.grpc.GrpcService;
import com.footyandsweep.apiauthenticationservice.model.AuthProvider;
import com.footyandsweep.apiauthenticationservice.model.User;
import com.footyandsweep.apiauthenticationservice.service.UserService;
import com.google.rpc.Code;
import com.google.rpc.Status;
import io.grpc.protobuf.StatusProto;
import io.grpc.stub.StreamObserver;
import org.springframework.security.crypto.password.PasswordEncoder;

@GrpcService
public class AuthenticationControllerGrpc extends AuthenticationServiceGrpc.AuthenticationServiceImplBase {

    private final UserDao userDao;
    private final PasswordEncoder passwordEncoder;
    private final UserService userService;

    public AuthenticationControllerGrpc(UserDao userDao, PasswordEncoder passwordEncoder, UserService userService) {
        this.userDao = userDao;
        this.passwordEncoder = passwordEncoder;
        this.userService = userService;
    }

    @Override
    public void findUserByUserId(AuthenticationServiceOuterClass.findUserByIdRequest request, StreamObserver<AuthenticationServiceOuterClass.User> responseObserver) {
        /* TODO: Call the dao method here! */
    }

    @Override
    public void signUp(AuthenticationServiceOuterClass.SignUpRequest request, StreamObserver<Status> responseObserver) {
        try {
            userService.checkSignUpRequestIsValid(request);

            /* Constructing the new user from the fields that have been requested */
            User user = new User();

            /* Reflecting the values and persisting the user */
            user.setName(request.getName());
            user.setEmail(request.getEmail());
            user.setPassword(request.getPassword());
            user.setProvider(AuthProvider.local);
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            userDao.save(user);

            Status status =
                    Status.newBuilder()
                            .setCode(Code.OK.getNumber())
                            .setMessage("User registered successfully!")
                            .build();

            responseObserver.onNext(status);
            responseObserver.onCompleted();
        } catch (SignUpException e) {
            /* If there are any exceptions then tell the user */
            Status errorStatus =
                    Status.newBuilder()
                            .setCode(Code.ALREADY_EXISTS.getNumber())
                            .setMessage(e.getMessage())
                            .build();

            responseObserver.onError(StatusProto.toStatusRuntimeException(errorStatus));
        } catch (Exception e) {
            /* If there are any exceptions then tell the user */
            Status errorStatus =
                    Status.newBuilder()
                            .setCode(Code.INTERNAL.getNumber())
                            .setMessage(e.getMessage())
                            .build();

            responseObserver.onError(StatusProto.toStatusRuntimeException(errorStatus));
        }
    }
}
