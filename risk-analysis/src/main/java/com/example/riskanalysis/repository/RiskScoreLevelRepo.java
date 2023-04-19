package com.example.riskanalysis.repository;

import com.example.riskanalysis.entity.RiskScoreLevel;
import org.springframework.data.repository.CrudRepository;

/**
 * interface which is used to perform crud operations on RiskScoreLevel Table.
 */

public interface RiskScoreLevelRepo extends CrudRepository<RiskScoreLevel, Integer> {
  boolean existsByLevel(String level);
}
