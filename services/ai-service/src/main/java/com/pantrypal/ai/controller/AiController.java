package com.pantrypal.ai.controller;

import com.pantrypal.ai.dto.*;
import com.pantrypal.ai.service.RecipeSynthesisService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/ai")
@RequiredArgsConstructor
public class AiController {

    private final RecipeSynthesisService recipeService;

    @GetMapping("/health")
    public ResponseEntity<Map<String, String>> health() {
        return ResponseEntity.ok(Map.of("service", "ai-service", "status", "UP"));
    }

    @PostMapping("/recipes/generate")
    public ResponseEntity<RecipeResponse> generateRecipe(@Valid @RequestBody RecipeRequest request) {
        return ResponseEntity.ok(recipeService.generateRecipe(request));
    }

    @PostMapping("/substitutes")
    public ResponseEntity<SubstituteResponse> getSubstitutes(@Valid @RequestBody SubstituteRequest request) {
        return ResponseEntity.ok(recipeService.getSubstitutes(request));
    }
}