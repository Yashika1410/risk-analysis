package com.example.riskanalysis.controller;

import com.example.riskanalysis.entity.AnalysisJobTrasaction;
import com.example.riskanalysis.repository.AnalysisJobTrasactionRepo;
import com.example.riskanalysis.responsemodel.ListResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

/**
 * AnalysisJobTrasactionController is a controller class which is used to
 * perform crud operation on AnalysisJobTrasaction table .
 */

@RestController
@RequestMapping("/api/v1/trasactions")
public class AnalysisJobTrasactionController {
  /**
   * autowired repo class.
   */
  @Autowired
    private AnalysisJobTrasactionRepo analysisJobTrasactionRepo;
  /**
   * private log object for logging.
   */
  private final Logger log = LoggerFactory.getLogger(
        AnalysisJobTrasactionController.class);


  /**
   * when get transactions got hit it will return a list as response.

   * @param skip  number of objects to be skip.
   * @param limit size of the list.
   * @return list of analysis job transaction objects.
   */
  @GetMapping("")
  @Operation(summary = "Get List of Trasactions",
  security = @SecurityRequirement(name = "bearerAuth"))
    public ListResponse<AnalysisJobTrasaction> getData(
      @RequestParam(defaultValue = "0") final int skip,
      @RequestParam(defaultValue = "10") final int limit) {
    try {
      ListResponse<AnalysisJobTrasaction> listResponse =
          new ListResponse<AnalysisJobTrasaction>();
      listResponse.setList((List<AnalysisJobTrasaction>)
          analysisJobTrasactionRepo.findAll(skip, limit));
      listResponse.setTotalCount(analysisJobTrasactionRepo.count());
      return listResponse;
    } catch (Exception e) {
      log.error(e.getMessage());
      throw new ResponseStatusException(
        HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());

    }
  }
}
