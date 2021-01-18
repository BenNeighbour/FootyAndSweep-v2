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

package com.footyandsweep;

import static io.grpc.MethodDescriptor.generateFullMethodName;
import static io.grpc.stub.ClientCalls.asyncUnaryCall;
import static io.grpc.stub.ClientCalls.blockingUnaryCall;
import static io.grpc.stub.ClientCalls.futureUnaryCall;
import static io.grpc.stub.ServerCalls.asyncUnaryCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall;

/** */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.16.1)",
    comments = "Source: AuthenticationService.proto")
public final class AuthenticationServiceGrpc {

  public static final String SERVICE_NAME = "com.footyandsweep.AuthenticationService";
  private static final int METHODID_SIGN_UP = 0;
  private static final int METHODID_FIND_USER_BY_USER_ID = 1;
  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<
          com.footyandsweep.AuthenticationServiceOuterClass.SignUpRequest, com.google.rpc.Status>
      getSignUpMethod;
  private static volatile io.grpc.MethodDescriptor<
          com.footyandsweep.AuthenticationServiceOuterClass.findUserByIdRequest,
          com.footyandsweep.AuthenticationServiceOuterClass.User>
      getFindUserByUserIdMethod;
  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  private AuthenticationServiceGrpc() {}

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "signUp",
      requestType = com.footyandsweep.AuthenticationServiceOuterClass.SignUpRequest.class,
      responseType = com.google.rpc.Status.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<
          com.footyandsweep.AuthenticationServiceOuterClass.SignUpRequest, com.google.rpc.Status>
      getSignUpMethod() {
    io.grpc.MethodDescriptor<
            com.footyandsweep.AuthenticationServiceOuterClass.SignUpRequest, com.google.rpc.Status>
        getSignUpMethod;
    if ((getSignUpMethod = AuthenticationServiceGrpc.getSignUpMethod) == null) {
      synchronized (AuthenticationServiceGrpc.class) {
        if ((getSignUpMethod = AuthenticationServiceGrpc.getSignUpMethod) == null) {
          AuthenticationServiceGrpc.getSignUpMethod =
              getSignUpMethod =
                  io.grpc.MethodDescriptor
                      .<com.footyandsweep.AuthenticationServiceOuterClass.SignUpRequest,
                          com.google.rpc.Status>
                          newBuilder()
                      .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
                      .setFullMethodName(
                          generateFullMethodName(
                              "com.footyandsweep.AuthenticationService", "signUp"))
                      .setSampledToLocalTracing(true)
                      .setRequestMarshaller(
                          io.grpc.protobuf.ProtoUtils.marshaller(
                              com.footyandsweep.AuthenticationServiceOuterClass.SignUpRequest
                                  .getDefaultInstance()))
                      .setResponseMarshaller(
                          io.grpc.protobuf.ProtoUtils.marshaller(
                              com.google.rpc.Status.getDefaultInstance()))
                      .setSchemaDescriptor(
                          new AuthenticationServiceMethodDescriptorSupplier("signUp"))
                      .build();
        }
      }
    }
    return getSignUpMethod;
  }

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "findUserByUserId",
      requestType = com.footyandsweep.AuthenticationServiceOuterClass.findUserByIdRequest.class,
      responseType = com.footyandsweep.AuthenticationServiceOuterClass.User.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<
          com.footyandsweep.AuthenticationServiceOuterClass.findUserByIdRequest,
          com.footyandsweep.AuthenticationServiceOuterClass.User>
      getFindUserByUserIdMethod() {
    io.grpc.MethodDescriptor<
            com.footyandsweep.AuthenticationServiceOuterClass.findUserByIdRequest,
            com.footyandsweep.AuthenticationServiceOuterClass.User>
        getFindUserByUserIdMethod;
    if ((getFindUserByUserIdMethod = AuthenticationServiceGrpc.getFindUserByUserIdMethod) == null) {
      synchronized (AuthenticationServiceGrpc.class) {
        if ((getFindUserByUserIdMethod = AuthenticationServiceGrpc.getFindUserByUserIdMethod)
            == null) {
          AuthenticationServiceGrpc.getFindUserByUserIdMethod =
              getFindUserByUserIdMethod =
                  io.grpc.MethodDescriptor
                      .<com.footyandsweep.AuthenticationServiceOuterClass.findUserByIdRequest,
                          com.footyandsweep.AuthenticationServiceOuterClass.User>
                          newBuilder()
                      .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
                      .setFullMethodName(
                          generateFullMethodName(
                              "com.footyandsweep.AuthenticationService", "findUserByUserId"))
                      .setSampledToLocalTracing(true)
                      .setRequestMarshaller(
                          io.grpc.protobuf.ProtoUtils.marshaller(
                              com.footyandsweep.AuthenticationServiceOuterClass.findUserByIdRequest
                                  .getDefaultInstance()))
                      .setResponseMarshaller(
                          io.grpc.protobuf.ProtoUtils.marshaller(
                              com.footyandsweep.AuthenticationServiceOuterClass.User
                                  .getDefaultInstance()))
                      .setSchemaDescriptor(
                          new AuthenticationServiceMethodDescriptorSupplier("findUserByUserId"))
                      .build();
        }
      }
    }
    return getFindUserByUserIdMethod;
  }

