package org.ballz.app.domain;

import com.fasterxml.jackson.annotation.JsonInclude;

import jakarta.annotation.Nonnull;
import java.util.Map;
import java.util.UUID;

/**
 * Basic Ingredient representation. All values are provided specific to 100 g portion.
 * @param micronutrients add individual micronutrientes of interest here (sodium, cholesterol, vitamins) in milligrams
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public record Ingredient(
    @Nonnull UUID id,
    @Nonnull String name,
    IngredientType type,
    CookingState cookingState,
    String note,
    Macros ingredientMacros,
    Map<String, Integer> micronutrients,
    Map<String, Integer> otherValues,
    int cost,
    Currency currency
) {

  public enum CookingState {
    RAW, GRILLED, BOILED, STEAMED, FRIED, SAUTEED
  }

  public enum IngredientType {
    VEGGIE, MEAT, DAIRY, EGG, GRAIN, FRUIT, DESSERT, STARCH
  }
}
