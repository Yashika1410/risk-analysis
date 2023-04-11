package com.example.riskanalysis.controller;

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

import com.example.riskanalysis.entity.CompanyRiskScore;
import com.example.riskanalysis.service.CompanyRiskScoreService;

@RestController
@RequestMapping("/api/v1/company-risk-scores")
public class CompanyRiskScoreController {

    final static Logger log = LoggerFactory.getLogger(CompanyRiskScoreController.class);
    @Autowired
    private CompanyRiskScoreService companyRiskScoreService;

    /**
     * @param id
     * @return CompanyRiskScore
     */
    @GetMapping("/{id}")
    public CompanyRiskScore getCompanyRiskScore(@PathVariable int id) {
        try {
            return companyRiskScoreService.getCompanyRiskScore(id);
        } catch (ResponseStatusException re) {
            log.error(re.getMessage());
            throw new ResponseStatusException(re.getStatus(), re.getMessage());
        } catch (Exception e) {
            // TODO: handle exception
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }

    }

    /**
     * @return List<CompanyRiskScore>
     */
    @GetMapping("")
    public List<CompanyRiskScore> getListofCompanyRiskScores() {
        try {
            return companyRiskScoreService.getAllCompanyRiskScores();
        } catch (Exception e) {
            // TODO: handle exception
            log.error(e.getMessage());
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    /**
     * @param companyRiskScore
     * @return CompanyRiskScore
     */
    @PostMapping("")
    public CompanyRiskScore createCompanyRiskScore(@Valid @RequestBody CompanyRiskScore companyRiskScore) {
        try {
            return companyRiskScoreService.addCompanyRiskScore(companyRiskScore);
        } catch (ResponseStatusException re) {
            log.error(re.getMessage());
            throw new ResponseStatusException(re.getStatus(), re.getMessage());
        } catch (Exception e) {
            // TODO: handle exception
            log.error(e.getMessage());
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    /**
     * @param id
     * @param companyRiskScore
     * @return CompanyRiskScore
     */
    @PatchMapping("/{id}")
    public CompanyRiskScore patchCompanyRiskScore(@PathVariable int id,
            @Valid @RequestBody CompanyRiskScore companyRiskScore) {
        try {
            return companyRiskScoreService.updateCompanyRiskScore(id, companyRiskScore);
        } catch (ResponseStatusException re) {
            log.error(re.getMessage());
            throw new ResponseStatusException(re.getStatus(), re.getMessage());
        } catch (Exception e) {
            // TODO: handle exception
            log.error(e.getMessage());
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    /**
     * @param id
     * @return String
     * @throws ResponseStatusException({@link ResponseStatusException},{@link String})
     */
    @DeleteMapping("/{id}")
    public String deleteCompanyRiskScore(@PathVariable int id) {
        try {
            return companyRiskScoreService.deleteCompanyRiskScore(id);
        } catch (ResponseStatusException re) {
            log.error(re.getMessage());
            throw new ResponseStatusException(re.getStatus(), re.getMessage());
        } catch (Exception e) {
            // TODO: handle exception
            log.error(e.getMessage());
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

}
