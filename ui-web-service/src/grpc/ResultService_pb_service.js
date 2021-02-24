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
// file: ResultService.proto

var ResultService_pb = require("./ResultService_pb");
var google_protobuf_empty_pb = require("google-protobuf/google/protobuf/empty_pb");
var grpc = require("@improbable-eng/grpc-web").grpc;

var ResultService = (function () {
    function ResultService() {
    }

    ResultService.serviceName = "com.footyandsweep.ResultService";
    return ResultService;
}());

ResultService.checkForSweepstakeResults = {
    methodName: "checkForSweepstakeResults",
    service: ResultService,
    requestStream: false,
    responseStream: false,
    requestType: google_protobuf_empty_pb.Empty,
    responseType: google_protobuf_empty_pb.Empty
};

exports.ResultService = ResultService;

function ResultServiceClient(serviceHost, options) {
    this.serviceHost = serviceHost;
    this.options = options || {};
}

ResultServiceClient.prototype.checkForSweepstakeResults = function checkForSweepstakeResults(requestMessage, metadata, callback) {
    if (arguments.length === 2) {
        callback = arguments[1];
    }
    var client = grpc.unary(ResultService.checkForSweepstakeResults, {
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

exports.ResultServiceClient = ResultServiceClient;

