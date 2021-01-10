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

/** */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.16.1)",
    comments = "Source: SweepstakeService.proto")
public final class SweepstakeServiceGrpc {

  public static final String SERVICE_NAME = "com.footyandsweep.SweepstakeService";
  private static final int METHODID_FIND_SWEEPSTAKE_BY_JOIN_CODE = 0;
  private static final int METHODID_FIND_SWEEPSTAKE_BY_ID = 1;
  private static final int METHODID_GET_RESULT_HELPER_MAP = 2;
  private static final int METHODID_REQUEST_NEW_SWEEPSTAKE = 3;
  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<
          com.footyandsweep.SweepstakeServiceOuterClass.JoinCode,
          com.footyandsweep.SweepstakeServiceOuterClass.Sweepstake>
      getFindSweepstakeByJoinCodeMethod;
  private static volatile io.grpc.MethodDescriptor<
          com.footyandsweep.SweepstakeServiceOuterClass.SweepstakeId,
          com.footyandsweep.SweepstakeServiceOuterClass.Sweepstake>
      getFindSweepstakeByIdMethod;
  private static volatile io.grpc.MethodDescriptor<
          com.footyandsweep.SweepstakeServiceOuterClass.Sweepstake,
          com.footyandsweep.SweepstakeServiceOuterClass.Map>
      getGetResultHelperMapMethod;
  private static volatile io.grpc.MethodDescriptor<
          com.footyandsweep.SweepstakeServiceOuterClass.Sweepstake,
          com.footyandsweep.SweepstakeServiceOuterClass.Sweepstake>
      getRequestNewSweepstakeMethod;
  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  private SweepstakeServiceGrpc() {}

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "findSweepstakeByJoinCode",
      requestType = com.footyandsweep.SweepstakeServiceOuterClass.JoinCode.class,
      responseType = com.footyandsweep.SweepstakeServiceOuterClass.Sweepstake.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<
          com.footyandsweep.SweepstakeServiceOuterClass.JoinCode,
          com.footyandsweep.SweepstakeServiceOuterClass.Sweepstake>
      getFindSweepstakeByJoinCodeMethod() {
    io.grpc.MethodDescriptor<
            com.footyandsweep.SweepstakeServiceOuterClass.JoinCode,
            com.footyandsweep.SweepstakeServiceOuterClass.Sweepstake>
        getFindSweepstakeByJoinCodeMethod;
    if ((getFindSweepstakeByJoinCodeMethod =
            SweepstakeServiceGrpc.getFindSweepstakeByJoinCodeMethod)
        == null) {
      synchronized (SweepstakeServiceGrpc.class) {
        if ((getFindSweepstakeByJoinCodeMethod =
                SweepstakeServiceGrpc.getFindSweepstakeByJoinCodeMethod)
            == null) {
          SweepstakeServiceGrpc.getFindSweepstakeByJoinCodeMethod =
              getFindSweepstakeByJoinCodeMethod =
                  io.grpc.MethodDescriptor
                      .<com.footyandsweep.SweepstakeServiceOuterClass.JoinCode,
                          com.footyandsweep.SweepstakeServiceOuterClass.Sweepstake>
                          newBuilder()
                      .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
                      .setFullMethodName(
                          generateFullMethodName(
                              "com.footyandsweep.SweepstakeService", "findSweepstakeByJoinCode"))
                      .setSampledToLocalTracing(true)
                      .setRequestMarshaller(
                          io.grpc.protobuf.ProtoUtils.marshaller(
                              com.footyandsweep.SweepstakeServiceOuterClass.JoinCode
                                  .getDefaultInstance()))
                      .setResponseMarshaller(
                          io.grpc.protobuf.ProtoUtils.marshaller(
                              com.footyandsweep.SweepstakeServiceOuterClass.Sweepstake
                                  .getDefaultInstance()))
                      .setSchemaDescriptor(
                          new SweepstakeServiceMethodDescriptorSupplier("findSweepstakeByJoinCode"))
                      .build();
        }
      }
    }
    return getFindSweepstakeByJoinCodeMethod;
  }

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "findSweepstakeById",
      requestType = com.footyandsweep.SweepstakeServiceOuterClass.SweepstakeId.class,
      responseType = com.footyandsweep.SweepstakeServiceOuterClass.Sweepstake.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<
          com.footyandsweep.SweepstakeServiceOuterClass.SweepstakeId,
          com.footyandsweep.SweepstakeServiceOuterClass.Sweepstake>
      getFindSweepstakeByIdMethod() {
    io.grpc.MethodDescriptor<
            com.footyandsweep.SweepstakeServiceOuterClass.SweepstakeId,
            com.footyandsweep.SweepstakeServiceOuterClass.Sweepstake>
        getFindSweepstakeByIdMethod;
    if ((getFindSweepstakeByIdMethod = SweepstakeServiceGrpc.getFindSweepstakeByIdMethod) == null) {
      synchronized (SweepstakeServiceGrpc.class) {
        if ((getFindSweepstakeByIdMethod = SweepstakeServiceGrpc.getFindSweepstakeByIdMethod)
            == null) {
          SweepstakeServiceGrpc.getFindSweepstakeByIdMethod =
              getFindSweepstakeByIdMethod =
                  io.grpc.MethodDescriptor
                      .<com.footyandsweep.SweepstakeServiceOuterClass.SweepstakeId,
                          com.footyandsweep.SweepstakeServiceOuterClass.Sweepstake>
                          newBuilder()
                      .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
                      .setFullMethodName(
                          generateFullMethodName(
                              "com.footyandsweep.SweepstakeService", "findSweepstakeById"))
                      .setSampledToLocalTracing(true)
                      .setRequestMarshaller(
                          io.grpc.protobuf.ProtoUtils.marshaller(
                              com.footyandsweep.SweepstakeServiceOuterClass.SweepstakeId
                                  .getDefaultInstance()))
                      .setResponseMarshaller(
                          io.grpc.protobuf.ProtoUtils.marshaller(
                              com.footyandsweep.SweepstakeServiceOuterClass.Sweepstake
                                  .getDefaultInstance()))
                      .setSchemaDescriptor(
                          new SweepstakeServiceMethodDescriptorSupplier("findSweepstakeById"))
                      .build();
        }
      }
    }
    return getFindSweepstakeByIdMethod;
  }

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "getResultHelperMap",
      requestType = com.footyandsweep.SweepstakeServiceOuterClass.Sweepstake.class,
      responseType = com.footyandsweep.SweepstakeServiceOuterClass.Map.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<
          com.footyandsweep.SweepstakeServiceOuterClass.Sweepstake,
          com.footyandsweep.SweepstakeServiceOuterClass.Map>
      getGetResultHelperMapMethod() {
    io.grpc.MethodDescriptor<
            com.footyandsweep.SweepstakeServiceOuterClass.Sweepstake,
            com.footyandsweep.SweepstakeServiceOuterClass.Map>
        getGetResultHelperMapMethod;
    if ((getGetResultHelperMapMethod = SweepstakeServiceGrpc.getGetResultHelperMapMethod) == null) {
      synchronized (SweepstakeServiceGrpc.class) {
        if ((getGetResultHelperMapMethod = SweepstakeServiceGrpc.getGetResultHelperMapMethod)
            == null) {
          SweepstakeServiceGrpc.getGetResultHelperMapMethod =
              getGetResultHelperMapMethod =
                  io.grpc.MethodDescriptor
                      .<com.footyandsweep.SweepstakeServiceOuterClass.Sweepstake,
                          com.footyandsweep.SweepstakeServiceOuterClass.Map>
                          newBuilder()
                      .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
                      .setFullMethodName(
                          generateFullMethodName(
                              "com.footyandsweep.SweepstakeService", "getResultHelperMap"))
                      .setSampledToLocalTracing(true)
                      .setRequestMarshaller(
                          io.grpc.protobuf.ProtoUtils.marshaller(
                              com.footyandsweep.SweepstakeServiceOuterClass.Sweepstake
                                  .getDefaultInstance()))
                      .setResponseMarshaller(
                          io.grpc.protobuf.ProtoUtils.marshaller(
                              com.footyandsweep.SweepstakeServiceOuterClass.Map
                                  .getDefaultInstance()))
                      .setSchemaDescriptor(
                          new SweepstakeServiceMethodDescriptorSupplier("getResultHelperMap"))
                      .build();
        }
      }
    }
    return getGetResultHelperMapMethod;
  }

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "requestNewSweepstake",
      requestType = com.footyandsweep.SweepstakeServiceOuterClass.Sweepstake.class,
      responseType = com.footyandsweep.SweepstakeServiceOuterClass.Sweepstake.class,
      methodType = io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
  public static io.grpc.MethodDescriptor<
          com.footyandsweep.SweepstakeServiceOuterClass.Sweepstake,
          com.footyandsweep.SweepstakeServiceOuterClass.Sweepstake>
      getRequestNewSweepstakeMethod() {
    io.grpc.MethodDescriptor<
            com.footyandsweep.SweepstakeServiceOuterClass.Sweepstake,
            com.footyandsweep.SweepstakeServiceOuterClass.Sweepstake>
        getRequestNewSweepstakeMethod;
    if ((getRequestNewSweepstakeMethod = SweepstakeServiceGrpc.getRequestNewSweepstakeMethod)
        == null) {
      synchronized (SweepstakeServiceGrpc.class) {
        if ((getRequestNewSweepstakeMethod = SweepstakeServiceGrpc.getRequestNewSweepstakeMethod)
            == null) {
          SweepstakeServiceGrpc.getRequestNewSweepstakeMethod =
              getRequestNewSweepstakeMethod =
                  io.grpc.MethodDescriptor
                      .<com.footyandsweep.SweepstakeServiceOuterClass.Sweepstake,
                          com.footyandsweep.SweepstakeServiceOuterClass.Sweepstake>
                          newBuilder()
                      .setType(io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
                      .setFullMethodName(
                          generateFullMethodName(
                              "com.footyandsweep.SweepstakeService", "requestNewSweepstake"))
                      .setSampledToLocalTracing(true)
                      .setRequestMarshaller(
                          io.grpc.protobuf.ProtoUtils.marshaller(
                              com.footyandsweep.SweepstakeServiceOuterClass.Sweepstake
                                  .getDefaultInstance()))
                      .setResponseMarshaller(
                          io.grpc.protobuf.ProtoUtils.marshaller(
                              com.footyandsweep.SweepstakeServiceOuterClass.Sweepstake
                                  .getDefaultInstance()))
                      .setSchemaDescriptor(
                          new SweepstakeServiceMethodDescriptorSupplier("requestNewSweepstake"))
                      .build();
        }
      }
    }
    return getRequestNewSweepstakeMethod;
  }

  /** Creates a new async stub that supports all call types for the service */
  public static SweepstakeServiceStub newStub(io.grpc.Channel channel) {
    return new SweepstakeServiceStub(channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static SweepstakeServiceBlockingStub newBlockingStub(io.grpc.Channel channel) {
    return new SweepstakeServiceBlockingStub(channel);
  }

  /** Creates a new ListenableFuture-style stub that supports unary calls on the service */
  public static SweepstakeServiceFutureStub newFutureStub(io.grpc.Channel channel) {
    return new SweepstakeServiceFutureStub(channel);
  }

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (SweepstakeServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor =
              result =
                  io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
                      .setSchemaDescriptor(new SweepstakeServiceFileDescriptorSupplier())
                      .addMethod(getFindSweepstakeByJoinCodeMethod())
                      .addMethod(getFindSweepstakeByIdMethod())
                      .addMethod(getGetResultHelperMapMethod())
                      .addMethod(getRequestNewSweepstakeMethod())
                      .build();
        }
      }
    }
    return result;
  }

