package com.footyandsweep.apicommonlibrary.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@MappedSuperclass
@Embeddable
public class TicketCommon implements Serializable {

  private static final long serialVersionUID = -7269123358093904648L;

  @Id
  @GeneratedValue
  @Column(columnDefinition = "uuid", updatable = false, name = "id")
  private UUID ticketId;

  private TicketStatus status = TicketStatus.PENDING;

  private UUID sweepstakeId;

  private UUID allocationId;

  private UUID userId;

  @CreationTimestamp private Date created;

  @UpdateTimestamp private Date updated;

  enum TicketStatus {
    PENDING(0),
    INPLAY(1),

    REFUNDED(2),
    WON(3),
    LOST(4);

    TicketStatus(int code) {
    }
  }
}
