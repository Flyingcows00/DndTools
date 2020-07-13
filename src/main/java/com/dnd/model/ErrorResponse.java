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
    public static final ErrorResponse SPELL_NOT_FOUND = new ErrorResponse(404, "Spell Not Found", "The requested spell could not be found in the database.");
    public static final ErrorResponse SPELL_ALREADY_EXISTS = new ErrorResponse(404, "Spell Already Exists", "Failed to create spell.  One with that name already exists.");
    public static final ErrorResponse CAMPAIGN_NOT_FOUND = new ErrorResponse(404, "Campaign Not Found", "The requested campaign could not be found in the database.");
    public static final ErrorResponse CAMPAIGN_ALREADY_EXISTS = new ErrorResponse(400, "Campaign Already Exists", "Failed to create campaign.  One with that name already exists.");
    public static final ErrorResponse CAMPAIGN_REQUIRED_FIELDS = new ErrorResponse(400, "Missing Required Fields", "Failed to update campaign.  Missing one of the required fields; character_name, alive, notes.");
    public static final ErrorResponse CHARACTER_NOT_FOUND = new ErrorResponse(404, "Character Not Found", "The requested character could not be found in the database.");

    public ErrorResponse(int statusCode) {
        this.statusCode = statusCode;
        this.error = UNKNOWN_ERROR;
        this.description = UNKNOWN_DESCRIPTION;
    }

    public ErrorResponse(int statusCode, Exception e) {
        this.statusCode = statusCode;
        this.error = e.getClass().getSimpleName();
        this.description = e.getMessage();
//        this.description = (e.getCause() != null) ? e.getCause().getMessage() : e.getMessage();
    }

    public ErrorResponse(int statusCode, String error, String description) {
        this.statusCode = statusCode;
        this.error = error;
        this.description = description;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
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
