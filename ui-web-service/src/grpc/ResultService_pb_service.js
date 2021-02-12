// package: com.footyandsweep
// file: ResultService.proto

var ResultService_pb = require("./ResultService_pb");
var google_protobuf_empty_pb = require("google-protobuf/google/protobuf/empty_pb");
var grpc = require("@improbable-eng/grpc-web").grpc;

var ResultService = (function () {
  function ResultService() {}
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

