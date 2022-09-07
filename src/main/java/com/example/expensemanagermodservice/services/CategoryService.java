package com.example.expensemanagermodservice.services;

import com.example.expensemanagermodservice.dtos.CategoryDto;
import com.example.expensemanagermodservice.entities.CategoryEntity;
import com.example.expensemanagermodservice.handlers.CannotDeleteEntityException;
import com.example.expensemanagermodservice.handlers.DataAlreadyExistException;
import com.example.expensemanagermodservice.handlers.NotFoundEntityException;
import com.example.expensemanagermodservice.models.requests.CategoryRequestModel;
import com.example.expensemanagermodservice.models.responses.CategoryResponseModel;
import com.example.expensemanagermodservice.models.responses.SubCategoryOnCategoryResponse;
import com.example.expensemanagermodservice.repositories.CategoryRepository;
import com.example.expensemanagermodservice.repositories.SubCategoryRepository;
import com.example.expensemanagermodservice.services.shares.CategoryPropertyShare;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.text.Normalizer;
import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Service
public class CategoryService {

    @Autowired
    CategoryRepository categoryRepository;
    @Autowired
    SubCategoryRepository subCategoryRepository;
    @Autowired
    private CategoryPropertyShare categoryPropertyShare;

    private static final Pattern NONLATIN = Pattern.compile("[^\\w-]");
    private static final Pattern WHITESPACE = Pattern.compile("[\\s]");


    public List<CategoryResponseModel> getCategories() {
        return findAllCategories().stream().map(this::categoryResponse).collect(Collectors.toList());
    }

    private List<CategoryDto> findAllCategories(){
        return categoryRepository.findAll().stream().map(this::categoryDto).collect(Collectors.toList());
    }

    private CategoryDto categoryDto(CategoryEntity categoryEntity){
        CategoryDto categoryDto = new CategoryDto();
        BeanUtils.copyProperties(categoryEntity, categoryDto);
        return categoryDto;
    }

    private CategoryResponseModel categoryResponse(CategoryDto categoryDto){
        CategoryResponseModel categoryResponseModel = new CategoryResponseModel();
        BeanUtils.copyProperties(categoryDto, categoryResponseModel);

        List<SubCategoryOnCategoryResponse> subCategoryResponse = new ArrayList<>();
        categoryDto.getSubCategories().forEach(subCategoryEntity -> {
            SubCategoryOnCategoryResponse subCategoryOnCategoryResponse = new SubCategoryOnCategoryResponse();
            subCategoryOnCategoryResponse.setName(categoryDto.getName());
            subCategoryOnCategoryResponse.setSlug(categoryDto.getSlug());
            subCategoryResponse.add(subCategoryOnCategoryResponse);
        });

        categoryResponseModel.setSubCategories(subCategoryResponse);

        return categoryResponseModel;
    }

    public CategoryDto showCategory(String categorySlug) throws NotFoundEntityException {
        CategoryDto categoryDto = new CategoryDto();
        Optional<CategoryEntity> categoryOptional = categoryRepository.findBySlug(categorySlug);

        categoryOptional.ifPresentOrElse((category) ->  {
            BeanUtils.copyProperties(category, categoryDto);
        },()-> { throw new NotFoundEntityException("Category Not Found On This Slug");});
        return categoryDto;
    }


    public CategoryDto createCategory(CategoryRequestModel categoryRequest) throws DataAlreadyExistException {
        CategoryDto categoryDto = categoryPropertyShare.requestToDto(categoryRequest);
        String categorySlug = toSlug(categoryDto.getName());
        Optional<CategoryEntity> categoryExistence = categoryRepository.findBySlug(categorySlug);

        if(categoryExistence.isPresent()){
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

    public CategoryDto updateCategory(CategoryDto categoryDto) throws NotFoundEntityException, DataAlreadyExistException{

        Optional<CategoryEntity> categoryOptional = categoryRepository.findBySlug(categoryDto.getSlug());
        String newSlug = toSlug(categoryDto.getName());
        categoryOptional.ifPresentOrElse((category) ->  {
            Optional<CategoryEntity> categoryExistence = categoryRepository.findBySlug(newSlug);

            if(categoryExistence.isPresent()){
                if(!Objects.equals(categoryOptional.get().getId(), categoryExistence.get().getId())){
                    throw new DataAlreadyExistException("Category Already Exist");
                }
            }
        },()-> { throw new NotFoundEntityException("Category Not Found On This Slug");});

        categoryDto.setId(categoryOptional.get().getId());
        categoryDto.setSlug(newSlug);
        BeanUtils.copyProperties(categoryDto, categoryOptional.get());
        CategoryEntity categorySaved = categoryRepository.save(categoryOptional.get());
        CategoryDto returnedCategory = new CategoryDto();
        BeanUtils.copyProperties(categorySaved, returnedCategory);
        return returnedCategory;
    }

    @Transactional
    public boolean deleteCategory(String slug) throws NotFoundEntityException, CannotDeleteEntityException{

        Optional<CategoryEntity> categoryOptional = categoryRepository.findBySlug(slug);
        categoryOptional.ifPresentOrElse((category) ->  {
                subCategoryRepository.deleteByCategoryId(category.getId());
        },()-> { throw new NotFoundEntityException("Category Not Found On This Slug");});

        categoryRepository.delete(categoryOptional.get());

        return true;
    }

    private static String toSlug(String name) {
        String nowhitespace = WHITESPACE.matcher(name).replaceAll("-");
        String normalized = Normalizer.normalize(nowhitespace, Normalizer.Form.NFD);
        String slug = NONLATIN.matcher(normalized).replaceAll("");
        return slug.toLowerCase(Locale.ENGLISH);
    }
}
