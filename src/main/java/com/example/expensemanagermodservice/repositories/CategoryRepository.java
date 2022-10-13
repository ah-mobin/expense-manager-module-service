package com.example.expensemanagermodservice.repositories;

import com.example.expensemanagermodservice.entities.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface CategoryRepository extends JpaRepository<CategoryEntity, String> {
    Optional<CategoryEntity> findByUuid(String uuid);
}
