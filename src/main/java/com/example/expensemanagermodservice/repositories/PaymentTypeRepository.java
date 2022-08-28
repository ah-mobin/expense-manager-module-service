package com.example.expensemanagermodservice.repositories;

import com.example.expensemanagermodservice.entities.PaymentTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface PaymentTypeRepository extends JpaRepository<PaymentTypeEntity, UUID> {

    public Optional<PaymentTypeEntity> findByUUID(UUID uuid);

}
