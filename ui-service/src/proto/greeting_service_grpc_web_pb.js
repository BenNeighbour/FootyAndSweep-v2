/**
 * @fileoverview gRPC-Web generated client stub for com.benneighbour.grpc
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
proto.com.benneighbour = {};
proto.com.benneighbour.grpc = require('./greeting_service_pb.js');

/**
 * @param {string} hostname
 * @param {?Object} credentials
 * @param {?Object} options
 * @constructor
 * @struct
 * @final
 */
proto.com.benneighbour.grpc.GreetingServiceClient =
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
proto.com.benneighbour.grpc.GreetingServicePromiseClient =
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
 *   !proto.com.benneighbour.grpc.HelloRequest,
 *   !proto.com.benneighbour.grpc.HelloResponse>}
 */
const methodDescriptor_GreetingService_greeting = new grpc.web.MethodDescriptor(
  '/com.benneighbour.grpc.GreetingService/greeting',
  grpc.web.MethodType.UNARY,
  proto.com.benneighbour.grpc.HelloRequest,
  proto.com.benneighbour.grpc.HelloResponse,
  /**
   * @param {!proto.com.benneighbour.grpc.HelloRequest} request
   * @return {!Uint8Array}
   */
  function(request) {
    return request.serializeBinary();
  },
  proto.com.benneighbour.grpc.HelloResponse.deserializeBinary
);


/**
 * @const
 * @type {!grpc.web.AbstractClientBase.MethodInfo<
 *   !proto.com.benneighbour.grpc.HelloRequest,
 *   !proto.com.benneighbour.grpc.HelloResponse>}
 */
const methodInfo_GreetingService_greeting = new grpc.web.AbstractClientBase.MethodInfo(
  proto.com.benneighbour.grpc.HelloResponse,
  /**
   * @param {!proto.com.benneighbour.grpc.HelloRequest} request
   * @return {!Uint8Array}
   */
  function(request) {
    return request.serializeBinary();
  },
  proto.com.benneighbour.grpc.HelloResponse.deserializeBinary
);


/**
 * @param {!proto.com.benneighbour.grpc.HelloRequest} request The
 *     request proto
 * @param {?Object<string, string>} metadata User defined
 *     call metadata
 * @param {function(?grpc.web.Error, ?proto.com.benneighbour.grpc.HelloResponse)}
 *     callback The callback function(error, response)
 * @return {!grpc.web.ClientReadableStream<!proto.com.benneighbour.grpc.HelloResponse>|undefined}
 *     The XHR Node Readable Stream
 */
proto.com.benneighbour.grpc.GreetingServiceClient.prototype.greeting =
    function(request, metadata, callback) {
  return this.client_.rpcCall(this.hostname_ +
      '/com.benneighbour.grpc.GreetingService/greeting',
      request,
      metadata || {},
      methodDescriptor_GreetingService_greeting,
      callback);
};


/**
 * @param {!proto.com.benneighbour.grpc.HelloRequest} request The
 *     request proto
 * @param {?Object<string, string>} metadata User defined
 *     call metadata
 * @return {!Promise<!proto.com.benneighbour.grpc.HelloResponse>}
 *     Promise that resolves to the response
 */
proto.com.benneighbour.grpc.GreetingServicePromiseClient.prototype.greeting =
    function(request, metadata) {
  return this.client_.unaryCall(this.hostname_ +
      '/com.benneighbour.grpc.GreetingService/greeting',
      request,
      metadata || {},
      methodDescriptor_GreetingService_greeting);
};


/**
 * @const
 * @type {!grpc.web.MethodDescriptor<
 *   !proto.com.benneighbour.grpc.HelloRequest,
 *   !proto.com.benneighbour.grpc.HelloResponse>}
 */
const methodDescriptor_GreetingService_greetingWithResponseStream = new grpc.web.MethodDescriptor(
  '/com.benneighbour.grpc.GreetingService/greetingWithResponseStream',
  grpc.web.MethodType.SERVER_STREAMING,
  proto.com.benneighbour.grpc.HelloRequest,
  proto.com.benneighbour.grpc.HelloResponse,
  /**
   * @param {!proto.com.benneighbour.grpc.HelloRequest} request
   * @return {!Uint8Array}
   */
  function(request) {
    return request.serializeBinary();
  },
  proto.com.benneighbour.grpc.HelloResponse.deserializeBinary
);


/**
 * @const
 * @type {!grpc.web.AbstractClientBase.MethodInfo<
 *   !proto.com.benneighbour.grpc.HelloRequest,
 *   !proto.com.benneighbour.grpc.HelloResponse>}
 */
const methodInfo_GreetingService_greetingWithResponseStream = new grpc.web.AbstractClientBase.MethodInfo(
  proto.com.benneighbour.grpc.HelloResponse,
  /**
   * @param {!proto.com.benneighbour.grpc.HelloRequest} request
   * @return {!Uint8Array}
   */
  function(request) {
    return request.serializeBinary();
  },
  proto.com.benneighbour.grpc.HelloResponse.deserializeBinary
);


/**
 * @param {!proto.com.benneighbour.grpc.HelloRequest} request The request proto
 * @param {?Object<string, string>} metadata User defined
 *     call metadata
 * @return {!grpc.web.ClientReadableStream<!proto.com.benneighbour.grpc.HelloResponse>}
 *     The XHR Node Readable Stream
 */
proto.com.benneighbour.grpc.GreetingServiceClient.prototype.greetingWithResponseStream =
    function(request, metadata) {
  return this.client_.serverStreaming(this.hostname_ +
      '/com.benneighbour.grpc.GreetingService/greetingWithResponseStream',
      request,
      metadata || {},
      methodDescriptor_GreetingService_greetingWithResponseStream);
};


/**
 * @param {!proto.com.benneighbour.grpc.HelloRequest} request The request proto
 * @param {?Object<string, string>} metadata User defined
 *     call metadata
 * @return {!grpc.web.ClientReadableStream<!proto.com.benneighbour.grpc.HelloResponse>}
 *     The XHR Node Readable Stream
 */
proto.com.benneighbour.grpc.GreetingServicePromiseClient.prototype.greetingWithResponseStream =
    function(request, metadata) {
  return this.client_.serverStreaming(this.hostname_ +
      '/com.benneighbour.grpc.GreetingService/greetingWithResponseStream',
      request,
      metadata || {},
      methodDescriptor_GreetingService_greetingWithResponseStream);
};


module.exports = proto.com.benneighbour.grpc;

