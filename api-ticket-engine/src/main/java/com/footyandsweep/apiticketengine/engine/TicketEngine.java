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

package com.footyandsweep.apiticketengine.engine;

import com.footyandsweep.apicommonlibrary.model.ticket.TicketCommon;
import com.footyandsweep.apiticketengine.engine.saga.BuyTicketSagaData;
import com.footyandsweep.apiticketengine.model.Ticket;
import io.eventuate.tram.commands.consumer.CommandWithDestination;

import java.lang.reflect.InvocationTargetException;

public interface TicketEngine {

  void buyTickets(BuyTicketSagaData sagaData);

  void getParentSweepstakeAndParticipant(BuyTicketSagaData sagaData);

  void deleteTicket(String ticketId);

  void modifyTickets(String ticketId, String allocationId);

  CommandWithDestination updateUserBalance(BuyTicketSagaData buyTicketSagaData);
}
