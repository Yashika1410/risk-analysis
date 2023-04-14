package com.example.riskanalysis.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.riskanalysis.entity.RiskScoreCap;
import com.example.riskanalysis.repository.RiskScoreCapRepo;

@Service
public class RiskScoreCapService {
    @Autowired
    private RiskScoreCapRepo riskScoreCapRepo;

    /**
     * @param id
     * @return
     */
    public RiskScoreCap getRiskScoreCap(int id) {
        return riskScoreCapRepo.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                ("Risk Score Cap is not found by this id " + id)));
    }

    /**
     * @param riskScoreCap
     * @return
     */
    public RiskScoreCap addRiskScoreCap(RiskScoreCap riskScoreCap) {
        return riskScoreCapRepo.save(riskScoreCap);
    }

    /**
     * @return
     */
    public List<RiskScoreCap> getAllRiskScoreCaps() {
        return (List<RiskScoreCap>) riskScoreCapRepo.findAll();
    }

    /**
     * @param riskScoreCap
     * @return
     */
    public RiskScoreCap updateRiskScoreCap(int id, RiskScoreCap riskScoreCap) {
        RiskScoreCap exsistingRiskScoreCap = riskScoreCapRepo.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        ("Risk Score Cap is not found by this id " + id)));
        exsistingRiskScoreCap.setCappedScore(riskScoreCap.getCappedScore());
        exsistingRiskScoreCap.setConditionCnt(riskScoreCap.getConditionCnt());
        riskScoreCapRepo.save(exsistingRiskScoreCap);
        return exsistingRiskScoreCap;
    }

    /**
     * @param id
     * @return
     */
    public String deleteRiskScoreCap(int id) {
        RiskScoreCap riskScoreCap = riskScoreCapRepo.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        ("Risk Score Cap is not found by this id " + id)));
        riskScoreCapRepo.delete(riskScoreCap);
        return "Successfully deleted Risk Score Cap using id " + id;
    }

}
