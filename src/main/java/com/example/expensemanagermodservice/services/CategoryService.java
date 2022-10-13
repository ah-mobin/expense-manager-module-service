package com.example.expensemanagermodservice.services;

import com.example.expensemanagermodservice.dtos.CategoryDto;
import com.example.expensemanagermodservice.entities.CategoryEntity;
import com.example.expensemanagermodservice.models.requests.CategoryRequestModel;
import com.example.expensemanagermodservice.models.responses.CategoryResponseModel;
import com.example.expensemanagermodservice.repositories.CategoryRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepo;

//    public List<CategoryResponseModel> categories(){
//        List<CategoryEntity> categoryEntities = categoryRepo.findAll();
//
//    }

    public CategoryResponseModel store(CategoryRequestModel categoryRequest) {
        CategoryEntity category = new CategoryEntity();
        BeanUtils.copyProperties(categoryRequest, category);
        CategoryEntity savedCategory = categoryRepo.save(category);
        CategoryResponseModel categoryResponse = new CategoryResponseModel();
        BeanUtils.copyProperties(savedCategory, categoryResponse);
        return categoryResponse;
    }

    public CategoryResponseModel show(String categoryId){
        CategoryResponseModel categoryResponseModel = new CategoryResponseModel();
        Optional<CategoryEntity> category = categoryRepo.findById(categoryId);
        if (category.isPresent()){
            BeanUtils.copyProperties(category.get(), categoryResponseModel);
            return categoryResponseModel;
        }
        return null;
    }


    public CategoryResponseModel update(String uuid, CategoryRequestModel categoryRequest){
        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setName(categoryRequest.getName());
        categoryDto.setUuid(uuid);
        CategoryEntity category = new CategoryEntity();
        BeanUtils.copyProperties(categoryDto, category);
        CategoryEntity updatedCategory = categoryRepo.save(category);
        CategoryResponseModel categoryResponse = new CategoryResponseModel();
        BeanUtils.copyProperties(updatedCategory, categoryResponse);
        return categoryResponse;
    }
}
