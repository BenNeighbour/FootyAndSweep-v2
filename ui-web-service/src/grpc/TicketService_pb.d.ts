// package: com.footyandsweep
// file: TicketService.proto

import * as jspb from "google-protobuf";
import * as google_protobuf_descriptor_pb from "google-protobuf/google/protobuf/descriptor_pb";
import * as Common_pb from "./Common_pb";

export class Ticket extends jspb.Message {
  getId(): string;
  setId(value: string): void;

  getStatus(): TicketStatusMap[keyof TicketStatusMap];
  setStatus(value: TicketStatusMap[keyof TicketStatusMap]): void;

  getSweepstakeid(): string;
  setSweepstakeid(value: string): void;

  getAllocationid(): string;
  setAllocationid(value: string): void;

  getIswinner(): boolean;
  setIswinner(value: boolean): void;

  getUserid(): string;
  setUserid(value: string): void;

  serializeBinary(): Uint8Array;
  toObject(includeInstance?: boolean): Ticket.AsObject;
  static toObject(includeInstance: boolean, msg: Ticket): Ticket.AsObject;
  static extensions: {[key: number]: jspb.ExtensionFieldInfo<jspb.Message>};
  static extensionsBinary: {[key: number]: jspb.ExtensionFieldBinaryInfo<jspb.Message>};
  static serializeBinaryToWriter(message: Ticket, writer: jspb.BinaryWriter): void;
  static deserializeBinary(bytes: Uint8Array): Ticket;
  static deserializeBinaryFromReader(message: Ticket, reader: jspb.BinaryReader): Ticket;
}

export namespace Ticket {
  export type AsObject = {
    id: string,
    status: TicketStatusMap[keyof TicketStatusMap],
    sweepstakeid: string,
    allocationid: string,
    iswinner: boolean,
    userid: string,
  }
}

export class TicketList extends jspb.Message {
  clearTicketList(): void;
  getTicketList(): Array<Ticket>;
  setTicketList(value: Array<Ticket>): void;
  addTicket(value?: Ticket, index?: number): Ticket;

  serializeBinary(): Uint8Array;
  toObject(includeInstance?: boolean): TicketList.AsObject;
  static toObject(includeInstance: boolean, msg: TicketList): TicketList.AsObject;
  static extensions: {[key: number]: jspb.ExtensionFieldInfo<jspb.Message>};
  static extensionsBinary: {[key: number]: jspb.ExtensionFieldBinaryInfo<jspb.Message>};
  static serializeBinaryToWriter(message: TicketList, writer: jspb.BinaryWriter): void;
  static deserializeBinary(bytes: Uint8Array): TicketList;
  static deserializeBinaryFromReader(message: TicketList, reader: jspb.BinaryReader): TicketList;
}

export namespace TicketList {
  export type AsObject = {
    ticketList: Array<Ticket.AsObject>,
  }
}

  export const ticketStatus: jspb.ExtensionFieldInfo<string>;

export interface TicketStatusMap {
  PENDING: 0;
  INPLAY: 1;
  REFUNDED: 2;
  WON: 3;
  LOST: 4;
}

export const TicketStatus: TicketStatusMap;

