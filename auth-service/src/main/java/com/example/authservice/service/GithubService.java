package com.example.authservice.service;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;
import com.example.authservice.model.UserModel;

@Service
@SuppressWarnings({ "rawtypes", "unchecked" })
public class GithubService {

  /**
   * github client id for github auth.
   */
  private String githubId;
  /**
   * jwt issuer details for token generation.
   */
  private String githubSecret;

  /**
   * object of rest template to send request to auth service for auth.
   */
  private RestTemplate restTemplate = new RestTemplate();
  /**
   * github user url to get user details.
   */
  private String githubUserUrl = "https://api.github.com/user";
  /**
   * github user url to get user details.
   */
  private String githubTokenUrl =
  "https://github.com/login/oauth/access_token";

  /**
     * Parameterized constructor to get data from application properties.
     * @param id
     * @param secret
     */
    public GithubService(
        @Value("${github.client.id}") final String id,
        @Value("${github.client.secret}") final String secret) {
        this.githubId = id;
        this.githubSecret = secret;
    }

    /**
     * @param code
     * @return dfefw.
     */
    private String getAccessToken(final String code) {
      try {
      // creating request body
      Map<String, String> map = new HashMap<>();
      map.put("client_id", githubId);
      map.put("client_secret", githubSecret);
      map.put("code", code);
      // setting content type
      HttpHeaders headers = new HttpHeaders();
      headers.setContentType(MediaType.APPLICATION_JSON);
      //Creating http entity
      HttpEntity<Map<String, String>> request = new HttpEntity<>(
          map, headers);
      // send a post request to get access token
      ResponseEntity<Map> result = restTemplate.exchange(
          githubTokenUrl,
          HttpMethod.POST, request, Map.class);
      // check status
        if (!result.getStatusCode().is2xxSuccessful()) {
          throw new HttpClientErrorException(result.getStatusCode(),
              result.getBody().toString());
        }
        if (result.getBody().containsKey("error")) {
          throw new ResponseStatusException(
              HttpStatus.INTERNAL_SERVER_ERROR,
              "Some Error contact to administrator");

        }
          return result.getBody().get("access_token").toString();
        } catch (HttpClientErrorException hte) {
          throw new ResponseStatusException(hte.getStatusCode(),
          hte.getMessage());
        }
    }

    private String getEmail(final String token) {
      HttpHeaders headers = new HttpHeaders();
      headers.setContentType(MediaType.APPLICATION_JSON);
      headers.setBearerAuth(token);
      RequestEntity<Void> requestEntity;
      try {
        requestEntity = new RequestEntity<>(
            headers, HttpMethod.GET,
            new URI(githubUserUrl + "/emails"));
        ResponseEntity<List> result = restTemplate.exchange(
            requestEntity, List.class);
        List<Map<String, String>> emails = (
          List<Map<String, String>>) result.getBody();
        return emails.get(0).get("email");
      } catch (HttpClientErrorException hte) {
        throw new ResponseStatusException(hte.getStatusCode(),
            hte.getMessage());
      } catch (URISyntaxException e) {
        e.printStackTrace();
        throw new ResponseStatusException(
            HttpStatus.INTERNAL_SERVER_ERROR,
            "Some Error contact to administrator");
      }
    }

    /**
     * @param code
     * @return idcsd.
     */
    public final UserModel getUser(final String code) {
      String token = getAccessToken(code);
       HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.setBearerAuth(token);
            RequestEntity<Void> requestEntity;
            try {
                requestEntity = new RequestEntity<>(
                    headers, HttpMethod.GET,
                        new URI(githubUserUrl));
                ResponseEntity<Map> result = restTemplate.exchange(
                        requestEntity, Map.class);
                String email;
                if (result.getBody().get("email") == null) {
                  email = getEmail(token);
                } else {
                  email = result.getBody().get("email").toString();
                }
                UserModel user = new UserModel(
                        email,
                        result.getBody().get("id").toString());
                return user;
            } catch (HttpClientErrorException hte) {
              throw new ResponseStatusException(hte.getStatusCode(),
          hte.getMessage());
            } catch (URISyntaxException e) {
                e.printStackTrace();
                throw new ResponseStatusException(
                        HttpStatus.INTERNAL_SERVER_ERROR,
                        "Some Error contact to administrator");
            }
    }
}
