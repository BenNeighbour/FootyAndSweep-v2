package com.footyandsweep.apicommonlibrary.events;

import com.footyandsweep.apicommonlibrary.model.TicketCommon;
import io.eventuate.tram.events.common.DomainEvent;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class TicketDecisioningSuccess implements Serializable, DomainEvent {

    private static final long serialVersionUID = 5234774270984284159L;

    private UUID sweepstakeId;

    private List<TicketCommon> tickets;

}
