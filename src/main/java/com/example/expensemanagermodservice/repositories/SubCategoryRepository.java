package com.example.expensemanagermodservice.repositories;

import com.example.expensemanagermodservice.entities.SubCategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubCategoryRepository extends JpaRepository<SubCategoryEntity, Long> {
}
