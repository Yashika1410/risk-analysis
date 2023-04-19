package com.example.riskanalysis.controller;

import com.example.riskanalysis.entity.RiskScoreCap;
import com.example.riskanalysis.entity.RiskScoreLevel;
import com.example.riskanalysis.requestobject.RiskScoreCapModel;
import com.example.riskanalysis.service.RiskScoreCapService;
import com.example.riskanalysis.service.RiskScoreLevelService;
import java.util.List;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

/**
 * creating RiskScoreCapController class to perform crud operations.
 */
@RestController
@RequestMapping("/api/v1/risk-score-caps")
public class RiskScoreCapController {
  /**
   * log variable which used for logging.
   */
  private final Logger log = LoggerFactory.getLogger(
      RiskScoreCapController.class);
  /**
   * autowired risk score  cap service.
   */
  @Autowired
    private RiskScoreCapService riskScoreCapService;
  /**
   * autowired risk level cap service.
   */
  @Autowired
    private RiskScoreLevelService riskScoreLevelService;
  /**
 * getting risk score cap using id.

     * @param id
     *
     * @return RiskScoreCap
     */
  @GetMapping("/{id}")
    public RiskScoreCap getRiskScoreCap(@PathVariable final int id) {
    try {
      return riskScoreCapService.getRiskScoreCap(id);
    } catch (ResponseStatusException re) {
      log.error(re.getMessage());
      throw new ResponseStatusException(re.getStatus(), re.getMessage());
    } catch (Exception e) {
      throw new ResponseStatusException(
        HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
    }

  }

  /**
     * getting list of risk score caps.

     * @return List(RiskScoreCap)
     */
  @GetMapping("")
    public List<RiskScoreCap> getListofRiskScoreCaps() {
    try {
      return riskScoreCapService.getAllRiskScoreCaps();
    } catch (Exception e) {
      log.error(e.getMessage());
      throw new ResponseStatusException(
        HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
    }
  }

  /**
   * creating new risk score cap data.

   * @param riskScoreCapModel
   *
   * @return risk score cap object
   *
   */
  @PostMapping("")
    public RiskScoreCap createRiskScoreCap(
        @Valid @RequestBody final RiskScoreCapModel riskScoreCapModel) {
    try {
      RiskScoreLevel riskScoreLevel = riskScoreLevelService
                    .getRiskScoreLevel(riskScoreCapModel.getRiskScoreLevelId());
      RiskScoreCap riskScoreCap = new RiskScoreCap();
      riskScoreCap.setCappedScore(riskScoreCapModel.getCappedScore());
      riskScoreCap.setConditionCnt(riskScoreCapModel.getConditionCnt());
      riskScoreCap.setRiskScoreLevel(riskScoreLevel);
      return riskScoreCapService.addRiskScoreCap(riskScoreCap);
    } catch (ResponseStatusException re) {
      log.error(re.getMessage());
      throw new ResponseStatusException(re.getStatus(), re.getMessage());
    } catch (Exception e) {
      log.error(e.getMessage());
      throw new ResponseStatusException(
        HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
    }
  }
  /**
  * updating risk score cap using id.

  * @param id unique id.

  * @param riskScoreCap updated data.

  * @return risk score cap object.
  */
  @PatchMapping("/{id}")
  public RiskScoreCap patchRiskScoreCap(
      @PathVariable final int id,
      @Valid @RequestBody final RiskScoreCap riskScoreCap) {
    try {
      return riskScoreCapService.updateRiskScoreCap(id, riskScoreCap);
    } catch (ResponseStatusException re) {
      log.error(re.getMessage());
      throw new ResponseStatusException(re.getStatus(), re.getMessage());
    } catch (Exception e) {
      log.error(e.getMessage());
      throw new ResponseStatusException(
        HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
    }
  }

  /**
     * delete risk score cap using id.

     * @param id
     *
     * @return String
     */
  @DeleteMapping("/{id}")
    public String deleteRiskScoreCap(@PathVariable final int id) {
    try {
      return riskScoreCapService.deleteRiskScoreCap(id);
    } catch (ResponseStatusException re) {
      log.error(re.getMessage());
      throw new ResponseStatusException(re.getStatus(), re.getMessage());
    } catch (Exception e) {
      log.error(e.getMessage());
      throw new ResponseStatusException(
        HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
    }
  }
}
