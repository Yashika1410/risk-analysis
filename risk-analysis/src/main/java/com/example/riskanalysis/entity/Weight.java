package com.example.riskanalysis.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.ColumnTransformer;
/**
 * entity class which represents weight table.
 */

@Entity
@Table(name = "weight")
@Data
@NoArgsConstructor
@RequiredArgsConstructor
public class Weight {
  /**
   * unique id.
   */
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
    private int id;
  /**
   * dimension of the company.
   */
  @Column(name = "dimension", nullable = false)
  @ColumnTransformer(write = "LOWER(?)", read = "LOWER(dimension)")
  @NonNull
  @NotNull(message = "Dimension is mandatory")
    private String dimension;
  /**
   * weight of each dimension exists in the company table.
   */
  @Column(name = "weight")
  @NotNull(message = "Weight is mandatory")
    private double weight;
}
