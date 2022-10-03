package com.example.expensemanagermodservice.repositories;

import com.example.expensemanagermodservice.entities.SubCategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SubCategoryRepository extends JpaRepository<SubCategoryEntity, Long> {
//    public List<SubCategoryEntity> findByCategoryId(Long categoryId);
//    public void deleteByCategoryId(Long categoryId);
}
