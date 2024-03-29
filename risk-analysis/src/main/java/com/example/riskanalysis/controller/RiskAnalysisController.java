package com.example.riskanalysis.controller;

import com.example.riskanalysis.entity.Output;
import com.example.riskanalysis.service.RiskAnalysisService;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
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

/**
 * creating RiskAnalysisController class to perform crud operations.
 */
@RestController
@RequestMapping("/api/v1/risk-analysis")
public class RiskAnalysisController {
  /**
   * log variable which used for logging.
   */
  private final Logger log = LoggerFactory.getLogger(
      RiskAnalysisController.class);

  /**
   * autowired risk analysis service class.
   */
  @Autowired
    private RiskAnalysisService riskAnalysisService;

  /**
   * create a executor service object for processing data using thread.
   */
  private final ExecutorService executor = Executors.newSingleThreadExecutor();

  /**
   * get data from riskAnalysisService.

   * @return List(Output)
   */
  @GetMapping("/get-data")
  @Operation(summary = "Get latest output Data", security = @SecurityRequirement(name = "bearerAuth"))
    public List<Output> getData() {
    try {
      return riskAnalysisService.getOutputList();
    } catch (Exception e) {
      log.error(e.getMessage());
      throw new ResponseStatusException(
        HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());

    }

  }
  /**
 * starting the process by triggering the API.

 * @return ResponseEntity object
 *
 */
  @PostMapping("/start-process")
  
  @Operation(summary = "Start Process", security = @SecurityRequirement(name = "bearerAuth"))
    public ResponseEntity<?> startProcess() {
    try {
      executor.execute(() -> {
        riskAnalysisService.saveAnalysiedData("API");
      });
      return ResponseEntity.accepted().body("Started Process");
    } catch (Exception e) {
      log.error(e.getMessage());
      throw new ResponseStatusException(
        HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
    }

  }

}
