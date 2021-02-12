// package: com.footyandsweep
// file: SweepstakeService.proto

import * as SweepstakeService_pb from "./SweepstakeService_pb";
import * as Common_pb from "./Common_pb";
import {grpc} from "@improbable-eng/grpc-web";

type SweepstakeServicefindSweepstakeByJoinCode = {
  readonly methodName: string;
  readonly service: typeof SweepstakeService;
  readonly requestStream: false;
  readonly responseStream: false;
  readonly requestType: typeof SweepstakeService_pb.JoinCode;
  readonly responseType: typeof SweepstakeService_pb.Sweepstake;
};

type SweepstakeServicefindSweepstakeByFootballMatchId = {
  readonly methodName: string;
  readonly service: typeof SweepstakeService;
  readonly requestStream: false;
  readonly responseStream: false;
  readonly requestType: typeof Common_pb.Id;
  readonly responseType: typeof SweepstakeService_pb.SweepstakeList;
};

type SweepstakeServicefindSweepstakeById = {
  readonly methodName: string;
  readonly service: typeof SweepstakeService;
  readonly requestStream: false;
  readonly responseStream: false;
  readonly requestType: typeof Common_pb.Id;
  readonly responseType: typeof SweepstakeService_pb.Sweepstake;
};

type SweepstakeServicegetResultHelperMap = {
  readonly methodName: string;
  readonly service: typeof SweepstakeService;
  readonly requestStream: false;
  readonly responseStream: false;
  readonly requestType: typeof SweepstakeService_pb.Sweepstake;
  readonly responseType: typeof Common_pb.Map;
};

type SweepstakeServicerequestNewSweepstake = {
  readonly methodName: string;
  readonly service: typeof SweepstakeService;
  readonly requestStream: false;
  readonly responseStream: false;
  readonly requestType: typeof SweepstakeService_pb.Sweepstake;
  readonly responseType: typeof SweepstakeService_pb.Sweepstake;
};

type SweepstakeServicegetAllSweepstakeParticipants = {
  readonly methodName: string;
  readonly service: typeof SweepstakeService;
  readonly requestStream: false;
  readonly responseStream: false;
  readonly requestType: typeof Common_pb.Id;
  readonly responseType: typeof Common_pb.Ids;
};

type SweepstakeServiceresultHelper = {
  readonly methodName: string;
  readonly service: typeof SweepstakeService;
  readonly requestStream: false;
  readonly responseStream: false;
  readonly requestType: typeof SweepstakeService_pb.Sweepstake;
  readonly responseType: typeof SweepstakeService_pb.PairList;
};

export class SweepstakeService {
  static readonly serviceName: string;
  static readonly findSweepstakeByJoinCode: SweepstakeServicefindSweepstakeByJoinCode;
  static readonly findSweepstakeByFootballMatchId: SweepstakeServicefindSweepstakeByFootballMatchId;
  static readonly findSweepstakeById: SweepstakeServicefindSweepstakeById;
  static readonly getResultHelperMap: SweepstakeServicegetResultHelperMap;
  static readonly requestNewSweepstake: SweepstakeServicerequestNewSweepstake;
  static readonly getAllSweepstakeParticipants: SweepstakeServicegetAllSweepstakeParticipants;
  static readonly resultHelper: SweepstakeServiceresultHelper;
}

export type ServiceError = { message: string, code: number; metadata: grpc.Metadata }
export type Status = { details: string, code: number; metadata: grpc.Metadata }

interface UnaryResponse {
  cancel(): void;
}
interface ResponseStream<T> {
  cancel(): void;
  on(type: 'data', handler: (message: T) => void): ResponseStream<T>;
  on(type: 'end', handler: (status?: Status) => void): ResponseStream<T>;
  on(type: 'status', handler: (status: Status) => void): ResponseStream<T>;
}
interface RequestStream<T> {
  write(message: T): RequestStream<T>;
  end(): void;
  cancel(): void;
  on(type: 'end', handler: (status?: Status) => void): RequestStream<T>;
  on(type: 'status', handler: (status: Status) => void): RequestStream<T>;
}
interface BidirectionalStream<ReqT, ResT> {
  write(message: ReqT): BidirectionalStream<ReqT, ResT>;
  end(): void;
  cancel(): void;
  on(type: 'data', handler: (message: ResT) => void): BidirectionalStream<ReqT, ResT>;
  on(type: 'end', handler: (status?: Status) => void): BidirectionalStream<ReqT, ResT>;
  on(type: 'status', handler: (status: Status) => void): BidirectionalStream<ReqT, ResT>;
}

