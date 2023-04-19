package com.example.riskanalysis.entity;

import java.sql.Timestamp;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;
/**
 * entity class which represents output table.
 */

@Entity
@Data
@NoArgsConstructor
@Table(name = "output_table")
public class Output {

  @Id

  @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
  private String company_name;
  private double info_sec_weight;
  private double resilince_cal_weight;
  private double conduct_cal_weight;
  private double total_risk_score;
  private double composite_risk_score;
  private Timestamp timestamp;
}