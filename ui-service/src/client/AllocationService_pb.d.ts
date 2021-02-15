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
// file: AllocationService.proto

import * as jspb from "google-protobuf";

export class Allocation extends jspb.Message {
    static extensions: { [key: number]: jspb.ExtensionFieldInfo<jspb.Message> };
    static extensionsBinary: { [key: number]: jspb.ExtensionFieldBinaryInfo<jspb.Message> };

    static toObject(includeInstance: boolean, msg: Allocation): Allocation.AsObject;

    static serializeBinaryToWriter(message: Allocation, writer: jspb.BinaryWriter): void;

    static deserializeBinary(bytes: Uint8Array): Allocation;

    static deserializeBinaryFromReader(message: Allocation, reader: jspb.BinaryReader): Allocation;

    getId(): string;

    setId(value: string): void;

    getDescription(): string;

    setDescription(value: string): void;

    getCode(): number;

    setCode(value: number): void;

    getTicketid(): string;

    setTicketid(value: string): void;

    getPlayerid(): string;

    setPlayerid(value: string): void;

    serializeBinary(): Uint8Array;

    toObject(includeInstance?: boolean): Allocation.AsObject;
}

export namespace Allocation {
    export type AsObject = {
        id: string,
        description: string,
        code: number,
        ticketid: string,
        playerid: string,
    }
}

