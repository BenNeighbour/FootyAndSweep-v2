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

package com.footyandsweep.apicommonlibrary.model.user;

import com.footyandsweep.apicommonlibrary.events.ProcessStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@MappedSuperclass
@Embeddable
public class UserCommon implements Serializable {

  private static final long serialVersionUID = -8782116311771480122L;

  @Id @GeneratedValue private UUID id;

  private String name;

  private String password;

  private AuthProvider provider;

  private String email;

  private BigDecimal balance;

  private boolean isSubscribedToEmails;

  private Date dateOfBirth;

  @CreationTimestamp private Date created;

  @UpdateTimestamp private Date updated;

  @Enumerated(EnumType.STRING)
  private ProcessStatus processStatus = ProcessStatus.RELATIONS_PENDING;

  enum AuthProvider {
    local,
    facebook,
    google
  }
}
