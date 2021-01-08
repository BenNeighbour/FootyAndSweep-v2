/**
 * @fileoverview gRPC-Web generated client stub for com.footyandsweep
 * @enhanceable
 * @public
 */

// GENERATED CODE -- DO NOT EDIT!


/* eslint-disable */
// @ts-nocheck



const grpc = {};
grpc.web = require('grpc-web');

const proto = {};
proto.com = {};
proto.com.footyandsweep = require('./SweepstakeService_pb.js');

/**
 * @param {string} hostname
 * @param {?Object} credentials
 * @param {?Object} options
 * @constructor
 * @struct
 * @final
 */
proto.com.footyandsweep.SweepstakeServiceClient =
    function(hostname, credentials, options) {
  if (!options) options = {};
  options['format'] = 'text';

  /**
   * @private @const {!grpc.web.GrpcWebClientBase} The client
   */
  this.client_ = new grpc.web.GrpcWebClientBase(options);

  /**
   * @private @const {string} The hostname
   */
  this.hostname_ = hostname;

};


/**
 * @param {string} hostname
 * @param {?Object} credentials
 * @param {?Object} options
 * @constructor
 * @struct
 * @final
 */
proto.com.footyandsweep.SweepstakeServicePromiseClient =
    function(hostname, credentials, options) {
  if (!options) options = {};
  options['format'] = 'text';

  /**
   * @private @const {!grpc.web.GrpcWebClientBase} The client
   */
  this.client_ = new grpc.web.GrpcWebClientBase(options);

  /**
   * @private @const {string} The hostname
   */
  this.hostname_ = hostname;

};


/**
 * @const
 * @type {!grpc.web.MethodDescriptor<
 *   !proto.com.footyandsweep.JoinCode,
 *   !proto.com.footyandsweep.Sweepstake>}
 */
const methodDescriptor_SweepstakeService_findSweepstakeByJoinCode = new grpc.web.MethodDescriptor(
  '/com.footyandsweep.SweepstakeService/findSweepstakeByJoinCode',
  grpc.web.MethodType.UNARY,
  proto.com.footyandsweep.JoinCode,
  proto.com.footyandsweep.Sweepstake,
  /**
   * @param {!proto.com.footyandsweep.JoinCode} request
   * @return {!Uint8Array}
   */
  function(request) {
    return request.serializeBinary();
  },
  proto.com.footyandsweep.Sweepstake.deserializeBinary
);


/**
 * @const
 * @type {!grpc.web.AbstractClientBase.MethodInfo<
 *   !proto.com.footyandsweep.JoinCode,
 *   !proto.com.footyandsweep.Sweepstake>}
 */
const methodInfo_SweepstakeService_findSweepstakeByJoinCode = new grpc.web.AbstractClientBase.MethodInfo(
  proto.com.footyandsweep.Sweepstake,
  /**
   * @param {!proto.com.footyandsweep.JoinCode} request
   * @return {!Uint8Array}
   */
  function(request) {
    return request.serializeBinary();
  },
  proto.com.footyandsweep.Sweepstake.deserializeBinary
);


/**
 * @param {!proto.com.footyandsweep.JoinCode} request The
 *     request proto
 * @param {?Object<string, string>} metadata User defined
 *     call metadata
 * @param {function(?grpc.web.Error, ?proto.com.footyandsweep.Sweepstake)}
 *     callback The callback function(error, response)
 * @return {!grpc.web.ClientReadableStream<!proto.com.footyandsweep.Sweepstake>|undefined}
 *     The XHR Node Readable Stream
 */
proto.com.footyandsweep.SweepstakeServiceClient.prototype.findSweepstakeByJoinCode =
    function(request, metadata, callback) {
  return this.client_.rpcCall(this.hostname_ +
      '/com.footyandsweep.SweepstakeService/findSweepstakeByJoinCode',
      request,
      metadata || {},
      methodDescriptor_SweepstakeService_findSweepstakeByJoinCode,
      callback);
};


/**
 * @param {!proto.com.footyandsweep.JoinCode} request The
 *     request proto
 * @param {?Object<string, string>} metadata User defined
 *     call metadata
 * @return {!Promise<!proto.com.footyandsweep.Sweepstake>}
 *     Promise that resolves to the response
 */
proto.com.footyandsweep.SweepstakeServicePromiseClient.prototype.findSweepstakeByJoinCode =
    function(request, metadata) {
  return this.client_.unaryCall(this.hostname_ +
      '/com.footyandsweep.SweepstakeService/findSweepstakeByJoinCode',
      request,
      metadata || {},
      methodDescriptor_SweepstakeService_findSweepstakeByJoinCode);
};


