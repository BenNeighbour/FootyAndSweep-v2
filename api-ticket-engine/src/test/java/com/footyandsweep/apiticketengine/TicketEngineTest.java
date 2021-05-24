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

package com.footyandsweep.apiticketengine;

import com.footyandsweep.apicommonlibrary.model.sweepstake.SweepstakeCommon;
import com.footyandsweep.apicommonlibrary.model.sweepstake.SweepstakeTypeCommon;
import com.footyandsweep.apicommonlibrary.model.user.UserCommon;
import com.footyandsweep.apiticketengine.dao.TicketDao;
import com.footyandsweep.apiticketengine.engine.TicketEngine;
import com.footyandsweep.apiticketengine.engine.TicketEngineImpl;
import com.footyandsweep.apiticketengine.engine.saga.BuyTicketSagaData;
import com.footyandsweep.apiticketengine.grpc.client.TicketClientGrpc;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.math.BigDecimal;
import java.util.UUID;

public class TicketEngineTest {

    private TicketDao ticketDao = Mockito.mock(TicketDao.class);
    private TicketClientGrpc ticketClientGrpc = Mockito.mock(TicketClientGrpc.class);
    private TicketEngine ticketEngine;

  @BeforeEach
  void setup() {
    this.ticketEngine = new TicketEngineImpl(ticketDao, ticketClientGrpc);
  }

    @Test
    public void shouldBuyTickets() {
        UserCommon user = new UserCommon();
        user.setId(UUID.randomUUID().toString());
        user.setBalance(new BigDecimal("100.00"));

        SweepstakeCommon parentSweepstake = new SweepstakeCommon();
        parentSweepstake.setId(UUID.randomUUID().toString());
        parentSweepstake.setName("Test");
        parentSweepstake.setStake(new BigDecimal("3.00"));
        parentSweepstake.setSweepstakeType(SweepstakeTypeCommon.Correct_Score_FT);
        parentSweepstake.setStatus(SweepstakeCommon.SweepstakeStatus.OPEN);

        BuyTicketSagaData sagaData = new BuyTicketSagaData();
        sagaData.setParentSweepstake(parentSweepstake);
        sagaData.setNumberOfTickets(3);

        ticketEngine.buyTickets(sagaData);

        Assertions.assertNotNull(ticketDao.findAllTicketsBySweepstakeId(parentSweepstake.getId()));
    }
}
