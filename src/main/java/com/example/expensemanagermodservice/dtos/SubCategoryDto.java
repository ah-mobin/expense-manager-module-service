package com.example.expensemanagermodservice.dtos;

import com.example.expensemanagermodservice.entities.CategoryEntity;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class SubCategoryDto {
    private Long id;
    private Long category_id;
    private CategoryEntity category;
    private String name;
}
