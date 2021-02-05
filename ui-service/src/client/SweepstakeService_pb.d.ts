// package: com.footyandsweep
// file: SweepstakeService.proto

import * as jspb from "google-protobuf";
import * as google_protobuf_timestamp_pb from "google-protobuf/google/protobuf/timestamp_pb";

export class Sweepstake extends jspb.Message {
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
  static toObject(includeInstance: boolean, msg: Sweepstake): Sweepstake.AsObject;
  static extensions: {[key: number]: jspb.ExtensionFieldInfo<jspb.Message>};
  static extensionsBinary: {[key: number]: jspb.ExtensionFieldBinaryInfo<jspb.Message>};
  static serializeBinaryToWriter(message: Sweepstake, writer: jspb.BinaryWriter): void;
  static deserializeBinary(bytes: Uint8Array): Sweepstake;
  static deserializeBinaryFromReader(message: Sweepstake, reader: jspb.BinaryReader): Sweepstake;
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
  getJoincode(): string;
  setJoincode(value: string): void;

  serializeBinary(): Uint8Array;
  toObject(includeInstance?: boolean): JoinCode.AsObject;
  static toObject(includeInstance: boolean, msg: JoinCode): JoinCode.AsObject;
  static extensions: {[key: number]: jspb.ExtensionFieldInfo<jspb.Message>};
  static extensionsBinary: {[key: number]: jspb.ExtensionFieldBinaryInfo<jspb.Message>};
  static serializeBinaryToWriter(message: JoinCode, writer: jspb.BinaryWriter): void;
  static deserializeBinary(bytes: Uint8Array): JoinCode;
  static deserializeBinaryFromReader(message: JoinCode, reader: jspb.BinaryReader): JoinCode;
}

export namespace JoinCode {
  export type AsObject = {
    joincode: string,
  }
}

export class Id extends jspb.Message {
  getId(): string;
  setId(value: string): void;

  serializeBinary(): Uint8Array;
  toObject(includeInstance?: boolean): Id.AsObject;
  static toObject(includeInstance: boolean, msg: Id): Id.AsObject;
  static extensions: {[key: number]: jspb.ExtensionFieldInfo<jspb.Message>};
  static extensionsBinary: {[key: number]: jspb.ExtensionFieldBinaryInfo<jspb.Message>};
  static serializeBinaryToWriter(message: Id, writer: jspb.BinaryWriter): void;
  static deserializeBinary(bytes: Uint8Array): Id;
  static deserializeBinaryFromReader(message: Id, reader: jspb.BinaryReader): Id;
}

export namespace Id {
  export type AsObject = {
    id: string,
  }
}

export class Map extends jspb.Message {
  getPairsMap(): jspb.Map<number, string>;
  clearPairsMap(): void;
  serializeBinary(): Uint8Array;
  toObject(includeInstance?: boolean): Map.AsObject;
  static toObject(includeInstance: boolean, msg: Map): Map.AsObject;
  static extensions: {[key: number]: jspb.ExtensionFieldInfo<jspb.Message>};
  static extensionsBinary: {[key: number]: jspb.ExtensionFieldBinaryInfo<jspb.Message>};
  static serializeBinaryToWriter(message: Map, writer: jspb.BinaryWriter): void;
  static deserializeBinary(bytes: Uint8Array): Map;
  static deserializeBinaryFromReader(message: Map, reader: jspb.BinaryReader): Map;
}

export namespace Map {
  export type AsObject = {
    pairsMap: Array<[number, string]>,
  }
}

export class SweepstakeList extends jspb.Message {
  clearSweepstakesList(): void;
  getSweepstakesList(): Array<Sweepstake>;
  setSweepstakesList(value: Array<Sweepstake>): void;
  addSweepstakes(value?: Sweepstake, index?: number): Sweepstake;

  serializeBinary(): Uint8Array;
  toObject(includeInstance?: boolean): SweepstakeList.AsObject;
  static toObject(includeInstance: boolean, msg: SweepstakeList): SweepstakeList.AsObject;
  static extensions: {[key: number]: jspb.ExtensionFieldInfo<jspb.Message>};
  static extensionsBinary: {[key: number]: jspb.ExtensionFieldBinaryInfo<jspb.Message>};
  static serializeBinaryToWriter(message: SweepstakeList, writer: jspb.BinaryWriter): void;
  static deserializeBinary(bytes: Uint8Array): SweepstakeList;
  static deserializeBinaryFromReader(message: SweepstakeList, reader: jspb.BinaryReader): SweepstakeList;
}

export namespace SweepstakeList {
  export type AsObject = {
    sweepstakesList: Array<Sweepstake.AsObject>,
  }
}

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

