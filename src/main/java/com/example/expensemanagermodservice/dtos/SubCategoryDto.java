package com.example.expensemanagermodservice.dtos;

import com.example.expensemanagermodservice.entities.CategoryEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class SubCategoryDto {
    private Long id;
    private String category_slug;

    @JsonIgnore
    private CategoryEntity category;
    private String name;
    private String slug;
}
