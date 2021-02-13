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

var TicketService_pb = require("./TicketService_pb");
var Common_pb = require("./Common_pb");
var grpc = require("@improbable-eng/grpc-web").grpc;

var TicketService = (function () {
  function TicketService() {}
  TicketService.serviceName = "com.footyandsweep.TicketService";
  return TicketService;
}());

TicketService.findTicketById = {
  methodName: "findTicketById",
  service: TicketService,
  requestStream: false,
  responseStream: false,
  requestType: Common_pb.Id,
  responseType: TicketService_pb.Ticket
};

TicketService.getAllTicketsBySweepstakeId = {
  methodName: "getAllTicketsBySweepstakeId",
  service: TicketService,
  requestStream: false,
  responseStream: false,
  requestType: Common_pb.Id,
  responseType: TicketService_pb.TicketList
};

exports.TicketService = TicketService;

function TicketServiceClient(serviceHost, options) {
  this.serviceHost = serviceHost;
  this.options = options || {};
}

TicketServiceClient.prototype.findTicketById = function findTicketById(requestMessage, metadata, callback) {
  if (arguments.length === 2) {
    callback = arguments[1];
  }
  var client = grpc.unary(TicketService.findTicketById, {
    request: requestMessage,
    host: this.serviceHost,
    metadata: metadata,
    transport: this.options.transport,
    debug: this.options.debug,
    onEnd: function (response) {
      if (callback) {
        if (response.status !== grpc.Code.OK) {
          var err = new Error(response.statusMessage);
          err.code = response.status;
          err.metadata = response.trailers;
          callback(err, null);
        } else {
          callback(null, response.message);
        }
      }
    }
  });
  return {
    cancel: function () {
      callback = null;
      client.close();
    }
  };
};

TicketServiceClient.prototype.getAllTicketsBySweepstakeId = function getAllTicketsBySweepstakeId(requestMessage, metadata, callback) {
  if (arguments.length === 2) {
    callback = arguments[1];
  }
  var client = grpc.unary(TicketService.getAllTicketsBySweepstakeId, {
    request: requestMessage,
    host: this.serviceHost,
    metadata: metadata,
    transport: this.options.transport,
    debug: this.options.debug,
    onEnd: function (response) {
      if (callback) {
        if (response.status !== grpc.Code.OK) {
          var err = new Error(response.statusMessage);
          err.code = response.status;
          err.metadata = response.trailers;
          callback(err, null);
        } else {
          callback(null, response.message);
        }
      }
    }
  });
  return {
    cancel: function () {
      callback = null;
      client.close();
    }
  };
};

exports.TicketServiceClient = TicketServiceClient;

