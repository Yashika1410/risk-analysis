package com.example.riskanalysis.requestobject;

import javax.validation.constraints.NotNull;
import lombok.Data;
/**
 * request model class which is used to create risk score cap entity object.
 */

@Data
public class RiskScoreCapModel {
  @NotNull(message = "Condition Count is mandatory")
    private int conditionCnt;
  @NotNull(message = "Capped Score is mandatory")
    private double cappedScore;
  @NotNull(message = "Condition Level is mandatory")
    private int riskScoreLevelId;
}
