package com.example.expensemanagermodservice.advices;

import com.example.expensemanagermodservice.handlers.DataAlreadyExistException;
import com.example.expensemanagermodservice.handlers.NotFoundEntityException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class ExceptionHandlers {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleInvalidArguments(MethodArgumentNotValidException exception){
        Map<String, String> errorMap = new HashMap<>();
        exception.getBindingResult().getFieldErrors().forEach(error -> {
            errorMap.put(error.getField(), error.getDefaultMessage());
        });
        return errorMap;
    }


    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NotFoundEntityException.class)
    public Map<String, String> handleBusinessExceptions(NotFoundEntityException exception){
        Map<String, String> errorMap = new HashMap<>();
        errorMap.put("message",exception.getMessage());
        return errorMap;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(DataAlreadyExistException.class)
    public Map<String, String> handleBusinessExceptions(DataAlreadyExistException exception){
        Map<String, String> errorMap = new HashMap<>();
        errorMap.put("message",exception.getMessage());
        return errorMap;
    }


}
