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
// file: ResultService.proto

import * as google_protobuf_empty_pb from "google-protobuf/google/protobuf/empty_pb";
import {grpc} from "@improbable-eng/grpc-web";

type ResultServicecheckForSweepstakeResults = {
    readonly methodName: string;
    readonly service: typeof ResultService;
    readonly requestStream: false;
    readonly responseStream: false;
    readonly requestType: typeof google_protobuf_empty_pb.Empty;
    readonly responseType: typeof google_protobuf_empty_pb.Empty;
};

export class ResultService {
    static readonly serviceName: string;
    static readonly checkForSweepstakeResults: ResultServicecheckForSweepstakeResults;
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

export class ResultServiceClient {
    readonly serviceHost: string;

    constructor(serviceHost: string, options?: grpc.RpcOptions);

    checkForSweepstakeResults(
        requestMessage: google_protobuf_empty_pb.Empty,
        metadata: grpc.Metadata,
        callback: (error: ServiceError | null, responseMessage: google_protobuf_empty_pb.Empty | null) => void
    ): UnaryResponse;
    checkForSweepstakeResults(
        requestMessage: google_protobuf_empty_pb.Empty,
        callback: (error: ServiceError | null, responseMessage: google_protobuf_empty_pb.Empty | null) => void
    ): UnaryResponse;
}

