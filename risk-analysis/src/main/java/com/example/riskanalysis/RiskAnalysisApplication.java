package com.example.riskanalysis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * Springboot main class.
 */
@SpringBootApplication
@EnableScheduling
public class RiskAnalysisApplication {
  /**
 * .

 * @param args .
 */
  public static void main(final String[] args) {
    SpringApplication.run(RiskAnalysisApplication.class, args);
  }

}
