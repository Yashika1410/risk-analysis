package com.example.riskanalysis.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.ColumnTransformer;



/**
 * entity in which dimension and score is saved for each company.
 * 
 */
@Data
@NoArgsConstructor
@RequiredArgsConstructor
@Entity
@Table(name = "score")
public class Score {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
    private int id;
  @NonNull
  @Column(name = "dimension", nullable = false)
  @ColumnTransformer(write = "LOWER(?)", read = "LOWER(dimension)")
    private String dimension;
  @Column(name = "score", nullable = false)
    private double score;
}
