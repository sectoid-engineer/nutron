package org.ballz.app.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;

import jakarta.annotation.Nonnull;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Ingredient representation.
 * Tracked.macros is the amount of macros per portion.
 *
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
public class Ingredient extends NutritionEntity {
  private final IngredientType type;
  private final CookingState cookingState;
  private final Map<String, Quantity> micronutrients = new HashMap<>();
  private final Map<String, Quantity> otherValues = new HashMap<>();
  private final Cost cost;
  private final PortionSize portionSize;

  public Ingredient(@Nonnull UUID id,
                    String name,
                    String note,
                    @Nonnull Macros macros,
                    IngredientType type,
                    CookingState cookingState,
                    Cost cost,
                    PortionSize portionSize) {
    super(id, macros);
    this.type = type;
    this.cookingState = cookingState;
    this.cost = cost;
    this.portionSize = portionSize;
  }

  public enum CookingState {
    RAW, GRILLED, BOILED, STEAMED, FRIED, SAUTEED
  }

  public enum IngredientType {
    VEGGIE, MEAT, DAIRY, EGG, GRAIN, FRUIT, DESSERT, STARCH
  }

  @AllArgsConstructor
  @Getter
  public enum PortionSize {
    HUNDRED_GRAM(100), POUND(545), OUNCE(28);

    final int grams;
  }

  public Macros scaleMacrosFor(Quantity amount) {
    return this.macros.scale(calculateScale(amount));
  }

  public Cost scaleCostFor(Quantity amount) {
    return this.cost.scale(calculateScale(amount));
  }

  BigDecimal calculateScale(Quantity amount) {
    return amount.inGrams().divide(BigDecimal.valueOf(portionSize.getGrams()), RoundingMode.HALF_UP);
  }
}
