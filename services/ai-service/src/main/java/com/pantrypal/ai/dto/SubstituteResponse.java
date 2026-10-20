package com.pantrypal.ai.dto;

import java.util.List;

public record SubstituteResponse(
        String missingIngredient,
        List<SubstitutionOption> options
) {
    public record SubstitutionOption(
            String substituteName,
            String ratio,
            String impactOnFlavorOrTexture,
            Boolean availableInPantry
    ) {}
}