  /** */
  public abstract static class SweepstakeServiceImplBase implements io.grpc.BindableService {

    /** */
    public void findSweepstakeByJoinCode(
        com.footyandsweep.SweepstakeServiceOuterClass.JoinCode request,
        io.grpc.stub.StreamObserver<com.footyandsweep.SweepstakeServiceOuterClass.Sweepstake>
            responseObserver) {
      asyncUnimplementedUnaryCall(getFindSweepstakeByJoinCodeMethod(), responseObserver);
    }

    /** */
    public void findSweepstakeById(
        com.footyandsweep.SweepstakeServiceOuterClass.SweepstakeId request,
        io.grpc.stub.StreamObserver<com.footyandsweep.SweepstakeServiceOuterClass.Sweepstake>
            responseObserver) {
      asyncUnimplementedUnaryCall(getFindSweepstakeByIdMethod(), responseObserver);
    }

    /** */
    public void getResultHelperMap(
        com.footyandsweep.SweepstakeServiceOuterClass.Sweepstake request,
        io.grpc.stub.StreamObserver<com.footyandsweep.SweepstakeServiceOuterClass.Map>
            responseObserver) {
      asyncUnimplementedUnaryCall(getGetResultHelperMapMethod(), responseObserver);
    }

    /** */
    public void requestNewSweepstake(
        com.footyandsweep.SweepstakeServiceOuterClass.Sweepstake request,
        io.grpc.stub.StreamObserver<com.footyandsweep.SweepstakeServiceOuterClass.Sweepstake>
            responseObserver) {
      asyncUnimplementedUnaryCall(getRequestNewSweepstakeMethod(), responseObserver);
    }

