package com.example.riskanalysis.service;

import com.example.riskanalysis.entity.Formula;
import com.example.riskanalysis.repository.FormulaRepo;
import com.example.riskanalysis.repository.ScoreRepo;
import com.example.riskanalysis.repository.WeightRepo;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
/**
 * service class which is used to perform operations by controller class.
 */

@Service
public class FormulaService {
  /**
   * autowired Formula Repo interface.
   */
  @Autowired
    private FormulaRepo formulaRepo;

  /**
   * autowired company risk score Repo interface.
   */
  @Autowired
  private ScoreRepo scoreRepo;
  /**
   * autowired Weight Repo interface.
   */
  @Autowired
  private WeightRepo weightRepo;


  private List<String> getAllVariables() {
    Set<String> variables = new HashSet<String>();
    formulaRepo.findAll().forEach(f -> variables.add(f.getEntityName()));
    weightRepo.findAll().forEach(w -> variables.add(w.getDimension()));
    scoreRepo.findAll().forEach(s -> variables.add(s.getDimension()));
    List<String> variableList = new ArrayList<String>();
    variableList.addAll(variables);
    return variableList;

  }

  private boolean checkFormulaVariables(final Formula formula) {
    String regex = "(?<![a-zA-Z0-9_])([a-zA-Z_][a-zA-Z0-9_]*)(?![a-zA-Z0-9_])";
    Pattern pattern = Pattern.compile(regex);
    Matcher matcher = pattern.matcher(formula.getFormula());

    List<String> variables = new ArrayList<>();
    while (matcher.find()) {
      variables.add(matcher.group(1));
    }
    variables.forEach(v -> System.out.println(v));
    System.out.println(getAllVariables().containsAll(variables));
    return getAllVariables().containsAll(variables);

  }

  /**
     * get formula by id.

     * @param id unique id.
     * @return Formula object
     * @throws ResponseStatusException throws exception accr to the scnerio.
     */
  public Formula getFormula(final int id) {
    return formulaRepo.findById(id).orElseThrow(
                () -> new ResponseStatusException(
                HttpStatus.NOT_FOUND, "Formula Not Found by this id " + id));
  }

  /**
   * save formula data using valid formula object.

     * @param formula valid data
     * @return formula
     * @throws ResponseStatusException ({@link HttpStatus.Series},
     *     {@value String}})
     */
  public Formula addFormula(final Formula formula) {
    if (formulaRepo.existsByEntityName(formula.getEntityName().toLowerCase())) {
      throw new ResponseStatusException(
        HttpStatus.CONFLICT,
        "Formula already exists by "
        + (" this entity name " + formula.getEntityName()));
    } else if (!checkFormulaVariables(formula)) {
      throw new ResponseStatusException(
          HttpStatus.UNPROCESSABLE_ENTITY,
          "variables doesn't exists which are used in this formula"
           + formula.getFormula());
    }
    return formulaRepo.save(formula);
  }

  /**
   * return list of formulas.

     * @return List(formula)
     */
  public List<Formula> getAllFormulas() {

    return (List<Formula>) formulaRepo.findAll();
  }

  /**
     * update formula using valid id and valid updated data.

     * @param id unique id.
     * @param formula valid updated data.
     * @return updated Formula object
     * @throws ResponseStatusException throws an exception if
     *     formula not found by id.
     */
  public Formula updateFormula(final int id, final Formula formula) {
    Formula existingFormula = formulaRepo.findById(id).orElseThrow(
                () -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Formula Not Found by this id " + id));
    if (!existingFormula.getEntityName().equals(
        formula.getEntityName().toLowerCase())) {
      if (formulaRepo.existsByEntityName(formula.getEntityName().toLowerCase(
      ))) {
        throw new ResponseStatusException(HttpStatus.CONFLICT,
                        "Entity Name already exists with different formula");
      }
    } else if (!checkFormulaVariables(formula)) {
      throw new ResponseStatusException(
          HttpStatus.UNPROCESSABLE_ENTITY,
          "variables doesn't exists which are used in this formula"
              + formula.getFormula());
    }
    existingFormula.setEntityName(formula.getEntityName());
    existingFormula.setFormula(formula.getFormula());
    formulaRepo.save(existingFormula);
    return existingFormula;
  }

  /**
   * delete formula using id.

     * @param id unique formula id.
     *
     * @return String message
     */
  public String deleteFormula(final int id) {
    Formula formula = formulaRepo.findById(id).orElseThrow(
                () -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Formula Not Found by this id " + id));
    formulaRepo.delete(formula);
    return "Successfully deleted formula by this id " + id;
  }
}
