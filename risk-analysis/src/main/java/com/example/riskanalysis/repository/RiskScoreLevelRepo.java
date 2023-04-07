package com.example.riskanalysis.repository;

import org.springframework.data.repository.CrudRepository;

import com.example.riskanalysis.entity.RiskScoreLevel;

public interface RiskScoreLevelRepo extends CrudRepository<RiskScoreLevel, Integer> {

}
