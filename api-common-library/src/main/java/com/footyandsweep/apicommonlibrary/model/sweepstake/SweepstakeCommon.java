/*
 *   Copyright 2021 FootyAndSweep
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

import com.fasterxml.jackson.annotation.JsonValue;
import com.google.gson.annotations.Expose;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.security.SecureRandom;
import java.util.Date;
import java.util.Map;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@MappedSuperclass
public class SweepstakeCommon implements Serializable {

  private static final long serialVersionUID = -771315870335755392L;

  @Id
  @GeneratedValue(generator = "system-uuid")
  @GenericGenerator(name = "system-uuid", strategy = "uuid")
  private String id;

  @NotNull(message = "There must be a Sweepstake name")
  private String name;

  private String joinCode = generateSweepstakeCode();

  private SweepstakeStatus status = SweepstakeStatus.OPEN;

  private Boolean isPrivate;

  @NotNull(message = "There must be a an owner id!")
  private String ownerId;

  @NotNull(message = "The Sweepstake must be a linked event!")
  private String sweepstakeEventId;

  @NotNull(message = "You must select a sweepstake type!")
  private SweepstakeTypeCommon sweepstakeType = this.getSweepstakeType();

  @Min(2)
  @NotNull(message = "The Sweepstake must have at least 2 players!")
  private int minimumPlayers;

  @NotNull private int maximumPlayerTickets;

  @DecimalMin(value = "1.0", inclusive = false, message = "The stake must be at least 1 FootyCoin")
  @NotNull(message = "There must be a stake!")
  private BigDecimal stake;

  private int totalNumberOfTickets;

  @Expose(serialize = false, deserialize = false)
  @CreationTimestamp
  private Date created;

  @Expose(serialize = false, deserialize = false)
  @UpdateTimestamp
  private Date updated;

  public Map<Integer, String> getSweepstakeResultMap() {
    return null;
  }

  public static String generateSweepstakeCode() {
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
    OPEN("Open"),
    INPLAY("In-Play"),
    SETTLED("Settled");

    private String value;

    SweepstakeStatus(String value) {
      this.value = value;
    }

    @JsonValue
    public String getValue() {
      return value;
    }
  }
}
