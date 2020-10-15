package com.footyandsweep.apigatewayservice.relation;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.UUID;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "sweepstake_participant_id")
public class SweepstakeIds implements Serializable {

    private static final long serialVersionUID = 5778408986459720940L;

    @Id
    @Column(columnDefinition = "uuid", updatable = false)
    private UUID participantId;

    @Column(columnDefinition = "uuid", updatable = false)
    private UUID sweepstakeId;


}
