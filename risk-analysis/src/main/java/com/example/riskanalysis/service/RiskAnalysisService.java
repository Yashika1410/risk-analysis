package com.example.riskanalysis.service;

import com.example.riskanalysis.entity.AnalysisJobTrasaction;
import com.example.riskanalysis.entity.CompanyRiskScore;
import com.example.riskanalysis.entity.Formula;
import com.example.riskanalysis.entity.Output;
import com.example.riskanalysis.entity.RiskScoreCap;
import com.example.riskanalysis.entity.RiskScoreLevel;
import com.example.riskanalysis.entity.Score;
import com.example.riskanalysis.entity.Weight;
import com.example.riskanalysis.repository.AnalysisJobTrasactionRepo;
import com.example.riskanalysis.repository.CompanyRiskScoreRepo;
import com.example.riskanalysis.repository.FormulaRepo;
import com.example.riskanalysis.repository.OutputRepo;
import com.example.riskanalysis.repository.RiskScoreCapRepo;
import com.example.riskanalysis.repository.RiskScoreLevelRepo;
import com.example.riskanalysis.repository.WeightRepo;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fathzer.soft.javaluator.DoubleEvaluator;
import com.fathzer.soft.javaluator.StaticVariableSet;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * The {@link RiskAnalysisService} is a service class used for get
 * and generate analysied data of company data using some parameters.
 *
 */
@Service
public class RiskAnalysisService {
  /**
   * variable to store magic number.
   */
  private static final double MAX_SCORE = 100.0;

  /**
   * autowired Company Risk Score  Repo interface.
   */
  @Autowired
  private CompanyRiskScoreRepo companyRiskScoreRepo;
  /**
   * autowired Formula Repo interface.
   */
  @Autowired
  private FormulaRepo formulaRepo;
  /**
   * autowired Weight Repo interface.
   */
  @Autowired
  private WeightRepo weightRepo;
  /**
   * autowired Risk Score  Repo interface.
   */
  @Autowired
  private RiskScoreCapRepo riskScoreCapRepo;
  /**
   * autowired Risk Score Cap Repo interface.
   */
  @Autowired
  private RiskScoreLevelRepo riskScoreLevelRepo;
  /**
   * autowired Risk Score Level Repo interface.
   */
  @Autowired
  private OutputRepo outputRepo;
  /**
   * autowired Risk Score  Repo interface.
   */
  @Autowired
  private AnalysisJobTrasactionRepo analysisJobTrasactionRepo;
  /**
   * private log object for logging.
   */
  private final Logger log = LoggerFactory.getLogger(
      RiskAnalysisService.class);


  /**
   * It is used to get anlysied data using formulas
   * and company data exists in db.

   * @param formulas represents List of Formula class object

   * @param companyRiskScore represents Company Risk Score Class object

   * @param variableSet repesents
   * @return Map
   */
  private Map<String, Object> getResultMap(final List<Formula> formulas,
      final CompanyRiskScore companyRiskScore,
      final StaticVariableSet<Double> variableSet) {
    DoubleEvaluator doubleEvaluator = new DoubleEvaluator();
    Map<String, Object> result = new HashMap<>();
    result.put("companyName", companyRiskScore.getCompanyName());
    Map<String, Double> output = new HashMap<>();
    // calculating data using formulas present in formula table
    formulas.forEach(formula -> {
      output.put(formula.getEntityName(), doubleEvaluator.evaluate(
          formula.getFormula(), variableSet));
      variableSet.set(formula.getEntityName(),  output.get(
          formula.getEntityName()));
    });
    result.put("result", output);
    return result;
  }

  /**
   * Update the Trasaction object in db according to the status of the job.
   *
   * @param analysisJobTrasaction is a AnalysisJobTrasaction Class object
   *
   * @param totalCompaniesCnt     is a long variable represent total number of
   *                              companies
   *
   * @param setOfFailedCompanies  set of id of failed companies
   *
   * @param processedCompanies    count of processed comp
   *
   * @param status                status of the job
   */
  private void updatingTrasaction(
      final AnalysisJobTrasaction analysisJobTrasaction,
      final long totalCompaniesCnt, final Set<Integer> setOfFailedCompanies,
      final long processedCompanies, final String status) {
    analysisJobTrasaction.setListOfFailedCompaniesId(setOfFailedCompanies);
    analysisJobTrasaction.setProcessedCompanies(processedCompanies);
    analysisJobTrasaction.setTotalCompanies(totalCompaniesCnt);
    analysisJobTrasaction.setEndedAt(new Timestamp(System.currentTimeMillis()));
    analysisJobTrasaction.setStatus(status);
    analysisJobTrasactionRepo.save(analysisJobTrasaction);
  }

  /**
   * Create a trasaction in db.
   *
   * @param triggerBy trigger by api or cron job
   * @return AnalysisJobTrasaction
   */
  private AnalysisJobTrasaction creatingNewTrasaction(final String triggerBy) {
    AnalysisJobTrasaction newAnalysisJobTrasaction = new AnalysisJobTrasaction(
    );
    newAnalysisJobTrasaction.setStartedAt(
      new Timestamp(System.currentTimeMillis()));
    newAnalysisJobTrasaction.setTriggerBy(triggerBy);
    newAnalysisJobTrasaction.setStatus("Started");
    AnalysisJobTrasaction analysisJobTrasaction =
        analysisJobTrasactionRepo.save(
        newAnalysisJobTrasaction);
    return analysisJobTrasaction;
  }

