package com.example.riskanalysis.service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

@Service
public class RiskAnalysisService {
    @Autowired
    private CompanyRiskScoreRepo companyRiskScoreRepo;
    @Autowired
    private FormulaRepo formulaRepo;
    @Autowired
    private WeightRepo weightRepo;
    @Autowired
    private RiskScoreCapRepo riskScoreCapRepo;
    @Autowired
    private RiskScoreLevelRepo riskScoreLevelRepo;
    @Autowired
    private OutputRepo outputRepo;
    @Autowired
    private AnalysisJobTrasactionRepo analysisJobTrasactionRepo;
    /**
     *
     */
    public final static Logger log = LoggerFactory.getLogger(RiskAnalysisService.class);

    /**
     * 
     */
    public void saveAnalysiedData(String triggerBy) {
        log.info("Data Analysis Job Started at: " + LocalDateTime.now());
        AnalysisJobTrasaction newAnalysisJobTrasaction = new AnalysisJobTrasaction();
        newAnalysisJobTrasaction.setStartedAt(new Timestamp(System.currentTimeMillis()));
        newAnalysisJobTrasaction.setTriggerBy(triggerBy);
        newAnalysisJobTrasaction.setStatus("Started");
        AnalysisJobTrasaction analysisJobTrasaction = analysisJobTrasactionRepo.save(newAnalysisJobTrasaction);
        try {
            // getting all the data from data base
            List<Weight> weights = (List<Weight>) weightRepo.findAll();
            List<RiskScoreLevel> riskScoreLevels = (List<RiskScoreLevel>) riskScoreLevelRepo.findAll();
            List<RiskScoreCap> riskScoreCaps = (List<RiskScoreCap>) riskScoreCapRepo.findAll();
            List<Formula> formulas = (List<Formula>) formulaRepo.findAll();
            List<CompanyRiskScore> companyRiskScores = (List<CompanyRiskScore>) companyRiskScoreRepo.findAll();

            DoubleEvaluator doubleEvaluator = new DoubleEvaluator();
            StaticVariableSet<Double> variableSet = new StaticVariableSet<Double>();

            weights.forEach(weight -> {
                variableSet.set(weight.getDimension(), weight.getWeight());
            });

            companyRiskScores.forEach(companyRiskScore -> {
                // Setting Company risk score values
                for (Score score : companyRiskScore.getDimensionScores()) {
                    variableSet.set(score.getDimension(), score.getScore());
                }
                // variableSet.set("information_security",
                // companyRiskScore.getInformationSecurity());
                // variableSet.set("resilince", companyRiskScore.getResilience());
                // variableSet.set("conduct", companyRiskScore.getConduct());

                // getting total risk capped score and setting it
                variableSet.set("total_risk_capped_score",
                        getTotalRiskCappedScore(riskScoreCaps, riskScoreLevels, companyRiskScore));
                Map<String, Object> result = new HashMap<>();
                result.put("company_name", companyRiskScore.getCompanyName());

                // calculating data using formulas present in formula table
                formulas.forEach(formula -> {
                    result.put(formula.getEntityName(), doubleEvaluator.evaluate(
                            formula.getFormula(), variableSet));
                    variableSet.set(formula.getEntityName(), (Double) result.get(
                            formula.getEntityName()));
                });
                final ObjectMapper mapper = new ObjectMapper();
                result.put("timestamp", new Timestamp(System.currentTimeMillis()));
                Output output = mapper.convertValue(result, Output.class);
                // saving the result
                outputRepo.save(output);
                log.info("Successfully Anaylised and saved data in table for " + companyRiskScore.getCompanyName()
                        + " company");
            });
            analysisJobTrasaction.setEndedAt(new Timestamp(System.currentTimeMillis()));
            analysisJobTrasaction.setStatus("Completed");
            analysisJobTrasactionRepo.save(analysisJobTrasaction);
            log.info("Data Analysis Job Completed at: " + LocalDateTime.now());
        } catch (Exception e) {
            // TODO: handle exception
            analysisJobTrasaction.setEndedAt(new Timestamp(System.currentTimeMillis()));
            analysisJobTrasaction.setStatus("Failed");
            analysisJobTrasactionRepo.save(analysisJobTrasaction);
            e.printStackTrace();
            log.error(e.getMessage(), e.getCause());
        }
    }

    /**
     * @param riskScoreCaps
     * @param riskScoreLevels
     * @param companyRiskScore
     * @return int totalRiskCappedScore
     */
    private double getTotalRiskCappedScore(List<RiskScoreCap> riskScoreCaps, List<RiskScoreLevel> riskScoreLevels,
            CompanyRiskScore companyRiskScore) {
        List<String> levels = new ArrayList<>();
        // getting level for each Company risk score
        for (Score score : companyRiskScore.getDimensionScores())
            levels.add(getLevel(riskScoreLevels, score.getScore()));
        // levels.add(getLevel(riskScoreLevels,
        // companyRiskScore.getInformationSecurity()));
        // levels.add(getLevel(riskScoreLevels, companyRiskScore.getResilience()));
        // levels.add(getLevel(riskScoreLevels, companyRiskScore.getConduct()));
        double totalRiskCappedScore = 100.0;
        for (RiskScoreCap riskScoreCap : riskScoreCaps) {
            int frequency = Collections.frequency(levels, riskScoreCap.getRiskScoreLevel().getLevel());
            if (riskScoreCap.getConditionCnt() == frequency) {
                totalRiskCappedScore = Math.min(totalRiskCappedScore, riskScoreCap.getCappedScore());
            }
        }
        return totalRiskCappedScore;
    }

    private String getLevel(List<RiskScoreLevel> riskScoreLevels, double value) {
        return riskScoreLevels.stream().filter(v -> Objects.equals(true, v.inRange(
                value))).findAny().orElse(
                        null)
                .getLevel().toLowerCase();
    }

    /**
     * @return List<Output>
     */
    public List<Output> getOutputList() {
        return outputRepo.getLatestData();
    }

}
