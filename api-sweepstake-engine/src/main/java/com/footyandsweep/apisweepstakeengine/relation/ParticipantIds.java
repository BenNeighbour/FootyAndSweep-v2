package com.footyandsweep.apisweepstakeengine.relation;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.UUID;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "sweepstake_participant_id")
public class ParticipantIds implements Serializable {

    private static final long serialVersionUID = 6784017128307902451L;

    @Id
    @Column(columnDefinition = "uuid", updatable = false)
    private UUID sweepstakeId;

    @Column(columnDefinition = "uuid", updatable = false)
    private UUID participantId;

}