/**
 * @const
 * @type {!grpc.web.MethodDescriptor<
 *   !proto.com.footyandsweep.SweepstakeId,
 *   !proto.com.footyandsweep.Sweepstake>}
 */
const methodDescriptor_SweepstakeService_findSweepstakeById = new grpc.web.MethodDescriptor(
  '/com.footyandsweep.SweepstakeService/findSweepstakeById',
  grpc.web.MethodType.UNARY,
  proto.com.footyandsweep.SweepstakeId,
  proto.com.footyandsweep.Sweepstake,
  /**
   * @param {!proto.com.footyandsweep.SweepstakeId} request
   * @return {!Uint8Array}
   */
  function(request) {
    return request.serializeBinary();
  },
  proto.com.footyandsweep.Sweepstake.deserializeBinary
);


/**
 * @const
 * @type {!grpc.web.AbstractClientBase.MethodInfo<
 *   !proto.com.footyandsweep.SweepstakeId,
 *   !proto.com.footyandsweep.Sweepstake>}
 */
const methodInfo_SweepstakeService_findSweepstakeById = new grpc.web.AbstractClientBase.MethodInfo(
  proto.com.footyandsweep.Sweepstake,
  /**
   * @param {!proto.com.footyandsweep.SweepstakeId} request
   * @return {!Uint8Array}
   */
  function(request) {
    return request.serializeBinary();
  },
  proto.com.footyandsweep.Sweepstake.deserializeBinary
);


/**
 * @param {!proto.com.footyandsweep.SweepstakeId} request The
 *     request proto
 * @param {?Object<string, string>} metadata User defined
 *     call metadata
 * @param {function(?grpc.web.Error, ?proto.com.footyandsweep.Sweepstake)}
 *     callback The callback function(error, response)
 * @return {!grpc.web.ClientReadableStream<!proto.com.footyandsweep.Sweepstake>|undefined}
 *     The XHR Node Readable Stream
 */
proto.com.footyandsweep.SweepstakeServiceClient.prototype.findSweepstakeById =
    function(request, metadata, callback) {
  return this.client_.rpcCall(this.hostname_ +
      '/com.footyandsweep.SweepstakeService/findSweepstakeById',
      request,
      metadata || {},
      methodDescriptor_SweepstakeService_findSweepstakeById,
      callback);
};


/**
 * @param {!proto.com.footyandsweep.SweepstakeId} request The
 *     request proto
 * @param {?Object<string, string>} metadata User defined
 *     call metadata
 * @return {!Promise<!proto.com.footyandsweep.Sweepstake>}
 *     Promise that resolves to the response
 */
proto.com.footyandsweep.SweepstakeServicePromiseClient.prototype.findSweepstakeById =
    function(request, metadata) {
  return this.client_.unaryCall(this.hostname_ +
      '/com.footyandsweep.SweepstakeService/findSweepstakeById',
      request,
      metadata || {},
      methodDescriptor_SweepstakeService_findSweepstakeById);
};


/**
 * @const
 * @type {!grpc.web.MethodDescriptor<
 *   !proto.com.footyandsweep.Sweepstake,
 *   !proto.com.footyandsweep.Map>}
 */
const methodDescriptor_SweepstakeService_getResultHelperMap = new grpc.web.MethodDescriptor(
  '/com.footyandsweep.SweepstakeService/getResultHelperMap',
  grpc.web.MethodType.UNARY,
  proto.com.footyandsweep.Sweepstake,
  proto.com.footyandsweep.Map,
  /**
   * @param {!proto.com.footyandsweep.Sweepstake} request
   * @return {!Uint8Array}
   */
  function(request) {
    return request.serializeBinary();
  },
  proto.com.footyandsweep.Map.deserializeBinary
);


/**
 * @const
 * @type {!grpc.web.AbstractClientBase.MethodInfo<
 *   !proto.com.footyandsweep.Sweepstake,
 *   !proto.com.footyandsweep.Map>}
 */
const methodInfo_SweepstakeService_getResultHelperMap = new grpc.web.AbstractClientBase.MethodInfo(
  proto.com.footyandsweep.Map,
  /**
   * @param {!proto.com.footyandsweep.Sweepstake} request
   * @return {!Uint8Array}
   */
  function(request) {
    return request.serializeBinary();
  },
  proto.com.footyandsweep.Map.deserializeBinary
);


