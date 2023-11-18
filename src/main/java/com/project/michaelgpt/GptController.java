package com.project.michaelgpt;

import com.project.michaelgpt.requests.PromptRequest;
import com.project.michaelgpt.payloads.ReplicateBody;
import com.project.michaelgpt.payloads.Urls;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/gpt")
public class GptController {
  private static final String API_KEY = "r8_DUF1YioddQIGhTiEadWHnX6mlE2ybUL2tmBav"; // Replace with environmental variable
  private static final String REPLICATE_BASE_URL = "https://api.replicate.com/v1/deployments/gullman99/my-llama/predictions";

  @PostMapping("/post")
  @ResponseBody
  public ResponseEntity<String> postGpt(@RequestBody PromptRequest request) {
    String prompt = request.getPrompt();
    
    System.out.println(request.getPrompt());

    RestTemplate restTemplate = new RestTemplate();

    // Prepare the request body
    String requestBody = "{\"input\": {\"prompt\": \"" + prompt + "\"}}";


    // Set up headers
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);
    headers.set("Authorization", "Token " + API_KEY);

    // Create the HTTP entity
    HttpEntity<String> httpEntity = new HttpEntity<>(requestBody, headers);

     // Make the POST request
    ResponseEntity<String> responseEntity = restTemplate.postForEntity(REPLICATE_BASE_URL, httpEntity, String.class);

    ObjectMapper objectMapper = new ObjectMapper();

    try {
        // Extract the response body from ResponseEntity

        // Convert the response body to ReplicateBody
        ReplicateBody replicateBody = objectMapper.readValue(responseEntity.getBody(), ReplicateBody.class);

        System.out.println(replicateBody.getUrls().getGet());
    } catch (Exception e) {
        e.printStackTrace();
    }
    return responseEntity;
  }
}