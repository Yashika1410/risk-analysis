package com.example.riskanalysis.controller;

import com.example.riskanalysis.entity.CompanyRiskScore;
import com.example.riskanalysis.service.CompanyRiskScoreService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
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
 * controller which is used to handle request based on company risk score.
 */
@RestController
@RequestMapping("/api/v1/company-risk-scores")
public class CompanyRiskScoreController {

  /**
   * log variable which used for logging.
   */
  private final Logger log = LoggerFactory.getLogger(
      CompanyRiskScoreController.class);
  /**
   * autowired company risk score service class.
   */
  @Autowired
    private CompanyRiskScoreService companyRiskScoreService;

  /**
     * get company by id.

     * @param id unique id to get data
     *
     * @return CompanyRiskScore
     */
  @GetMapping("/{id}")
  @Operation(summary = "Get Company Risk Score by id",
  security = @SecurityRequirement(name = "bearerAuth"))
    public CompanyRiskScore getCompanyRiskScore(@PathVariable final int id) {
    try {
      return companyRiskScoreService.getCompanyRiskScore(id);
    } catch (ResponseStatusException re) {
      log.error(re.getMessage());
      throw new ResponseStatusException(re.getStatus(), re.getMessage());
    } catch (Exception e) {
      throw new ResponseStatusException(
        HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
    }

  }

  /**
   * to get list of companies.

     * @return List of CompanyRiskScore objects.
     */
  @GetMapping("")
  @Operation(summary = "Get All Company Risk Scores",
  security = @SecurityRequirement(name = "bearerAuth"))
    public List<CompanyRiskScore> getListofCompanyRiskScores() {
    try {
      return companyRiskScoreService.getAllCompanyRiskScores();
    } catch (Exception e) {
      log.error(e.getMessage());
      throw new ResponseStatusException(
        HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
    }
  }

  /**
   * to create new company data.

     * @param companyRiskScore
     *
     * @return CompanyRiskScore
     */
  @PostMapping("")
  @Operation(summary = "Create New Company Risk Score",
  security = @SecurityRequirement(name = "bearerAuth"))
public CompanyRiskScore createCompanyRiskScore(
        @Valid @RequestBody final  CompanyRiskScore companyRiskScore) {
    try {
      return companyRiskScoreService.addCompanyRiskScore(companyRiskScore);
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
     * update company data by using id.

     * @param id
     *
     * @param companyRiskScore
     *
     * @return CompanyRiskScore
     */
  @PatchMapping("/{id}")
  @Operation(summary = "Update Company Risk Score by id",
  security = @SecurityRequirement(name = "bearerAuth"))
    public CompanyRiskScore patchCompanyRiskScore(@PathVariable final int id,
            @Valid @RequestBody final CompanyRiskScore companyRiskScore) {
    try {
      return companyRiskScoreService.updateCompanyRiskScore(id,
      companyRiskScore);
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
  * this function returns a string when company is successfully deleted from db.

  * @param id unique id which is used to get data.
  * @return String
  * @throws ResponseStatusException (
    {@link ResponseStatusException},{@link String})
  */
  @DeleteMapping("/{id}")
  @Operation(summary = "Delete Company Risk Score by id",
  security = @SecurityRequirement(name = "bearerAuth"))
    public String deleteCompanyRiskScore(@PathVariable final int id) {
    try {
      return companyRiskScoreService.deleteCompanyRiskScore(id);
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
