package com.example.expensemanagermodservice.repositories;

import com.example.expensemanagermodservice.entities.SubCategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SubCategoryRepository extends JpaRepository<SubCategoryEntity, Long> {
    Optional<SubCategoryEntity> findById(Long id);
}
