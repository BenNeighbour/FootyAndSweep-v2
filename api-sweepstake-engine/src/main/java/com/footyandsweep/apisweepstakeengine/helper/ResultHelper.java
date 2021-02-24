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

package com.footyandsweep.apisweepstakeengine.helper;

import com.footyandsweep.apicommonlibrary.model.TeamCommon;
import com.footyandsweep.apicommonlibrary.model.football.FootballPlayerCommon;
import com.footyandsweep.apicommonlibrary.model.sweepstake.SweepstakeTypeCommon;
import com.footyandsweep.apisweepstakeengine.dao.FootballMatchDao;
import com.footyandsweep.apisweepstakeengine.dao.FootballMatchSquadDao;
import com.footyandsweep.apisweepstakeengine.model.FootballMatch;
import com.footyandsweep.apisweepstakeengine.model.FootballMatchSquad;
import com.footyandsweep.apisweepstakeengine.model.FootballMatchSweepstake;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class ResultHelper {

  private final FootballMatchDao footballMatchDao;
  private final FootballMatchSquadDao footballMatchSquadDao;

  private final Map<Integer, String> RESULT = new HashMap<>();

  public ResultHelper(
      FootballMatchDao footballMatchDao, FootballMatchSquadDao footballMatchSquadDao) {
    this.footballMatchDao = footballMatchDao;
    this.footballMatchSquadDao = footballMatchSquadDao;
  }

  public Map<Integer, String> buildResultsForSweepstakeType(
      SweepstakeTypeCommon sweepstakeType, FootballMatchSweepstake sweepstake) {
    switch (sweepstakeType) {
      case Correct_Score_FT:
        return this.buildCorrectScoreAtMap(sweepstake.getCorrectScoreMax());
      case Correct_Score_HT:
        return this.buildCorrectScoreAtMap(sweepstake.getCorrectScoreMax());
    }

    return null;
  }

  public Map<Integer, String> buildCorrectScoreAtMap(int max) {
    RESULT.clear();

    for (int i = 1, j = 0; j <= max; j += 1) {
      for (int k = 0; k <= max; k++, i++) {
        RESULT.put(i, j + " - " + k);
      }
    }
    return RESULT;
  }

  public Map<Integer, String> buildNumberOfMap(int max, int range) {
    RESULT.clear();

    for (int i = 1, j = 0; j <= max; i++, j += range) {
      RESULT.put(i, j + (range > 1 ? " - " + j + (range - 1) : ""));
    }
    return RESULT;
  }

  private Map<Integer, String> buildResultAtMap() {
    RESULT.clear();

    RESULT.put(1, "Home");
    RESULT.put(2, "Draw");
    RESULT.put(3, "Away");

    return RESULT;
  }

  private Map<Integer, String> buildResultAtHalfTimeAndFullTimeMap() {
    RESULT.clear();

    RESULT.put(1, "Home/Home");
    RESULT.put(2, "Home/Draw");
    RESULT.put(3, "Home/Away");
    RESULT.put(4, "Draw/Home");
    RESULT.put(5, "Draw/Draw");
    RESULT.put(6, "Draw/Away");
    RESULT.put(7, "Away/Home");
    RESULT.put(8, "Away/Draw");
    RESULT.put(9, "Away/Away");

    return RESULT;
  }

  public Map<Integer, String> buildTimeOfMap(int range) {
    RESULT.clear();

    for (int i = 1, j = 1; j <= 90; i++, j += range) {
      RESULT.put(
          i,
          getMinuteText(j) + (range > 1 ? " - " + getMinuteText(j + (range - 1)) : "") + " minute");
    }

    return RESULT;
  }

  private String getMinuteText(int minute) {
    String minuteValue = String.valueOf(minute);
    if (minuteValue.endsWith("1") && !minuteValue.equalsIgnoreCase("11")) {
      return minuteValue + "st";
    } else if (minuteValue.endsWith("2") && !minuteValue.equalsIgnoreCase("12")) {
      return minuteValue + "nd";
    } else if (minuteValue.endsWith("3") && !minuteValue.equalsIgnoreCase("13")) {
      return minuteValue + "rd";
    } else {
      return minuteValue + "th";
    }
  }

  public Map<Integer, String> buildScorerOfMap(FootballMatchSweepstake footballMatchSweepstake) {
    RESULT.clear();

    List<FootballPlayerCommon> footballMatchPlayerList = new ArrayList<>();

    FootballMatch footballMatch =
        footballMatchDao.findFootballMatchById(footballMatchSweepstake.getFootballMatchId());
    FootballMatchSquad footballMatchSquadHome =
        footballMatchSquadDao.findFootballMatchSquadById(footballMatch.getHomeMatchSquadId());
    FootballMatchSquad footballMatchSquadAway =
        footballMatchSquadDao.findFootballMatchSquadById(footballMatch.getAwayMatchSquadId());

    footballMatchPlayerList.addAll(footballMatchSquadHome.getPlayers());
    footballMatchPlayerList.addAll(footballMatchSquadAway.getPlayers());

    if (!footballMatchSweepstake.getIncludeBench()) {
      footballMatchPlayerList =
          footballMatchPlayerList.stream()
              .filter(footballMatchPlayer -> !footballMatchPlayer.getIsSubstitute())
              .collect(Collectors.toList());
    }

    if (!footballMatchSweepstake.getIncludeStartingGoalkeeper()) {
      footballMatchPlayerList =
          footballMatchPlayerList.stream()
              .filter(footballMatchPlayer -> !footballMatchPlayer.getIsStartingGoalkeeper())
              .collect(Collectors.toList());
    }

    if (footballMatchSweepstake.getIncludeNoGoalScorer()) {
      RESULT.put(0, "No Goal Scorer");
    }

    footballMatchPlayerList.forEach(
        footballMatchPlayer -> RESULT.put(1, footballMatchPlayer.getPosition()));

    return RESULT;
  }

  public int getWinningResultCode(int min, int max, int range, int value) {
    for (int i = 1, j = min; j <= max; i++, j += range) {
      if (value >= j && value <= j + (range - 1)) {
        return i;
      }
    }
    return 0;
  }

  public int getWinningScoreResultCode(int max, int homeScore, int awayScore) {
    for (int i = 1, j = 0; j <= max; j += 1) {
      for (int k = 0; k <= max; k++, i++) {
        if (j == homeScore && k == awayScore) {
          return i;
        }
      }
    }
    return 0;
  }

  public int getWinningResultCode(List<TeamCommon> footballTeamList, String winningTeamId) {
    for (int i = 0; i < footballTeamList.size(); i++) {
      TeamCommon footballTeam = footballTeamList.get(i);

      if (footballTeam.getId().equals(winningTeamId)) {
        return i + 1;
      }
    }

    return 0;
  }
}
