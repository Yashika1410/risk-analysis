package com.example.riskanalysis.entity;

import java.util.Objects;
import java.util.stream.IntStream;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.ColumnTransformer;

/**
 * entity class which represents risk_score_level table.
 */
@Entity
@Table(name = "risk_score_level")
@Data
@NoArgsConstructor
@RequiredArgsConstructor
public class RiskScoreLevel {
  /**
   * uniquie id.
   */
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
    private int id;
  /**
   * range of the level.
   */
  @Column(name = "score", nullable = false)
  @NonNull
  @NotNull(message = "Score is mandatory")
  @NotEmpty(message = "Score is mandatory")
    private String score;
  /**
   * level of the score wrt it's range.
   */
  @Column(name = "`level`", unique = true)
  @ColumnTransformer(write = "LOWER(?)", read = "LOWER(`level`)")
  @NonNull
  @NotNull(message = "Level is mandatory")
  @NotEmpty(message = "Level is mandatory")
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
