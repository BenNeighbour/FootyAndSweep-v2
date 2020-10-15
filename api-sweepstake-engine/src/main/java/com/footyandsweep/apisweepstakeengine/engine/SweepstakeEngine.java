package com.footyandsweep.apisweepstakeengine.engine;

import com.footyandsweep.apicommonlibrary.model.TicketCommon;
import com.footyandsweep.apisweepstakeengine.model.Sweepstake;

import java.util.List;
import java.util.UUID;

public interface SweepstakeEngine {

    Sweepstake saveProcessedTickets(UUID sweepstakeId, List<TicketCommon> tickets);

    Sweepstake saveSweepstake(UUID ownerId, Sweepstake sweepstake);

}
