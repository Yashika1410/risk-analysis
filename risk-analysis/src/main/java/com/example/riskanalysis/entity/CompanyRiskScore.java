package com.example.riskanalysis.entity;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
/**
 * entity class that is used to represent company risk score table.
 */

@Entity
@Table(name = "company_risk_score")
@Data
@NoArgsConstructor
@RequiredArgsConstructor
public class CompanyRiskScore {

  /**
   * unique id.
   */
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "company_id")
    private int companyId;

  /**
   * string variable that stores company name and cannot be nullable.
   */
  @Column(name = "company_name", nullable = false)
  @NonNull
  @NotNull(message = "Company Name is mandatory")
  @NotEmpty(message = "Company Name is mandatory")
    private String companyName;

  /**
   * set collection that stores company scores wrt there dimensions
   * and cannot be nullable.
   */
  @NotNull(message = "Dimension score is mandatory")
  @NotEmpty(message = "Dimesion score is mandatory")
  @OneToMany(targetEntity = com.example.riskanalysis.entity.Score.class,
      cascade = CascadeType.ALL, fetch = FetchType.EAGER,
      orphanRemoval = true)
    private Set<Score> dimensionScores = new HashSet<>();
  /**
   * add all dimension scores.

   * @param newDimensionScores set of score objects.
   */
  public void setDimensionScores(final Set<Score> newDimensionScores) {
    this.dimensionScores.clear();
    this.dimensionScores.addAll(newDimensionScores);
  }

}
