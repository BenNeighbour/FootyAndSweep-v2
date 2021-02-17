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
    comments = "Source: TicketService.proto")
public final class TicketServiceGrpc {

  private TicketServiceGrpc() {}

  public static final String SERVICE_NAME = "com.footyandsweep.TicketService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<com.footyandsweep.Common.Id,
      com.footyandsweep.TicketServiceOuterClass.Ticket> getFindTicketByIdMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "findTicketById",
      requestType = com.footyandsweep.Common.Id.class,
      responseType = com.footyandsweep.TicketServiceOuterClass.Ticket.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.footyandsweep.Common.Id,
      com.footyandsweep.TicketServiceOuterClass.Ticket> getFindTicketByIdMethod() {
    io.grpc.MethodDescriptor<com.footyandsweep.Common.Id, com.footyandsweep.TicketServiceOuterClass.Ticket> getFindTicketByIdMethod;
    if ((getFindTicketByIdMethod = TicketServiceGrpc.getFindTicketByIdMethod) == null) {
      synchronized (TicketServiceGrpc.class) {
        if ((getFindTicketByIdMethod = TicketServiceGrpc.getFindTicketByIdMethod) == null) {
          TicketServiceGrpc.getFindTicketByIdMethod = getFindTicketByIdMethod = 
              io.grpc.MethodDescriptor.<com.footyandsweep.Common.Id, com.footyandsweep.TicketServiceOuterClass.Ticket>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "com.footyandsweep.TicketService", "findTicketById"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.footyandsweep.Common.Id.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.footyandsweep.TicketServiceOuterClass.Ticket.getDefaultInstance()))
                  .setSchemaDescriptor(new TicketServiceMethodDescriptorSupplier("findTicketById"))
                  .build();
          }
        }
     }
     return getFindTicketByIdMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.footyandsweep.Common.Id,
      com.footyandsweep.TicketServiceOuterClass.TicketList> getGetAllTicketsBySweepstakeIdMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "getAllTicketsBySweepstakeId",
      requestType = com.footyandsweep.Common.Id.class,
      responseType = com.footyandsweep.TicketServiceOuterClass.TicketList.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.footyandsweep.Common.Id,
      com.footyandsweep.TicketServiceOuterClass.TicketList> getGetAllTicketsBySweepstakeIdMethod() {
    io.grpc.MethodDescriptor<com.footyandsweep.Common.Id, com.footyandsweep.TicketServiceOuterClass.TicketList> getGetAllTicketsBySweepstakeIdMethod;
    if ((getGetAllTicketsBySweepstakeIdMethod = TicketServiceGrpc.getGetAllTicketsBySweepstakeIdMethod) == null) {
      synchronized (TicketServiceGrpc.class) {
        if ((getGetAllTicketsBySweepstakeIdMethod = TicketServiceGrpc.getGetAllTicketsBySweepstakeIdMethod) == null) {
          TicketServiceGrpc.getGetAllTicketsBySweepstakeIdMethod = getGetAllTicketsBySweepstakeIdMethod = 
              io.grpc.MethodDescriptor.<com.footyandsweep.Common.Id, com.footyandsweep.TicketServiceOuterClass.TicketList>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "com.footyandsweep.TicketService", "getAllTicketsBySweepstakeId"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.footyandsweep.Common.Id.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.footyandsweep.TicketServiceOuterClass.TicketList.getDefaultInstance()))
                  .setSchemaDescriptor(new TicketServiceMethodDescriptorSupplier("getAllTicketsBySweepstakeId"))
                  .build();
          }
        }
     }
     return getGetAllTicketsBySweepstakeIdMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static TicketServiceStub newStub(io.grpc.Channel channel) {
    return new TicketServiceStub(channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static TicketServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    return new TicketServiceBlockingStub(channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static TicketServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    return new TicketServiceFutureStub(channel);
  }

  /**
   */
  public static abstract class TicketServiceImplBase implements io.grpc.BindableService {

    /**
     */
    public void findTicketById(com.footyandsweep.Common.Id request,
        io.grpc.stub.StreamObserver<com.footyandsweep.TicketServiceOuterClass.Ticket> responseObserver) {
      asyncUnimplementedUnaryCall(getFindTicketByIdMethod(), responseObserver);
    }

    /**
     */
    public void getAllTicketsBySweepstakeId(com.footyandsweep.Common.Id request,
        io.grpc.stub.StreamObserver<com.footyandsweep.TicketServiceOuterClass.TicketList> responseObserver) {
      asyncUnimplementedUnaryCall(getGetAllTicketsBySweepstakeIdMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getFindTicketByIdMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.footyandsweep.Common.Id,
                com.footyandsweep.TicketServiceOuterClass.Ticket>(
                  this, METHODID_FIND_TICKET_BY_ID)))
          .addMethod(
            getGetAllTicketsBySweepstakeIdMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.footyandsweep.Common.Id,
                com.footyandsweep.TicketServiceOuterClass.TicketList>(
                  this, METHODID_GET_ALL_TICKETS_BY_SWEEPSTAKE_ID)))
          .build();
    }
  }

  /**
   */
  public static final class TicketServiceStub extends io.grpc.stub.AbstractStub<TicketServiceStub> {
    private TicketServiceStub(io.grpc.Channel channel) {
      super(channel);
    }

    private TicketServiceStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected TicketServiceStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new TicketServiceStub(channel, callOptions);
    }

    /**
     */
    public void findTicketById(com.footyandsweep.Common.Id request,
        io.grpc.stub.StreamObserver<com.footyandsweep.TicketServiceOuterClass.Ticket> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getFindTicketByIdMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void getAllTicketsBySweepstakeId(com.footyandsweep.Common.Id request,
        io.grpc.stub.StreamObserver<com.footyandsweep.TicketServiceOuterClass.TicketList> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getGetAllTicketsBySweepstakeIdMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   */
  public static final class TicketServiceBlockingStub extends io.grpc.stub.AbstractStub<TicketServiceBlockingStub> {
    private TicketServiceBlockingStub(io.grpc.Channel channel) {
      super(channel);
    }

    private TicketServiceBlockingStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected TicketServiceBlockingStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new TicketServiceBlockingStub(channel, callOptions);
    }

    /**
     */
    public com.footyandsweep.TicketServiceOuterClass.Ticket findTicketById(com.footyandsweep.Common.Id request) {
      return blockingUnaryCall(
          getChannel(), getFindTicketByIdMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.footyandsweep.TicketServiceOuterClass.TicketList getAllTicketsBySweepstakeId(com.footyandsweep.Common.Id request) {
      return blockingUnaryCall(
          getChannel(), getGetAllTicketsBySweepstakeIdMethod(), getCallOptions(), request);
    }
  }

  /**
   */
  public static final class TicketServiceFutureStub extends io.grpc.stub.AbstractStub<TicketServiceFutureStub> {
    private TicketServiceFutureStub(io.grpc.Channel channel) {
      super(channel);
    }

    private TicketServiceFutureStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected TicketServiceFutureStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new TicketServiceFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.footyandsweep.TicketServiceOuterClass.Ticket> findTicketById(
        com.footyandsweep.Common.Id request) {
      return futureUnaryCall(
          getChannel().newCall(getFindTicketByIdMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.footyandsweep.TicketServiceOuterClass.TicketList> getAllTicketsBySweepstakeId(
        com.footyandsweep.Common.Id request) {
      return futureUnaryCall(
          getChannel().newCall(getGetAllTicketsBySweepstakeIdMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_FIND_TICKET_BY_ID = 0;
  private static final int METHODID_GET_ALL_TICKETS_BY_SWEEPSTAKE_ID = 1;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final TicketServiceImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(TicketServiceImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_FIND_TICKET_BY_ID:
          serviceImpl.findTicketById((com.footyandsweep.Common.Id) request,
              (io.grpc.stub.StreamObserver<com.footyandsweep.TicketServiceOuterClass.Ticket>) responseObserver);
          break;
        case METHODID_GET_ALL_TICKETS_BY_SWEEPSTAKE_ID:
          serviceImpl.getAllTicketsBySweepstakeId((com.footyandsweep.Common.Id) request,
              (io.grpc.stub.StreamObserver<com.footyandsweep.TicketServiceOuterClass.TicketList>) responseObserver);
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

  private static abstract class TicketServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    TicketServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return com.footyandsweep.TicketServiceOuterClass.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("TicketService");
    }
  }

  private static final class TicketServiceFileDescriptorSupplier
      extends TicketServiceBaseDescriptorSupplier {
    TicketServiceFileDescriptorSupplier() {}
  }

  private static final class TicketServiceMethodDescriptorSupplier
      extends TicketServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    TicketServiceMethodDescriptorSupplier(String methodName) {
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
      synchronized (TicketServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new TicketServiceFileDescriptorSupplier())
              .addMethod(getFindTicketByIdMethod())
              .addMethod(getGetAllTicketsBySweepstakeIdMethod())
              .build();
        }
      }
    }
    return result;
  }
}
