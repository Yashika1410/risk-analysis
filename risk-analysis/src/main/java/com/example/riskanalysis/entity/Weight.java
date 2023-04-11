package com.example.riskanalysis.entity;

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

@Entity
@Table(name = "weight")
@Data
@NoArgsConstructor
@RequiredArgsConstructor
public class Weight {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "dimension", nullable = false)
    @NonNull
    @NotNull(message = "Dimension is mandatory")
    @NotEmpty(message = "Dimension is mandatory")
    private String dimension;
    @Column(name = "weight")
    @NonNull
    @NotNull(message = "Weight is mandatory")
    @NotEmpty(message = "Weight is mandatory")
    private double weight;
}
