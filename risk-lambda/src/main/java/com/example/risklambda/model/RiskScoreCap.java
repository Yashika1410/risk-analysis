package com.example.risklambda.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
/**
 * entity class which represents risk_capped_score table .
 */
@Data
@NoArgsConstructor
@RequiredArgsConstructor
public class RiskScoreCap {
  /**
   *unique id.
   */
    private int id;

  /**
   * cnt to match contion occurence for a company.
   */
    private int conditionCnt;

  /**
   * the score to awared each company if condition matched.
   */
    private double cappedScore;
  /**
   * level of the score according to the range
   * that is avaliable in risk score level table.
   */
  @NonNull
    private RiskScoreLevel riskScoreLevel;
}