  /**
   * Used to convert Map object to Output class object.
   *
   * @param result map which contains analysied data
   * @return Output
   */
  private Output mapToOutput(final Map<String, Object> result) {
    final ObjectMapper mapper = new ObjectMapper();
    result.put("timestamp", new Timestamp(System.currentTimeMillis()));
    Output output = mapper.convertValue(result, Output.class);
    return output;
  }

  /**
   * It is used to calculate total risk capped score
   * according to the company scores.

   * @param riskScoreCaps risk score cap class object

   * @param riskScoreLevels list of risk score levels

   * @param companyRiskScore object of company risk score entity class

   * @return int totalRiskCappedScore
   */
  private double getTotalRiskCappedScore(final List<RiskScoreCap> riskScoreCaps,
      final List<RiskScoreLevel> riskScoreLevels,
      final CompanyRiskScore companyRiskScore) {
    List<String> levels = new ArrayList<>();
    // getting level for each Company risk score
    for (Score score : companyRiskScore.getDimensionScores()) {
      levels.add(getLevel(riskScoreLevels, score.getScore()));
    }

    double totalRiskCappedScore = MAX_SCORE;
    for (RiskScoreCap riskScoreCap : riskScoreCaps) {
      int frequency = Collections.frequency(
          levels, riskScoreCap.getRiskScoreLevel().getLevel());
      if (riskScoreCap.getConditionCnt() == frequency) {
        totalRiskCappedScore = Math.min(
          totalRiskCappedScore, riskScoreCap.getCappedScore());
      }
    }
    return totalRiskCappedScore;
  }

  /**
   * It is used to get level of company scores according
   * to the range defiend in the DB.

   * @param riskScoreLevels list of risk score level class objects

   * @param value score of company which is required to get level

   * @return level according to the score and range
   */
  private String getLevel(final List<RiskScoreLevel> riskScoreLevels,
      final double value) {
    return riskScoreLevels.stream().filter(v -> Objects.equals(true, v.inRange(
        value))).findAny().orElse(
            null)
        .getLevel().toLowerCase();
  }
  /**
   * It is used to start Analysis job.

   * @param triggerBy tigger by api or cronjob
   */
  public void saveAnalysiedData(final String triggerBy) {
    log.info("Data Analysis Job Started at: " + LocalDateTime.now());
    AnalysisJobTrasaction analysisJobTrasaction = creatingNewTrasaction(
        triggerBy);
    try {
      // getting all the data from data base
      List<Weight> weights = (List<Weight>) weightRepo.findAll();
      List<RiskScoreLevel> riskScoreLevels = (
          List<RiskScoreLevel>) riskScoreLevelRepo.findAll();
      List<RiskScoreCap> riskScoreCaps = (
          List<RiskScoreCap>) riskScoreCapRepo.findAll();
      List<Formula> formulas = (List<Formula>) formulaRepo.findAll();
      List<CompanyRiskScore> companyRiskScores = (List<CompanyRiskScore>
      ) companyRiskScoreRepo.findAll();

      Set<Integer> setOfFailedCompanies = new HashSet<Integer>();
      long processedCompanies = 0;

      for (CompanyRiskScore companyRiskScore : companyRiskScores) {
        StaticVariableSet<Double> variableSet = new StaticVariableSet<Double>();
        weights.forEach(weight -> {
          variableSet.set(weight.getDimension(), weight.getWeight());
        });
        try {

          // setting scores wrt there dimensions
          for (Score score : companyRiskScore.getDimensionScores()) {
            variableSet.set(score.getDimension(), score.getScore());
          }

          // getting total risk capped score and setting it
          variableSet.set("total_risk_capped_score",
              getTotalRiskCappedScore(
                riskScoreCaps, riskScoreLevels, companyRiskScore));
          // Analysing and saving result
          outputRepo.save(mapToOutput(
              getResultMap(
                  formulas, companyRiskScore, variableSet)));
          processedCompanies += 1.0;
          log.info("Successfully Anaylised and saved data in table for "
              + companyRiskScore.getCompanyName()
              + " company");
        } catch (Exception e) {
          setOfFailedCompanies.add(companyRiskScore.getCompanyId());
          e.printStackTrace();
          log.error(e.getMessage(), e);
        }
      }
      updatingTrasaction(analysisJobTrasaction,
          companyRiskScores.size(),
          setOfFailedCompanies,
          processedCompanies,
          "Completed");
      log.info("Data Analysis Job Completed at: " + LocalDateTime.now());
    } catch (Exception e) {
      updatingTrasaction(analysisJobTrasaction,
          0, null, 0, "Failed");
      e.printStackTrace();
      log.error(e.getMessage(), e.getCause());
    }
  }

  /**
   * It is used to get latest data from db for each company according
     to the timestamp and company name.

   * @return List{Output}
   */
  public List<Output> getOutputList() {
    return outputRepo.getLatestData();
  }

}
