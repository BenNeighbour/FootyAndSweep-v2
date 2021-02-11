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

package com.footyandsweep.apiresultengine;

import com.footyandsweep.ResultServiceGrpc;
import com.footyandsweep.apiresultengine.dao.ResultDao;
import com.footyandsweep.apiresultengine.engine.saga.ProcessSweepstakeResultSaga;
import com.footyandsweep.apiresultengine.engine.saga.ProcessSweepstakeResultSagaData;
import com.footyandsweep.apiresultengine.grpc.GrpcService;
import com.google.protobuf.Empty;
import io.eventuate.tram.sagas.orchestration.SagaInstanceFactory;
import io.grpc.stub.StreamObserver;

@GrpcService
public class ResultControllerGrpc extends ResultServiceGrpc.ResultServiceImplBase {

  private final SagaInstanceFactory sagaInstanceFactory;

  private final ProcessSweepstakeResultSaga processSweepstakeResultSaga;
  private final ResultDao resultDao;

  public ResultControllerGrpc(
      SagaInstanceFactory sagaInstanceFactory,
      ProcessSweepstakeResultSaga processSweepstakeResultSaga,
      ResultDao resultDao) {
    this.sagaInstanceFactory = sagaInstanceFactory;
    this.processSweepstakeResultSaga = processSweepstakeResultSaga;
    this.resultDao = resultDao;
  }

  @Override
  public void checkForSweepstakeResults(Empty request, StreamObserver<Empty> responseObserver) {
    resultDao.findAll().stream()
        .filter(result -> !result.isProcessed())
        .forEach(
            result -> {
              ProcessSweepstakeResultSagaData data = new ProcessSweepstakeResultSagaData();
              data.setResult(result);

              sagaInstanceFactory.create(processSweepstakeResultSaga, data);
            });

    responseObserver.onNext(Empty.newBuilder().build());
    responseObserver.onCompleted();
  }
}
