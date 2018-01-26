package com.dnd.model;

import com.google.gson.Gson;

public class ErrorResponse {

    private int statusCode;
    private String error;
    private String description;

    public static final String UNKNOWN_ERROR = "Unknown Error";
    public static final String UNKNOWN_DESCRIPTION = "Something when wrong while processing your request";

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
        return (new Gson()).toJson(this);
    }

}
