package com.example.riskanalysis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class RiskAnalysisApplication {
	public final static Logger log = LoggerFactory.getLogger(RiskAnalysisApplication.class);
	public static void main(String[] args) {
		SpringApplication.run(RiskAnalysisApplication.class, args);
	}

}
