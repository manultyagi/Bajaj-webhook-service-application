package com.bajajfinservhealth.qualifiertask.runner;

import com.bajajfinservhealth.qualifiertask.model.WebhookResponse;
import com.bajajfinservhealth.qualifiertask.service.SqlQueryService;
import com.bajajfinservhealth.qualifiertask.service.WebhookService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class QualifierTaskRunner implements CommandLineRunner {
    
    private static final Logger logger = LoggerFactory.getLogger(QualifierTaskRunner.class);
    
    @Autowired
    private WebhookService webhookService;
    
    @Autowired
    private SqlQueryService sqlQueryService;

    @Override
    public void run(String... args) throws Exception {
        logger.info("=".repeat(80));
        logger.info("Starting Bajaj Finserv Health Qualifier Task");
        logger.info("=".repeat(80));
        
        try {
            // Step 1: Generate webhook and get credentials
            logger.info("Step 1: Generating webhook and retrieving credentials...");
            WebhookResponse webhookResponse = webhookService.generateWebhook();
            
            if (webhookResponse == null || 
                webhookResponse.getWebhookUrl() == null || 
                webhookResponse.getAccessToken() == null) {
                logger.error("Failed to generate webhook or retrieve credentials");
                logger.error("Application cannot continue without valid webhook response");
                return;
            }
            
            logger.info("✓ Successfully generated webhook");
            
            // Step 2: Determine question and generate SQL solution
            logger.info("Step 2: Determining question based on registration number...");
            String regNo = "REG12347";
            String sqlSolution = sqlQueryService.getQuestion1Solution(regNo);
            
            logger.info("✓ Generated SQL solution");
            logger.info("SQL Query Preview: {}", 
                       sqlSolution.length() > 100 ? 
                       sqlSolution.substring(0, 100) + "..." : 
                       sqlSolution);
            
            // Step 3: Send solution to webhook
            logger.info("Step 3: Sending solution to webhook...");
            boolean success = webhookService.sendSolution(
                webhookResponse.getWebhookUrl(),
                webhookResponse.getAccessToken(),
                sqlSolution
            );
            
            if (success) {
                logger.info("✓ Successfully completed qualifier task!");
                logger.info("=".repeat(80));
                logger.info("QUALIFIER TASK COMPLETED SUCCESSFULLY");
                logger.info("=".repeat(80));
            } else {
                logger.error("✗ Failed to send solution to webhook");
                logger.error("=".repeat(80));
                logger.error("QUALIFIER TASK FAILED");
                logger.error("=".repeat(80));
            }
            
        } catch (Exception e) {
            logger.error("Unexpected error during qualifier task execution: {}", e.getMessage(), e);
            logger.error("=".repeat(80));
            logger.error("QUALIFIER TASK FAILED WITH EXCEPTION");
            logger.error("=".repeat(80));
        }
    }
}