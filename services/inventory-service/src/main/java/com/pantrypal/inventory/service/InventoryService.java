package com.pantrypal.inventory.service;

import com.pantrypal.inventory.dto.InventoryItemRequest;
import com.pantrypal.inventory.entity.InventoryItem;
import com.pantrypal.inventory.repository.InventoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class InventoryService {

    private final InventoryRepository inventoryRepository;

    public InventoryItem addItem(InventoryItemRequest request) {
        InventoryItem item = InventoryItem.builder()
                .userId(request.userId())
                .ingredientName(request.ingredientName())
                .category(request.category())
                .quantity(request.quantity())
                .unit(request.unit())
                .expirationDate(request.expirationDate())
                .build();
        return inventoryRepository.save(item);
    }

    public List<InventoryItem> getItemsByUserId(Long userId) {
        return inventoryRepository.findByUserId(userId);
    }

    public List<InventoryItem> getExpiringSoonItems(Long userId, int withinDays) {
        LocalDate threshold = LocalDate.now().plusDays(withinDays);
        return inventoryRepository.findByUserIdAndExpirationDateLessThanEqual(userId, threshold);
    }

    public void deleteItem(Long itemId) {
        inventoryRepository.deleteById(itemId);
    }
}