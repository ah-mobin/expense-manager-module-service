package com.example.expensemanagermodservice.resources;


import com.example.expensemanagermodservice.dtos.CategoryDto;
import com.example.expensemanagermodservice.entities.CategoryEntity;
import com.example.expensemanagermodservice.models.requests.CategoryRequestModel;
import com.example.expensemanagermodservice.models.responses.CategoryResponseModel;
import com.example.expensemanagermodservice.services.CategoryService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("categories")
public class CategoryResource {

    @Autowired
    CategoryService categoryService;

    @GetMapping
    public List<CategoryEntity> index(){
        return null;
    }


    @PostMapping
    public CategoryResponseModel store(@RequestBody CategoryRequestModel categoryRequest){
        CategoryResponseModel responseModel = new CategoryResponseModel();
        CategoryDto categoryDto = new CategoryDto();
        BeanUtils.copyProperties(categoryRequest, categoryDto);
        CategoryDto createCategory = categoryService.createCategory(categoryDto);
        BeanUtils.copyProperties(createCategory, responseModel);
        return responseModel;
    }

}
