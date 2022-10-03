package com.example.expensemanagermodservice.dtos;

import com.example.expensemanagermodservice.entities.SubCategoryEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
public class CategoryDto {
    private UUID id;
    private String name;
    private List<SubCategoryEntity> subCategories;
}
