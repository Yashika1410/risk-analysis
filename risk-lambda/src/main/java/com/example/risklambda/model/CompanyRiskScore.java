package com.example.risklambda.model;

import java.util.HashSet;
import java.util.Set;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
/**
 * entity class that is used to represent company risk score table.
 */

@Data
@NoArgsConstructor
@RequiredArgsConstructor
public class CompanyRiskScore {

  /**
   * unique id.
   */
    private int companyId;

  /**
   * string variable that stores company name and cannot be nullable.
   */

  @NonNull
    private String companyName;

  /**
   * set collection that stores company scores wrt there dimensions
   * and cannot be nullable.
   */
    private Set<Score> dimensionScores = new HashSet<>();
}
