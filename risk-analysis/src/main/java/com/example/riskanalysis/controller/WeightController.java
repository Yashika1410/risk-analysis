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

import com.example.riskanalysis.entity.Weight;
import com.example.riskanalysis.service.WeightService;

@RestController
@RequestMapping("/api/v1/weights")
public class WeightController {
    final static Logger log = LoggerFactory.getLogger(WeightController.class);
    @Autowired
    private WeightService weightService;

    /**
     * @param id
     * @return Weight
     */
    @GetMapping("/{id}")
    public Weight getWeight(@PathVariable int id) {
        try {
            return weightService.getWeight(id);
        } catch (ResponseStatusException re) {
            log.error(re.getMessage());
            throw new ResponseStatusException(re.getStatus(), re.getMessage());
        } catch (Exception e) {
            // TODO: handle exception
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }

    }

    /**
     * @return List<Weight>
     */
    @GetMapping("")
    public List<Weight> getListofWeights() {
        try {
            return weightService.getAllWeights();
        } catch (Exception e) {
            // TODO: handle exception
            log.error(e.getMessage());
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    /**
     * @param weight
     * @return Weight
     */
    @PostMapping("")
    public Weight createWeight(@Valid @RequestBody Weight weight) {
        try {
            return weightService.addWeight(weight);
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
     * @param weight
     * @return Weight
     */
    @PatchMapping("/{id}")
    public Weight patchWeight(@PathVariable int id, @Valid @RequestBody Weight weight) {
        try {
            return weightService.updateWeight(id, weight);
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
    public String deleteWeight(@PathVariable int id) {
        try {
            return weightService.deleteWeight(id);
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
