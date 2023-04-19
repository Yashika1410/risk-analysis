package com.example.riskanalysis.service;

import com.example.riskanalysis.entity.RiskScoreLevel;
import com.example.riskanalysis.repository.RiskScoreLevelRepo;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
/**
 * service class which is used by controller to perform operations.
 */

@Service
public class RiskScoreLevelService {
  @Autowired
    private RiskScoreLevelRepo riskScoreLevelRepo;

  /**
   * get risk score level using id.

   * @param id unique id.
   * @return RiskScoreLevel
   * @throws ResponseStatusException throws exception if risk score level not found
   *                                 by id.
   */
  public RiskScoreLevel getRiskScoreLevel(int id) {
    return riskScoreLevelRepo.findById(id).orElseThrow(
      () -> new ResponseStatusException(
       HttpStatus.NOT_FOUND, "Risk Score Level not found by this id " + id));
  }

  /**
     * return list of risk score level objects.

     *  @return List(RiskScoreLevel)
     */
  public List<RiskScoreLevel> getAllRiskScoreLevels() {
    return (List<RiskScoreLevel>) riskScoreLevelRepo.findAll();
  }
  
  /**
   * save data using valid risk score level object.

   * @param riskScoreLevel valid risk score level object.
   * @return risk score level object.
   * @throws ResponseStatusException throws exception if level already exists.
   */
  public RiskScoreLevel addRiskScoreLevel(RiskScoreLevel riskScoreLevel) {
    if (riskScoreLevelRepo.existsByLevel(riskScoreLevel.getLevel().toLowerCase())) {
      throw new ResponseStatusException(HttpStatus.CONFLICT,
          "Risk Score Level already exists by " + (" this level " + riskScoreLevel.getLevel()));
    }
    return riskScoreLevelRepo.save(riskScoreLevel);
  }

  /**
   * update risk score level using id and valid data .

   * @param riskScoreLevel valid data.
   * @return RiskScoreLevel
   * @throws ResponseStatusException throws exception according to the scnerio.
  
   */
  public RiskScoreLevel updateRiskScoreLevel(int id, RiskScoreLevel riskScoreLevel) {
    RiskScoreLevel existingRiskScoreLevel = riskScoreLevelRepo.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Risk Score Level not found by this id " + id));
    if (existingRiskScoreLevel.getLevel() != riskScoreLevel.getLevel().toLowerCase()) {
      if (riskScoreLevelRepo.existsByLevel(riskScoreLevel.getLevel().toLowerCase())) {
        throw new ResponseStatusException(HttpStatus.CONFLICT,
                        "Level already exists with different Risk Score Level");
      }
    }
    existingRiskScoreLevel.setLevel(riskScoreLevel.getLevel());
    existingRiskScoreLevel.setScore(riskScoreLevel.getScore());
    riskScoreLevelRepo.save(existingRiskScoreLevel);
    return existingRiskScoreLevel;
  }

  /**
   * delete risk score level using id.

   * @param id unique id.

   * @return String
   * @throws ResponseStatusException throws exception if risk score level not found
   *                                 by id.
   */
  public String deleteRiskScoreLevel(int id) {
    RiskScoreLevel riskScoreLevel = riskScoreLevelRepo.findById(id).orElseThrow(
        () -> new ResponseStatusException(
        HttpStatus.NOT_FOUND, "Risk Score Level not found by this id " + id));
    riskScoreLevelRepo.delete(riskScoreLevel);
    return "Successfully deleted risk score level by this id " + id;
  }

}
