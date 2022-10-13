package com.example.expensemanagermodservice.advices;

import com.example.expensemanagermodservice.customs.ApiResponse;
import com.example.expensemanagermodservice.handlers.CannotDeleteEntityException;
import com.example.expensemanagermodservice.handlers.DataAlreadyExistException;
import com.example.expensemanagermodservice.handlers.NotFoundEntityException;
import org.modelmapper.spi.ErrorMessage;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolationException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestControllerAdvice
public class ExceptionHandlers extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ApiResponse> handleInvalidArgument(ConstraintViolationException ex, WebRequest webRequest) {
        return new ResponseEntity<>(new ApiResponse("Invalid Property"), HttpStatus.BAD_REQUEST);
    }


    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NotFoundEntityException.class)
    public Map<String, String> handleBusinessExceptions(NotFoundEntityException exception) {
        Map<String, String> errorMap = new HashMap<>();
        errorMap.put("message", exception.getMessage());
        return errorMap;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(DataAlreadyExistException.class)
    public Map<String, String> handleAlreadyExistExceptions(DataAlreadyExistException exception) {
        Map<String, String> errorMap = new HashMap<>();
        errorMap.put("message", exception.getMessage());
        return errorMap;
    }

    @ResponseStatus(HttpStatus.IM_USED)
    @ExceptionHandler(CannotDeleteEntityException.class)
    public Map<String, String> handleCannotDeleteExceptions(CannotDeleteEntityException exception) {
        Map<String, String> errorMap = new HashMap<>();
        errorMap.put("message", exception.getMessage());
        return errorMap;
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        List<String> errors = new ArrayList<>();
        List<FieldError> fieldErrors = ex.getBindingResult().getFieldErrors();
        for (FieldError fieldError : fieldErrors) {
            errors.add(fieldError.getField() + " " + fieldError.getDefaultMessage());
        }
        return new ResponseEntity<>(new ApiResponse("Invalid Properties", errors), status);
    }
}
