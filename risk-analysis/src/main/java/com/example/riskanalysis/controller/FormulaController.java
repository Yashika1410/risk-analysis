package com.example.riskanalysis.controller;

import com.example.riskanalysis.entity.Formula;
import com.example.riskanalysis.service.FormulaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
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

/**
 * creating FormulaController class to perform crud operations.
 */
@RestController
@RequestMapping("/api/v1/formulas")
public class FormulaController {
  /**
   * log variable which used for logging.
   */
  private final Logger log = LoggerFactory.getLogger(FormulaController.class);
  /**
   * autowired formula service class.
   */
  @Autowired
    private FormulaService formulaService;

  /**
     * getting formula using id.

     * @param id
     *
     * @return Formula
     */
  @GetMapping("/{id}")
  @Operation(summary = "Get formula by id",
  security = @SecurityRequirement(name = "bearerAuth"))
    public Formula getFormula(@PathVariable final int id) {
    try {
      return formulaService.getFormula(id);
    } catch (ResponseStatusException re) {
      log.error(re.getMessage());
      throw new ResponseStatusException(re.getStatus(), re.getMessage());
    } catch (Exception e) {
      throw new ResponseStatusException(
        HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
    }

  }

  /**
     * getting list of all formulas.

     * @return List(Formula)
     */
  @GetMapping("")
  @Operation(summary = "Get List of Fromulas",
  security = @SecurityRequirement(name = "bearerAuth"))
    public List<Formula> getListofFormulas() {
    try {
      return formulaService.getAllFormulas();
    } catch (Exception e) {
      log.error(e.getMessage());
      throw new ResponseStatusException(
        HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
    }
  }

  /**
     * create formula using formulaService.

     * @param formula
     *
     * @return Formula
     */
  @PostMapping("")
  @Operation(summary = "Create new Formula",
  security = @SecurityRequirement(name = "bearerAuth"))
    public Formula createFormula(@Valid @RequestBody final Formula formula) {
    try {
      return formulaService.addFormula(formula);
    } catch (ResponseStatusException re) {
      log.error(re.getMessage());
      throw new ResponseStatusException(re.getStatus(), re.getMessage());
    } catch (Exception e) {
      log.error(e.getMessage());
      throw new ResponseStatusException(
        HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
    }
  }

  /**
     * updating formula using id.

     * @param id
     *
     * @param formula
     *
     * @return Formula
     */
  @PatchMapping("/{id}")
  @Operation(summary = "Update Formula by id",
  security = @SecurityRequirement(name = "bearerAuth"))
    public Formula patchFormula(@PathVariable final int id,
      @RequestBody final Formula formula) {
    try {
      return formulaService.updateFormula(id, formula);
    } catch (ResponseStatusException re) {
      log.error(re.getMessage());
      throw new ResponseStatusException(re.getStatus(), re.getMessage());
    } catch (Exception e) {
      log.error(e.getMessage());
      throw new ResponseStatusException(
        HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
    }
  }

  /**
     * delete formula using id.

     * @param id
     *
     * @return String
     */
  @DeleteMapping("/{id}")
  @Operation(summary = "Delete Formula by id",
  security = @SecurityRequirement(name = "bearerAuth"))
    public String deleteFormula(@PathVariable final int id) {
    try {
      return formulaService.deleteFormula(id);
    } catch (ResponseStatusException re) {
      log.error(re.getMessage());
      throw new ResponseStatusException(re.getStatus(), re.getMessage());
    } catch (Exception e) {
      log.error(e.getMessage());
      throw new ResponseStatusException(
        HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
    }
  }
}
