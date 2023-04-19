package com.example.riskanalysis.controller;

import com.example.riskanalysis.entity.AnalysisJobTrasaction;
import com.example.riskanalysis.repository.AnalysisJobTrasactionRepo;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

/**
 * AnalysisJobTrasactionController is a controller class which is used to
 * perform crud operation on AnalysisJobTrasaction table .
 */

@RestController
@RequestMapping("/api/v1/trasactions")
public class AnalysisJobTrasactionController {
  @Autowired
    AnalysisJobTrasactionRepo analysisJobTrasactionRepo;
  private final Logger log = LoggerFactory.getLogger(
        AnalysisJobTrasactionController.class);

  /**
    * when get transactions got hit it will return a list as response.

    * @return list of analysis job transaction objects. 
    */
  @GetMapping("")
    public List<AnalysisJobTrasaction> getData() {
    try {
      return (List<AnalysisJobTrasaction>) analysisJobTrasactionRepo.findAll();
    } catch (Exception e) {
      log.error(e.getMessage());
      throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());

    }

  }
    
}
