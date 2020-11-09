package com.monolithmind.task.core.repository;

import com.monolithmind.task.core.model.Purchase;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PurchaseRepository extends JpaRepository<Purchase, String> {
    Optional<Purchase> findById(String id);
}
