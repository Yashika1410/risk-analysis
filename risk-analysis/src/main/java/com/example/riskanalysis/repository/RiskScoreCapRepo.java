package com.example.riskanalysis.repository;

import com.example.riskanalysis.entity.RiskScoreCap;
import org.springframework.data.repository.CrudRepository;

/**
 * interface which is used to perform crud operations on RiskScoreCap Table.
 */

public interface RiskScoreCapRepo extends CrudRepository<RiskScoreCap, Integer> {

}
