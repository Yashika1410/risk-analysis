package com.example.riskanalysis.repository;

import org.springframework.data.repository.CrudRepository;

import com.example.riskanalysis.entity.Formula;

public interface FormulaRepo extends CrudRepository<Formula, Integer> {
    boolean existsByEntityName(String entityName);
}
