package com.example.expensemanagermodservice.models.responses;

import com.example.expensemanagermodservice.entities.SubCategoryEntity;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CategoryResponseModel {
    private String slug;
    private String name;
    private List<SubCategoryOnCategoryResponse> subCategories;
}
