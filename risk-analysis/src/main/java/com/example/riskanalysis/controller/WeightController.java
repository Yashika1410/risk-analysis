package com.example.riskanalysis.controller;

import com.example.riskanalysis.entity.Weight;
import com.example.riskanalysis.service.WeightService;
import java.util.List;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
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
 * WeightController class used to perform crud operations on entity.
 */
@RestController
@RequestMapping("/api/v1/weights")
public class WeightController {
  /**
   * log variable which used for logging.
   */
  private final Logger log = LoggerFactory.getLogger(WeightController.class);
  /**
   * autowired weight service class.
   */
  @Autowired
    private WeightService weightService;

  /**
     * get weight by unique id.

     * @param id
     *
     * @return Weight
     */
  @GetMapping("/{id}")
  @Operation(summary = "Get Weight by id", security = @SecurityRequirement(name = "bearerAuth"))
    public Weight getWeight(@PathVariable final int id) {
    try {
      return weightService.getWeight(id);
    } catch (ResponseStatusException re) {
      log.error(re.getMessage());
      throw new ResponseStatusException(re.getStatus(), re.getMessage());
    } catch (Exception e) {
      throw new ResponseStatusException(
        HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
    }

  }

  /**
     * get list of weights.

     * @return List(Weight)
     */
  @GetMapping("")
  @Operation(summary = "Get List of Weights", security = @SecurityRequirement(name = "bearerAuth"))
    public List<Weight> getListofWeights() {
    try {
      return weightService.getAllWeights();
    } catch (Exception e) {
      log.error(e.getMessage());
      throw new ResponseStatusException(
        HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
    }
  }

  /**
     * create new weight.

     * @param weight
     *
     * @return Weight
     */
  @PostMapping("")
  @Operation(summary = "Create new Weight", security = @SecurityRequirement(name = "bearerAuth"))
    public Weight createWeight(@Valid @RequestBody final Weight weight) {
    try {
      return weightService.addWeight(weight);
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
     * update weight using id.

     * @param id
     *
     * @param weight
     *
     * @return Weight
     */
  @PatchMapping("/{id}")
  @Operation(summary = "Update Weight by id", security = @SecurityRequirement(name = "bearerAuth"))
    public Weight patchWeight(@PathVariable final int id,
      @Valid @RequestBody final Weight weight) {
    try {
      return weightService.updateWeight(id, weight);
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
     * delete weight using id.

     * @param id
     *
     * @return String
     */
  @DeleteMapping("/{id}")
  @Operation(summary = "Delete Weight by id", security = @SecurityRequirement(name = "bearerAuth"))
    public String deleteWeight(@PathVariable final int id) {
    try {
      return weightService.deleteWeight(id);
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