export class SweepstakeServiceClient {
  readonly serviceHost: string;

  constructor(serviceHost: string, options?: grpc.RpcOptions);
  findSweepstakeByJoinCode(
    requestMessage: SweepstakeService_pb.JoinCode,
    metadata: grpc.Metadata,
    callback: (error: ServiceError|null, responseMessage: SweepstakeService_pb.Sweepstake|null) => void
  ): UnaryResponse;
  findSweepstakeByJoinCode(
    requestMessage: SweepstakeService_pb.JoinCode,
    callback: (error: ServiceError|null, responseMessage: SweepstakeService_pb.Sweepstake|null) => void
  ): UnaryResponse;
  findSweepstakeByFootballMatchId(
    requestMessage: Common_pb.Id,
    metadata: grpc.Metadata,
    callback: (error: ServiceError|null, responseMessage: SweepstakeService_pb.SweepstakeList|null) => void
  ): UnaryResponse;
  findSweepstakeByFootballMatchId(
    requestMessage: Common_pb.Id,
    callback: (error: ServiceError|null, responseMessage: SweepstakeService_pb.SweepstakeList|null) => void
  ): UnaryResponse;
  findSweepstakeById(
    requestMessage: Common_pb.Id,
    metadata: grpc.Metadata,
    callback: (error: ServiceError|null, responseMessage: SweepstakeService_pb.Sweepstake|null) => void
  ): UnaryResponse;
  findSweepstakeById(
    requestMessage: Common_pb.Id,
    callback: (error: ServiceError|null, responseMessage: SweepstakeService_pb.Sweepstake|null) => void
  ): UnaryResponse;
  getResultHelperMap(
    requestMessage: SweepstakeService_pb.Sweepstake,
    metadata: grpc.Metadata,
    callback: (error: ServiceError|null, responseMessage: Common_pb.Map|null) => void
  ): UnaryResponse;
  getResultHelperMap(
    requestMessage: SweepstakeService_pb.Sweepstake,
    callback: (error: ServiceError|null, responseMessage: Common_pb.Map|null) => void
  ): UnaryResponse;
  requestNewSweepstake(
    requestMessage: SweepstakeService_pb.Sweepstake,
    metadata: grpc.Metadata,
    callback: (error: ServiceError|null, responseMessage: SweepstakeService_pb.Sweepstake|null) => void
  ): UnaryResponse;
  requestNewSweepstake(
    requestMessage: SweepstakeService_pb.Sweepstake,
    callback: (error: ServiceError|null, responseMessage: SweepstakeService_pb.Sweepstake|null) => void
  ): UnaryResponse;
  getAllSweepstakeParticipants(
    requestMessage: Common_pb.Id,
    metadata: grpc.Metadata,
    callback: (error: ServiceError|null, responseMessage: Common_pb.Ids|null) => void
  ): UnaryResponse;
  getAllSweepstakeParticipants(
    requestMessage: Common_pb.Id,
    callback: (error: ServiceError|null, responseMessage: Common_pb.Ids|null) => void
  ): UnaryResponse;
  resultHelper(
    requestMessage: SweepstakeService_pb.Sweepstake,
    metadata: grpc.Metadata,
    callback: (error: ServiceError|null, responseMessage: SweepstakeService_pb.PairList|null) => void
  ): UnaryResponse;
  resultHelper(
    requestMessage: SweepstakeService_pb.Sweepstake,
    callback: (error: ServiceError|null, responseMessage: SweepstakeService_pb.PairList|null) => void
  ): UnaryResponse;
}

