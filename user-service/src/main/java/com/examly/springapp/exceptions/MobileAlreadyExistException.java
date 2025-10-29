package com.examly.springapp.exceptions;

public class MobileAlreadyExistException extends RuntimeException {
    public MobileAlreadyExistException(String message){
        super(message);
    }
}
