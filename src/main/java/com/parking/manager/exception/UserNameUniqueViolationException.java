package com.parking.manager.exception;

public class UserNameUniqueViolationException extends RuntimeException{

    public UserNameUniqueViolationException(String message) {
        super(message);
    }
}
