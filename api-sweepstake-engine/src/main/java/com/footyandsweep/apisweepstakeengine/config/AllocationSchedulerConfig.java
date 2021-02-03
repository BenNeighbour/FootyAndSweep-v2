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

import com.footyandsweep.AllocationServiceGrpc;
import com.footyandsweep.SweepstakeServiceOuterClass;
import com.footyandsweep.apicommonlibrary.helper.ProtoConverterUtils;
import com.footyandsweep.apicommonlibrary.model.sweepstake.SweepstakeCommon;
import com.footyandsweep.apisweepstakeengine.dao.SweepstakeDao;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.lang.reflect.InvocationTargetException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class AllocationSchedulerConfig {

  private static final Logger log = LoggerFactory.getLogger(AllocationSchedulerConfig.class);
  private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ssZ");

  private final SweepstakeDao sweepstakeDao;

  public AllocationSchedulerConfig(SweepstakeDao sweepstakeDao) {
    this.sweepstakeDao = sweepstakeDao;
  }

  /* Scheduled for every 2 minutes */
  @Scheduled(fixedRate = 60000)
  public void checkAndAllocateSweepstakes() {
      /* Logging the periodic check */
      log.info("Periodic check for unallocated sweepstakes at {}", dateFormat.format(new
              Date()));

      /* For each sweepstake that is open, get the event id */
      sweepstakeDao
              .findAllSweepstakesByStatus(SweepstakeCommon.SweepstakeStatus.OPEN)
              .forEach(
                      sweepstake -> {
                          /* Call the RPC, which in turn, calls the saga for each sweepstake */
                           ManagedChannel channel = ManagedChannelBuilder.forAddress("api-allocation-engine", 9090)
                                  .usePlaintext()
                                  .build();

                          AllocationServiceGrpc.AllocationServiceBlockingStub clientStub = AllocationServiceGrpc.newBlockingStub(channel);
                          SweepstakeServiceOuterClass.Sweepstake.Builder grpcSweepstake = SweepstakeServiceOuterClass.Sweepstake.newBuilder();

                          ProtoConverterUtils.convertToProto(grpcSweepstake, sweepstake);

                          clientStub.allocateSweepstake(grpcSweepstake.build());
                      });
  }
}
