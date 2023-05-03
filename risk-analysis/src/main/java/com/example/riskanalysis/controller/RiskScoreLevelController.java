package com.example.riskanalysis.controller;

import com.example.riskanalysis.entity.RiskScoreLevel;
import com.example.riskanalysis.service.RiskScoreLevelService;
import java.util.List;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
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
 * RiskScoreLevelController class to perform crud operations.
 */
@RestController
@RequestMapping("/api/v1/risk-score-levels")
public class RiskScoreLevelController {

  /**
   * log variable which used for logging.
   */
  private final Logger log = LoggerFactory.getLogger(
      RiskScoreLevelController.class);

  /**
   * autowired risk score level service.
   */
  @Autowired
    private RiskScoreLevelService riskScoreLevelService;

  /**
 * get risk score level using id.

     * @param id
     *
     * @return RiskScoreLevel
     */
  @GetMapping("/{id}")
  @Operation(summary = "Get Risk Score Level by id", security = @SecurityRequirement(name = "bearerAuth"))
    public RiskScoreLevel getRiskScoreLevel(@PathVariable final int id) {
    try {
      return riskScoreLevelService.getRiskScoreLevel(id);
    } catch (ResponseStatusException re) {
      log.error(re.getMessage());
      throw new ResponseStatusException(re.getStatus(), re.getMessage());
    } catch (Exception e) {
      throw new ResponseStatusException(
        HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
    }

  }

  /**
     * getting list of risk score levels.

     * @return List(RiskScoreLevel)
     */
  @GetMapping("")
  @Operation(summary = "Get List of Risk Score Levels", security = @SecurityRequirement(name = "bearerAuth"))
    public List<RiskScoreLevel> getListofRiskScoreLevels() {
    try {
      return riskScoreLevelService.getAllRiskScoreLevels();
    } catch (Exception e) {
      log.error(e.getMessage());
      throw new ResponseStatusException(
        HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
    }
  }

  /**
     * creating new risk score level.

     * @param riskScoreLevel
     *
     * @return RiskScoreLevel
     */
  @PostMapping("")
  @Operation(summary = "Create new Risk Score Level", security = @SecurityRequirement(name = "bearerAuth"))
    public RiskScoreLevel createRiskScoreLevel(
      @Valid @RequestBody final RiskScoreLevel riskScoreLevel) {
    try {
      return riskScoreLevelService.addRiskScoreLevel(riskScoreLevel);
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
     * updating risk score level using id.

     * @param id
     *
     * @param riskScoreLevel
     *
     * @return RiskScoreLevel
     */
  @PatchMapping("/{id}")
  @Operation(summary = "Update Risk Score Level by id", security = @SecurityRequirement(name = "bearerAuth"))
    public RiskScoreLevel patchRiskScoreLevel(
        @PathVariable final int id,
        @Valid @RequestBody final RiskScoreLevel riskScoreLevel) {
    try {
      return riskScoreLevelService.updateRiskScoreLevel(id, riskScoreLevel);
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
     * delete risk score level using id.

     * @param id
     *
     * @return String
     */
  @DeleteMapping("/{id}")
  @Operation(summary = "Delete Risk Score Level by id", security = @SecurityRequirement(name = "bearerAuth"))
    public String deleteRiskScoreLevel(@PathVariable final int id) {
    try {
      return riskScoreLevelService.deleteRiskScoreLevel(id);
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
