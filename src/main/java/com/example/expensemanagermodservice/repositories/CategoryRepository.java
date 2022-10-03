package com.example.expensemanagermodservice.repositories;

import com.example.expensemanagermodservice.entities.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategoryRepository extends JpaRepository<CategoryEntity, Long> {
//    Optional<CategoryEntity> findById(Long id);
//    Optional<CategoryEntity> findBySlug(String slug);
//    Optional<CategoryEntity> findByIdAndSlug(Long id, String slug);
}
