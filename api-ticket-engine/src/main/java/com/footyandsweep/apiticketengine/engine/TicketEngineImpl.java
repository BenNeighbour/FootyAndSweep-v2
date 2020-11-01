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

package com.footyandsweep.apiticketengine.engine;

import com.footyandsweep.apiticketengine.dao.TicketDao;
import com.footyandsweep.apiticketengine.model.Ticket;
import io.eventuate.tram.events.publisher.DomainEventPublisher;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class TicketEngineImpl implements TicketEngine {

  private final TicketDao ticketDao;
  private final DomainEventPublisher domainEventPublisher;

  public TicketEngineImpl(
      final TicketDao ticketDao, final DomainEventPublisher domainEventPublisher) {
    this.ticketDao = ticketDao;
    this.domainEventPublisher = domainEventPublisher;
  }

  @Override
  public List<Ticket> buyTickets(UUID userId, int numberOfTickets, String joinCode) {
    return null;
  }
}
