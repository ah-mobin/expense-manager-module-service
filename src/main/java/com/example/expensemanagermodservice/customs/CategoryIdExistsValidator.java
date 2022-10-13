package com.example.expensemanagermodservice.customs;

import com.example.expensemanagermodservice.repositories.CategoryRepository;
import lombok.RequiredArgsConstructor;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.UUID;

@RequiredArgsConstructor
public class CategoryIdExistsValidator implements ConstraintValidator<CategoryIdExists, String> {

    private final CategoryRepository categoryRepository;
    @Override
    public void initialize(CategoryIdExists constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String categoryId, ConstraintValidatorContext constraintValidatorContext) {
        return categoryRepository.existsById(categoryId);
    }
}
