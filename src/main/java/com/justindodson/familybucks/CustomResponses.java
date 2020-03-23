package com.justindodson.familybucks;

public class CustomResponses {
    public static final String ERROR = "ERROR";
    public static final String SUCCESS = "SUCCESS";
    private String message;

    public CustomResponses(String message) {
        this.message = message;
    }

    public static String getERROR() {
        return ERROR;
    }

    public static String getSUCCESS() {
        return SUCCESS;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
