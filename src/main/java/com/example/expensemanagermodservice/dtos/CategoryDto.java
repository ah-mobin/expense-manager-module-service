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
    private String uuid;
    private String name;
    private List<SubCategoryEntity> subCategories;
}
