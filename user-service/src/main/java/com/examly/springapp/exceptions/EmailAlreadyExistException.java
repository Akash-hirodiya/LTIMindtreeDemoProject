package com.examly.springapp.exceptions;

public class EmailAlreadyExistException extends RuntimeException {
    public EmailAlreadyExistException(String message){
            super(message);
    }
}