  /** Creates a new async stub that supports all call types for the service */
  public static AuthenticationServiceStub newStub(io.grpc.Channel channel) {
    return new AuthenticationServiceStub(channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static AuthenticationServiceBlockingStub newBlockingStub(io.grpc.Channel channel) {
    return new AuthenticationServiceBlockingStub(channel);
  }

  /** Creates a new ListenableFuture-style stub that supports unary calls on the service */
  public static AuthenticationServiceFutureStub newFutureStub(io.grpc.Channel channel) {
    return new AuthenticationServiceFutureStub(channel);
  }

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (AuthenticationServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor =
              result =
                  io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
                      .setSchemaDescriptor(new AuthenticationServiceFileDescriptorSupplier())
                      .addMethod(getSignUpMethod())
                      .addMethod(getFindUserByUserIdMethod())
                      .build();
        }
      }
    }
    return result;
  }

  /** */
  public abstract static class AuthenticationServiceImplBase implements io.grpc.BindableService {

    /** */
    public void signUp(
        com.footyandsweep.AuthenticationServiceOuterClass.SignUpRequest request,
        io.grpc.stub.StreamObserver<com.google.rpc.Status> responseObserver) {
      asyncUnimplementedUnaryCall(getSignUpMethod(), responseObserver);
    }

    /** */
    public void findUserByUserId(
        com.footyandsweep.AuthenticationServiceOuterClass.findUserByIdRequest request,
        io.grpc.stub.StreamObserver<com.footyandsweep.AuthenticationServiceOuterClass.User>
            responseObserver) {
      asyncUnimplementedUnaryCall(getFindUserByUserIdMethod(), responseObserver);
    }

    @java.lang.Override
    public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
              getSignUpMethod(),
              asyncUnaryCall(
                  new MethodHandlers<
                      com.footyandsweep.AuthenticationServiceOuterClass.SignUpRequest,
                      com.google.rpc.Status>(this, METHODID_SIGN_UP)))
          .addMethod(
              getFindUserByUserIdMethod(),
              asyncUnaryCall(
                  new MethodHandlers<
                      com.footyandsweep.AuthenticationServiceOuterClass.findUserByIdRequest,
                      com.footyandsweep.AuthenticationServiceOuterClass.User>(
                      this, METHODID_FIND_USER_BY_USER_ID)))
          .build();
    }
  }

  /** */
  public static final class AuthenticationServiceStub
      extends io.grpc.stub.AbstractStub<AuthenticationServiceStub> {
    private AuthenticationServiceStub(io.grpc.Channel channel) {
      super(channel);
    }

    private AuthenticationServiceStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected AuthenticationServiceStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new AuthenticationServiceStub(channel, callOptions);
    }

    /** */
    public void signUp(
        com.footyandsweep.AuthenticationServiceOuterClass.SignUpRequest request,
        io.grpc.stub.StreamObserver<com.google.rpc.Status> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getSignUpMethod(), getCallOptions()), request, responseObserver);
    }

    /** */
    public void findUserByUserId(
        com.footyandsweep.AuthenticationServiceOuterClass.findUserByIdRequest request,
        io.grpc.stub.StreamObserver<com.footyandsweep.AuthenticationServiceOuterClass.User>
            responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getFindUserByUserIdMethod(), getCallOptions()),
          request,
          responseObserver);
    }
  }

  /** */
  public static final class AuthenticationServiceBlockingStub
      extends io.grpc.stub.AbstractStub<AuthenticationServiceBlockingStub> {
    private AuthenticationServiceBlockingStub(io.grpc.Channel channel) {
      super(channel);
    }

    private AuthenticationServiceBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected AuthenticationServiceBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new AuthenticationServiceBlockingStub(channel, callOptions);
    }

    /** */
    public com.google.rpc.Status signUp(
        com.footyandsweep.AuthenticationServiceOuterClass.SignUpRequest request) {
      return blockingUnaryCall(getChannel(), getSignUpMethod(), getCallOptions(), request);
    }

    /** */
    public com.footyandsweep.AuthenticationServiceOuterClass.User findUserByUserId(
        com.footyandsweep.AuthenticationServiceOuterClass.findUserByIdRequest request) {
      return blockingUnaryCall(
          getChannel(), getFindUserByUserIdMethod(), getCallOptions(), request);
    }
  }

  /** */
  public static final class AuthenticationServiceFutureStub
      extends io.grpc.stub.AbstractStub<AuthenticationServiceFutureStub> {
    private AuthenticationServiceFutureStub(io.grpc.Channel channel) {
      super(channel);
    }

    private AuthenticationServiceFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected AuthenticationServiceFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new AuthenticationServiceFutureStub(channel, callOptions);
    }

    /** */
    public com.google.common.util.concurrent.ListenableFuture<com.google.rpc.Status> signUp(
        com.footyandsweep.AuthenticationServiceOuterClass.SignUpRequest request) {
      return futureUnaryCall(getChannel().newCall(getSignUpMethod(), getCallOptions()), request);
    }

    /** */
    public com.google.common.util.concurrent.ListenableFuture<
            com.footyandsweep.AuthenticationServiceOuterClass.User>
        findUserByUserId(
            com.footyandsweep.AuthenticationServiceOuterClass.findUserByIdRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getFindUserByUserIdMethod(), getCallOptions()), request);
    }
  }

  private static final class MethodHandlers<Req, Resp>
      implements io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
          io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
          io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
          io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final AuthenticationServiceImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(AuthenticationServiceImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_SIGN_UP:
          serviceImpl.signUp(
              (com.footyandsweep.AuthenticationServiceOuterClass.SignUpRequest) request,
              (io.grpc.stub.StreamObserver<com.google.rpc.Status>) responseObserver);
          break;
        case METHODID_FIND_USER_BY_USER_ID:
          serviceImpl.findUserByUserId(
              (com.footyandsweep.AuthenticationServiceOuterClass.findUserByIdRequest) request,
              (io.grpc.stub.StreamObserver<com.footyandsweep.AuthenticationServiceOuterClass.User>)
                  responseObserver);
          break;
        default:
          throw new AssertionError();
      }
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        default:
          throw new AssertionError();
      }
    }
  }

  private abstract static class AuthenticationServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier,
          io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    AuthenticationServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return com.footyandsweep.AuthenticationServiceOuterClass.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("AuthenticationService");
    }
  }

  private static final class AuthenticationServiceFileDescriptorSupplier
      extends AuthenticationServiceBaseDescriptorSupplier {
    AuthenticationServiceFileDescriptorSupplier() {}
  }

  private static final class AuthenticationServiceMethodDescriptorSupplier
      extends AuthenticationServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    AuthenticationServiceMethodDescriptorSupplier(String methodName) {
      this.methodName = methodName;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.MethodDescriptor getMethodDescriptor() {
      return getServiceDescriptor().findMethodByName(methodName);
    }
  }
}
