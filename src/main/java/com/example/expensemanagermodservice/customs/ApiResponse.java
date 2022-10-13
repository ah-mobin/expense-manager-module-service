package com.example.expensemanagermodservice.customs;

import java.util.ArrayList;
import java.util.List;

public class ApiResponse {
    private String message;
    private List<String> errors = new ArrayList<>();
    private Object data;
    public ApiResponse(String message, List<String> errors, Object data) {
        this.message = message;
        this.errors = errors;
        this.data = data;
    }
    public ApiResponse(String message, List<String> errors) {
        this.message = message;
        this.errors = errors;
    }
    public ApiResponse(String message, Object data) {
        this.message = message;
        this.data = data;
    }
    public ApiResponse (String message){
        this.message = message;
    }
    public String getMessage() {
        return message;
    }
    public List<String> getErrors() {
        return errors;
    }
    public Object getData() {
        return data;
    }
}
