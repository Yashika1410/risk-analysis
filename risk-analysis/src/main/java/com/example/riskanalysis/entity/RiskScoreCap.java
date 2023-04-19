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
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
    private int id;

  @Column(name = "condition_count")
 
  @NotNull(message = "Condition Count is mandatory")
    private int conditionCnt;
    
  @Column(name = "capped_score")

  @NotNull(message = "Capped Score is mandatory")
    private double cappedScore;

  @NonNull
  @JoinColumn(name = "condition_level")
  @ManyToOne
    private RiskScoreLevel riskScoreLevel;
}
