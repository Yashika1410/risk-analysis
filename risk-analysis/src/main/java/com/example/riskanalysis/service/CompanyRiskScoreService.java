package com.example.riskanalysis.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.riskanalysis.entity.CompanyRiskScore;
import com.example.riskanalysis.repository.CompanyRiskScoreRepo;

@Service
public class CompanyRiskScoreService {
    @Autowired
    private CompanyRiskScoreRepo companyRiskScoreRepo;

    /**
     * @param id
     * @return CompanyRiskScore
     */
    public CompanyRiskScore getCompanyRiskScore(final int id) {
        return companyRiskScoreRepo.findById(id).orElseThrow(
                () -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Company Risk Score Not Found by this id " + id));
    }

    /**
     * @param companyRiskScore
     * @return CompanyRiskScore
     */
    public CompanyRiskScore addCompanyRiskScore(CompanyRiskScore companyRiskScore) {
        return companyRiskScoreRepo.save(companyRiskScore);
    }

    /**
     * @return List<CompanyRiskScore>
     */
    public List<CompanyRiskScore> getAllCompanyRiskScores() {

        return (List<CompanyRiskScore>) companyRiskScoreRepo.findAll();
    }

    /**
     * @param id
     * @param companyRiskScore
     * @return CompanyRiskScore
     */
    public CompanyRiskScore updateCompanyRiskScore(final int id, CompanyRiskScore companyRiskScore) {
        CompanyRiskScore existingCompanyRiskScore = companyRiskScoreRepo.findById(id).orElseThrow(
                () -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Company Risk Score Not Found by this id " + id));
        existingCompanyRiskScore.setInformationSecurity(companyRiskScore.getInformationSecurity());
        existingCompanyRiskScore.setConduct(companyRiskScore.getConduct());
        existingCompanyRiskScore.setResilience(companyRiskScore.getResilience());
        ;
        companyRiskScoreRepo.save(existingCompanyRiskScore);
        return existingCompanyRiskScore;
    }

    /**
     * @param id
     * @return String message
     */
    public String deleteCompanyRiskScore(final int id) {
        CompanyRiskScore companyRiskScore = companyRiskScoreRepo.findById(id).orElseThrow(
                () -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Company Risk Score Not Found by this id " + id));
        companyRiskScoreRepo.delete(companyRiskScore);
        return "Successfully deleted Company Risk Score by this id " + id;
    }
}
