/*
 *   Copyright 2020 FootyAndSweep
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

package com.footyandsweep.apisweepstakeengine;

import com.footyandsweep.SweepstakeServiceGrpc;
import com.footyandsweep.SweepstakeServiceOuterClass;
import com.footyandsweep.apicommonlibrary.gRPC.GrpcService;
import io.grpc.stub.StreamObserver;

@GrpcService
public class SweepstakeServiceImpl extends SweepstakeServiceGrpc.SweepstakeServiceImplBase {

    @Override
    public void updateSweepstake(SweepstakeServiceOuterClass.Sweepstake request, StreamObserver<SweepstakeServiceOuterClass.Sweepstake> responseObserver) {
        /* TODO: Call service method here */
    }

    @Override
    public void findSweepstakeByJoinCode(SweepstakeServiceOuterClass.JoinCode request, StreamObserver<SweepstakeServiceOuterClass.Sweepstake> responseObserver) {
        super.findSweepstakeByJoinCode(request, responseObserver);
    }

    @Override
    public void findSweepstakeById(SweepstakeServiceOuterClass.SweepstakeId request, StreamObserver<SweepstakeServiceOuterClass.Sweepstake> responseObserver) {
        super.findSweepstakeById(request, responseObserver);
    }

    @Override
    public void getResultHelperMap(SweepstakeServiceOuterClass.Sweepstake request, StreamObserver<SweepstakeServiceOuterClass.Map> responseObserver) {
        super.getResultHelperMap(request, responseObserver);
    }

    @Override
    public void requestNewSweepstake(SweepstakeServiceOuterClass.Sweepstake request, StreamObserver<SweepstakeServiceOuterClass.Sweepstake> responseObserver) {
        super.requestNewSweepstake(request, responseObserver);
    }

    @Override
    public void createSweepstake(SweepstakeServiceOuterClass.Sweepstake request, StreamObserver<SweepstakeServiceOuterClass.Sweepstake> responseObserver) {
        super.createSweepstake(request, responseObserver);
    }
}
