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
// file: SweepstakeService.proto

import * as jspb from "google-protobuf";

export class Sweepstake extends jspb.Message {
    static extensions: { [key: number]: jspb.ExtensionFieldInfo<jspb.Message> };
    static extensionsBinary: { [key: number]: jspb.ExtensionFieldBinaryInfo<jspb.Message> };

    static toObject(includeInstance: boolean, msg: Sweepstake): Sweepstake.AsObject;

    static serializeBinaryToWriter(message: Sweepstake, writer: jspb.BinaryWriter): void;

    static deserializeBinary(bytes: Uint8Array): Sweepstake;

    static deserializeBinaryFromReader(message: Sweepstake, reader: jspb.BinaryReader): Sweepstake;

    getId(): string;

    setId(value: string): void;

    getName(): string;

    setName(value: string): void;

    getJoincode(): string;

    setJoincode(value: string): void;

    getStatuscode(): number;

    setStatuscode(value: number): void;

    getIsprivate(): boolean;

    setIsprivate(value: boolean): void;

    getOwnerid(): string;

    setOwnerid(value: string): void;

    getSweepstakeeventid(): string;

    setSweepstakeeventid(value: string): void;

    getSweepstaketypecode(): number;

    setSweepstaketypecode(value: number): void;

    getSweepstakelistsize(): number;

    setSweepstakelistsize(value: number): void;

    getMinimumplayers(): number;

    setMinimumplayers(value: number): void;

    getMaximumplayertickets(): number;

    setMaximumplayertickets(value: number): void;

    getStake(): number;

    setStake(value: number): void;

    getTotalnumberoftickets(): number;

    setTotalnumberoftickets(value: number): void;

    getSweepstaketype(): SweepstakeTypeCommonMap[keyof SweepstakeTypeCommonMap];

    setSweepstaketype(value: SweepstakeTypeCommonMap[keyof SweepstakeTypeCommonMap]): void;

    getStatus(): SweepstakeStatusMap[keyof SweepstakeStatusMap];

    setStatus(value: SweepstakeStatusMap[keyof SweepstakeStatusMap]): void;

    getNumberofrange(): number;

    setNumberofrange(value: number): void;

    getNumberofmax(): number;

    setNumberofmax(value: number): void;

    getMaxnumberofranges(): number;

    setMaxnumberofranges(value: number): void;

    getCorrectscoremax(): number;

    setCorrectscoremax(value: number): void;

    getMinuterange(): number;

    setMinuterange(value: number): void;

    getIncludebench(): boolean;

    setIncludebench(value: boolean): void;

    getIncludestartinggoalkeeper(): boolean;

    setIncludestartinggoalkeeper(value: boolean): void;

    getIncludenogoalscorer(): boolean;

    setIncludenogoalscorer(value: boolean): void;

    getIncludeowngoals(): boolean;

    setIncludeowngoals(value: boolean): void;

    getFootballmatchid(): string;

    setFootballmatchid(value: string): void;

    serializeBinary(): Uint8Array;

    toObject(includeInstance?: boolean): Sweepstake.AsObject;
}

export namespace Sweepstake {
    export type AsObject = {
        id: string,
        name: string,
        joincode: string,
        statuscode: number,
        isprivate: boolean,
        ownerid: string,
        sweepstakeeventid: string,
        sweepstaketypecode: number,
        sweepstakelistsize: number,
        minimumplayers: number,
        maximumplayertickets: number,
        stake: number,
        totalnumberoftickets: number,
        sweepstaketype: SweepstakeTypeCommonMap[keyof SweepstakeTypeCommonMap],
        status: SweepstakeStatusMap[keyof SweepstakeStatusMap],
        numberofrange: number,
        numberofmax: number,
        maxnumberofranges: number,
        correctscoremax: number,
        minuterange: number,
        includebench: boolean,
        includestartinggoalkeeper: boolean,
        includenogoalscorer: boolean,
        includeowngoals: boolean,
        footballmatchid: string,
    }
}

export class JoinCode extends jspb.Message {
    static extensions: { [key: number]: jspb.ExtensionFieldInfo<jspb.Message> };
    static extensionsBinary: { [key: number]: jspb.ExtensionFieldBinaryInfo<jspb.Message> };

