package com.bajajfinservhealth.qualifiertask.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class SqlQueryServiceTest {

    @InjectMocks
    private SqlQueryService sqlQueryService;

    @Test
    void testGetQuestion1Solution_OddRegNo() {
        String regNo = "REG12347";
        String result = sqlQueryService.getQuestion1Solution(regNo);
        
        assertNotNull(result);
        assertTrue(result.contains("SELECT"));
        assertTrue(result.length() > 50); // Ensure it's a substantial query
    }

    @Test
    void testGetQuestion1Solution_EvenRegNo() {
        String regNo = "REG12348";
        String result = sqlQueryService.getQuestion1Solution(regNo);
        
        assertNotNull(result);
        // For even numbers, it would return Question 2 placeholder
        assertTrue(result.contains("Question 2"));
    }
}