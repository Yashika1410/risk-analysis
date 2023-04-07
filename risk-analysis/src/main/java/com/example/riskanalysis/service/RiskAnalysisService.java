package com.example.riskanalysis.service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.riskanalysis.entity.CompanyRiskScore;
import com.example.riskanalysis.entity.Formula;
import com.example.riskanalysis.entity.Output;
import com.example.riskanalysis.entity.RiskScoreCap;
import com.example.riskanalysis.entity.RiskScoreLevel;
import com.example.riskanalysis.entity.Weight;
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

    public void saveAnalysiedData() {
        Map<Integer, String> numbers = new HashMap<>();
        numbers.put(0, "zero");
        numbers.put(1, "one");
        numbers.put(2, "two");
        numbers.put(3, "three");

        DoubleEvaluator doubleEvaluator = new DoubleEvaluator();
        StaticVariableSet<Double> variables = new StaticVariableSet<Double>();
        List<Weight> weights = (List<Weight>) weightRepo.findAll();

        weights.forEach(weight -> {
            variables.set(weight.getDimension(), weight.getWeight());
        });
        List<RiskScoreLevel> riskScoreLevels = (List<RiskScoreLevel>) riskScoreLevelRepo.findAll();
        List<RiskScoreCap> riskScoreCaps = (List<RiskScoreCap>) riskScoreCapRepo.findAll();
        List<Formula> formulas = (List<Formula>) formulaRepo.findAll();
        List<CompanyRiskScore> companyRiskScores = (List<CompanyRiskScore>) companyRiskScoreRepo.findAll();
        companyRiskScores.forEach(companyRiskScore -> {
            List<String> levels = new ArrayList<>();
            variables.set("information_security", companyRiskScore.getInformationSecurity());
            variables.set("resilince", companyRiskScore.getResilience());
            variables.set("conduct", companyRiskScore.getConduct());
            try {
                levels.add(riskScoreLevels.stream().filter(v -> Objects.equals(true, v.inRange(
                        companyRiskScore.getInformationSecurity()))).findAny().orElse(null).getLevel().toLowerCase());
                levels.add(riskScoreLevels.stream().filter(v -> Objects.equals(true, v.inRange(
                        companyRiskScore.getResilience()))).findAny().orElse(null).getLevel().toLowerCase());
                levels.add(riskScoreLevels.stream().filter(v -> Objects.equals(true, v.inRange(
                        companyRiskScore.getConduct()))).findAny().orElse(null).getLevel().toLowerCase());
            } catch (Exception e) {
                // TODO: handle exception
                e.printStackTrace();
            }
            double totalRiskCappedScore = 100.0;
            for (RiskScoreCap riskScoreCap : riskScoreCaps) {
                String[] split = riskScoreCap.getCondition().split(" ", 2);
                String conditionCount = split[0].toLowerCase();
                String condition = split[1].toLowerCase();
                int frequency = Collections.frequency(levels, condition);
                if (numbers.get(frequency).equals(conditionCount)) {
                    totalRiskCappedScore = Math.min(totalRiskCappedScore, riskScoreCap.getCappedScore());
                }
            }
            variables.set("total_risk_capped_score", totalRiskCappedScore);
            Map<String, Object> result = new HashMap<>();
            result.put("company_name", companyRiskScore.getCompanyName());
            try {
                formulas.forEach(formula -> {
                    result.put(formula.getEntityName(), doubleEvaluator.evaluate(formula.getFormula(), variables));
                    variables.set(formula.getEntityName(), (Double) result.get(formula.getEntityName()));
                });
            } catch (Exception e) {
                e.printStackTrace();
            }
            final ObjectMapper mapper = new ObjectMapper();
            try {
                result.put("timestamp", new Timestamp(System.currentTimeMillis()));
                Output output = mapper.convertValue(result, Output.class);
                // result.put("String", output.toString());
                outputRepo.save(output);
            } catch (Exception e) {
                // TODO: handle exception
                e.printStackTrace();
            }

        });
    }

    public List<Output> getOutputList() {
        return outputRepo.getLatestData();
    }

}
