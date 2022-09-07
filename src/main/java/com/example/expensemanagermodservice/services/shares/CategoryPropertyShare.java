package com.example.expensemanagermodservice.services.shares;

import com.example.expensemanagermodservice.dtos.CategoryDto;
import com.example.expensemanagermodservice.models.requests.CategoryRequestModel;
import com.example.expensemanagermodservice.models.responses.CategoryResponseModel;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class CategoryPropertyShare {

    public CategoryDto requestToDto(CategoryRequestModel categoryRequest){
        CategoryDto categoryDto = new CategoryDto();
        BeanUtils.copyProperties(categoryRequest, categoryDto);
        return categoryDto;
    }


    public CategoryResponseModel dtoToResponse(CategoryDto categoryDto){
        CategoryResponseModel responseModel = new CategoryResponseModel();
        BeanUtils.copyProperties(categoryDto, responseModel);
        return responseModel;
    }

}
