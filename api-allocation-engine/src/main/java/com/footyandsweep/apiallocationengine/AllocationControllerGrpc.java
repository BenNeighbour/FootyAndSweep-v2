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

package com.footyandsweep.apiallocationengine;

import com.footyandsweep.AllocationServiceGrpc;
import com.footyandsweep.SweepstakeServiceOuterClass;
import com.footyandsweep.apiallocationengine.engine.saga.AllocateSweepstakeSaga;
import com.footyandsweep.apiallocationengine.engine.saga.AllocateSweepstakeSagaData;
import com.footyandsweep.apiallocationengine.grpc.GrpcService;
import com.footyandsweep.apicommonlibrary.helper.ProtoConverterUtils;
import com.footyandsweep.apicommonlibrary.model.sweepstake.SweepstakeCommon;
import com.google.protobuf.Empty;
import io.eventuate.tram.sagas.orchestration.SagaInstanceFactory;
import io.grpc.stub.StreamObserver;

@GrpcService
public class AllocationControllerGrpc extends AllocationServiceGrpc.AllocationServiceImplBase {

  private final AllocateSweepstakeSaga allocateSweepstakeSaga;
  private final SagaInstanceFactory sagaInstanceFactory;

  public AllocationControllerGrpc(
      AllocateSweepstakeSaga allocateSweepstakeSaga, SagaInstanceFactory sagaInstanceFactory) {
    this.allocateSweepstakeSaga = allocateSweepstakeSaga;
    this.sagaInstanceFactory = sagaInstanceFactory;
  }

  @Override
  public void allocateSweepstake(
      SweepstakeServiceOuterClass.Sweepstake request, StreamObserver<Empty> responseObserver) {

      SweepstakeCommon sweepstake = new SweepstakeCommon();
      ProtoConverterUtils.convertToPojo(sweepstake.getClass(), request);

      AllocateSweepstakeSagaData data = new AllocateSweepstakeSagaData();
      data.setSweepstake(sweepstake);

      sagaInstanceFactory.create(allocateSweepstakeSaga, data);

      responseObserver.onNext(Empty.newBuilder().build());
      responseObserver.onCompleted();
  }
}
