package com.example.riskanalysis.cronJob;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.example.riskanalysis.repository.OutputRepo;
import com.example.riskanalysis.service.RiskAnalysisService;

@Component
public class RiskAnalysisCronJob {
    @Autowired
    private OutputRepo outputRepo;
    @Autowired
    private RiskAnalysisService riskAnalysisService;

    @Scheduled(cron = "0 0 */1 * * *")
    public void cronJob() {
        System.out.println("Started Job to store data");
        riskAnalysisService.saveAnalysiedData();
    }

    @Scheduled(cron = "0 25 03 * * *")
    public void emptyOutput() {
        System.out.println("empty All");
        outputRepo.deleteAll();
    }
}
