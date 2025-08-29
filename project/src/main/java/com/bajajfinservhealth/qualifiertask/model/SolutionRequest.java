package com.bajajfinservhealth.qualifiertask.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SolutionRequest {
    @JsonProperty("finalQuery")
    private String finalQuery;

    public SolutionRequest() {}

    public SolutionRequest(String finalQuery) {
        this.finalQuery = finalQuery;
    }

    public String getFinalQuery() {
        return finalQuery;
    }

    public void setFinalQuery(String finalQuery) {
        this.finalQuery = finalQuery;
    }
}