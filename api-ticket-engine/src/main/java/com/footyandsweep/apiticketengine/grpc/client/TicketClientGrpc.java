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

package com.footyandsweep.apiticketengine.grpc.client;

import com.footyandsweep.SweepstakeServiceGrpc;
import com.footyandsweep.SweepstakeServiceOuterClass;
import com.footyandsweep.apicommonlibrary.helper.ProtoConverterUtils;
import com.footyandsweep.apicommonlibrary.model.sweepstake.SweepstakeCommon;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TicketClientGrpc {

  private final SweepstakeServiceGrpc.SweepstakeServiceBlockingStub sweepstakeServiceChannel;

  public Optional<SweepstakeCommon> joinSweepstake(String sweepstakeJoinCode) {
    SweepstakeServiceOuterClass.JoinCode.Builder grpcSweepstakeJoinCode =
        SweepstakeServiceOuterClass.JoinCode.newBuilder().setJoinCode(sweepstakeJoinCode);
    return Optional.of(
        ProtoConverterUtils.convertToPojo(
            SweepstakeCommon.class,
            sweepstakeServiceChannel.findSweepstakeByJoinCode(grpcSweepstakeJoinCode.build())));
  }
}
