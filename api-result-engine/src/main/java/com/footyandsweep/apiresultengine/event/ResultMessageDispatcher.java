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

package com.footyandsweep.apiresultengine.event;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.footyandsweep.apicommonlibrary.BaseEvent;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

@Service
public class ResultMessageDispatcher {

  private final KafkaTemplate<String, BaseEvent> kafkaTemplate;
  private final ObjectMapper objectMapper;

  public ResultMessageDispatcher(
      final KafkaTemplate<String, BaseEvent> kafkaTemplate, final ObjectMapper objectMapper) {
    this.kafkaTemplate = kafkaTemplate;
    this.objectMapper = objectMapper;
  }

  public void publishEvent(BaseEvent event, String topic) {
    /* Adding listenable future to listen for a success or failure in sending the message to the kafka topic */
    ListenableFuture<SendResult<String, BaseEvent>> future = kafkaTemplate.send(topic, event);

    /* Adding callbacks that will be hit once the message is successfully/unsuccessfully */
    future.addCallback(
        new ListenableFutureCallback<SendResult<String, BaseEvent>>() {

          /* Logging/caching a failure */
          @Override
          public void onFailure(Throwable ex) {
            System.out.println("Send message failed");
          }

          /* Logging/handling success */
          @Override
          public void onSuccess(SendResult<String, BaseEvent> result) {
            System.out.println("Send message success!");
          }
        });
  }
}
