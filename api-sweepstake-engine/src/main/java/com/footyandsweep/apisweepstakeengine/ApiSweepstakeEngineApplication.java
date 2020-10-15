package com.footyandsweep.apisweepstakeengine;

import com.footyandsweep.apisweepstakeengine.config.RedisConfiguration;
import com.footyandsweep.apisweepstakeengine.config.WebConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import({WebConfiguration.class, RedisConfiguration.class})
public class ApiSweepstakeEngineApplication {

  public static void main(String[] args) {
    SpringApplication.run(ApiSweepstakeEngineApplication.class, args);
  }
}
