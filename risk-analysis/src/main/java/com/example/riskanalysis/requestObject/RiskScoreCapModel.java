package com.example.riskanalysis.requestObject;

import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class RiskScoreCapModel {
    @NotNull(message = "Condition Count is mandatory")
    private int conditionCnt;
    @NotNull(message = "Capped Score is mandatory")
    private double cappedScore;
    @NotNull(message = "Condition Level is mandatory")
    private int riskScoreLevelId;
}
