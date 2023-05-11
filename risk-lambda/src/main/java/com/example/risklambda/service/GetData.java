package com.example.risklambda.service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.example.risklambda.model.CompanyRiskScore;
import com.example.risklambda.model.Formula;
import com.example.risklambda.model.RiskScoreCap;
import com.example.risklambda.model.RiskScoreLevel;
import com.example.risklambda.model.Weight;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class GetData {
    /**
     * bearer token which is required for api auth token.
     */
    private String bearerToken;
    /**
     * protected default constructor.
     */
    protected GetData() {

    }
    /**
     * Paramiterised constructor.
     * @param token token string for api authentication.
     */
    public GetData(final String token) {
        this.bearerToken = "Bearer " + token;
    }

    /**
     *
     */
    private static final int DURATION_SECONDS = 40;
    /**
     *
     */
    private final Logger log = LoggerFactory.getLogger(
            GetData.class);
    /**
     *
     */
    private ObjectMapper mapper = new ObjectMapper();
    /**
     *
     */
    private HttpClient httpClient = HttpClient.newBuilder()
            .version(HttpClient.Version.HTTP_2)
            .connectTimeout(Duration.ofSeconds(DURATION_SECONDS))
            .build();

    /**
     * get list of weights from risk-analysis endpoint.
     * @return list of weights.
     */
    public List<Weight> getWeights() {
        try {
            HttpRequest request = HttpRequest.newBuilder()
            .GET().header("Authorization",  this.bearerToken)
            .uri(URI.create(
                System.getenv("RISK_SERVICE_URL") + "/api/v1/weights"))
            .build();
            HttpResponse<String> response = httpClient.send(request,
            HttpResponse.BodyHandlers.ofString());
        //  System.out.println("Body: " + response.body());
        return mapper.readValue(response.body(),
                new TypeReference<List<Weight>>() {
                });
      } catch (IOException | InterruptedException e) {
         e.printStackTrace();
         log.error(e.getMessage());
         return null;
      }
    }

    /**
     * get list of formulas from risk-analysis endpoint.

     * @return list of formula objects.
     */
    public List<Formula> getFormulas() {
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .GET().header("Authorization",  this.bearerToken)
                    .uri(URI.create(
                            System.getenv(
                                "RISK_SERVICE_URL") + "/api/v1/formulas"))
                    .build();
            HttpResponse<String> response = httpClient.send(request,
                    HttpResponse.BodyHandlers.ofString());
            // System.out.println("Body: " + response.body());
            return mapper.readValue(response.body(),
            new TypeReference<List<Formula>>() { });
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
         log.error(e.getMessage());
            return null;
        }
    }

    /**
     * get list of risk score levels from risk-analysis endpoint.

     * @return list of risk score levele objects.
     */
    public List<RiskScoreLevel> getRiskScoreLevels() {
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .GET().header("Authorization",  this.bearerToken)
                    .uri(URI.create(
                            System.getenv(
                                    "RISK_SERVICE_URL")
                                    + "/api/v1/risk-score-levels"))
                    .build();
            HttpResponse<String> response = httpClient.send(request,
                    HttpResponse.BodyHandlers.ofString());
            // System.out.println("Body: " + response.body());
            return mapper.readValue(response.body(),
                    new TypeReference<List<RiskScoreLevel>>() {
                    });
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
         log.error(e.getMessage());
            return null;
        }
    }

    /**
     * get list of score caps from risk-analysis endpoint.

     * @return list of risk score cap objects.
     */
    public List<RiskScoreCap> getRiskScoreCaps() {
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .GET().header("Authorization",  this.bearerToken)
                    .uri(URI.create(
                            System.getenv(
                                    "RISK_SERVICE_URL")
                                    + "/api/v1/risk-score-caps"))
                    .build();
            HttpResponse<String> response = httpClient.send(request,
                    HttpResponse.BodyHandlers.ofString());
            // System.out.println("Body: " + response.body());
            return mapper.readValue(response.body(),
                    new TypeReference<List<RiskScoreCap>>() {
                    });
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
         log.error(e.getMessage());
            return null;
        }
    }

    /**
     * get list of company data from risk-analysis endpoint.

     * @return list of company risk score objects.
     */
    public List<CompanyRiskScore> getCompanyRiskScores() {
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .GET().header("Authorization",  this.bearerToken)
                    .uri(URI.create(
                            System.getenv(
                                    "RISK_SERVICE_URL")
                                    + "/api/v1/company-risk-scores"))
                    .build();
            HttpResponse<String> response = httpClient.send(request,
                    HttpResponse.BodyHandlers.ofString());
            return mapper.readValue(response.body(),
                    new TypeReference<List<CompanyRiskScore>>() {
                    });
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
         log.error(e.getMessage());
            return null;
        }
    }

}
