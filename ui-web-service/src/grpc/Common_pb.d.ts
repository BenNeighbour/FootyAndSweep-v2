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
// file: Common.proto

import * as jspb from "google-protobuf";

export class Id extends jspb.Message {
    static extensions: { [key: number]: jspb.ExtensionFieldInfo<jspb.Message> };
    static extensionsBinary: { [key: number]: jspb.ExtensionFieldBinaryInfo<jspb.Message> };

    static toObject(includeInstance: boolean, msg: Id): Id.AsObject;

    static serializeBinaryToWriter(message: Id, writer: jspb.BinaryWriter): void;

    static deserializeBinary(bytes: Uint8Array): Id;

    static deserializeBinaryFromReader(message: Id, reader: jspb.BinaryReader): Id;

    getId(): string;

    setId(value: string): void;

    serializeBinary(): Uint8Array;

    toObject(includeInstance?: boolean): Id.AsObject;
}

export namespace Id {
    export type AsObject = {
        id: string,
    }
}

export class Ids extends jspb.Message {
    static extensions: { [key: number]: jspb.ExtensionFieldInfo<jspb.Message> };
    static extensionsBinary: { [key: number]: jspb.ExtensionFieldBinaryInfo<jspb.Message> };

    static toObject(includeInstance: boolean, msg: Ids): Ids.AsObject;

    static serializeBinaryToWriter(message: Ids, writer: jspb.BinaryWriter): void;

    static deserializeBinary(bytes: Uint8Array): Ids;

    static deserializeBinaryFromReader(message: Ids, reader: jspb.BinaryReader): Ids;

    clearIdList(): void;

    getIdList(): Array<string>;

    setIdList(value: Array<string>): void;

    addId(value: string, index?: number): string;

    serializeBinary(): Uint8Array;

    toObject(includeInstance?: boolean): Ids.AsObject;
}

export namespace Ids {
    export type AsObject = {
        idList: Array<string>,
    }
}

export class Map extends jspb.Message {
    static extensions: { [key: number]: jspb.ExtensionFieldInfo<jspb.Message> };
    static extensionsBinary: { [key: number]: jspb.ExtensionFieldBinaryInfo<jspb.Message> };

    static toObject(includeInstance: boolean, msg: Map): Map.AsObject;

    static serializeBinaryToWriter(message: Map, writer: jspb.BinaryWriter): void;

    static deserializeBinary(bytes: Uint8Array): Map;

    static deserializeBinaryFromReader(message: Map, reader: jspb.BinaryReader): Map;

    getPairsMap(): jspb.Map<number, string>;

    clearPairsMap(): void;

    serializeBinary(): Uint8Array;

    toObject(includeInstance?: boolean): Map.AsObject;
}

export namespace Map {
    export type AsObject = {
        pairsMap: Array<[number, string]>,
    }
}

