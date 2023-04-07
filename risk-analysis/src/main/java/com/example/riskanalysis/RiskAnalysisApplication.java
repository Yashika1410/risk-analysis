package com.example.riskanalysis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class RiskAnalysisApplication {

	public static void main(String[] args) {
		SpringApplication.run(RiskAnalysisApplication.class, args);
	}

}
