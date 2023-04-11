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

import com.example.riskanalysis.entity.RiskScoreLevel;
import com.example.riskanalysis.service.RiskScoreLevelService;

@RestController
@RequestMapping("/api/v1/risk-score-levels")
public class RiskScoreLevelController {

    final static Logger log = LoggerFactory.getLogger(RiskScoreLevelController.class);

    @Autowired
    private RiskScoreLevelService riskScoreLevelService;

    /**
     * @param id
     * @return RiskScoreLevel
     */
    @GetMapping("/{id}")
    public RiskScoreLevel getRiskScoreLevel(@PathVariable int id) {
        try {
            return riskScoreLevelService.getRiskScoreLevel(id);
        } catch (ResponseStatusException re) {
            log.error(re.getMessage());
            throw new ResponseStatusException(re.getStatus(), re.getMessage());
        } catch (Exception e) {
            // TODO: handle exception
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }

    }

    /**
     * @return List<RiskScoreLevel>
     */
    @GetMapping("")
    public List<RiskScoreLevel> getListofRiskScoreLevels() {
        try {
            return riskScoreLevelService.getAllRiskScoreLevels();
        } catch (Exception e) {
            // TODO: handle exception
            log.error(e.getMessage());
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    /**
     * @param riskScoreLevel
     * @return RiskScoreLevel
     */
    @PostMapping("")
    public RiskScoreLevel createRiskScoreLevel(@Valid @RequestBody RiskScoreLevel riskScoreLevel) {
        try {
            return riskScoreLevelService.addRiskScoreLevel(riskScoreLevel);
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
     * @param riskScoreLevel
     * @return RiskScoreLevel
     */
    @PatchMapping("/{id}")
    public RiskScoreLevel patchRiskScoreLevel(@PathVariable int id, @Valid @RequestBody RiskScoreLevel riskScoreLevel) {
        try {
            return riskScoreLevelService.updateRiskScoreLevel(id, riskScoreLevel);
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
     */
    @DeleteMapping("/{id}")
    public String deleteRiskScoreLevel(@PathVariable int id) {
        try {
            return riskScoreLevelService.deleteRiskScoreLevel(id);
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
