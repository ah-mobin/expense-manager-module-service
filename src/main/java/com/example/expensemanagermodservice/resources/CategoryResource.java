package com.example.expensemanagermodservice.resources;

import com.example.expensemanagermodservice.dtos.CategoryDto;
import com.example.expensemanagermodservice.handlers.CannotDeleteEntityException;
import com.example.expensemanagermodservice.handlers.NotFoundEntityException;
import com.example.expensemanagermodservice.models.requests.CategoryRequestModel;
import com.example.expensemanagermodservice.models.responses.CategoryResponseModel;
import com.example.expensemanagermodservice.services.CategoryService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("categories")
public class CategoryResource {

    @Autowired
    CategoryService categoryService;

    @GetMapping
    public ResponseEntity<List<CategoryResponseModel>> index() {
        return ResponseEntity.ok(categoryService.getCategories());
    }

    @GetMapping("/{slug}")
    public ResponseEntity<CategoryResponseModel> show(@PathVariable String slug) throws NotFoundEntityException {
        CategoryResponseModel categoryResponse = new CategoryResponseModel();
        CategoryDto category = categoryService.showCategory(slug);
        BeanUtils.copyProperties(category, categoryResponse);
        return ResponseEntity.ok(categoryResponse);
    }

    @PostMapping
    public ResponseEntity<CategoryResponseModel> store(@RequestBody @Valid CategoryRequestModel categoryRequest){
        CategoryResponseModel responseModel = new CategoryResponseModel();
        CategoryDto categoryDto = new CategoryDto();
        BeanUtils.copyProperties(categoryRequest, categoryDto);
        CategoryDto createCategory = categoryService.createCategory(categoryDto);
        BeanUtils.copyProperties(createCategory, responseModel);
        return new ResponseEntity<>(responseModel, HttpStatus.CREATED);
    }

    @PutMapping("/{slug}")
    public ResponseEntity<CategoryResponseModel> update(@RequestBody @Valid CategoryRequestModel categoryRequest, @PathVariable String slug){
        CategoryResponseModel responseModel = new CategoryResponseModel();
        CategoryDto categoryDto = new CategoryDto();
        BeanUtils.copyProperties(categoryRequest, categoryDto);
        categoryDto.setSlug(slug);
        CategoryDto updateCategory = categoryService.updateCategory(categoryDto);
        BeanUtils.copyProperties(updateCategory, responseModel);
        return new ResponseEntity<>(responseModel, HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/{slug}")
    public Boolean delete(@PathVariable String slug) throws NotFoundEntityException, CannotDeleteEntityException {
        return categoryService.deleteCategory(slug);
    }
}
