package com.example.expensemanagermodservice.services;

import com.example.expensemanagermodservice.dtos.CategoryDto;
import com.example.expensemanagermodservice.entities.CategoryEntity;
import com.example.expensemanagermodservice.repositories.CategoryRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class CategoryService {

    @Autowired
    CategoryRepository categoryRepository;

    public CategoryDto createCategory(CategoryDto categoryDto){
        CategoryEntity categoryEntity = new CategoryEntity();
        BeanUtils.copyProperties(categoryDto, categoryEntity);
        CategoryEntity categorySaved = categoryRepository.save(categoryEntity);
        CategoryDto returnedCategory = new CategoryDto();
        BeanUtils.copyProperties(categorySaved, returnedCategory);
        return returnedCategory;
    }

}
