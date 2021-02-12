// package: com.footyandsweep
// file: Common.proto

import * as jspb from "google-protobuf";

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

export class Ids extends jspb.Message {
  clearIdList(): void;
  getIdList(): Array<string>;
  setIdList(value: Array<string>): void;
  addId(value: string, index?: number): string;

  serializeBinary(): Uint8Array;
  toObject(includeInstance?: boolean): Ids.AsObject;
  static toObject(includeInstance: boolean, msg: Ids): Ids.AsObject;
  static extensions: {[key: number]: jspb.ExtensionFieldInfo<jspb.Message>};
  static extensionsBinary: {[key: number]: jspb.ExtensionFieldBinaryInfo<jspb.Message>};
  static serializeBinaryToWriter(message: Ids, writer: jspb.BinaryWriter): void;
  static deserializeBinary(bytes: Uint8Array): Ids;
  static deserializeBinaryFromReader(message: Ids, reader: jspb.BinaryReader): Ids;
}

export namespace Ids {
  export type AsObject = {
    idList: Array<string>,
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

