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
// file: AuthenticationService.proto

import * as AuthenticationService_pb from "./AuthenticationService_pb";
import {grpc} from "@improbable-eng/grpc-web";

type AuthenticationServicefindUserByUserId = {
    readonly methodName: string;
    readonly service: typeof AuthenticationService;
    readonly requestStream: false;
    readonly responseStream: false;
    readonly requestType: typeof AuthenticationService_pb.findUserByIdRequest;
    readonly responseType: typeof AuthenticationService_pb.User;
};

export class AuthenticationService {
    static readonly serviceName: string;
    static readonly findUserByUserId: AuthenticationServicefindUserByUserId;
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

export class AuthenticationServiceClient {
    readonly serviceHost: string;

    constructor(serviceHost: string, options?: grpc.RpcOptions);

    findUserByUserId(
        requestMessage: AuthenticationService_pb.findUserByIdRequest,
        metadata: grpc.Metadata,
        callback: (error: ServiceError | null, responseMessage: AuthenticationService_pb.User | null) => void
    ): UnaryResponse;
    findUserByUserId(
        requestMessage: AuthenticationService_pb.findUserByIdRequest,
        callback: (error: ServiceError | null, responseMessage: AuthenticationService_pb.User | null) => void
    ): UnaryResponse;
}

