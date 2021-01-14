package com.footyandsweep;

import static io.grpc.MethodDescriptor.generateFullMethodName;
import static io.grpc.stub.ClientCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ClientCalls.asyncClientStreamingCall;
import static io.grpc.stub.ClientCalls.asyncServerStreamingCall;
import static io.grpc.stub.ClientCalls.asyncUnaryCall;
import static io.grpc.stub.ClientCalls.blockingServerStreamingCall;
import static io.grpc.stub.ClientCalls.blockingUnaryCall;
import static io.grpc.stub.ClientCalls.futureUnaryCall;
import static io.grpc.stub.ServerCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ServerCalls.asyncClientStreamingCall;
import static io.grpc.stub.ServerCalls.asyncServerStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnaryCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.16.1)",
    comments = "Source: AuthenticationService.proto")
public final class AuthenticationServiceGrpc {

  private AuthenticationServiceGrpc() {}

  public static final String SERVICE_NAME = "com.footyandsweep.AuthenticationService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<com.footyandsweep.AuthenticationServiceOuterClass.AmIAuthenticatedRequest,
      com.footyandsweep.AuthenticationServiceOuterClass.AmIAuthenticatedResponse> getAmIAuthenticatedMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "AmIAuthenticated",
      requestType = com.footyandsweep.AuthenticationServiceOuterClass.AmIAuthenticatedRequest.class,
      responseType = com.footyandsweep.AuthenticationServiceOuterClass.AmIAuthenticatedResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.footyandsweep.AuthenticationServiceOuterClass.AmIAuthenticatedRequest,
      com.footyandsweep.AuthenticationServiceOuterClass.AmIAuthenticatedResponse> getAmIAuthenticatedMethod() {
    io.grpc.MethodDescriptor<com.footyandsweep.AuthenticationServiceOuterClass.AmIAuthenticatedRequest, com.footyandsweep.AuthenticationServiceOuterClass.AmIAuthenticatedResponse> getAmIAuthenticatedMethod;
    if ((getAmIAuthenticatedMethod = AuthenticationServiceGrpc.getAmIAuthenticatedMethod) == null) {
      synchronized (AuthenticationServiceGrpc.class) {
        if ((getAmIAuthenticatedMethod = AuthenticationServiceGrpc.getAmIAuthenticatedMethod) == null) {
          AuthenticationServiceGrpc.getAmIAuthenticatedMethod = getAmIAuthenticatedMethod = 
              io.grpc.MethodDescriptor.<com.footyandsweep.AuthenticationServiceOuterClass.AmIAuthenticatedRequest, com.footyandsweep.AuthenticationServiceOuterClass.AmIAuthenticatedResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "com.footyandsweep.AuthenticationService", "AmIAuthenticated"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.footyandsweep.AuthenticationServiceOuterClass.AmIAuthenticatedRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.footyandsweep.AuthenticationServiceOuterClass.AmIAuthenticatedResponse.getDefaultInstance()))
                  .setSchemaDescriptor(new AuthenticationServiceMethodDescriptorSupplier("AmIAuthenticated"))
                  .build();
          }
        }
     }
     return getAmIAuthenticatedMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static AuthenticationServiceStub newStub(io.grpc.Channel channel) {
    return new AuthenticationServiceStub(channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static AuthenticationServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    return new AuthenticationServiceBlockingStub(channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static AuthenticationServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    return new AuthenticationServiceFutureStub(channel);
  }

  /**
   */
  public static abstract class AuthenticationServiceImplBase implements io.grpc.BindableService {

    /**
     */
    public void amIAuthenticated(com.footyandsweep.AuthenticationServiceOuterClass.AmIAuthenticatedRequest request,
        io.grpc.stub.StreamObserver<com.footyandsweep.AuthenticationServiceOuterClass.AmIAuthenticatedResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getAmIAuthenticatedMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getAmIAuthenticatedMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.footyandsweep.AuthenticationServiceOuterClass.AmIAuthenticatedRequest,
                com.footyandsweep.AuthenticationServiceOuterClass.AmIAuthenticatedResponse>(
                  this, METHODID_AM_IAUTHENTICATED)))
          .build();
    }
  }

  /**
   */
  public static final class AuthenticationServiceStub extends io.grpc.stub.AbstractStub<AuthenticationServiceStub> {
    private AuthenticationServiceStub(io.grpc.Channel channel) {
      super(channel);
    }

    private AuthenticationServiceStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected AuthenticationServiceStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new AuthenticationServiceStub(channel, callOptions);
    }

    /**
     */
    public void amIAuthenticated(com.footyandsweep.AuthenticationServiceOuterClass.AmIAuthenticatedRequest request,
        io.grpc.stub.StreamObserver<com.footyandsweep.AuthenticationServiceOuterClass.AmIAuthenticatedResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getAmIAuthenticatedMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   */
  public static final class AuthenticationServiceBlockingStub extends io.grpc.stub.AbstractStub<AuthenticationServiceBlockingStub> {
    private AuthenticationServiceBlockingStub(io.grpc.Channel channel) {
      super(channel);
    }

    private AuthenticationServiceBlockingStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected AuthenticationServiceBlockingStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new AuthenticationServiceBlockingStub(channel, callOptions);
    }

    /**
     */
    public com.footyandsweep.AuthenticationServiceOuterClass.AmIAuthenticatedResponse amIAuthenticated(com.footyandsweep.AuthenticationServiceOuterClass.AmIAuthenticatedRequest request) {
      return blockingUnaryCall(
          getChannel(), getAmIAuthenticatedMethod(), getCallOptions(), request);
    }
  }

  /**
   */
  public static final class AuthenticationServiceFutureStub extends io.grpc.stub.AbstractStub<AuthenticationServiceFutureStub> {
    private AuthenticationServiceFutureStub(io.grpc.Channel channel) {
      super(channel);
    }

    private AuthenticationServiceFutureStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected AuthenticationServiceFutureStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new AuthenticationServiceFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.footyandsweep.AuthenticationServiceOuterClass.AmIAuthenticatedResponse> amIAuthenticated(
        com.footyandsweep.AuthenticationServiceOuterClass.AmIAuthenticatedRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getAmIAuthenticatedMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_AM_IAUTHENTICATED = 0;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
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
        case METHODID_AM_IAUTHENTICATED:
          serviceImpl.amIAuthenticated((com.footyandsweep.AuthenticationServiceOuterClass.AmIAuthenticatedRequest) request,
              (io.grpc.stub.StreamObserver<com.footyandsweep.AuthenticationServiceOuterClass.AmIAuthenticatedResponse>) responseObserver);
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

  private static abstract class AuthenticationServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
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

  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (AuthenticationServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new AuthenticationServiceFileDescriptorSupplier())
              .addMethod(getAmIAuthenticatedMethod())
              .build();
        }
      }
    }
    return result;
  }
}
