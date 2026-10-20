package com.pantrypal.ai.dto;

import java.util.List;

public record RecipeResponse(
        String recipeName,
        String description,
        Integer prepTimeMinutes,
        Integer cookTimeMinutes,
        Integer servings,
        Integer estimatedCaloriesPerServing,
        Double ingredientMatchPercentage,
        List<String> requiredIngredients,
        List<String> missingIngredients,
        List<String> instructions,
        List<String> chefTips
) {}