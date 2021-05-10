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

import java.util.Map;

public enum SweepstakeTypeCommon {
  Correct_Score_FT("Correct Score F/T"),
  Correct_Score_HT("Correct Score H/T"),

  RESULT_AT_HT("Result at H/T"),
  RESULT_AT_FT("Result at F/T"),
  RESULT_AT_HT_FT("Result at HT/FT"),

  NUMBER_OF_GOALS_FT("Number of Goals F/T"),
  NUMBER_OF_GOALS_HT("Number of Goals H/T"),
  RESULT_AT_HT_1ST_HALF("Number of Goals 1st-Half"),
  RESULT_AT_HT_2ND_HALF("Number of Goals 2st-Half");

  SweepstakeTypeCommon(String type) {}

  @Override
  public String toString() {
    return super.toString();
  }

  public Map<Integer, String> getSweepstakeResultMap() {
    return null;
  }
}
