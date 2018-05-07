package com.onuriltan.twitteranalyzerserver.api.geolocation.model;

public class CustomErrorType {

    private String errorCode;
    private String errorMessage;

    public CustomErrorType(String errorCode, String errorMessage){
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public String getErrorCode() {
        return errorCode;
    }
}
