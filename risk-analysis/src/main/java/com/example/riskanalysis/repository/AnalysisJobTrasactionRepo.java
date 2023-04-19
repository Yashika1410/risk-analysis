package com.example.riskanalysis.repository;

import com.example.riskanalysis.entity.AnalysisJobTrasaction;
import org.springframework.data.repository.CrudRepository;

/**
 * interface which is used for performing crud operations on
 * AnalysisJobTrasaction table.
 */

public interface AnalysisJobTrasactionRepo extends CrudRepository<
    AnalysisJobTrasaction, Integer> {

}
