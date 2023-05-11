package com.example.risklambda;

import java.time.LocalDateTime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.example.risklambda.service.EvaluateJob;

public class ScheduleJob implements RequestHandler<Void, Void> {

    /**
     *
     */
    @Override
    public Void handleRequest(final Void arg0, final Context context) {
        // TODO Auto-generated method stub
    EvaluateJob evaluateJob = new EvaluateJob();
    final Logger log = LoggerFactory.getLogger(
    "Lambda Function Cron Job");
        log.info("Lambda Cron Job Started at" + LocalDateTime.now().toString());
        evaluateJob.saveAnalysiedData();
        return null;
    }

}
