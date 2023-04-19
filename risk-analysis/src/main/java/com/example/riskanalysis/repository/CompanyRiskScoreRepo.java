package com.example.riskanalysis.repository;

import com.example.riskanalysis.entity.CompanyRiskScore;
import org.springframework.data.repository.CrudRepository;

/**
 * interface which is used to perform crud operations on companyRiskScore.
 */

public interface CompanyRiskScoreRepo extends CrudRepository<
    CompanyRiskScore, Integer> {

}
