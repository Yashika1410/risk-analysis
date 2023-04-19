package com.example.riskanalysis.cronjob;

import com.example.riskanalysis.repository.OutputRepo;
import com.example.riskanalysis.service.RiskAnalysisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
/**
 * component class used to schdule a job.
 */

@Component
public class RiskAnalysisCronJob {
  /**
   * autowired output repo interface.
   */
  @Autowired
    private OutputRepo outputRepo;
  /**
   * autowired risk analysis service class.
   */
  @Autowired
    private RiskAnalysisService riskAnalysisService;

  /**
   * A function which trigger every hour.
   */
  @Scheduled(cron = "0 0 */1 * * *")
    public void cronJob() {
    System.out.println("Started Job to store data");
    riskAnalysisService.saveAnalysiedData("Cron Job");
  }

  /**
   * a function which trigger at 3:25 am.
   */
  @Scheduled(cron = "0 25 03 * * *")
    public void emptyOutput() {
    System.out.println("empty All");
    outputRepo.deleteAll();
  }
}
