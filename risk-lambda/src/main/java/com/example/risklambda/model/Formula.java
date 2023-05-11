package com.example.risklambda.model;


import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

/**
 * entity class which represent in formula table.
 */
@Data
@NoArgsConstructor
@RequiredArgsConstructor
public class Formula {
  /**
   * uniquie id.
   */
    private int id;
  /**
   * string variable that stores entity name and it cannot be null.
   */
  @NonNull
    private String entityName;
  /**
   * string variable that stores formula and it cannot be null.
   */
  @NonNull
    private String formula;
}
