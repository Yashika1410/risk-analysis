package com.example.risklambda.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
/**
 * entity class which represents weight table.
 */

@Data
@NoArgsConstructor
@RequiredArgsConstructor
public class Weight {
  /**
   * unique id.
   */
    private int id;
  /**
   * dimension of the company.
   */
  @NonNull
    private String dimension;
  /**
   * weight of each dimension exists in the company table.
   */
    private double weight;
}
