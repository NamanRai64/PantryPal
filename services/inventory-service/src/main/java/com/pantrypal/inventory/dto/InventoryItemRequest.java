package com.pantrypal.inventory.dto;

import com.pantrypal.inventory.entity.Category;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDate;

public record InventoryItemRequest(
        @NotNull(message = "User ID is required")
        Long userId,

        @NotBlank(message = "Ingredient name is required")
        String ingredientName,

        @NotNull(message = "Category is required")
        Category category,

        @NotNull(message = "Quantity is required")
        @DecimalMin(value = "0.01", message = "Quantity must be greater than zero")
        BigDecimal quantity,

        @NotBlank(message = "Unit is required")
        String unit,

        LocalDate expirationDate
) {}