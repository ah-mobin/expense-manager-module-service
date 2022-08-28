package com.example.expensemanagermodservice.repositories;

import com.example.expensemanagermodservice.entities.CurrencyEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface CurrencyRepository extends JpaRepository<CurrencyEntity, UUID> {

    public Optional<CurrencyEntity> findByUUID(UUID uuid);
}
