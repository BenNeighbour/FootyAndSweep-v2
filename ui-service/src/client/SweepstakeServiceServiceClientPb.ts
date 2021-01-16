/**
 * @fileoverview gRPC-Web generated client stub for com.footyandsweep
 * @enhanceable
 * @public
 */

// GENERATED CODE -- DO NOT EDIT!


/* eslint-disable */
// @ts-nocheck


import * as grpcWeb from 'grpc-web';

import * as SweepstakeService_pb from './SweepstakeService_pb';


export class SweepstakeServiceClient {
  client_: grpcWeb.AbstractClientBase;
  hostname_: string;
  credentials_: null | { [index: string]: string; };
  options_: null | { [index: string]: any; };

  constructor (hostname: string,
               credentials?: null | { [index: string]: string; },
               options?: null | { [index: string]: any; }) {
    if (!options) options = {};
    if (!credentials) credentials = {};
    options['format'] = 'text';

    this.client_ = new grpcWeb.GrpcWebClientBase(options);
    this.hostname_ = hostname;
    this.credentials_ = credentials;
    this.options_ = options;
  }

  methodInfofindSweepstakeByJoinCode = new grpcWeb.AbstractClientBase.MethodInfo(
    SweepstakeService_pb.Sweepstake,
    (request: SweepstakeService_pb.JoinCode) => {
      return request.serializeBinary();
    },
    SweepstakeService_pb.Sweepstake.deserializeBinary
  );

  findSweepstakeByJoinCode(
    request: SweepstakeService_pb.JoinCode,
    metadata: grpcWeb.Metadata | null): Promise<SweepstakeService_pb.Sweepstake>;

  findSweepstakeByJoinCode(
    request: SweepstakeService_pb.JoinCode,
    metadata: grpcWeb.Metadata | null,
    callback: (err: grpcWeb.Error,
               response: SweepstakeService_pb.Sweepstake) => void): grpcWeb.ClientReadableStream<SweepstakeService_pb.Sweepstake>;

  findSweepstakeByJoinCode(
    request: SweepstakeService_pb.JoinCode,
    metadata: grpcWeb.Metadata | null,
    callback?: (err: grpcWeb.Error,
               response: SweepstakeService_pb.Sweepstake) => void) {
    if (callback !== undefined) {
      return this.client_.rpcCall(
        this.hostname_ +
          '/com.footyandsweep.SweepstakeService/findSweepstakeByJoinCode',
        request,
        metadata || {},
        this.methodInfofindSweepstakeByJoinCode,
        callback);
    }
    return this.client_.unaryCall(
    this.hostname_ +
      '/com.footyandsweep.SweepstakeService/findSweepstakeByJoinCode',
    request,
    metadata || {},
    this.methodInfofindSweepstakeByJoinCode);
  }

  methodInfofindSweepstakeById = new grpcWeb.AbstractClientBase.MethodInfo(
    SweepstakeService_pb.Sweepstake,
    (request: SweepstakeService_pb.SweepstakeId) => {
      return request.serializeBinary();
    },
    SweepstakeService_pb.Sweepstake.deserializeBinary
  );

  findSweepstakeById(
    request: SweepstakeService_pb.SweepstakeId,
    metadata: grpcWeb.Metadata | null): Promise<SweepstakeService_pb.Sweepstake>;

  findSweepstakeById(
    request: SweepstakeService_pb.SweepstakeId,
    metadata: grpcWeb.Metadata | null,
    callback: (err: grpcWeb.Error,
               response: SweepstakeService_pb.Sweepstake) => void): grpcWeb.ClientReadableStream<SweepstakeService_pb.Sweepstake>;

  findSweepstakeById(
    request: SweepstakeService_pb.SweepstakeId,
    metadata: grpcWeb.Metadata | null,
    callback?: (err: grpcWeb.Error,
               response: SweepstakeService_pb.Sweepstake) => void) {
    if (callback !== undefined) {
      return this.client_.rpcCall(
        this.hostname_ +
          '/com.footyandsweep.SweepstakeService/findSweepstakeById',
        request,
        metadata || {},
        this.methodInfofindSweepstakeById,
        callback);
    }
    return this.client_.unaryCall(
    this.hostname_ +
      '/com.footyandsweep.SweepstakeService/findSweepstakeById',
    request,
    metadata || {},
    this.methodInfofindSweepstakeById);
  }

  methodInfogetResultHelperMap = new grpcWeb.AbstractClientBase.MethodInfo(
    SweepstakeService_pb.Map,
    (request: SweepstakeService_pb.Sweepstake) => {
      return request.serializeBinary();
    },
    SweepstakeService_pb.Map.deserializeBinary
  );

  getResultHelperMap(
    request: SweepstakeService_pb.Sweepstake,
    metadata: grpcWeb.Metadata | null): Promise<SweepstakeService_pb.Map>;

  getResultHelperMap(
    request: SweepstakeService_pb.Sweepstake,
    metadata: grpcWeb.Metadata | null,
    callback: (err: grpcWeb.Error,
               response: SweepstakeService_pb.Map) => void): grpcWeb.ClientReadableStream<SweepstakeService_pb.Map>;

  getResultHelperMap(
    request: SweepstakeService_pb.Sweepstake,
    metadata: grpcWeb.Metadata | null,
    callback?: (err: grpcWeb.Error,
               response: SweepstakeService_pb.Map) => void) {
    if (callback !== undefined) {
      return this.client_.rpcCall(
        this.hostname_ +
          '/com.footyandsweep.SweepstakeService/getResultHelperMap',
        request,
        metadata || {},
        this.methodInfogetResultHelperMap,
        callback);
    }
    return this.client_.unaryCall(
    this.hostname_ +
      '/com.footyandsweep.SweepstakeService/getResultHelperMap',
    request,
    metadata || {},
    this.methodInfogetResultHelperMap);
  }

  methodInforequestNewSweepstake = new grpcWeb.AbstractClientBase.MethodInfo(
    SweepstakeService_pb.Sweepstake,
    (request: SweepstakeService_pb.Sweepstake) => {
      return request.serializeBinary();
    },
    SweepstakeService_pb.Sweepstake.deserializeBinary
  );

  requestNewSweepstake(
    request: SweepstakeService_pb.Sweepstake,
    metadata?: grpcWeb.Metadata) {
    return this.client_.serverStreaming(
      this.hostname_ +
        '/com.footyandsweep.SweepstakeService/requestNewSweepstake',
      request,
      metadata || {},
      this.methodInforequestNewSweepstake);
  }

}

