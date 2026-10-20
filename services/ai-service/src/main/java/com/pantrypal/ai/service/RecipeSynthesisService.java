package com.pantrypal.ai.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pantrypal.ai.dto.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RecipeSynthesisService {

    private final GeminiClientService geminiClient;
    private final ObjectMapper objectMapper;

    public RecipeResponse generateRecipe(RecipeRequest request) {
        String prompt = """
            You are an expert executive chef. Create an optimized recipe maximizing the use of these available ingredients:
            Available Ingredients: %s
            Dietary Preferences/Restrictions: %s
            Target Meal Type: %s
            Target Calories per serving: %s

            You must return a valid JSON object matching this schema:
            {
              "recipeName": "string",
              "description": "string",
              "prepTimeMinutes": integer,
              "cookTimeMinutes": integer,
              "servings": integer,
              "estimatedCaloriesPerServing": integer,
              "ingredientMatchPercentage": double (0.0 to 100.0),
              "requiredIngredients": ["string"],
              "missingIngredients": ["string"],
              "instructions": ["string"],
              "chefTips": ["string"]
            }
            Do not include markdown code block formatting (like ```json), output raw JSON only.
            """.formatted(
                request.ingredients(),
                request.dietaryPreferences() == null ? "None" : request.dietaryPreferences(),
                request.mealType() == null ? "Any" : request.mealType(),
                request.targetCalories() == null ? "Any" : request.targetCalories()
        );

        try {
            String jsonOutput = geminiClient.callGemini(prompt);
            return objectMapper.readValue(jsonOutput, RecipeResponse.class);
        } catch (Exception e) {
            throw new RuntimeException("Failed to parse recipe response: " + e.getMessage(), e);
        }
    }

    public SubstituteResponse getSubstitutes(SubstituteRequest request) {
        String prompt = """
            Provide practical culinary substitutions for the following missing ingredient:
            Missing Ingredient: %s
            Target Recipe Context: %s
            User's Other Available Pantry Items: %s

            Return a valid JSON object matching this schema:
            {
              "missingIngredient": "%s",
              "options": [
                {
                  "substituteName": "string",
                  "ratio": "e.g., 1:1 or 1/2 cup applesauce per 1 egg",
                  "impactOnFlavorOrTexture": "string",
                  "availableInPantry": boolean
                }
              ]
            }
            Do not include markdown code block formatting, output raw JSON only.
            """.formatted(
                request.missingIngredient(),
                request.targetRecipe() == null ? "General Cooking" : request.targetRecipe(),
                request.availablePantryItems() == null ? "None provided" : request.availablePantryItems(),
                request.missingIngredient()
        );

        try {
            String jsonOutput = geminiClient.callGemini(prompt);
            return objectMapper.readValue(jsonOutput, SubstituteResponse.class);
        } catch (Exception e) {
            throw new RuntimeException("Failed to parse substitute response: " + e.getMessage(), e);
        }
    }
}