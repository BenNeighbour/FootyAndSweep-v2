package com.footyandsweep.apisweepstakeengine.model;

import com.footyandsweep.apicommonlibrary.model.SweepstakeCommon;
import com.footyandsweep.apicommonlibrary.model.UserCommon;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "sweepstake")
public class Sweepstake extends SweepstakeCommon {

    @Transient
    private List<Ticket> tickets;

    @Transient
    private UserCommon owner;

}
