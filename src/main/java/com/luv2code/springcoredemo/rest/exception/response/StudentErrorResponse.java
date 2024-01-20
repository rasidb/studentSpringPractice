package com.luv2code.springcoredemo.rest.exception.response;
//pojo class
public class StudentErrorResponse {
private int statusCode;
private String message;
private Long timeStamp;

    public StudentErrorResponse(int status, String message, Long timeStamp) {
        this.statusCode = status;
        this.message = message;
        this.timeStamp = timeStamp;
    }
    public StudentErrorResponse(){

    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Long getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Long timeStamp) {
        this.timeStamp = timeStamp;
    }
}
