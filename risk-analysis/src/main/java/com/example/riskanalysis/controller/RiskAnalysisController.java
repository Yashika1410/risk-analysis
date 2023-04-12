package com.example.riskanalysis.controller;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.example.riskanalysis.entity.Output;
import com.example.riskanalysis.service.RiskAnalysisService;

@RestController
@RequestMapping("/api/v1/risk-analysis")
public class RiskAnalysisController {
    final static Logger log = LoggerFactory.getLogger(RiskAnalysisController.class);

    @Autowired
    private RiskAnalysisService riskAnalysisService;

    private final ExecutorService executor = Executors.newSingleThreadExecutor();

    /**
     * @return List<Output>
     */
    @GetMapping("/get-data")
    public List<Output> getData() {
        try {
            return riskAnalysisService.getOutputList();
        } catch (Exception e) {
            // TODO: handle exception
            log.error(e.getMessage());
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());

        }

    }

    @PostMapping("/start-process")
    public ResponseEntity<?> startProcess() {
        try {
            executor.execute(() -> {
                riskAnalysisService.saveAnalysiedData("API");
            });
            return ResponseEntity.accepted().body("Started Process");
        } catch (Exception e) {
            // TODO: handle exception
            log.error(e.getMessage());
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }

    }

}
