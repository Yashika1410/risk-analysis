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

import com.example.riskanalysis.entity.Formula;
import com.example.riskanalysis.service.FormulaService;

@RestController
@RequestMapping("/api/v1/formulas")
public class FormulaController {
    final static Logger log = LoggerFactory.getLogger(FormulaController.class);
    @Autowired
    private FormulaService formulaService;

    /**
     * @param id
     * @return Formula
     */
    @GetMapping("/{id}")
    public Formula getFormula(@PathVariable int id) {
        try {
            return formulaService.getFormula(id);
        } catch (ResponseStatusException re) {
            log.error(re.getMessage());
            throw new ResponseStatusException(re.getStatus(), re.getMessage());
        } catch (Exception e) {
            // TODO: handle exception
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }

    }

    /**
     * @return List<Formula>
     */
    @GetMapping("")
    public List<Formula> getListofFormulas() {
        try {
            return formulaService.getAllFormulas();
        } catch (Exception e) {
            // TODO: handle exception
            log.error(e.getMessage());
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    /**
     * @param formula
     * @return Formula
     */
    @PostMapping("")
    public Formula createFormula(@Valid @RequestBody Formula formula) {
        try {
            return formulaService.addFormula(formula);
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
     * @param formula
     * @return Formula
     */
    @PatchMapping("/{id}")
    public Formula patchFormula(@PathVariable int id, @RequestBody Formula formula) {
        try {
            return formulaService.updateFormula(id, formula);
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
    public String deleteFormula(@PathVariable int id) {
        try {
            return formulaService.deleteFormula(id);
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
