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
    comments = "Source: AllocationService.proto")
public final class AllocationServiceGrpc {

  private AllocationServiceGrpc() {}

  public static final String SERVICE_NAME = "com.footyandsweep.AllocationService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<com.footyandsweep.SweepstakeServiceOuterClass.Sweepstake,
      com.google.protobuf.Empty> getAllocateSweepstakeMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "allocateSweepstake",
      requestType = com.footyandsweep.SweepstakeServiceOuterClass.Sweepstake.class,
      responseType = com.google.protobuf.Empty.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.footyandsweep.SweepstakeServiceOuterClass.Sweepstake,
      com.google.protobuf.Empty> getAllocateSweepstakeMethod() {
    io.grpc.MethodDescriptor<com.footyandsweep.SweepstakeServiceOuterClass.Sweepstake, com.google.protobuf.Empty> getAllocateSweepstakeMethod;
    if ((getAllocateSweepstakeMethod = AllocationServiceGrpc.getAllocateSweepstakeMethod) == null) {
      synchronized (AllocationServiceGrpc.class) {
        if ((getAllocateSweepstakeMethod = AllocationServiceGrpc.getAllocateSweepstakeMethod) == null) {
          AllocationServiceGrpc.getAllocateSweepstakeMethod = getAllocateSweepstakeMethod = 
              io.grpc.MethodDescriptor.<com.footyandsweep.SweepstakeServiceOuterClass.Sweepstake, com.google.protobuf.Empty>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "com.footyandsweep.AllocationService", "allocateSweepstake"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.footyandsweep.SweepstakeServiceOuterClass.Sweepstake.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.google.protobuf.Empty.getDefaultInstance()))
                  .setSchemaDescriptor(new AllocationServiceMethodDescriptorSupplier("allocateSweepstake"))
                  .build();
          }
        }
     }
     return getAllocateSweepstakeMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static AllocationServiceStub newStub(io.grpc.Channel channel) {
    return new AllocationServiceStub(channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static AllocationServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    return new AllocationServiceBlockingStub(channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static AllocationServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    return new AllocationServiceFutureStub(channel);
  }

  /**
   */
  public static abstract class AllocationServiceImplBase implements io.grpc.BindableService {

    /**
     */
    public void allocateSweepstake(com.footyandsweep.SweepstakeServiceOuterClass.Sweepstake request,
        io.grpc.stub.StreamObserver<com.google.protobuf.Empty> responseObserver) {
      asyncUnimplementedUnaryCall(getAllocateSweepstakeMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getAllocateSweepstakeMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.footyandsweep.SweepstakeServiceOuterClass.Sweepstake,
                com.google.protobuf.Empty>(
                  this, METHODID_ALLOCATE_SWEEPSTAKE)))
          .build();
    }
  }

  /**
   */
  public static final class AllocationServiceStub extends io.grpc.stub.AbstractStub<AllocationServiceStub> {
    private AllocationServiceStub(io.grpc.Channel channel) {
      super(channel);
    }

    private AllocationServiceStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected AllocationServiceStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new AllocationServiceStub(channel, callOptions);
    }

    /**
     */
    public void allocateSweepstake(com.footyandsweep.SweepstakeServiceOuterClass.Sweepstake request,
        io.grpc.stub.StreamObserver<com.google.protobuf.Empty> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getAllocateSweepstakeMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   */
  public static final class AllocationServiceBlockingStub extends io.grpc.stub.AbstractStub<AllocationServiceBlockingStub> {
    private AllocationServiceBlockingStub(io.grpc.Channel channel) {
      super(channel);
    }

    private AllocationServiceBlockingStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected AllocationServiceBlockingStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new AllocationServiceBlockingStub(channel, callOptions);
    }

    /**
     */
    public com.google.protobuf.Empty allocateSweepstake(com.footyandsweep.SweepstakeServiceOuterClass.Sweepstake request) {
      return blockingUnaryCall(
          getChannel(), getAllocateSweepstakeMethod(), getCallOptions(), request);
    }
  }

  /**
   */
  public static final class AllocationServiceFutureStub extends io.grpc.stub.AbstractStub<AllocationServiceFutureStub> {
    private AllocationServiceFutureStub(io.grpc.Channel channel) {
      super(channel);
    }

    private AllocationServiceFutureStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected AllocationServiceFutureStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new AllocationServiceFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.google.protobuf.Empty> allocateSweepstake(
        com.footyandsweep.SweepstakeServiceOuterClass.Sweepstake request) {
      return futureUnaryCall(
          getChannel().newCall(getAllocateSweepstakeMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_ALLOCATE_SWEEPSTAKE = 0;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final AllocationServiceImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(AllocationServiceImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_ALLOCATE_SWEEPSTAKE:
          serviceImpl.allocateSweepstake((com.footyandsweep.SweepstakeServiceOuterClass.Sweepstake) request,
              (io.grpc.stub.StreamObserver<com.google.protobuf.Empty>) responseObserver);
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

  private static abstract class AllocationServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    AllocationServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return com.footyandsweep.AllocationServiceOuterClass.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("AllocationService");
    }
  }

  private static final class AllocationServiceFileDescriptorSupplier
      extends AllocationServiceBaseDescriptorSupplier {
    AllocationServiceFileDescriptorSupplier() {}
  }

  private static final class AllocationServiceMethodDescriptorSupplier
      extends AllocationServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    AllocationServiceMethodDescriptorSupplier(String methodName) {
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
      synchronized (AllocationServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new AllocationServiceFileDescriptorSupplier())
              .addMethod(getAllocateSweepstakeMethod())
              .build();
        }
      }
    }
    return result;
  }
}
