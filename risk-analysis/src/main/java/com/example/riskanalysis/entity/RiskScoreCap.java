package com.example.riskanalysis.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.ColumnTransformer;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Entity
@Table(name = "risk_capped_score")
@Data
@NoArgsConstructor
@RequiredArgsConstructor
public class RiskScoreCap {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "`condition`")
    @ColumnTransformer(write = "LOWER(?)", read = "LOWER(`condition`)")
    @NonNull
    @NotNull(message = "Condition is mandatory")
    @NotEmpty(message = "Condition is mandatory")
    private String condition;

    @Column(name = "capped_score")
    @NonNull
    @NotNull(message = "Capped Score is mandatory")
    @NotEmpty(message = "Capped Score is mandatory")
    private double cappedScore;

}
