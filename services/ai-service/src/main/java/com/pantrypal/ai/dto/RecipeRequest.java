package com.pantrypal.ai.dto;

import jakarta.validation.constraints.NotEmpty;
import java.util.List;

public record RecipeRequest(
        @NotEmpty(message = "Available ingredients list cannot be empty")
        List<String> ingredients,
        List<String> dietaryPreferences,
        String mealType,
        Integer targetCalories
) {}