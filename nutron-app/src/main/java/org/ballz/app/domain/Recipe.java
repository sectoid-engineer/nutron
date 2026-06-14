package org.ballz.app.domain;

import jakarta.annotation.Nonnull;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

/**
 * Combination of Ingredients ready to be eaten.
 * @param id
 * @param name
 * @param note
 * @param ingredientsQuantities kinds of ingredients and amount of each in tenths of grams
 */
public record Recipe(
    @Nonnull UUID id,
    @Nonnull String name,
    String note,
    Map<Ingredient, Integer> ingredientsQuantities,
    Macros subtotals,
    Map<String, Integer> subtotalMicronutrients,
    Map<String, Integer> subtotalOtherValues,
    int subtotalCost,
    Currency currency,
    Set<Tag> tags
) {
}
