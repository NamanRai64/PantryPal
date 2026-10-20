package com.pantrypal.inventory.controller;

import com.pantrypal.inventory.dto.InventoryItemRequest;
import com.pantrypal.inventory.entity.InventoryItem;
import com.pantrypal.inventory.service.InventoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/inventory")
@RequiredArgsConstructor
public class InventoryController {

    private final InventoryService inventoryService;

    @GetMapping("/health")
    public ResponseEntity<Map<String, String>> health() {
        return ResponseEntity.ok(Map.of("service", "inventory-service", "status", "UP"));
    }

    @PostMapping
    public ResponseEntity<InventoryItem> createItem(@Valid @RequestBody InventoryItemRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(inventoryService.addItem(request));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<InventoryItem>> getUserInventory(@PathVariable Long userId) {
        return ResponseEntity.ok(inventoryService.getItemsByUserId(userId));
    }

    @GetMapping("/user/{userId}/expiring")
    public ResponseEntity<List<InventoryItem>> getExpiringItems(
            @PathVariable Long userId,
            @RequestParam(defaultValue = "2") int days) {
        return ResponseEntity.ok(inventoryService.getExpiringSoonItems(userId, days));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removeItem(@PathVariable Long id) {
        inventoryService.deleteItem(id);
        return ResponseEntity.noContent().build();
    }
}