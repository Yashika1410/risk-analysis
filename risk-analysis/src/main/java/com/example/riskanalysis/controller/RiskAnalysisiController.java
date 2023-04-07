package com.example.riskanalysis.controller;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.IntStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.riskanalysis.entity.Output;
import com.example.riskanalysis.service.RiskAnalysisService;

@RestController
@RequestMapping("/get-data")
public class RiskAnalysisiController {
    @Autowired
    private RiskAnalysisService riskAnalysisService;

    @GetMapping("")
    public List<Output> getData() {
        return riskAnalysisService.getOutputList();
    }

}
