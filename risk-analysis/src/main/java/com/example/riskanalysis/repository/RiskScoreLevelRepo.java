package com.example.riskanalysis.repository;

import com.example.riskanalysis.entity.RiskScoreLevel;
import org.springframework.data.repository.CrudRepository;

/**
 * interface which is used to perform crud operations on RiskScoreLevel Table.
 */

public interface RiskScoreLevelRepo extends CrudRepository<
    RiskScoreLevel, Integer> {
  /**
   * a method which checks if any risk score level exists by this level.

   * @param level level of the risk score.
   * @return boolean
   */
  boolean existsByLevel(String level);
}