    @java.lang.Override
    public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
              getFindSweepstakeByJoinCodeMethod(),
              asyncUnaryCall(
                  new MethodHandlers<
                      com.footyandsweep.SweepstakeServiceOuterClass.JoinCode,
                      com.footyandsweep.SweepstakeServiceOuterClass.Sweepstake>(
                      this, METHODID_FIND_SWEEPSTAKE_BY_JOIN_CODE)))
          .addMethod(
              getFindSweepstakeByIdMethod(),
              asyncUnaryCall(
                  new MethodHandlers<
                      com.footyandsweep.SweepstakeServiceOuterClass.SweepstakeId,
                      com.footyandsweep.SweepstakeServiceOuterClass.Sweepstake>(
                      this, METHODID_FIND_SWEEPSTAKE_BY_ID)))
          .addMethod(
              getGetResultHelperMapMethod(),
              asyncUnaryCall(
                  new MethodHandlers<
                      com.footyandsweep.SweepstakeServiceOuterClass.Sweepstake,
                      com.footyandsweep.SweepstakeServiceOuterClass.Map>(
                      this, METHODID_GET_RESULT_HELPER_MAP)))
          .addMethod(
              getRequestNewSweepstakeMethod(),
              asyncServerStreamingCall(
                  new MethodHandlers<
                      com.footyandsweep.SweepstakeServiceOuterClass.Sweepstake,
                      com.footyandsweep.SweepstakeServiceOuterClass.Sweepstake>(
                      this, METHODID_REQUEST_NEW_SWEEPSTAKE)))
          .build();
    }
  }

  /** */
  public static final class SweepstakeServiceStub
      extends io.grpc.stub.AbstractStub<SweepstakeServiceStub> {
    private SweepstakeServiceStub(io.grpc.Channel channel) {
      super(channel);
    }

    private SweepstakeServiceStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected SweepstakeServiceStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new SweepstakeServiceStub(channel, callOptions);
    }

    /** */
    public void findSweepstakeByJoinCode(
        com.footyandsweep.SweepstakeServiceOuterClass.JoinCode request,
        io.grpc.stub.StreamObserver<com.footyandsweep.SweepstakeServiceOuterClass.Sweepstake>
            responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getFindSweepstakeByJoinCodeMethod(), getCallOptions()),
          request,
          responseObserver);
    }

    /** */
    public void findSweepstakeById(
        com.footyandsweep.SweepstakeServiceOuterClass.SweepstakeId request,
        io.grpc.stub.StreamObserver<com.footyandsweep.SweepstakeServiceOuterClass.Sweepstake>
            responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getFindSweepstakeByIdMethod(), getCallOptions()),
          request,
          responseObserver);
    }

    /** */
    public void getResultHelperMap(
        com.footyandsweep.SweepstakeServiceOuterClass.Sweepstake request,
        io.grpc.stub.StreamObserver<com.footyandsweep.SweepstakeServiceOuterClass.Map>
            responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getGetResultHelperMapMethod(), getCallOptions()),
          request,
          responseObserver);
    }

    /** */
    public void requestNewSweepstake(
        com.footyandsweep.SweepstakeServiceOuterClass.Sweepstake request,
        io.grpc.stub.StreamObserver<com.footyandsweep.SweepstakeServiceOuterClass.Sweepstake>
            responseObserver) {
      asyncServerStreamingCall(
          getChannel().newCall(getRequestNewSweepstakeMethod(), getCallOptions()),
          request,
          responseObserver);
    }
  }

  /** */
  public static final class SweepstakeServiceBlockingStub
      extends io.grpc.stub.AbstractStub<SweepstakeServiceBlockingStub> {
    private SweepstakeServiceBlockingStub(io.grpc.Channel channel) {
      super(channel);
    }

    private SweepstakeServiceBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected SweepstakeServiceBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new SweepstakeServiceBlockingStub(channel, callOptions);
    }

    /** */
    public com.footyandsweep.SweepstakeServiceOuterClass.Sweepstake findSweepstakeByJoinCode(
        com.footyandsweep.SweepstakeServiceOuterClass.JoinCode request) {
      return blockingUnaryCall(
          getChannel(), getFindSweepstakeByJoinCodeMethod(), getCallOptions(), request);
    }

    /** */
    public com.footyandsweep.SweepstakeServiceOuterClass.Sweepstake findSweepstakeById(
        com.footyandsweep.SweepstakeServiceOuterClass.SweepstakeId request) {
      return blockingUnaryCall(
          getChannel(), getFindSweepstakeByIdMethod(), getCallOptions(), request);
    }

    /** */
    public com.footyandsweep.SweepstakeServiceOuterClass.Map getResultHelperMap(
        com.footyandsweep.SweepstakeServiceOuterClass.Sweepstake request) {
      return blockingUnaryCall(
          getChannel(), getGetResultHelperMapMethod(), getCallOptions(), request);
    }

    /** */
    public java.util.Iterator<com.footyandsweep.SweepstakeServiceOuterClass.Sweepstake>
        requestNewSweepstake(com.footyandsweep.SweepstakeServiceOuterClass.Sweepstake request) {
      return blockingServerStreamingCall(
          getChannel(), getRequestNewSweepstakeMethod(), getCallOptions(), request);
    }
  }

  /** */
  public static final class SweepstakeServiceFutureStub
      extends io.grpc.stub.AbstractStub<SweepstakeServiceFutureStub> {
    private SweepstakeServiceFutureStub(io.grpc.Channel channel) {
      super(channel);
    }

    private SweepstakeServiceFutureStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected SweepstakeServiceFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new SweepstakeServiceFutureStub(channel, callOptions);
    }

    /** */
    public com.google.common.util.concurrent.ListenableFuture<
            com.footyandsweep.SweepstakeServiceOuterClass.Sweepstake>
        findSweepstakeByJoinCode(com.footyandsweep.SweepstakeServiceOuterClass.JoinCode request) {
      return futureUnaryCall(
          getChannel().newCall(getFindSweepstakeByJoinCodeMethod(), getCallOptions()), request);
    }

    /** */
    public com.google.common.util.concurrent.ListenableFuture<
            com.footyandsweep.SweepstakeServiceOuterClass.Sweepstake>
        findSweepstakeById(com.footyandsweep.SweepstakeServiceOuterClass.SweepstakeId request) {
      return futureUnaryCall(
          getChannel().newCall(getFindSweepstakeByIdMethod(), getCallOptions()), request);
    }

    /** */
    public com.google.common.util.concurrent.ListenableFuture<
            com.footyandsweep.SweepstakeServiceOuterClass.Map>
        getResultHelperMap(com.footyandsweep.SweepstakeServiceOuterClass.Sweepstake request) {
      return futureUnaryCall(
          getChannel().newCall(getGetResultHelperMapMethod(), getCallOptions()), request);
    }
  }

  private static final class MethodHandlers<Req, Resp>
      implements io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
          io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
          io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
          io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final SweepstakeServiceImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(SweepstakeServiceImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_FIND_SWEEPSTAKE_BY_JOIN_CODE:
          serviceImpl.findSweepstakeByJoinCode(
              (com.footyandsweep.SweepstakeServiceOuterClass.JoinCode) request,
              (io.grpc.stub.StreamObserver<
                      com.footyandsweep.SweepstakeServiceOuterClass.Sweepstake>)
                  responseObserver);
          break;
        case METHODID_FIND_SWEEPSTAKE_BY_ID:
          serviceImpl.findSweepstakeById(
              (com.footyandsweep.SweepstakeServiceOuterClass.SweepstakeId) request,
              (io.grpc.stub.StreamObserver<
                      com.footyandsweep.SweepstakeServiceOuterClass.Sweepstake>)
                  responseObserver);
          break;
        case METHODID_GET_RESULT_HELPER_MAP:
          serviceImpl.getResultHelperMap(
              (com.footyandsweep.SweepstakeServiceOuterClass.Sweepstake) request,
              (io.grpc.stub.StreamObserver<com.footyandsweep.SweepstakeServiceOuterClass.Map>)
                  responseObserver);
          break;
        case METHODID_REQUEST_NEW_SWEEPSTAKE:
          serviceImpl.requestNewSweepstake(
              (com.footyandsweep.SweepstakeServiceOuterClass.Sweepstake) request,
              (io.grpc.stub.StreamObserver<
                      com.footyandsweep.SweepstakeServiceOuterClass.Sweepstake>)
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

  private abstract static class SweepstakeServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier,
          io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    SweepstakeServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return com.footyandsweep.SweepstakeServiceOuterClass.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("SweepstakeService");
    }
  }

  private static final class SweepstakeServiceFileDescriptorSupplier
      extends SweepstakeServiceBaseDescriptorSupplier {
    SweepstakeServiceFileDescriptorSupplier() {}
  }

  private static final class SweepstakeServiceMethodDescriptorSupplier
      extends SweepstakeServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    SweepstakeServiceMethodDescriptorSupplier(String methodName) {
      this.methodName = methodName;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.MethodDescriptor getMethodDescriptor() {
      return getServiceDescriptor().findMethodByName(methodName);
    }
  }
}
