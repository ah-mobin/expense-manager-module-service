package com.example.expensemanagermodservice.controllers;

import com.example.expensemanagermodservice.dtos.SubCategoryDto;
import com.example.expensemanagermodservice.models.requests.SubCategoryRequestModel;
import com.example.expensemanagermodservice.models.responses.SubCategoryResponseModel;
import com.example.expensemanagermodservice.services.SubCategoryService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("subcategories")
public class SubCategoryResource {

    @Autowired
    SubCategoryService subCategoryService;

    @GetMapping
    public ResponseEntity<List<SubCategoryResponseModel>> index() {
        return ResponseEntity.ok(subCategoryService.subCategoryResponseList());
    }

    @PostMapping("/{categorySlug}")
    public ResponseEntity<SubCategoryResponseModel> create(@RequestBody SubCategoryRequestModel subCategoryRequest, @PathVariable String categorySlug){
        SubCategoryResponseModel subCategoryResponseModel = new SubCategoryResponseModel();
        SubCategoryDto subCategoryDto = new SubCategoryDto();
        subCategoryDto.setCategory_slug(categorySlug);
        BeanUtils.copyProperties(subCategoryRequest, subCategoryDto);
        SubCategoryDto returningDto = subCategoryService.createSubCategory(subCategoryDto);
        BeanUtils.copyProperties(returningDto, subCategoryResponseModel);
        return new ResponseEntity<>(subCategoryResponseModel, HttpStatus.CREATED);
    }

}
