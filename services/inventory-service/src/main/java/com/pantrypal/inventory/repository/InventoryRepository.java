package com.pantrypal.inventory.repository;

import com.pantrypal.inventory.entity.InventoryItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface InventoryRepository extends JpaRepository<InventoryItem, Long> {
    List<InventoryItem> findByUserId(Long userId);
    List<InventoryItem> findByUserIdAndExpirationDateLessThanEqual(Long userId, LocalDate dateThreshold);
}