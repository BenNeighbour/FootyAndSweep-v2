// package: com.footyandsweep
// file: AllocationService.proto

import * as jspb from "google-protobuf";
import * as SweepstakeService_pb from "./SweepstakeService_pb";
import * as google_protobuf_empty_pb from "google-protobuf/google/protobuf/empty_pb";

export class Allocation extends jspb.Message {
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
  static toObject(includeInstance: boolean, msg: Allocation): Allocation.AsObject;
  static extensions: {[key: number]: jspb.ExtensionFieldInfo<jspb.Message>};
  static extensionsBinary: {[key: number]: jspb.ExtensionFieldBinaryInfo<jspb.Message>};
  static serializeBinaryToWriter(message: Allocation, writer: jspb.BinaryWriter): void;
  static deserializeBinary(bytes: Uint8Array): Allocation;
  static deserializeBinaryFromReader(message: Allocation, reader: jspb.BinaryReader): Allocation;
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

