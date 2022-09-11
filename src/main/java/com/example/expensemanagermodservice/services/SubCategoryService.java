package com.example.expensemanagermodservice.services;

import com.example.expensemanagermodservice.dtos.SubCategoryDto;
import com.example.expensemanagermodservice.entities.CategoryEntity;
import com.example.expensemanagermodservice.entities.SubCategoryEntity;
import com.example.expensemanagermodservice.handlers.NotFoundEntityException;
import com.example.expensemanagermodservice.models.responses.SubCategoryResponseModel;
import com.example.expensemanagermodservice.repositories.CategoryRepository;
import com.example.expensemanagermodservice.repositories.SubCategoryRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.Normalizer;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Service
public class SubCategoryService {

    @Autowired
    SubCategoryRepository subCategoryRepository;
    @Autowired
    CategoryRepository categoryRepository;

    private static final Pattern NONLATIN = Pattern.compile("[^\\w-]");
    private static final Pattern WHITESPACE = Pattern.compile("[\\s]");

    public List<SubCategoryResponseModel> subCategoryResponseList(){
        return subcategoryEntities().stream().map(this::subcategoryResponse).collect(Collectors.toList());
    }

    private SubCategoryResponseModel subcategoryResponse(SubCategoryDto subCategoryDto){
        SubCategoryResponseModel subCategoryResponseModel = new SubCategoryResponseModel();
        BeanUtils.copyProperties(subCategoryDto, subCategoryResponseModel);
        return subCategoryResponseModel;
    }

    private List<SubCategoryDto> subcategoryEntities(){
        return subCategoryRepository.findAll().stream().map(this::subcategoryEntityToDto).collect(Collectors.toList());
    }

    private SubCategoryDto subcategoryEntityToDto(SubCategoryEntity subCategoryEntity){
        SubCategoryDto subCategoryDto = new SubCategoryDto();
        BeanUtils.copyProperties(subCategoryEntity, subCategoryDto);
        return subCategoryDto;
    }



    public SubCategoryDto createSubCategory(SubCategoryDto subCategoryDto){
        SubCategoryEntity subCategoryEntity = new SubCategoryEntity();
        subCategoryDto.setSlug(toSlug(subCategoryDto.getName()));
        Optional<CategoryEntity> categoryOptional = categoryRepository.findBySlug(subCategoryDto.getCategory_slug());
        categoryOptional.ifPresentOrElse((category)-> {
            subCategoryDto.setCategory(category);
            BeanUtils.copyProperties(subCategoryDto, subCategoryEntity);
        }, ()-> { throw new NotFoundEntityException("Category Not Found On This Slug");});
        SubCategoryEntity created = subCategoryRepository.save(subCategoryEntity);
        SubCategoryDto returned = new SubCategoryDto();
        BeanUtils.copyProperties(created, returned);
        return returned;
    }

    private static String toSlug(String name) {
        String nowhitespace = WHITESPACE.matcher(name).replaceAll("-");
        String normalized = Normalizer.normalize(nowhitespace, Normalizer.Form.NFD);
        String slug = NONLATIN.matcher(normalized).replaceAll("");
        return slug.toLowerCase(Locale.ENGLISH);
    }



}
