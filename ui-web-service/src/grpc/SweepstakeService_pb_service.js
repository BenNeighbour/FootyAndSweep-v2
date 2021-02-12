// package: com.footyandsweep
// file: SweepstakeService.proto

var SweepstakeService_pb = require("./SweepstakeService_pb");
var Common_pb = require("./Common_pb");
var grpc = require("@improbable-eng/grpc-web").grpc;

var SweepstakeService = (function () {
  function SweepstakeService() {}
  SweepstakeService.serviceName = "com.footyandsweep.SweepstakeService";
  return SweepstakeService;
}());

SweepstakeService.findSweepstakeByJoinCode = {
  methodName: "findSweepstakeByJoinCode",
  service: SweepstakeService,
  requestStream: false,
  responseStream: false,
  requestType: SweepstakeService_pb.JoinCode,
  responseType: SweepstakeService_pb.Sweepstake
};

SweepstakeService.findSweepstakeByFootballMatchId = {
  methodName: "findSweepstakeByFootballMatchId",
  service: SweepstakeService,
  requestStream: false,
  responseStream: false,
  requestType: Common_pb.Id,
  responseType: SweepstakeService_pb.SweepstakeList
};

SweepstakeService.findSweepstakeById = {
  methodName: "findSweepstakeById",
  service: SweepstakeService,
  requestStream: false,
  responseStream: false,
  requestType: Common_pb.Id,
  responseType: SweepstakeService_pb.Sweepstake
};

SweepstakeService.getResultHelperMap = {
  methodName: "getResultHelperMap",
  service: SweepstakeService,
  requestStream: false,
  responseStream: false,
  requestType: SweepstakeService_pb.Sweepstake,
  responseType: Common_pb.Map
};

SweepstakeService.requestNewSweepstake = {
  methodName: "requestNewSweepstake",
  service: SweepstakeService,
  requestStream: false,
  responseStream: false,
  requestType: SweepstakeService_pb.Sweepstake,
  responseType: SweepstakeService_pb.Sweepstake
};

SweepstakeService.getAllSweepstakeParticipants = {
  methodName: "getAllSweepstakeParticipants",
  service: SweepstakeService,
  requestStream: false,
  responseStream: false,
  requestType: Common_pb.Id,
  responseType: Common_pb.Ids
};

SweepstakeService.resultHelper = {
  methodName: "resultHelper",
  service: SweepstakeService,
  requestStream: false,
  responseStream: false,
  requestType: SweepstakeService_pb.Sweepstake,
  responseType: SweepstakeService_pb.PairList
};

exports.SweepstakeService = SweepstakeService;

function SweepstakeServiceClient(serviceHost, options) {
  this.serviceHost = serviceHost;
  this.options = options || {};
}

SweepstakeServiceClient.prototype.findSweepstakeByJoinCode = function findSweepstakeByJoinCode(requestMessage, metadata, callback) {
  if (arguments.length === 2) {
    callback = arguments[1];
  }
  var client = grpc.unary(SweepstakeService.findSweepstakeByJoinCode, {
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

SweepstakeServiceClient.prototype.findSweepstakeByFootballMatchId = function findSweepstakeByFootballMatchId(requestMessage, metadata, callback) {
  if (arguments.length === 2) {
    callback = arguments[1];
  }
  var client = grpc.unary(SweepstakeService.findSweepstakeByFootballMatchId, {
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

SweepstakeServiceClient.prototype.findSweepstakeById = function findSweepstakeById(requestMessage, metadata, callback) {
  if (arguments.length === 2) {
    callback = arguments[1];
  }
  var client = grpc.unary(SweepstakeService.findSweepstakeById, {
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

SweepstakeServiceClient.prototype.getResultHelperMap = function getResultHelperMap(requestMessage, metadata, callback) {
  if (arguments.length === 2) {
    callback = arguments[1];
  }
  var client = grpc.unary(SweepstakeService.getResultHelperMap, {
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

SweepstakeServiceClient.prototype.requestNewSweepstake = function requestNewSweepstake(requestMessage, metadata, callback) {
  if (arguments.length === 2) {
    callback = arguments[1];
  }
  var client = grpc.unary(SweepstakeService.requestNewSweepstake, {
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

SweepstakeServiceClient.prototype.getAllSweepstakeParticipants = function getAllSweepstakeParticipants(requestMessage, metadata, callback) {
  if (arguments.length === 2) {
    callback = arguments[1];
  }
  var client = grpc.unary(SweepstakeService.getAllSweepstakeParticipants, {
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

SweepstakeServiceClient.prototype.resultHelper = function resultHelper(requestMessage, metadata, callback) {
  if (arguments.length === 2) {
    callback = arguments[1];
  }
  var client = grpc.unary(SweepstakeService.resultHelper, {
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

exports.SweepstakeServiceClient = SweepstakeServiceClient;