    static toObject(includeInstance: boolean, msg: JoinCode): JoinCode.AsObject;

    static serializeBinaryToWriter(message: JoinCode, writer: jspb.BinaryWriter): void;

    static deserializeBinary(bytes: Uint8Array): JoinCode;

    static deserializeBinaryFromReader(message: JoinCode, reader: jspb.BinaryReader): JoinCode;

    getJoincode(): string;

    setJoincode(value: string): void;

    serializeBinary(): Uint8Array;

    toObject(includeInstance?: boolean): JoinCode.AsObject;
}

export namespace JoinCode {
    export type AsObject = {
        joincode: string,
    }
}

export class Pair extends jspb.Message {
    static extensions: { [key: number]: jspb.ExtensionFieldInfo<jspb.Message> };
    static extensionsBinary: { [key: number]: jspb.ExtensionFieldBinaryInfo<jspb.Message> };

    static toObject(includeInstance: boolean, msg: Pair): Pair.AsObject;

    static serializeBinaryToWriter(message: Pair, writer: jspb.BinaryWriter): void;

    static deserializeBinary(bytes: Uint8Array): Pair;

    static deserializeBinaryFromReader(message: Pair, reader: jspb.BinaryReader): Pair;

    getKey(): number;

    setKey(value: number): void;

    getValue(): string;

    setValue(value: string): void;

    serializeBinary(): Uint8Array;

    toObject(includeInstance?: boolean): Pair.AsObject;
}

export namespace Pair {
    export type AsObject = {
        key: number,
        value: string,
    }
}

export class PairList extends jspb.Message {
    static extensions: { [key: number]: jspb.ExtensionFieldInfo<jspb.Message> };
    static extensionsBinary: { [key: number]: jspb.ExtensionFieldBinaryInfo<jspb.Message> };

    static toObject(includeInstance: boolean, msg: PairList): PairList.AsObject;

    static serializeBinaryToWriter(message: PairList, writer: jspb.BinaryWriter): void;

    static deserializeBinary(bytes: Uint8Array): PairList;

    static deserializeBinaryFromReader(message: PairList, reader: jspb.BinaryReader): PairList;

    clearPairsList(): void;

    getPairsList(): Array<Pair>;

    setPairsList(value: Array<Pair>): void;

    addPairs(value?: Pair, index?: number): Pair;

    serializeBinary(): Uint8Array;

    toObject(includeInstance?: boolean): PairList.AsObject;
}

export namespace PairList {
    export type AsObject = {
        pairsList: Array<Pair.AsObject>,
    }
}

export class SweepstakeList extends jspb.Message {
    static extensions: { [key: number]: jspb.ExtensionFieldInfo<jspb.Message> };
    static extensionsBinary: { [key: number]: jspb.ExtensionFieldBinaryInfo<jspb.Message> };

    static toObject(includeInstance: boolean, msg: SweepstakeList): SweepstakeList.AsObject;

    static serializeBinaryToWriter(message: SweepstakeList, writer: jspb.BinaryWriter): void;

    static deserializeBinary(bytes: Uint8Array): SweepstakeList;

    static deserializeBinaryFromReader(message: SweepstakeList, reader: jspb.BinaryReader): SweepstakeList;

    clearSweepstakesList(): void;

    getSweepstakesList(): Array<Sweepstake>;

    setSweepstakesList(value: Array<Sweepstake>): void;

    addSweepstakes(value?: Sweepstake, index?: number): Sweepstake;

    serializeBinary(): Uint8Array;

    toObject(includeInstance?: boolean): SweepstakeList.AsObject;
}

export namespace SweepstakeList {
    export type AsObject = {
        sweepstakesList: Array<Sweepstake.AsObject>,
    }
}

export const sweepstakeStatus: jspb.ExtensionFieldInfo<string>;

export const sweepstakeType: jspb.ExtensionFieldInfo<string>;

export interface SweepstakeStatusMap {
    OPEN: 0;
    ALLOCATED: 1;
    CLOSED: 2;
}

export const SweepstakeStatus: SweepstakeStatusMap;

export interface SweepstakeTypeCommonMap {
    CORRECT_SCORE_FT: 0;
    CORRECT_SCORE_HT: 1;
}

export const SweepstakeTypeCommon: SweepstakeTypeCommonMap;

