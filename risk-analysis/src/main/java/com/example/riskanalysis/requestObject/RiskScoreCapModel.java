package com.example.riskanalysis.requestobject;

import javax.validation.constraints.NotNull;
import lombok.Data;
/**
 * request model class which is used to create risk score cap entity object.
 */

@Data
public class RiskScoreCapModel {
  /**
   * cnt to match contion occurence for a company.
   */
  @NotNull(message = "Condition Count is mandatory")
    private int conditionCnt;
  /**
   * the score to awared each company if condition matched.
   */
  @NotNull(message = "Capped Score is mandatory")
    private double cappedScore;
  /**
   * unique id of the risk score level.
   */
  @NotNull(message = "Condition Level is mandatory")
    private int riskScoreLevelId;
}
