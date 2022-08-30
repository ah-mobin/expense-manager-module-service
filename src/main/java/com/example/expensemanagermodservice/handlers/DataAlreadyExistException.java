package com.example.expensemanagermodservice.handlers;

public class DataAlreadyExistException extends RuntimeException{
    public DataAlreadyExistException(String message) {
        super(message);
    }
}
