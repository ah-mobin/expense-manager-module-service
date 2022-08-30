package com.example.expensemanagermodservice.models.requests;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class CategoryRequestModel {
    @NotNull(message = "Category Name Is Required")
    @Length(max = 64, min = 1)
    private String name;
}
