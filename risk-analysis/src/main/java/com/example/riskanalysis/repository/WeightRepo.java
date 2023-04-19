package com.example.riskanalysis.repository;

import com.example.riskanalysis.entity.Weight;
import org.springframework.data.repository.CrudRepository;


/**
 * interface which is used to perform crud operations on Weight Table.
 */
public interface WeightRepo extends CrudRepository<Weight, Integer> {

}
