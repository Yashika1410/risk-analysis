package com.example.risklambda.service;

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

import com.example.risklambda.dao.OutputDao;
import com.example.risklambda.dao.TransactionsDao;
import com.example.risklambda.entity.JobTrasactions;
import com.example.risklambda.entity.Output;
import com.example.risklambda.model.CompanyRiskScore;
import com.example.risklambda.model.Formula;
import com.example.risklambda.model.RiskScoreCap;
import com.example.risklambda.model.RiskScoreLevel;
import com.example.risklambda.model.Score;
import com.example.risklambda.model.Weight;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fathzer.soft.javaluator.DoubleEvaluator;
import com.fathzer.soft.javaluator.StaticVariableSet;

/**
 * Evaluate class to perform analysis.
 */
public class EvaluateJob {
    /**
     * private log object for logging.
     */
    private final Logger log = LoggerFactory.getLogger(
            EvaluateJob.class);
    /**
     * variable to store magic number.
     */
    private static final double MAX_SCORE = 100.0;

    /**
     * get data object which used to get raw data.
     */
    private GetData getData = new GetData(Token.getToken());
    /**
     * output dao object to perform operation on output table.
     */
    private OutputDao outputDao = new OutputDao();
    /**
     * transaction dao object to perform operation on trasaction table.
     */
    private TransactionsDao transactionsDao = new TransactionsDao();

    /**
     * It is used to get anlysied data using formulas
     * and company data exists in db.

     * @param formulas         represents List of Formula class object

     * @param companyRiskScore represents Company Risk Score Class object

     * @param variableSet      set of variables which value.
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
            variableSet.set(formula.getEntityName(), output.get(
                    formula.getEntityName()));
        });
        result.put("result", output);
        return result;
    }

    /**
     * Update the Trasaction object in db according to the status of the job.
     *
     * @param jobTrasaction        is a AnalysisJobTrasaction Class object
     *
     * @param totalCompaniesCnt    is a long variable represent total number of
     *                             companies
     *
     * @param setOfFailedCompanies set of id of failed companies
     *
     * @param processedCompanies   count of processed comp
     *
     * @param status               status of the job
     */
    private void updatingTrasaction(
            final JobTrasactions jobTrasaction,
            final long totalCompaniesCnt,
            final Set<Integer> setOfFailedCompanies,
            final long processedCompanies, final String status) {
        jobTrasaction.setListOfFailedCompaniesId(
                setOfFailedCompanies);
        jobTrasaction.setProcessedCompanies(processedCompanies);
        jobTrasaction.setTotalCompanies(totalCompaniesCnt);
        jobTrasaction.setEndedAt(
                new Timestamp(System.currentTimeMillis()));
        jobTrasaction.setStatus(status);
        transactionsDao.updateTrasaction(jobTrasaction);
    }

    /**
     * Create a trasaction in db.
     *
     * @return AnalysisJobTrasaction
     */
    private JobTrasactions creatingNewTrasaction() {
        JobTrasactions newJobTrasaction = new JobTrasactions();
        newJobTrasaction.setStartedAt(
                new Timestamp(System.currentTimeMillis()));
        newJobTrasaction.setStatus("Started");
        return transactionsDao.addTrasaction(
                newJobTrasaction);
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

     * @param riskScoreCaps    risk score cap class object

     * @param riskScoreLevels  list of risk score levels

     * @param companyRiskScore object of company risk score entity class

     * @return int totalRiskCappedScore
     */
    private double getTotalRiskCappedScore(
            final List<RiskScoreCap> riskScoreCaps,
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

     * @param value           score of company which is required to get level

     * @return level according to the score and range
     */
    private String getLevel(final List<RiskScoreLevel> riskScoreLevels,
            final double value) {
        return riskScoreLevels.stream().filter(
                v -> Objects.equals(true, v.inRange(
                        value)))
                .findAny().orElse(
                        null)
                .getLevel().toLowerCase();
    }

    /**
     * It is used to start Analysis job.
     */
    public void saveAnalysiedData() {
        log.info("Data Analysis Job Started at: " + LocalDateTime.now());
        JobTrasactions jobTrasaction = creatingNewTrasaction();
        try {
            // getting all the data from data base
            log.info("get Data");
            List<Weight> weights = getData.getWeights();
            List<RiskScoreLevel> riskScoreLevels = getData.getRiskScoreLevels();
            List<RiskScoreCap> riskScoreCaps = getData.getRiskScoreCaps();
            List<Formula> formulas = getData.getFormulas();
            List<CompanyRiskScore> companyRiskScores =
            getData.getCompanyRiskScores();

            Set<Integer> setOfFailedCompanies = new HashSet<Integer>();
            long processedCompanies = 0;

            for (CompanyRiskScore companyRiskScore : companyRiskScores) {
                StaticVariableSet<Double> variableSet =
                new StaticVariableSet<Double>();
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
                                    riskScoreCaps,
                                    riskScoreLevels,
                                    companyRiskScore));
                    // Analysing and saving result
                    outputDao.addOutput(mapToOutput(
                            getResultMap(
                                    formulas, companyRiskScore, variableSet)));
                    processedCompanies += 1.0;
                    log.info(
                        "Successfully Anaylised and saved data in table for "
                        + companyRiskScore.getCompanyName()
                        + " company");
                } catch (Exception e) {
                    setOfFailedCompanies.add(companyRiskScore.getCompanyId());
                    e.printStackTrace();
                    log.error(e.getMessage(), e);
                }
            }
            updatingTrasaction(jobTrasaction,
                    companyRiskScores.size(),
                    setOfFailedCompanies,
                    processedCompanies,
                    "Completed");
            log.info("Data Analysis Job Completed at: " + LocalDateTime.now());
        } catch (Exception e) {
            updatingTrasaction(jobTrasaction,
                    0, null, 0, "Failed");
            log.info(e.getMessage());
            e.printStackTrace();
            log.error(e.getMessage(), e.getCause());
        }
    }
}
