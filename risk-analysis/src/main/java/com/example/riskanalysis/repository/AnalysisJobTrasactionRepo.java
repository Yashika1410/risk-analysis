package com.example.riskanalysis.repository;

import com.example.riskanalysis.entity.AnalysisJobTrasaction;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

/**
 * interface which is used for performing crud operations on
 * AnalysisJobTrasaction table.
 */

public interface AnalysisJobTrasactionRepo extends CrudRepository<
    AnalysisJobTrasaction, Integer> {

  /**
  * return list of trasactions.

  * @param skip numer of objects to be skip
  * @param limit size of the data to be send.
  * @return List of trasaction objects.
  */
  @Query(nativeQuery = true,
      value = "select * from analysis_job_trasactions order"
      + " by id desc limit :skip, :limit")
  List<AnalysisJobTrasaction> findAll(@Param(value = "skip") int skip,
      @Param(value = "limit") int limit);
}
