package org.ballz.app.domain;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Basic Ingredient representation. All values are provided specific to 100 g portion.
 *
 * micronutrients add individual micronutrientes of interest here (sodium, cholesterol, vitamins) in milligrams
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Ingredient extends Tracked {
  private IngredientType type;
  private CookingState cookingState;
  private final Map<String, Quantity> micronutrients = new HashMap<>();
  private final Map<String, Quantity> otherValues = new HashMap<>();
  private Cost cost;

  public Ingredient(UUID id) {
    super(id);
  }

  public enum CookingState {
    RAW, GRILLED, BOILED, STEAMED, FRIED, SAUTEED
  }

  public enum IngredientType {
    VEGGIE, MEAT, DAIRY, EGG, GRAIN, FRUIT, DESSERT, STARCH
  }
}
