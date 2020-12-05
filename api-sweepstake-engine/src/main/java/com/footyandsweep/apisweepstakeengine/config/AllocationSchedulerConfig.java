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

package com.footyandsweep.apisweepstakeengine.config;

import com.footyandsweep.apicommonlibrary.events.EventType;
import com.footyandsweep.apicommonlibrary.events.SweepstakeEvent;
import com.footyandsweep.apicommonlibrary.model.sweepstake.SweepstakeCommon;
import com.footyandsweep.apisweepstakeengine.dao.SweepstakeDao;
import com.footyandsweep.apisweepstakeengine.event.SweepstakeMessageDispatcher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class AllocationSchedulerConfig {

  private static final Logger log = LoggerFactory.getLogger(AllocationSchedulerConfig.class);
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ssZ");

  private final SweepstakeDao sweepstakeDao;
  private final SweepstakeMessageDispatcher sweepstakeMessageDispatcher;

  public AllocationSchedulerConfig(
      SweepstakeDao sweepstakeDao, SweepstakeMessageDispatcher sweepstakeMessageDispatcher) {
    this.sweepstakeDao = sweepstakeDao;
    this.sweepstakeMessageDispatcher = sweepstakeMessageDispatcher;
  }

  /* Scheduled for every 4 minutes */
  @Scheduled(fixedRate = 480000)
  public void checkAndAllocateSweepstakes() {
    /* Logging the periodic check */
    log.info("Periodic check for unallocated sweepstakes at {}", dateFormat.format(new Date()));

    /* For each sweepstake that is open, get the event id */
    sweepstakeDao
        .findAllSweepstakesByStatus(SweepstakeCommon.SweepstakeStatus.OPEN)
        .forEach(
            sweepstake -> {
              /* Create the event object to be sent over */
              SweepstakeEvent sweepstakeEvent =
                  new SweepstakeEvent(sweepstake, EventType.NEEDS_ALLOCATING);

              /* Send a sweepstake needs allocating message */
              sweepstakeMessageDispatcher.publishEvent(sweepstakeEvent, "api-sweepstake-events-topic");
            });
  }
}
