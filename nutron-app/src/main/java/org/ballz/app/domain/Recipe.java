package org.ballz.app.domain;

import lombok.Getter;

import jakarta.annotation.Nonnull;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

/**
 * Combination of Ingredients ready to be eaten.
 * Tracked.macros is the amount of macros in the recipe
 */
@Getter
public class Recipe extends NutritionEntity {
  private final Map<Ingredient, Quantity> ingredientsQuantities = new HashMap<>();
  private final Map<String, Quantity> subtotalMicronutrients = new HashMap<>();
  private final Map<String, Quantity> subtotalOtherValues = new HashMap<>();
  private final Cost subtotalCost;
  private final Set<Tag> tags = new HashSet<>();

  public Recipe(@Nonnull UUID id, @Nonnull Macros macros, Cost subtotalCost) {
    super(id, macros);
    this.subtotalCost = subtotalCost;
  }

  void addIngredient(Ingredient ingredient, Quantity quantity) {
    ingredientsQuantities.put(ingredient, quantity);
//    macros.add()

  }


}
