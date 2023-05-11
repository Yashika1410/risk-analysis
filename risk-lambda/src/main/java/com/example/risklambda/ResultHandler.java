package com.example.risklambda;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import com.example.risklambda.dao.OutputDao;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

public class ResultHandler implements RequestHandler<
APIGatewayProxyRequestEvent, APIGatewayProxyResponseEvent> {
    /**
     *
     */
    private static final int DURATION_SECONDS = 40;
    /**
     *
     */
    private static final int UNAUTHORIZE = 40;
    /**
     *
     */
    private static final int OK = 40;
    /**
     *
     */
    private static final int INTERNAL_SERVER_ERROR = 40;
    /**
     *
     */
    @Override
    public APIGatewayProxyResponseEvent handleRequest(
    final  APIGatewayProxyRequestEvent input, final Context context) {
        final Logger log = LoggerFactory.getLogger(
                RequestHandler.class);
         HttpClient httpClient = HttpClient.newBuilder()
                .connectTimeout(Duration.ofSeconds(
                         DURATION_SECONDS))
                .build();
         ObjectWriter mapper = new ObjectMapper()
                .writer().withDefaultPrettyPrinter();
        OutputDao outputDao = new OutputDao();
        APIGatewayProxyResponseEvent responseEvent =
        new APIGatewayProxyResponseEvent();
        if (input.getHeaders().get("Authorization") == null) {
        responseEvent.setStatusCode(UNAUTHORIZE);
        return responseEvent;
        }
    final String authorization = input.getHeaders().get("Authorization");

    try {
        log.info("started");
        HttpRequest request2 = HttpRequest.newBuilder()
        .timeout(Duration.ofSeconds(
                        DURATION_SECONDS))
                .GET().header("Authorization", authorization)
                .uri(URI.create(
                        System.getenv(
                                "AUTH_SERVICE_URL")
                                + "/auth/api/v1/verify"))
                .build();
        log.info("Hi");
        log.info(request2.uri().toString());
        HttpResponse<String> response = httpClient.send(request2,
                HttpResponse.BodyHandlers.ofString());
        log.info(response.body());
        if (response.statusCode() == OK) {
        // System.out.println("Body: " + response.body());
        responseEvent.setBody(
            mapper.writeValueAsString(outputDao.getLatestOutputs()));
        responseEvent.setStatusCode(OK);
        return responseEvent;
    }
    responseEvent.setBody(response.body());
    responseEvent.setStatusCode(response.statusCode());
    return responseEvent;

    } catch (IOException | InterruptedException e) {
        e.printStackTrace();
        responseEvent.setBody(e.getMessage());
        responseEvent.setStatusCode(INTERNAL_SERVER_ERROR);
        return responseEvent;
    }
    }

}
