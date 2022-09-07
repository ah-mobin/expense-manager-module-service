package com.example.expensemanagermodservice.dtos;

import com.example.expensemanagermodservice.entities.SubCategoryEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class CategoryDto {
    private Long id;
    private String name;
    private String slug;
    private List<SubCategoryEntity> subCategories;
}
