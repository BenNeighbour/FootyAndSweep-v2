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

package com.footyandsweep.apicommonlibrary.model.football;

import com.footyandsweep.apicommonlibrary.model.sweepstake.SweepstakeCommon;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.util.UUID;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@MappedSuperclass
public class FootballMatchSweepstakeCommon extends SweepstakeCommon {

  private static final long serialVersionUID = -6345608862153991125L;

  @Column(name = "numberOfRange")
  private int numberOfRange;

  @Column(name = "numberOfMax")
  private int numberOfMax;

  @Column(name = "maxNumberOfRanges")
  private int maxNumberOfRanges;

  @Column(name = "correctScoreMax")
  private int correctScoreMax;

  @Column(name = "minuteRange")
  private int minuteRange;

  @Column(name = "includeBench")
  private Boolean includeBench = false;

  @Column(name = "includeStartingGoalkeeper")
  private Boolean includeStartingGoalkeeper = false;

  @Column(name = "includeNoGoalScorer")
  private Boolean includeNoGoalScorer = true;

  @Column(name = "includeOwnGoals")
  private Boolean includeOwnGoals = false;

  private UUID footballMatchId;
}
