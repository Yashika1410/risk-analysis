package com.example.risklambda.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;



/**
 * entity in which dimension and score is saved for each company.
 */
@Data
@NoArgsConstructor
@RequiredArgsConstructor
public class Score {
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
   * score of the company.
   */
    private double score;
}
