package com.example.expensemanagermodservice.dtos;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class CategoryDto {
    private Long id;
    private String name;
    private String slug;
}
