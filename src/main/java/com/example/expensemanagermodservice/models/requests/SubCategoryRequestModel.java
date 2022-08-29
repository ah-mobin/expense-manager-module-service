package com.example.expensemanagermodservice.models.requests;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class SubCategoryRequestModel {
    private Long category_id;
    private String name;
}
