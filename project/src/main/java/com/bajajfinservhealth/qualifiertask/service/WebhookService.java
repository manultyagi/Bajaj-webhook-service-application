package com.bajajfinservhealth.qualifiertask.service;

import com.bajajfinservhealth.qualifiertask.model.WebhookRequest;
import com.bajajfinservhealth.qualifiertask.model.WebhookResponse;
import com.bajajfinservhealth.qualifiertask.model.SolutionRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class WebhookService {
    
    private static final Logger logger = LoggerFactory.getLogger(WebhookService.class);
    private static final String WEBHOOK_GENERATE_URL = "https://bfhldevapigw.healthrx.co.in/hiring/generateWebhook/JAVA";
    
    private final RestTemplate restTemplate;

    public WebhookService() {
        this.restTemplate = new RestTemplate();
    }

    public WebhookResponse generateWebhook() {
        try {
            WebhookRequest request = new WebhookRequest("John Doe", "REG12347", "john@example.com");
            
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            
            HttpEntity<WebhookRequest> entity = new HttpEntity<>(request, headers);
            
            logger.info("Sending webhook generation request to: {}", WEBHOOK_GENERATE_URL);
            logger.info("Request body: name={}, regNo={}, email={}", 
                       request.getName(), request.getRegNo(), request.getEmail());
            
            WebhookResponse response = restTemplate.postForObject(
                WEBHOOK_GENERATE_URL, 
                entity, 
                WebhookResponse.class
            );
            
            if (response != null) {
                logger.info("Successfully received webhook response");
                logger.info("Webhook URL: {}", response.getWebhookUrl());
                logger.info("Access Token: {}", response.getAccessToken() != null ? "***MASKED***" : "null");
            } else {
                logger.error("Received null response from webhook generation");
            }
            
            return response;
        } catch (Exception e) {
            logger.error("Error generating webhook: {}", e.getMessage(), e);
            return null;
        }
    }

    public boolean sendSolution(String webhookUrl, String accessToken, String sqlQuery) {
        try {
            SolutionRequest request = new SolutionRequest(sqlQuery);
            
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.setBearerAuth(accessToken);
            
            HttpEntity<SolutionRequest> entity = new HttpEntity<>(request, headers);
            
            logger.info("Sending solution to webhook URL: {}", webhookUrl);
            logger.info("SQL Query: {}", sqlQuery);
            
            restTemplate.exchange(
                webhookUrl,
                HttpMethod.POST,
                entity,
                String.class
            );
            
            logger.info("Successfully sent solution to webhook");
            return true;
            
        } catch (Exception e) {
            logger.error("Error sending solution to webhook: {}", e.getMessage(), e);
            return false;
        }
    }
}