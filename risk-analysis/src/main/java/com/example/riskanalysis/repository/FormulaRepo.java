package com.example.riskanalysis.repository;

import com.example.riskanalysis.entity.Formula;
import org.springframework.data.repository.CrudRepository;

/**
 * interface which is used to perform crud operations on formula table.
 */

public interface FormulaRepo extends CrudRepository<Formula, Integer> {
  /**
   * a method which checks if any formula exists by this entity name.

   * @param entityName entity name
   * @return boolean
   */
  boolean existsByEntityName(String entityName);
}
