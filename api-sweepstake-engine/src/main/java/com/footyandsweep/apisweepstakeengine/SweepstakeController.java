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
import com.footyandsweep.apisweepstakeengine.dao.FootballMatchDao;
import com.footyandsweep.apisweepstakeengine.dao.ParticipantIdDao;
import com.footyandsweep.apisweepstakeengine.dao.SweepstakeDao;
import com.footyandsweep.apisweepstakeengine.engine.SweepstakeEngineImpl;
import com.footyandsweep.apisweepstakeengine.helper.ResultHelper;
import com.footyandsweep.apisweepstakeengine.model.Sweepstake;
import io.grpc.stub.StreamObserver;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.stereotype.Service;

import java.lang.reflect.InvocationTargetException;
import java.util.UUID;

@Service
@GrpcService
public class SweepstakeController extends SweepstakeServiceGrpc.SweepstakeServiceImplBase {

    private final SweepstakeEngineImpl sweepstakeEngine;
    private final SweepstakeDao sweepstakeDao;
    private final ParticipantIdDao participantIdDao;
    private final ResultHelper resultHelper;
    private final FootballMatchDao footballMatchDao;

    public SweepstakeController(
            final SweepstakeEngineImpl sweepstakeEngine,
            final SweepstakeDao sweepstakeDao,
            final ParticipantIdDao participantIdDao,
            final ResultHelper resultHelper,
            final FootballMatchDao footballMatchDao) {
        this.sweepstakeEngine = sweepstakeEngine;
        this.sweepstakeDao = sweepstakeDao;
        this.participantIdDao = participantIdDao;
        this.resultHelper = resultHelper;
        this.footballMatchDao = footballMatchDao;
    }

    @Override
    public void updateSweepstake(SweepstakeServiceOuterClass.Sweepstake request, StreamObserver<SweepstakeServiceOuterClass.Sweepstake> responseObserver) {
        /* TODO: Call service method here */
    }

    @Override
    public void findSweepstakeByJoinCode(SweepstakeServiceOuterClass.JoinCode request, StreamObserver<SweepstakeServiceOuterClass.Sweepstake> responseObserver) {
        try {
            SweepstakeServiceOuterClass.Sweepstake sweepstake = this.castHelper(sweepstakeDao.findSweepstakeByJoinCode(request.getJoinCode()));

            responseObserver.onNext(sweepstake);
            responseObserver.onCompleted();
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void findSweepstakeById(SweepstakeServiceOuterClass.SweepstakeId request, StreamObserver<SweepstakeServiceOuterClass.Sweepstake> responseObserver) {
        try {
            SweepstakeServiceOuterClass.Sweepstake sweepstake = this.castHelper(sweepstakeDao.findSweepstakeById(UUID.fromString(request.getSweepstakeId())));

            responseObserver.onNext(sweepstake);
            responseObserver.onCompleted();
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
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

    private SweepstakeServiceOuterClass.Sweepstake castHelper(Sweepstake sweepstake) throws InvocationTargetException, IllegalAccessException {
        /* Cast the sweepstake fields to the protobuf sweepstake */
        SweepstakeServiceOuterClass.Sweepstake response = SweepstakeServiceOuterClass.Sweepstake.newBuilder().build();
        BeanUtils.copyProperties(response, sweepstake);

        /* Return the casted version to be sent off to the client */
        return response;
    }
}
