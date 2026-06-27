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
//@Builder
@Getter
public class Recipe extends NutronEntity {
  private final Map<Ingredient, Quantity> ingredientsQuantities;
  private final Map<String, Quantity> subtotalMicronutrients;
  private final Map<String, Quantity> subtotalOtherValues;
  private final Cost subtotalCost;
  private final Set<Tag> tags;

  public Recipe(@Nonnull UUID id, @Nonnull Macros macros, Cost subtotalCost) {
    super(id, null, null, macros);
    this.subtotalCost = subtotalCost;
    this.ingredientsQuantities = new HashMap<>();
    this.subtotalMicronutrients = new HashMap<>();
    this.subtotalOtherValues = new HashMap<>();
    this.tags = new HashSet<>();
  }

  public Recipe(@Nonnull UUID id, String name, String note, Macros macros, Map<Ingredient, Quantity> ingredientsQuantities,
                Map<String, Quantity> subtotalMicronutrients, Map<String, Quantity> subtotalOtherValues, Cost subtotalCost,
                Set<Tag> tags) {
    super(id, name, note, macros);
    this.ingredientsQuantities = ingredientsQuantities;
    this.subtotalMicronutrients = subtotalMicronutrients;
    this.subtotalOtherValues = subtotalOtherValues;
    this.subtotalCost = subtotalCost;
    this.tags = tags;
  }

  public Recipe withIngredient(Ingredient ingredient, Quantity quantity) {
    ingredientsQuantities.put(ingredient, quantity);
    addToMap(ingredient.getMicronutrients(), subtotalMicronutrients);
    addToMap(ingredient.getOtherValues(), subtotalOtherValues);

    return new Recipe(
        this.id,
        this.name,
        this.note,
        this.macros.add(ingredient.scaleMacrosFor(quantity)),
        this.ingredientsQuantities,
        this.subtotalMicronutrients,
        this.subtotalOtherValues,
        this.subtotalCost.plus(ingredient.scaleCostFor(quantity)),
        this.tags
    );
  }

  private void addToMap(Map<String, Quantity> sourceMap,
                        Map<String, Quantity> targetMap) {

    sourceMap.forEach((addName, addQuant) ->
      targetMap.computeIfPresent(addName, (_, currentQuant) -> currentQuant.plus(addQuant))
    );
  }
}
