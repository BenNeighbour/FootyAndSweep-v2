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

package com.footyandsweep.apisweepstakeengine.grpc;

import io.grpc.BindableService;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class GrpcServerRunner implements CommandLineRunner, DisposableBean {

  private final ConfigurableApplicationContext applicationContext;
  private Server server;

  public GrpcServerRunner(@Autowired ConfigurableApplicationContext applicationContext) {
    this.applicationContext = applicationContext;
  }

  @Override
  public void run(String... args) throws Exception {
    ServerBuilder serverBuilder = ServerBuilder.forPort(9090);

    String[] beanNames = applicationContext.getBeanNamesForAnnotation(GrpcService.class);
    Arrays.stream(beanNames)
        .forEach(
            beanName -> {
              BindableService service = applicationContext.getBean(beanName, BindableService.class);
              serverBuilder.addService(service);
            });

    server = serverBuilder.build().start();

    runSever();
  }

  private void runSever() {
    Thread thread =
        new Thread(
            () -> {
              try {
                server.awaitTermination();
              } catch (InterruptedException e) {
                e.printStackTrace();
              }
            });
    thread.setDaemon(false);
    thread.start();
  }

  @Override
  public void destroy() {
    server.shutdown();
  }
}
