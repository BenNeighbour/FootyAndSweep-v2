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

// package: com.footyandsweep
// file: TicketService.proto

import * as TicketService_pb from "./TicketService_pb";
import * as Common_pb from "./Common_pb";
import {grpc} from "@improbable-eng/grpc-web";

type TicketServicefindTicketById = {
    readonly methodName: string;
    readonly service: typeof TicketService;
    readonly requestStream: false;
    readonly responseStream: false;
    readonly requestType: typeof Common_pb.Id;
    readonly responseType: typeof TicketService_pb.Ticket;
};

type TicketServicegetAllTicketsBySweepstakeId = {
    readonly methodName: string;
    readonly service: typeof TicketService;
    readonly requestStream: false;
    readonly responseStream: false;
    readonly requestType: typeof Common_pb.Id;
    readonly responseType: typeof TicketService_pb.TicketList;
};

export class TicketService {
    static readonly serviceName: string;
    static readonly findTicketById: TicketServicefindTicketById;
    static readonly getAllTicketsBySweepstakeId: TicketServicegetAllTicketsBySweepstakeId;
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

export class TicketServiceClient {
    readonly serviceHost: string;

    constructor(serviceHost: string, options?: grpc.RpcOptions);

    findTicketById(
        requestMessage: Common_pb.Id,
        metadata: grpc.Metadata,
        callback: (error: ServiceError | null, responseMessage: TicketService_pb.Ticket | null) => void
    ): UnaryResponse;
    findTicketById(
        requestMessage: Common_pb.Id,
        callback: (error: ServiceError | null, responseMessage: TicketService_pb.Ticket | null) => void
    ): UnaryResponse;

    getAllTicketsBySweepstakeId(
        requestMessage: Common_pb.Id,
        metadata: grpc.Metadata,
        callback: (error: ServiceError | null, responseMessage: TicketService_pb.TicketList | null) => void
    ): UnaryResponse;
    getAllTicketsBySweepstakeId(
        requestMessage: Common_pb.Id,
        callback: (error: ServiceError | null, responseMessage: TicketService_pb.TicketList | null) => void
    ): UnaryResponse;
}

