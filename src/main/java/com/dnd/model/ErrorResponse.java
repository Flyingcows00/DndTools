package com.dnd.model;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ErrorResponse {

    private int statusCode;
    private String error;
    private String description;

    public static final String UNKNOWN_ERROR = "Unknown Error";
    public static final String UNKNOWN_DESCRIPTION = "Something when wrong while processing your request";

    public static final ErrorResponse MONSTER_NOT_FOUND = new ErrorResponse(404, "Monster Not Found", "The requested monster could not be found in the database.");

    public ErrorResponse(int statusCode) {
        this.statusCode = statusCode;
        this.error = UNKNOWN_ERROR;
        this.description = UNKNOWN_DESCRIPTION;
    }

    public ErrorResponse(int statusCode, Exception e) {
        this.statusCode = statusCode;
        this.error = e.getClass().getSimpleName();
        this.description = e.getMessage();
    }

    public ErrorResponse(int statusCode, String error, String description) {
        this.statusCode = statusCode;
        this.error = error;
        this.description = description;
    }

    public String toJson() {
        ObjectMapper mapper = new ObjectMapper();
        String json = null;
        try {
            json = mapper.writeValueAsString(this);
        } catch (JsonProcessingException e) {
            json = "Unknown Error";
        }
        return json;
    }

}
