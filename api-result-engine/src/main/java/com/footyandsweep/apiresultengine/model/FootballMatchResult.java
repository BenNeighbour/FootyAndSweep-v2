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

package com.footyandsweep.apiresultengine.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.UUID;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "FootballMatchResult")
public class FootballMatchResult extends Result implements Serializable {

  private static final long serialVersionUID = 5062727639670623525L;

  @Column(name = "footballMatchId")
  private String footballMatchId;

  @Column(name = "halfTimeHomeScore")
  private int halfTimeHomeScore;

  @Column(name = "halfTimeAwayScore")
  private int halfTimeAwayScore;

  @Column(name = "fullTimeHomeScore")
  private int fullTimeHomeScore;

  @Column(name = "fullTimeAwayScore")
  private int fullTimeAwayScore;

  @Column(name = "firstGoalMinute")
  private int firstGoalMinute;

  @Column(name = "lastGoalMinute")
  private int lastGoalMinute;

  @Column(name = "numberOfYellowCards")
  private int numberOfYellowCards;

  @Column(name = "numberOfCornersHalfTime")
  private int numberOfCornersHalfTime;

  @Column(name = "numberOfCornersFullTime")
  private int numberOfCornersFullTime;

  @Column(name = "firstGoalScorer")
  private String firstGoalScorer;

  @Column(name = "lastGoalScorer")
  private String lastGoalScorer;
}
