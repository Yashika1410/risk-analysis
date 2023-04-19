package com.example.riskanalysis.entity;

import java.sql.Timestamp;
import java.util.Map;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
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

  /**
   * uniquie id.
   */
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
  /**
   * string variable to store company name.
   */
  @Column(name = "company_name")
  private String companyName;
  /**
   * result map which contains analysied data.
   */
  @ElementCollection
  private Map<String, Double> result;

  /**
   * timestamp which is used to get latest data.
   */
  @Column(name = "timestamp")
  private Timestamp timestamp;
}
