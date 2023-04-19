package com.example.riskanalysis.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
/**
 * entity class which represents risk_capped_score table .
 */

@Entity
@Table(name = "risk_capped_score")
@Data
@NoArgsConstructor
@RequiredArgsConstructor
public class RiskScoreCap {
  /**
   *unique id.
   */
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
    private int id;

  /**
   * cnt to match contion occurence for a company.
   */
  @Column(name = "condition_count")
  @NotNull(message = "Condition Count is mandatory")
    private int conditionCnt;

  /**
   * the score to awared each company if condition matched.
   */
  @Column(name = "capped_score")
  @NotNull(message = "Capped Score is mandatory")
    private double cappedScore;
  /**
   * level of the score according to the range
   * that is avaliable in risk score level table.
   */
  @NonNull
  @JoinColumn(name = "condition_level")
  @ManyToOne
    private RiskScoreLevel riskScoreLevel;
}
