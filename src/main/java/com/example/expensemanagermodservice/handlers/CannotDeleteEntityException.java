package com.example.expensemanagermodservice.handlers;

public class CannotDeleteEntityException extends RuntimeException{
    public CannotDeleteEntityException(String message){
        super(message);
    }
}
