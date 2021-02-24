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
// file: TicketService.proto

import * as jspb from "google-protobuf";

export class Ticket extends jspb.Message {
    static extensions: { [key: number]: jspb.ExtensionFieldInfo<jspb.Message> };
    static extensionsBinary: { [key: number]: jspb.ExtensionFieldBinaryInfo<jspb.Message> };

    static toObject(includeInstance: boolean, msg: Ticket): Ticket.AsObject;

    static serializeBinaryToWriter(message: Ticket, writer: jspb.BinaryWriter): void;

    static deserializeBinary(bytes: Uint8Array): Ticket;

    static deserializeBinaryFromReader(message: Ticket, reader: jspb.BinaryReader): Ticket;

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
    static extensions: { [key: number]: jspb.ExtensionFieldInfo<jspb.Message> };
    static extensionsBinary: { [key: number]: jspb.ExtensionFieldBinaryInfo<jspb.Message> };

    static toObject(includeInstance: boolean, msg: TicketList): TicketList.AsObject;

    static serializeBinaryToWriter(message: TicketList, writer: jspb.BinaryWriter): void;

    static deserializeBinary(bytes: Uint8Array): TicketList;

    static deserializeBinaryFromReader(message: TicketList, reader: jspb.BinaryReader): TicketList;

    clearTicketList(): void;

    getTicketList(): Array<Ticket>;

    setTicketList(value: Array<Ticket>): void;

    addTicket(value?: Ticket, index?: number): Ticket;

    serializeBinary(): Uint8Array;

    toObject(includeInstance?: boolean): TicketList.AsObject;
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

