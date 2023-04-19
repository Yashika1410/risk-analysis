package com.example.riskanalysis.service;

import com.example.riskanalysis.entity.Weight;
import com.example.riskanalysis.repository.WeightRepo;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
/**
 * servie class which is used by controller class to perform operations .
 */

@Service
public class WeightService {
  /**
   * autowired weight repo interface.
   */
  @Autowired
    private WeightRepo weightRepo;

  /**
   * get weight by id.

     * @param id unique id.
     *
     * @return weight object
     * @throws ResponseStatusException ({@link HttpStatus},{@value String}})
     */
  public Weight getWeight(final int id) {
    return weightRepo.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Weight Not Found by this id " + id));
  }

  /**
   *save valid data.

     * @param weight update data.
     * @return weight object
     */
  public Weight addWeight(final Weight weight) {
    return weightRepo.save(weight);
  }

  /**
   * get list of all weights.

     * @return List(Weight)
     */
  public List<Weight> getAllWeights() {

    return (List<Weight>) weightRepo.findAll();
  }

  /**
   * update weight using id.

   * @param id   unique id.

   * @param weight update data.

   * @return updated Weight object
   * @throws ResponseStatusException throws exception if weight found by id.

   */
  public Weight updateWeight(final int id, final Weight weight) {
    Weight existingWeight = weightRepo.findById(id).orElseThrow(
                () -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Weight Not Found by this id " + id));
    existingWeight.setDimension(weight.getDimension());
    existingWeight.setWeight(weight.getWeight());
    weightRepo.save(existingWeight);
    return existingWeight;
  }

  /**
   * delete weight by id.

     * @param id unique id
     * @return String
     * @throws ResponseStatusException (
     *     HttpStatus.NOT_FOUND, "Weight Not Found by this id " + id)
     */
  public String deleteWeight(final int id) {
    Weight weight = weightRepo.findById(id).orElseThrow(
                () -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Weight Not Found by this id " + id));
    weightRepo.delete(weight);
    return "Successfully deleted weight by this id " + id;
  }

}
