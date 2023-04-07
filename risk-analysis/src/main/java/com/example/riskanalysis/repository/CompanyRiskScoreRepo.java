package com.example.riskanalysis.repository;

import org.springframework.data.repository.CrudRepository;

import com.example.riskanalysis.entity.CompanyRiskScore;

public interface CompanyRiskScoreRepo extends CrudRepository<CompanyRiskScore, Integer> {

}
