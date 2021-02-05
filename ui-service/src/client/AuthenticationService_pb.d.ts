// package: com.footyandsweep
// file: AuthenticationService.proto

import * as jspb from "google-protobuf";

export class User extends jspb.Message {
  serializeBinary(): Uint8Array;
  toObject(includeInstance?: boolean): User.AsObject;
  static toObject(includeInstance: boolean, msg: User): User.AsObject;
  static extensions: {[key: number]: jspb.ExtensionFieldInfo<jspb.Message>};
  static extensionsBinary: {[key: number]: jspb.ExtensionFieldBinaryInfo<jspb.Message>};
  static serializeBinaryToWriter(message: User, writer: jspb.BinaryWriter): void;
  static deserializeBinary(bytes: Uint8Array): User;
  static deserializeBinaryFromReader(message: User, reader: jspb.BinaryReader): User;
}

export namespace User {
  export type AsObject = {
  }
}

export class findUserByIdRequest extends jspb.Message {
  getUserid(): string;
  setUserid(value: string): void;

  serializeBinary(): Uint8Array;
  toObject(includeInstance?: boolean): findUserByIdRequest.AsObject;
  static toObject(includeInstance: boolean, msg: findUserByIdRequest): findUserByIdRequest.AsObject;
  static extensions: {[key: number]: jspb.ExtensionFieldInfo<jspb.Message>};
  static extensionsBinary: {[key: number]: jspb.ExtensionFieldBinaryInfo<jspb.Message>};
  static serializeBinaryToWriter(message: findUserByIdRequest, writer: jspb.BinaryWriter): void;
  static deserializeBinary(bytes: Uint8Array): findUserByIdRequest;
  static deserializeBinaryFromReader(message: findUserByIdRequest, reader: jspb.BinaryReader): findUserByIdRequest;
}

export namespace findUserByIdRequest {
  export type AsObject = {
    userid: string,
  }
}

