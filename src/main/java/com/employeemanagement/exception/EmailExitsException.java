package com.employeemanagement.exception;

public class EmailExitsException extends RuntimeException {
    public EmailExitsException(String msg) {
        super(msg);
    }
}
