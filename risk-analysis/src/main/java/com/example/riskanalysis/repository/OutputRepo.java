package com.example.riskanalysis.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.riskanalysis.entity.Output;

@Repository
public interface OutputRepo extends CrudRepository<Output, Integer> {
    @Query(nativeQuery = true, value = "select id, company_name, composite_risk_score, info_sec_weight, resilince_cal_weight, conduct_cal_weight, total_risk_score, timestamp from (SELECT *,  ROW_NUMBER() over (PARTITION BY company_name ORDER BY timestamp desc) as `rows` FROM output_table) a where a.rows=1")
    public List<Output> getLatestData();
}
