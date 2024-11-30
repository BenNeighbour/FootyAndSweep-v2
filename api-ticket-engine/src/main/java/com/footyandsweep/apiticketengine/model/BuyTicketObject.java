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

package com.footyandsweep.apiticketengine.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BuyTicketObject implements Serializable {

  private static final long serialVersionUID = -137905864975367114L;

  @NotNull(message = "There must be an owner of these tickets!")
  private String ownerId;

  @NotNull(message = "You need to put in a join code to join a Sweepstake!")
  private String joinCode;

  @Min(value = 1, message = "You must buy at least 1 ticket!")
  @Max(value = 19, message = "You cannot buy more than 19 tickets!")
  @NotNull(message = "Please select the number of tickets you want to buy!")
  private int numberOfTickets;
}
