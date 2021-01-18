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

package com.footyandsweep.apiresultengine.event;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.footyandsweep.apiresultengine.dao.ResultDao;
import com.footyandsweep.apiresultengine.engine.ResultEngine;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class ResultMessageListener {

  private final ObjectMapper objectMapper;
  private final ResultEngine resultEngine;
  private final ResultDao resultDao;

  public ResultMessageListener(
      ObjectMapper objectMapper, ResultEngine resultEngine, ResultDao resultDao) {
    this.objectMapper = objectMapper;
    this.resultEngine = resultEngine;
    this.resultDao = resultDao;
  }

  @KafkaListener(topics = "api-result-events-topic")
  public void allocationEventListener(String serializedMessage) {}
}
