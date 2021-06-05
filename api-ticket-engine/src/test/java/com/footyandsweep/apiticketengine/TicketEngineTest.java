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
import com.footyandsweep.apiticketengine.engine.TicketEngineImpl;
import com.footyandsweep.apiticketengine.engine.saga.BuyTicketSagaData;
import com.footyandsweep.apiticketengine.model.Ticket;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;
import java.util.UUID;

@RunWith(MockitoJUnitRunner.class)
public class TicketEngineTest {

  private static Ticket ticket;
  private static final UserCommon user = new UserCommon();

  @Mock private TicketDao ticketDao;

  @InjectMocks private TicketEngineImpl ticketEngine;

  @BeforeEach
  public void setUp() {
    ticket.setId(UUID.randomUUID().toString());
    ticket.setAllocationId(UUID.randomUUID().toString());
    ticket.setUserId(UUID.randomUUID().toString());
    ticket.setSweepstakeId(UUID.randomUUID().toString());

    user.setId(UUID.randomUUID().toString());
    user.setBalance(new BigDecimal("100.00"));

    MockitoAnnotations.initMocks(this);
  }

  @Test
  public void shouldBuyTickets() {
    SweepstakeCommon parentSweepstake = new SweepstakeCommon();
    parentSweepstake.setId(UUID.randomUUID().toString());
    parentSweepstake.setName("Test");
    parentSweepstake.setStake(new BigDecimal("3.00"));
    parentSweepstake.setSweepstakeType(SweepstakeTypeCommon.Correct_Score_FT);
    parentSweepstake.setStatus(SweepstakeCommon.SweepstakeStatus.OPEN);

    BuyTicketSagaData sagaData = new BuyTicketSagaData();
    sagaData.setParentSweepstake(parentSweepstake);
    sagaData.setNumberOfTickets(3);
    sagaData.setParticipant(user);

    /* Call the service method */
    ticketEngine.buyTickets(sagaData);

    /* Actually verify that it's been saved */
    Mockito.verify(ticketDao).save(Mockito.any(Ticket.class));

    Assertions.assertNotNull(ticketDao.findAllTicketsBySweepstakeId(parentSweepstake.getId()));
  }

  @Test
  public void shouldModifyTickets() {
    String allocationId = ticket.getAllocationId();

    Mockito.when(ticketDao.save(Mockito.any(Ticket.class))).thenAnswer(i -> i.getArguments()[0]);

    ticket = ticketDao.save(ticket);

    Mockito.when(ticketDao.findTicketById(ArgumentMatchers.anyString())).thenReturn(ticket);

    ticketEngine.modifyTickets(ticket.getId(), UUID.randomUUID().toString());

    Assertions.assertNotEquals(
        ticketDao.findTicketById(ticket.getId()).getAllocationId(), allocationId);
  }

  @Test
  public void shouldDeleteTickets() {
    Mockito.when(ticketDao.save(Mockito.any(Ticket.class))).thenAnswer(i -> i.getArguments()[0]);

    ticket = ticketDao.save(ticket);

    ticketEngine.deleteTicket(ticket.getId());

    Assertions.assertNull(ticketDao.findTicketById(ticket.getId()));
  }
}
