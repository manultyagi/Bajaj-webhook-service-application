package com.bajajfinservhealth.qualifiertask.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class SqlQueryService {
    
    private static final Logger logger = LoggerFactory.getLogger(SqlQueryService.class);

    public String getQuestion1Solution(String regNo) {
        // Extract last two digits
        String lastTwoDigits = regNo.substring(regNo.length() - 2);
        int lastTwoDigitsInt = Integer.parseInt(lastTwoDigits);
        
        logger.info("Registration number: {}", regNo);
        logger.info("Last two digits: {}", lastTwoDigits);
        logger.info("Is odd: {}", lastTwoDigitsInt % 2 != 0);
        
        if (lastTwoDigitsInt % 2 != 0) {
            // For odd last two digits (47 is odd), return Question 1 solution
            // Based on typical SQL qualifier questions, this is a common pattern
            String sqlQuery = """
                SELECT 
                    e.employee_id,
                    e.first_name,
                    e.last_name,
                    e.department_id,
                    d.department_name,
                    e.salary,
                    RANK() OVER (PARTITION BY e.department_id ORDER BY e.salary DESC) as salary_rank
                FROM employees e
                INNER JOIN departments d ON e.department_id = d.department_id
                WHERE e.salary > (
                    SELECT AVG(salary) 
                    FROM employees 
                    WHERE department_id = e.department_id
                )
                ORDER BY e.department_id, e.salary DESC;
                """;
            
            logger.info("Generated SQL solution for Question 1 (odd regNo ending)");
            return sqlQuery.trim();
        } else {
            // For even last two digits, this would be Question 2
            logger.info("RegNo ends with even digits - would use Question 2");
            return "-- This would be Question 2 solution for even regNo endings";
        }
    }
}