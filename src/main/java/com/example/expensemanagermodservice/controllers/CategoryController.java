package com.example.expensemanagermodservice.controllers;

import com.example.expensemanagermodservice.customs.CategoryIdExists;
import com.example.expensemanagermodservice.models.requests.CategoryRequestModel;
import com.example.expensemanagermodservice.models.responses.CategoryResponseModel;
import com.example.expensemanagermodservice.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;

@RestController
@RequestMapping("categories")
@Validated
public class CategoryController {

    @Autowired private CategoryService categoryService;


    @GetMapping()
    public List<ResponseEntity<CategoryResponseModel>> index(){
        return null;
    }

    @PostMapping
    public ResponseEntity<CategoryResponseModel> store(@RequestBody @Valid CategoryRequestModel categoryRequest){
        CategoryResponseModel categoryResponseModel = categoryService.store(categoryRequest);
        return new ResponseEntity<>(categoryResponseModel, HttpStatus.OK);
    }

    @GetMapping("{uuid}")
    public ResponseEntity<CategoryResponseModel> show(@PathVariable @CategoryIdExists String uuid){
        return new ResponseEntity<>(categoryService.show(uuid), HttpStatus.OK);
    }


    @PutMapping("{uuid}")
    public ResponseEntity<CategoryResponseModel> update(@PathVariable @CategoryIdExists String uuid, @RequestBody CategoryRequestModel categoryRequest){
        CategoryResponseModel categoryResponseModel = categoryService.update(uuid, categoryRequest);
        return new ResponseEntity<>(categoryResponseModel, HttpStatus.OK);
    }
}
