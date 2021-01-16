import * as jspb from 'google-protobuf'



export class Sweepstake extends jspb.Message {
  getId(): string;
  setId(value: string): Sweepstake;

  getName(): string;
  setName(value: string): Sweepstake;

  getJoincode(): string;
  setJoincode(value: string): Sweepstake;

  getStatus(): SweepstakeStatus;
  setStatus(value: SweepstakeStatus): Sweepstake;

  getIsprivate(): boolean;
  setIsprivate(value: boolean): Sweepstake;

  getOwnerid(): string;
  setOwnerid(value: string): Sweepstake;

  getSweepstakeeventid(): string;
  setSweepstakeeventid(value: string): Sweepstake;

  getSweepstaketype(): SweepstakeTypeCommon;
  setSweepstaketype(value: SweepstakeTypeCommon): Sweepstake;

  getProcessstatus(): ProcessStatus;
  setProcessstatus(value: ProcessStatus): Sweepstake;

  getSweepstakelistsize(): number;
  setSweepstakelistsize(value: number): Sweepstake;

  getMinimumplayers(): number;
  setMinimumplayers(value: number): Sweepstake;

  getMaximumplayertickets(): number;
  setMaximumplayertickets(value: number): Sweepstake;

  getStake(): BigDecimal | undefined;
  setStake(value?: BigDecimal): Sweepstake;
  hasStake(): boolean;
  clearStake(): Sweepstake;

  getTotalnumberoftickets(): number;
  setTotalnumberoftickets(value: number): Sweepstake;

  serializeBinary(): Uint8Array;
  toObject(includeInstance?: boolean): Sweepstake.AsObject;
  static toObject(includeInstance: boolean, msg: Sweepstake): Sweepstake.AsObject;
  static serializeBinaryToWriter(message: Sweepstake, writer: jspb.BinaryWriter): void;
  static deserializeBinary(bytes: Uint8Array): Sweepstake;
  static deserializeBinaryFromReader(message: Sweepstake, reader: jspb.BinaryReader): Sweepstake;
}

export namespace Sweepstake {
  export type AsObject = {
    id: string,
    name: string,
    joincode: string,
    status: SweepstakeStatus,
    isprivate: boolean,
    ownerid: string,
    sweepstakeeventid: string,
    sweepstaketype: SweepstakeTypeCommon,
    processstatus: ProcessStatus,
    sweepstakelistsize: number,
    minimumplayers: number,
    maximumplayertickets: number,
    stake?: BigDecimal.AsObject,
    totalnumberoftickets: number,
  }
}

export class BigDecimal extends jspb.Message {
  getScale(): number;
  setScale(value: number): BigDecimal;

  getIntVal(): BigInteger | undefined;
  setIntVal(value?: BigInteger): BigDecimal;
  hasIntVal(): boolean;
  clearIntVal(): BigDecimal;

  serializeBinary(): Uint8Array;
  toObject(includeInstance?: boolean): BigDecimal.AsObject;
  static toObject(includeInstance: boolean, msg: BigDecimal): BigDecimal.AsObject;
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
  setValue(value: Uint8Array | string): BigInteger;

  serializeBinary(): Uint8Array;
  toObject(includeInstance?: boolean): BigInteger.AsObject;
  static toObject(includeInstance: boolean, msg: BigInteger): BigInteger.AsObject;
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
  setJoincode(value: string): JoinCode;

  serializeBinary(): Uint8Array;
  toObject(includeInstance?: boolean): JoinCode.AsObject;
  static toObject(includeInstance: boolean, msg: JoinCode): JoinCode.AsObject;
  static serializeBinaryToWriter(message: JoinCode, writer: jspb.BinaryWriter): void;
  static deserializeBinary(bytes: Uint8Array): JoinCode;
  static deserializeBinaryFromReader(message: JoinCode, reader: jspb.BinaryReader): JoinCode;
}

export namespace JoinCode {
  export type AsObject = {
    joincode: string,
  }
}

export class SweepstakeId extends jspb.Message {
  getSweepstakeid(): string;
  setSweepstakeid(value: string): SweepstakeId;

  serializeBinary(): Uint8Array;
  toObject(includeInstance?: boolean): SweepstakeId.AsObject;
  static toObject(includeInstance: boolean, msg: SweepstakeId): SweepstakeId.AsObject;
  static serializeBinaryToWriter(message: SweepstakeId, writer: jspb.BinaryWriter): void;
  static deserializeBinary(bytes: Uint8Array): SweepstakeId;
  static deserializeBinaryFromReader(message: SweepstakeId, reader: jspb.BinaryReader): SweepstakeId;
}

export namespace SweepstakeId {
  export type AsObject = {
    sweepstakeid: string,
  }
}

export class Map extends jspb.Message {
  getPairsMap(): jspb.Map<number, string>;
  clearPairsMap(): Map;

  serializeBinary(): Uint8Array;
  toObject(includeInstance?: boolean): Map.AsObject;
  static toObject(includeInstance: boolean, msg: Map): Map.AsObject;
  static serializeBinaryToWriter(message: Map, writer: jspb.BinaryWriter): void;
  static deserializeBinary(bytes: Uint8Array): Map;
  static deserializeBinaryFromReader(message: Map, reader: jspb.BinaryReader): Map;
}

export namespace Map {
  export type AsObject = {
    pairsMap: Array<[number, string]>,
  }
}

export enum SweepstakeStatus { 
  OPEN = 0,
  ALLOCATED = 1,
  CLOSED = 2,
}
export enum SweepstakeTypeCommon { 
  CORRECT_SCORE_FT = 0,
  CORRECT_SCORE_HT = 1,
}
export enum ProcessStatus { 
  PENDING = 0,
  INVALID = 1,
  PERSISTED = 2,
}
