package com.example.expensemanagermodservice.services;

import com.example.expensemanagermodservice.dtos.CategoryDto;
import com.example.expensemanagermodservice.entities.CategoryEntity;
import com.example.expensemanagermodservice.handlers.DataAlreadyExistException;
import com.example.expensemanagermodservice.handlers.NotFoundEntityException;
import com.example.expensemanagermodservice.repositories.CategoryRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.Normalizer;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class CategoryService {

    @Autowired
    CategoryRepository categoryRepository;

    private static final Pattern NONLATIN = Pattern.compile("[^\\w-]");
    private static final Pattern WHITESPACE = Pattern.compile("[\\s]");


    public List<CategoryDto> index(){
        return categoryRepository.findAll().stream().map(this::categoryDto).collect(Collectors.toList());
    }

    private CategoryDto categoryDto(CategoryEntity categoryEntity){
        CategoryDto categoryDto = new CategoryDto();
        BeanUtils.copyProperties(categoryEntity, categoryDto);
        return categoryDto;
    }

    public CategoryDto showCategory(String categorySlug) throws NotFoundEntityException {
        CategoryDto categoryDto = new CategoryDto();
        Optional<CategoryEntity> categoryOptional = categoryRepository.findBySlug(categorySlug);

        categoryOptional.ifPresentOrElse((category) ->  {
            BeanUtils.copyProperties(category, categoryDto);
        },()-> { throw new NotFoundEntityException("Category Not Found On This Slug");});
        return categoryDto;
    }


    public CategoryDto createCategory(CategoryDto categoryDto) throws DataAlreadyExistException {
        String categorySlug = toSlug(categoryDto.getName());

        if(checkCategoryExists(categorySlug)){
            throw new DataAlreadyExistException("Category Already Exist");
        }

        CategoryEntity categoryEntity = new CategoryEntity();
        categoryDto.setSlug(categorySlug);
        BeanUtils.copyProperties(categoryDto, categoryEntity);

        CategoryEntity categorySaved = categoryRepository.save(categoryEntity);
        CategoryDto returnedCategory = new CategoryDto();
        BeanUtils.copyProperties(categorySaved, returnedCategory);
        return returnedCategory;
    }

    private static String toSlug(String name) {
        String nowhitespace = WHITESPACE.matcher(name).replaceAll("-");
        String normalized = Normalizer.normalize(nowhitespace, Normalizer.Form.NFD);
        String slug = NONLATIN.matcher(normalized).replaceAll("");
        return slug.toLowerCase(Locale.ENGLISH);
    }

    private Boolean checkCategoryExists(String categorySlug){
        Optional<CategoryEntity> categoryExistence = categoryRepository.findBySlug(categorySlug);
        return categoryExistence.isPresent();
    }
}
