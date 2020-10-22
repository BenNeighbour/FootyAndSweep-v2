/*
 * Copyright 2020 FootyAndSweep
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

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
