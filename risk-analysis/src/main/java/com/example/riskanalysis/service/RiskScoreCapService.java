package com.example.riskanalysis.service;

import com.example.riskanalysis.entity.RiskScoreCap;
import com.example.riskanalysis.repository.RiskScoreCapRepo;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

/**
 * service class which help controllers to perform operations.
 */
@Service
public class RiskScoreCapService {
  /**
   * autowired risk score cap repo interface.
   */
  @Autowired
    private RiskScoreCapRepo riskScoreCapRepo;

  /**
   * get risk score cap using id.

     * @param id updating id.
     *
     * @return risk score cap object.
     */
  public RiskScoreCap getRiskScoreCap(final int id) {
    return riskScoreCapRepo.findById(id).orElseThrow(
         () -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                ("Risk Score Cap is not found by this id " + id)));
  }

  /**
   * add risk score cap.

   * @param riskScoreCap valid risk score cap data.
   *
   * @return risk score cap entity class object.
   */
  public RiskScoreCap addRiskScoreCap(final RiskScoreCap riskScoreCap) {
    return riskScoreCapRepo.save(riskScoreCap);
  }

  /**
   * return list of risk score cap.

     * @return list of risk score cap objects.
     */
  public List<RiskScoreCap> getAllRiskScoreCaps() {
    return (List<RiskScoreCap>) riskScoreCapRepo.findAll();
  }

  /**
   * update risk score cap using id and updated data.

   *@param id unique risk score cap id
     * @param riskScoreCap updated data .
     * @return updated risk score cap object.
     * @throws ResponseStatusException throws exception if
     *     risk score cap not found by id.
     */
  public RiskScoreCap updateRiskScoreCap(final int id,
      final RiskScoreCap riskScoreCap) {
    RiskScoreCap exsistingRiskScoreCap = riskScoreCapRepo.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                  HttpStatus.NOT_FOUND,
                        ("Risk Score Cap is not found by this id " + id)));
    exsistingRiskScoreCap.setCappedScore(riskScoreCap.getCappedScore());
    exsistingRiskScoreCap.setConditionCnt(riskScoreCap.getConditionCnt());
    riskScoreCapRepo.save(exsistingRiskScoreCap);
    return exsistingRiskScoreCap;
  }

  /**
   * delete risk score cap usind id.

   * @param id unique id.
   * @return string mesaage.
   * @throws ResponseStatusException throws
   *     exception if risk score cap not found by id.
   */
  public String deleteRiskScoreCap(final int id) {
    RiskScoreCap riskScoreCap = riskScoreCapRepo.findById(id)
                .orElseThrow(
                  () -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        ("Risk Score Cap is not found by this id " + id)));
    riskScoreCapRepo.delete(riskScoreCap);
    return "Successfully deleted Risk Score Cap using id " + id;
  }

}
