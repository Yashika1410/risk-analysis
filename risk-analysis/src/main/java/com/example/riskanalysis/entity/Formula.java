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
@Table(name = "formula")
@Data
@NoArgsConstructor
@RequiredArgsConstructor
public class Formula {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "entity_name", nullable = false)
    @NonNull
    @NotNull(message = "Entity Name is mandatory")
    @NotEmpty(message = "Entity Name is mandatory")
    private String entityName;
    @Column(name = "formula")
    @NonNull
    @NotNull(message = "Formula is mandatory")
    @NotEmpty(message = "Formula is mandatory")
    private String formula;
}
