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

package com.footyandsweep.apisweepstakeengine.config;

import com.footyandsweep.ResultServiceGrpc;
import com.footyandsweep.apicommonlibrary.model.sweepstake.SweepstakeCommon;
import com.footyandsweep.apisweepstakeengine.dao.SweepstakeDao;
import com.google.protobuf.Empty;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;

@Component
public class ResultSchedulerConfig {

  private static final Logger log = LoggerFactory.getLogger(ResultSchedulerConfig.class);
  private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ssZ");

  private final SweepstakeDao sweepstakeDao;

  public ResultSchedulerConfig(SweepstakeDao sweepstakeDao) {
    this.sweepstakeDao = sweepstakeDao;
  }

  /* Scheduled for every 4 minutes (240000) */
  @Scheduled(fixedRate = 120000)
  public void fetchAndDecisionSweepstakes() {
    sweepstakeDao
        .findAllSweepstakesByStatus(SweepstakeCommon.SweepstakeStatus.ALLOCATED)
        /* TODO: Filter by the current football match id */
        .forEach(
            sweepstake -> {
              /* Call the RPC, which in turn, calls the saga for each sweepstake */
              ManagedChannel channel =
                  ManagedChannelBuilder.forAddress("api-result-engine", 9090)
                      .usePlaintext()
                      .build();

              ResultServiceGrpc.ResultServiceBlockingStub clientStub =
                  ResultServiceGrpc.newBlockingStub(channel);
              clientStub.checkForSweepstakeResults(Empty.newBuilder().build());

              channel.shutdown();
            });
  }
}
