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

package com.footyandsweep.apicommonlibrary.model.sweepstake;

import com.footyandsweep.apicommonlibrary.events.ProcessStatus;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.security.SecureRandom;
import java.util.Date;
import java.util.Map;
import java.util.UUID;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@MappedSuperclass
public class SweepstakeCommon implements Serializable {

  private static final long serialVersionUID = -771315870335755392L;

  @Id @GeneratedValue private UUID id;

  @NotNull(message = "There must be a Sweepstake name")
  private String name;

  private String joinCode = generateSweepstakeCode();

  private SweepstakeStatus status = SweepstakeStatus.OPEN;

  private Boolean isPrivate;

  @NotNull(message = "There must be a an owner id!")
  private UUID ownerId;

  @NotNull(message = "The Sweepstake must be a linked event!")
  private UUID sweepstakeEventId;

  @NotNull(message = "You must select a sweepstake type!")
  @Enumerated(EnumType.STRING)
  private SweepstakeTypeCommon sweepstakeType = this.getSweepstakeType();

  @Enumerated(EnumType.STRING)
  private ProcessStatus processStatus = ProcessStatus.RELATIONS_PENDING;

  @Transient private int sweepstakeListSize;

  @Min(2)
  @NotNull(message = "The Sweepstake must have at least 2 players!")
  private int minimumPlayers;

  @NotNull private int maximumPlayerTickets;

  @DecimalMin(value = "1.0", inclusive = false, message = "The stake must be at least 1 FootyCoin")
  @NotNull(message = "There must be a stake!")
  private BigDecimal stake;

  private int totalNumberOfTickets;

  @CreationTimestamp private Date created;

  @UpdateTimestamp private Date updated;

  public Map<Integer, String> getSweepstakeResultMap() {
    return null;
  }

  private String generateSweepstakeCode() {
    String alphabet = "abcdefghijklmnopqrstuvwxyz";
    String alphabetUppercase = alphabet.toUpperCase();
    String possibleNumbers = "0123456789";

    String randomString = alphabet + alphabetUppercase + possibleNumbers;
    SecureRandom random = new SecureRandom();

    StringBuilder sb = new StringBuilder(8);
    for (int i = 0; i < 8; i++) {
      int rndCharAt = random.nextInt(randomString.length());
      char rndChar = randomString.charAt(rndCharAt);

      sb.append(rndChar);
    }

    return sb.toString();
  }

  public enum SweepstakeStatus {
    OPEN(0),
    ALLOCATED(1),
    CLOSED(2);

    private int code;

    SweepstakeStatus(int code) {
      this.code = code;
    }

    public int getSweepstakeStatus() {
      return this.code;
    }
  }
}
