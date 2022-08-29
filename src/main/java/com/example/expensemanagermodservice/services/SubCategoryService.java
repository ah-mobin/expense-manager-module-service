package com.example.expensemanagermodservice.services;

import com.example.expensemanagermodservice.dtos.SubCategoryDto;
import com.example.expensemanagermodservice.entities.CategoryEntity;
import com.example.expensemanagermodservice.entities.SubCategoryEntity;
import com.example.expensemanagermodservice.repositories.CategoryRepository;
import com.example.expensemanagermodservice.repositories.SubCategoryRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SubCategoryService {

    @Autowired
    SubCategoryRepository subCategoryRepository;
    @Autowired
    CategoryRepository categoryRepository;

    public SubCategoryDto createSubCategory(SubCategoryDto subCategoryDto){
        SubCategoryEntity subCategoryEntity = new SubCategoryEntity();
        System.out.println("category id"+subCategoryDto.getCategory_id());
        CategoryEntity category = categoryRepository.findById(subCategoryDto.getCategory_id()).get();
        System.out.println(category);
        subCategoryDto.setCategory(category);
        BeanUtils.copyProperties(subCategoryDto, subCategoryEntity);
        SubCategoryEntity created = subCategoryRepository.save(subCategoryEntity);
        SubCategoryDto returned = new SubCategoryDto();
        BeanUtils.copyProperties(created, returned);
        return returned;
    }



}
