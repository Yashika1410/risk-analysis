package com.example.risklambda.model;

import java.util.Objects;
import java.util.stream.IntStream;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

/**
 * entity class which represents risk_score_level table.
 */
@Data
@NoArgsConstructor
@RequiredArgsConstructor
public class RiskScoreLevel {
  /**
   * uniquie id.
   */
    private int id;
  /**
   * range of the level.
   */
  @NonNull
    private String score;
  /**
   * level of the score wrt it's range.
   */
  @NonNull
    private String level;
  /**
     * returns boolean if score exsits in it's range.

     * @param value score of the company.
     * @return  boolean
     */
  public boolean inRange(final double value) {
    String[] values = score.split("-");
    return IntStream.range(
      Integer.parseInt(values[0]) - 1, Integer.parseInt(values[1]) + 1)
                .anyMatch(v -> Objects.equals(v, (int) value));

  }
}
