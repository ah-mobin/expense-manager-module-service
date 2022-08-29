package com.example.expensemanagermodservice.dtos;

import com.example.expensemanagermodservice.entities.CategoryEntity;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class SubCategoryDto {
    private Long id;
    private String category_slug;
    private CategoryEntity category;
    private String name;
    private String slug;
}
