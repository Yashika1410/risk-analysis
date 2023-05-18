package com.example.riskanalysis.repository;

import org.springframework.data.repository.CrudRepository;

import com.example.riskanalysis.entity.Score;

public interface ScoreRepo extends CrudRepository<Score, Integer> {

}
