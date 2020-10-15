package com.footyandsweep.apisweepstakeengine.model;

import com.footyandsweep.apicommonlibrary.model.TicketCommon;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "sweepstake")
public class Ticket extends TicketCommon {
}
