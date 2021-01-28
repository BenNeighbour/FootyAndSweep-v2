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
import com.footyandsweep.apicommonlibrary.model.user.UserCommon;
import com.footyandsweep.apiticketengine.dao.TicketDao;
import com.footyandsweep.apiticketengine.engine.saga.BuyTicketSaga;
import com.footyandsweep.apiticketengine.engine.saga.BuyTicketSagaData;
import com.footyandsweep.apiticketengine.model.BuyTicketObject;
import com.footyandsweep.apiticketengine.model.Ticket;
import io.eventuate.tram.sagas.orchestration.SagaInstanceFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/internal/ticket")
public class TicketController {

  private final BuyTicketSaga buyTicketSaga;
  private final TicketDao ticketDao;

  private final SagaInstanceFactory sagaInstanceFactory;

  public TicketController(BuyTicketSaga buyTicketSaga, TicketDao ticketDao, SagaInstanceFactory sagaInstanceFactory) {
    this.buyTicketSaga = buyTicketSaga;
    this.ticketDao = ticketDao;
    this.sagaInstanceFactory = sagaInstanceFactory;
  }

  @GetMapping("/by/sweepstake/{sweepstakeId}")
  public Optional<List<Ticket>> findTicketBySweepstakeId(
          @PathVariable("sweepstakeId") UUID sweepstakeId) {
    return ticketDao.findAllTicketsBySweepstakeId(sweepstakeId);
  }

  @PostMapping("/buy")
  public BuyTicketObject buyTickets(@RequestBody BuyTicketObject buyTicket) {
    BuyTicketSagaData data = new BuyTicketSagaData();
    data.setNumberOfTickets(buyTicket.getNumberOfTickets());
    data.setParentSweepstake(new SweepstakeCommon());
    data.getParentSweepstake().setJoinCode(buyTicket.getJoinCode());

    data.setParticipant(new UserCommon());
    data.getParticipant().setId(buyTicket.getOwnerId());

    sagaInstanceFactory.create(buyTicketSaga, data);

    return buyTicket;
  }
}
