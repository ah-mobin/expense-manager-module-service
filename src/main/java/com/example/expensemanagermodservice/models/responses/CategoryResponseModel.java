package com.example.expensemanagermodservice.models.responses;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class CategoryResponseModel {
    private UUID uuid;
    private String name;
}
