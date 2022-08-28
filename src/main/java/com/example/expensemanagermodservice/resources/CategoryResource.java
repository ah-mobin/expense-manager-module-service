package com.example.expensemanagermodservice.resources;


import com.example.expensemanagermodservice.entities.CategoryEntity;
import com.example.expensemanagermodservice.models.responses.CategoryResponseModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("categories")
public class CategoryResource {

    @GetMapping
    public List<CategoryEntity> index(){
        return null;
    }


    @PostMapping
    public CategoryResponseModel store(){
        return null;
    }

}
