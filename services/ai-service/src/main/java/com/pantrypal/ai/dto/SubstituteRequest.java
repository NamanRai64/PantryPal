package com.pantrypal.ai.dto;

import jakarta.validation.constraints.NotBlank;
import java.util.List;

public record SubstituteRequest(
        @NotBlank(message = "Missing ingredient is required")
        String missingIngredient,
        String targetRecipe,
        List<String> availablePantryItems
) {}