package com.luv2code.springcoredemo.rest.exception;

public class StudentBadRequestException extends RuntimeException{
    public StudentBadRequestException() {
    }

    public StudentBadRequestException(String message) {
        super(message);
    }

    public StudentBadRequestException(String message, Throwable cause) {
        super(message, cause);
    }

    public StudentBadRequestException(Throwable cause) {
        super(cause);
    }
}
