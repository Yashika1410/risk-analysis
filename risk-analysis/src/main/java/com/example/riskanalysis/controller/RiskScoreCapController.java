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

import com.example.riskanalysis.entity.RiskScoreCap;
import com.example.riskanalysis.service.RiskScoreCapService;

@RestController
@RequestMapping("/api/v1/risk-score-caps")
public class RiskScoreCapController {
    final static Logger log = LoggerFactory.getLogger(RiskScoreCapController.class);
    @Autowired
    private RiskScoreCapService riskScoreCapService;

    /**
     * @param id
     * @return RiskScoreCap
     */
    @GetMapping("/{id}")
    public RiskScoreCap getRiskScoreCap(@PathVariable int id) {
        try {
            return riskScoreCapService.getRiskScoreCap(id);
        } catch (ResponseStatusException re) {
            log.error(re.getMessage());
            throw new ResponseStatusException(re.getStatus(), re.getMessage());
        } catch (Exception e) {
            // TODO: handle exception
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }

    }

    /**
     * @return List<RiskScoreCap>
     */
    @GetMapping("")
    public List<RiskScoreCap> getListofRiskScoreCaps() {
        try {
            return riskScoreCapService.getAllRiskScoreCaps();
        } catch (Exception e) {
            // TODO: handle exception
            log.error(e.getMessage());
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    /**
     * @param riskScoreCap
     * @return RiskScoreCap
     */
    @PostMapping("")
    public RiskScoreCap createRiskScoreCap(@Valid @RequestBody RiskScoreCap riskScoreCap) {
        try {
            return riskScoreCapService.addRiskScoreCap(riskScoreCap);
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
     * @param riskScoreCap
     * @return RiskScoreCap
     */
    @PatchMapping("/{id}")
    public RiskScoreCap patchRiskScoreCap(@PathVariable int id,@Valid @RequestBody RiskScoreCap riskScoreCap) {
        try {
            return riskScoreCapService.updateRiskScoreCap(id, riskScoreCap);
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
    public String deleteRiskScoreCap(@PathVariable int id) {
        try {
            return riskScoreCapService.deleteRiskScoreCap(id);
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
