package com.example.expensemanagermodservice.resources;

import com.example.expensemanagermodservice.dtos.SubCategoryDto;
import com.example.expensemanagermodservice.models.requests.SubCategoryRequestModel;
import com.example.expensemanagermodservice.models.responses.SubCategoryResponseModel;
import com.example.expensemanagermodservice.services.SubCategoryService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("subcategories")
public class SubCategoryResource {

    @Autowired
    SubCategoryService subCategoryService;

    @PostMapping
    public SubCategoryResponseModel create(@RequestBody SubCategoryRequestModel subCategoryRequest){
        SubCategoryResponseModel subCategoryResponseModel = new SubCategoryResponseModel();
        SubCategoryDto subCategoryDto = new SubCategoryDto();
        BeanUtils.copyProperties(subCategoryRequest, subCategoryDto);
        SubCategoryDto returningDto = subCategoryService.createSubCategory(subCategoryDto);
        BeanUtils.copyProperties(returningDto, subCategoryResponseModel);
        return subCategoryResponseModel;
    }

}
