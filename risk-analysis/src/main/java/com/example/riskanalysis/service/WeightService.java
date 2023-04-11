package com.example.riskanalysis.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.riskanalysis.entity.Weight;
import com.example.riskanalysis.repository.WeightRepo;

@Service
public class WeightService {

    @Autowired
    private WeightRepo weightRepo;

    /**
     * @param id
     * @return weight object
     * @throws ResponseStatusException({@link HttpStatus.Series},{@value String}})
     */
    public Weight getWeight(int id) {
        return weightRepo.findById(id).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,
                "Weight Not Found by this id " + id));
    }


    /**
     * @param weight
     * @return weight object
     */
    public Weight addWeight(Weight weight) {
            return weightRepo.save(weight);
    }

    /**
     * @return List<Weight>
     */
    public List<Weight> getAllWeights() {

        return (List<Weight>) weightRepo.findAll();
    }

   
    /**
     * @param id
     * @param weight
     * @return updated Weight object
     */
    public Weight updateWeight(int id,Weight weight) {
        Weight existingWeight = weightRepo.findById(id).orElseThrow(
                () -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Weight Not Found by this id " + id));
        existingWeight.setDimension(weight.getDimension());
        existingWeight.setWeight(weight.getWeight());
        weightRepo.save(existingWeight);
        return existingWeight;
    }

    /**
     * @param id
     * @return String
     * @throws ResponseStatusException(HttpStatus.NOT_FOUND, "Weight Not Found by this id " + id)
     */
    public String deleteWeight(int id) {
        Weight weight = weightRepo.findById(id).orElseThrow(
                () -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Weight Not Found by this id " + id));
        weightRepo.delete(weight);
        return "Successfully deleted weight by this id " + id;
    }
    
}
