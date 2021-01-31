// package: com.footyandsweep
// file: AllocationService.proto

import * as jspb from "google-protobuf";

export class Allocation extends jspb.Message {
  serializeBinary(): Uint8Array;
  toObject(includeInstance?: boolean): Allocation.AsObject;
  static toObject(includeInstance: boolean, msg: Allocation): Allocation.AsObject;
  static extensions: {[key: number]: jspb.ExtensionFieldInfo<jspb.Message>};
  static extensionsBinary: {[key: number]: jspb.ExtensionFieldBinaryInfo<jspb.Message>};
  static serializeBinaryToWriter(message: Allocation, writer: jspb.BinaryWriter): void;
  static deserializeBinary(bytes: Uint8Array): Allocation;
  static deserializeBinaryFromReader(message: Allocation, reader: jspb.BinaryReader): Allocation;
}

export namespace Allocation {
  export type AsObject = {
  }
}

