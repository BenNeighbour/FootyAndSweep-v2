// package: com.footyandsweep
// file: SweepstakeService.proto

import * as jspb from "google-protobuf";

export class Sweepstake extends jspb.Message {
  getId(): string;
  setId(value: string): void;

  getName(): string;
  setName(value: string): void;

  getJoincode(): string;
  setJoincode(value: string): void;

  getStatus(): SweepstakeStatusMap[keyof SweepstakeStatusMap];
  setStatus(value: SweepstakeStatusMap[keyof SweepstakeStatusMap]): void;

  getIsprivate(): boolean;
  setIsprivate(value: boolean): void;

  getOwnerid(): string;
  setOwnerid(value: string): void;

  getSweepstakeeventid(): string;
  setSweepstakeeventid(value: string): void;

  getSweepstaketype(): SweepstakeTypeCommonMap[keyof SweepstakeTypeCommonMap];
  setSweepstaketype(value: SweepstakeTypeCommonMap[keyof SweepstakeTypeCommonMap]): void;

  getSweepstakelistsize(): number;
  setSweepstakelistsize(value: number): void;

  getMinimumplayers(): number;
  setMinimumplayers(value: number): void;

  getMaximumplayertickets(): number;
  setMaximumplayertickets(value: number): void;

  hasStake(): boolean;
  clearStake(): void;
  getStake(): BigDecimal | undefined;
  setStake(value?: BigDecimal): void;

  getTotalnumberoftickets(): number;
  setTotalnumberoftickets(value: number): void;

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
    status: SweepstakeStatusMap[keyof SweepstakeStatusMap],
    isprivate: boolean,
    ownerid: string,
    sweepstakeeventid: string,
    sweepstaketype: SweepstakeTypeCommonMap[keyof SweepstakeTypeCommonMap],
    sweepstakelistsize: number,
    minimumplayers: number,
    maximumplayertickets: number,
    stake?: BigDecimal.AsObject,
    totalnumberoftickets: number,
  }
}

export class BigDecimal extends jspb.Message {
  getScale(): number;
  setScale(value: number): void;

  hasIntVal(): boolean;
  clearIntVal(): void;
  getIntVal(): BigInteger | undefined;
  setIntVal(value?: BigInteger): void;

  serializeBinary(): Uint8Array;
  toObject(includeInstance?: boolean): BigDecimal.AsObject;
  static toObject(includeInstance: boolean, msg: BigDecimal): BigDecimal.AsObject;
  static extensions: {[key: number]: jspb.ExtensionFieldInfo<jspb.Message>};
  static extensionsBinary: {[key: number]: jspb.ExtensionFieldBinaryInfo<jspb.Message>};
  static serializeBinaryToWriter(message: BigDecimal, writer: jspb.BinaryWriter): void;
  static deserializeBinary(bytes: Uint8Array): BigDecimal;
  static deserializeBinaryFromReader(message: BigDecimal, reader: jspb.BinaryReader): BigDecimal;
}

export namespace BigDecimal {
  export type AsObject = {
    scale: number,
    intVal?: BigInteger.AsObject,
  }
}

export class BigInteger extends jspb.Message {
  getValue(): Uint8Array | string;
  getValue_asU8(): Uint8Array;
  getValue_asB64(): string;
  setValue(value: Uint8Array | string): void;

  serializeBinary(): Uint8Array;
  toObject(includeInstance?: boolean): BigInteger.AsObject;
  static toObject(includeInstance: boolean, msg: BigInteger): BigInteger.AsObject;
  static extensions: {[key: number]: jspb.ExtensionFieldInfo<jspb.Message>};
  static extensionsBinary: {[key: number]: jspb.ExtensionFieldBinaryInfo<jspb.Message>};
  static serializeBinaryToWriter(message: BigInteger, writer: jspb.BinaryWriter): void;
  static deserializeBinary(bytes: Uint8Array): BigInteger;
  static deserializeBinaryFromReader(message: BigInteger, reader: jspb.BinaryReader): BigInteger;
}

export namespace BigInteger {
  export type AsObject = {
    value: Uint8Array | string,
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

