package com.example.riskanalysis.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.riskanalysis.entity.Formula;
import com.example.riskanalysis.repository.FormulaRepo;

@Service
public class FormulaService {

    @Autowired
    private FormulaRepo formulaRepo;

    /**
     * @param id
     * @return Formula object
     * @throws ResponseStatusException(HttpStatus.NOT_FOUND,String)
     */
    public Formula getFormula(int id) {
        return formulaRepo.findById(id).orElseThrow(
                () -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Formula Not Found by this id " + id));
    }

    /**
     * @param formula
     * @return formula
     * @throws ResponseStatusException({@link HttpStatus.Series},{@value String}})
     */
    public Formula addFormula(Formula formula) {
        if (formulaRepo.existsByEntityName(formula.getEntityName())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT,
                    "Formula already exists by " + (" this entity name " + formula.getEntityName()));
        }
        return formulaRepo.save(formula);
    }

    /**
     * @return List<formula>
     */
    public List<Formula> getAllFormulas() {

        return (List<Formula>) formulaRepo.findAll();
    }

    /**
     * @param formula
     * @return updated Formula object
     */
    public Formula updateFormula(int id, Formula formula) {
        Formula existingFormula = formulaRepo.findById(id).orElseThrow(
                () -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Formula Not Found by this id " + id));
        if (existingFormula.getEntityName() != formula.getEntityName()) {
            if (formulaRepo.existsByEntityName(formula.getEntityName()))
                throw new ResponseStatusException(HttpStatus.CONFLICT,
                        "Entity Name already exists with different formula");
        }
        existingFormula.setEntityName(formula.getEntityName());
        existingFormula.setFormula(formula.getFormula());
        formulaRepo.save(existingFormula);
        return existingFormula;
    }

    /**
     * @param id
     * @return String message
     */
    public String deleteFormula(int id) {
        Formula formula = formulaRepo.findById(id).orElseThrow(
                () -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Formula Not Found by this id " + id));
        formulaRepo.delete(formula);
        return "Successfully deleted formula by this id " + id;
    }
}