/**
 * @param {!proto.com.footyandsweep.Sweepstake} request The
 *     request proto
 * @param {?Object<string, string>} metadata User defined
 *     call metadata
 * @param {function(?grpc.web.Error, ?proto.com.footyandsweep.Map)}
 *     callback The callback function(error, response)
 * @return {!grpc.web.ClientReadableStream<!proto.com.footyandsweep.Map>|undefined}
 *     The XHR Node Readable Stream
 */
proto.com.footyandsweep.SweepstakeServiceClient.prototype.getResultHelperMap =
    function(request, metadata, callback) {
  return this.client_.rpcCall(this.hostname_ +
      '/com.footyandsweep.SweepstakeService/getResultHelperMap',
      request,
      metadata || {},
      methodDescriptor_SweepstakeService_getResultHelperMap,
      callback);
};


/**
 * @param {!proto.com.footyandsweep.Sweepstake} request The
 *     request proto
 * @param {?Object<string, string>} metadata User defined
 *     call metadata
 * @return {!Promise<!proto.com.footyandsweep.Map>}
 *     Promise that resolves to the response
 */
proto.com.footyandsweep.SweepstakeServicePromiseClient.prototype.getResultHelperMap =
    function(request, metadata) {
  return this.client_.unaryCall(this.hostname_ +
      '/com.footyandsweep.SweepstakeService/getResultHelperMap',
      request,
      metadata || {},
      methodDescriptor_SweepstakeService_getResultHelperMap);
};


/**
 * @const
 * @type {!grpc.web.MethodDescriptor<
 *   !proto.com.footyandsweep.Sweepstake,
 *   !proto.com.footyandsweep.Sweepstake>}
 */
const methodDescriptor_SweepstakeService_requestNewSweepstake = new grpc.web.MethodDescriptor(
  '/com.footyandsweep.SweepstakeService/requestNewSweepstake',
  grpc.web.MethodType.SERVER_STREAMING,
  proto.com.footyandsweep.Sweepstake,
  proto.com.footyandsweep.Sweepstake,
  /**
   * @param {!proto.com.footyandsweep.Sweepstake} request
   * @return {!Uint8Array}
   */
  function(request) {
    return request.serializeBinary();
  },
  proto.com.footyandsweep.Sweepstake.deserializeBinary
);


/**
 * @const
 * @type {!grpc.web.AbstractClientBase.MethodInfo<
 *   !proto.com.footyandsweep.Sweepstake,
 *   !proto.com.footyandsweep.Sweepstake>}
 */
const methodInfo_SweepstakeService_requestNewSweepstake = new grpc.web.AbstractClientBase.MethodInfo(
  proto.com.footyandsweep.Sweepstake,
  /**
   * @param {!proto.com.footyandsweep.Sweepstake} request
   * @return {!Uint8Array}
   */
  function(request) {
    return request.serializeBinary();
  },
  proto.com.footyandsweep.Sweepstake.deserializeBinary
);


/**
 * @param {!proto.com.footyandsweep.Sweepstake} request The request proto
 * @param {?Object<string, string>} metadata User defined
 *     call metadata
 * @return {!grpc.web.ClientReadableStream<!proto.com.footyandsweep.Sweepstake>}
 *     The XHR Node Readable Stream
 */
proto.com.footyandsweep.SweepstakeServiceClient.prototype.requestNewSweepstake =
    function(request, metadata) {
  return this.client_.serverStreaming(this.hostname_ +
      '/com.footyandsweep.SweepstakeService/requestNewSweepstake',
      request,
      metadata || {},
      methodDescriptor_SweepstakeService_requestNewSweepstake);
};


/**
 * @param {!proto.com.footyandsweep.Sweepstake} request The request proto
 * @param {?Object<string, string>} metadata User defined
 *     call metadata
 * @return {!grpc.web.ClientReadableStream<!proto.com.footyandsweep.Sweepstake>}
 *     The XHR Node Readable Stream
 */
proto.com.footyandsweep.SweepstakeServicePromiseClient.prototype.requestNewSweepstake =
    function(request, metadata) {
  return this.client_.serverStreaming(this.hostname_ +
      '/com.footyandsweep.SweepstakeService/requestNewSweepstake',
      request,
      metadata || {},
      methodDescriptor_SweepstakeService_requestNewSweepstake);
};


module.exports = proto.com.footyandsweep;

