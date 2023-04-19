package com.example.riskanalysis.repository;

import com.example.riskanalysis.entity.Output;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * interface which is used to perform crud operations on Output Table.
 */
@Repository
public interface OutputRepo extends CrudRepository<Output, Integer> {
  /**
   * return latest data from output table.

   * @return list of output.
   */
  @Query(nativeQuery = true,
      value = ("select id, company_name, composite_risk_score,"
      + " info_sec_weight, resilince_cal_weight,"
      + " conduct_cal_weight, total_risk_score, timestamp"
      + " from (SELECT *,  ROW_NUMBER()"
      + " over (PARTITION BY company_name ORDER BY timestamp desc)"
      + " as `rows` FROM output_table) a where a.rows=1"))
  List<Output> getLatestData();
}
