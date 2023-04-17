package com.example.riskanalysis.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.riskanalysis.entity.RiskScoreLevel;
import com.example.riskanalysis.repository.RiskScoreLevelRepo;

@Service
public class RiskScoreLevelService {
    @Autowired
    private RiskScoreLevelRepo riskScoreLevelRepo;

    /**
     * @param id
     * @return RiskScoreLevel
     */
    public RiskScoreLevel getRiskScoreLevel(final int id) {
        return riskScoreLevelRepo.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Risk Score Level not found by this id " + id));
    }

    /**
     * @return List<RiskScoreLevel>
     */
    public List<RiskScoreLevel> getAllRiskScoreLevels() {
        return (List<RiskScoreLevel>) riskScoreLevelRepo.findAll();
    }

    public RiskScoreLevel addRiskScoreLevel(RiskScoreLevel riskScoreLevel) {
        if (riskScoreLevelRepo.existsByLevel(riskScoreLevel.getLevel().toLowerCase())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT,
                    "Risk Score Level already exists by " + (" this level " + riskScoreLevel.getLevel()));
        }
        return riskScoreLevelRepo.save(riskScoreLevel);

    }

    /**
     * @param riskScoreLevel
     * @return RiskScoreLevel
     */
    public RiskScoreLevel updateRiskScoreLevel(final int id, RiskScoreLevel riskScoreLevel) {
        RiskScoreLevel existingRiskScoreLevel = riskScoreLevelRepo.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Risk Score Level not found by this id " + id));
        if (existingRiskScoreLevel.getLevel() != riskScoreLevel.getLevel().toLowerCase()) {
            if (riskScoreLevelRepo.existsByLevel(riskScoreLevel.getLevel().toLowerCase()))
                throw new ResponseStatusException(HttpStatus.CONFLICT,
                        "Level already exists with different Risk Score Level");
        }
        existingRiskScoreLevel.setLevel(riskScoreLevel.getLevel());
        existingRiskScoreLevel.setScore(riskScoreLevel.getScore());
        riskScoreLevelRepo.save(existingRiskScoreLevel);
        return existingRiskScoreLevel;
    }

    /**
     * @param id
     * @return String
     */
    public String deleteRiskScoreLevel(final int id) {
        RiskScoreLevel riskScoreLevel = riskScoreLevelRepo.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Risk Score Level not found by this id " + id));
        riskScoreLevelRepo.delete(riskScoreLevel);
        return "Successfully deleted risk score level by this id " + id;
    }

}
