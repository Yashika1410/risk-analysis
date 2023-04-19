package com.example.riskanalysis.service;

import com.example.riskanalysis.entity.CompanyRiskScore;
import com.example.riskanalysis.repository.CompanyRiskScoreRepo;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

/**
 * service class which is used to help controller class to perform operations.
 */
@Service
public class CompanyRiskScoreService {
  /**
   * autowired company risk score repo interface.
   */
  @Autowired
    private CompanyRiskScoreRepo companyRiskScoreRepo;

  /**
   * get company data using id.

     * @param id
     *
     * @return CompanyRiskScore
     */
  public CompanyRiskScore getCompanyRiskScore(final int id) {
    return companyRiskScoreRepo.findById(id).orElseThrow(
                () -> new ResponseStatusException(
        HttpStatus.NOT_FOUND, "Company Risk Score Not Found by this id " + id));
  }

  /**
     * save valid company data .

     * @param companyRiskScore
     *
     * @return CompanyRiskScore
     */
  public CompanyRiskScore addCompanyRiskScore(
      @Valid final CompanyRiskScore companyRiskScore) {
    return companyRiskScoreRepo.save(companyRiskScore);
  }

  /**
     * get list of company data.

     * @return List(CompanyRiskScore)
     */
  public List<CompanyRiskScore> getAllCompanyRiskScores() {

    return (List<CompanyRiskScore>) companyRiskScoreRepo.findAll();
  }

  /**
      * update company data using id and valid updated data.

     * @param id unique id.

     * @param companyRiskScore updated data.
     * @return CompanyRiskScore
     */
  public CompanyRiskScore updateCompanyRiskScore(final int id,
      final CompanyRiskScore companyRiskScore) {
    CompanyRiskScore existingCompanyRiskScore = companyRiskScoreRepo.findById(
        id).orElseThrow(
                () -> new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Company Risk Score Not Found by this id " + id));
    existingCompanyRiskScore.setDimensionScores(
        companyRiskScore.getDimensionScores());
    companyRiskScoreRepo.save(existingCompanyRiskScore);
    return existingCompanyRiskScore;
  }

  /**
     * delete comapny data using id and return successful message.

     * @param id unique id.
     * @return String message
     */
  public String deleteCompanyRiskScore(final int id) {
    CompanyRiskScore companyRiskScore =
        companyRiskScoreRepo.findById(id).orElseThrow(
                () -> new ResponseStatusException(
            HttpStatus.NOT_FOUND,
            "Company Risk Score Not Found by this id " + id));
    companyRiskScoreRepo.delete(companyRiskScore);
    return "Successfully deleted Company Risk Score by this id " + id;
  }
}
