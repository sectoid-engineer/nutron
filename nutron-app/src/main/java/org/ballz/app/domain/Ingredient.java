package org.ballz.app.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.data.relational.core.mapping.Embedded;
import org.springframework.data.relational.core.mapping.Table;

import java.math.BigDecimal;
import java.math.MathContext;
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
@Table("nu_ingredient")
public class Ingredient extends NutronEntity {
  private final IngredientType type;
  private final CookingState cookingState;

  @Embedded(onEmpty = Embedded.OnEmpty.USE_EMPTY, prefix = "pportion_")
  private final NutritionValues perPortion;
  private final Cost cost;
  private final PortionSize portionSize;

  public Ingredient(UUID id,
                    int version,
                    String name,
                    String note,
                    IngredientType type,
                    CookingState cookingState,
                    NutritionValues perPortion,
                    Cost cost,
                    PortionSize portionSize) {
    super(id, version, name, note);
    this.type = type;
    this.cookingState = cookingState;
    this.perPortion = perPortion;
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
    return this.perPortion.macros().scale(calculateScale(amount));
  }

  public Cost scaleCostFor(Quantity amount) {
    return this.cost.scale(calculateScale(amount));
  }

  BigDecimal calculateScale(Quantity amount) {
      return amount.inGrams().divide(BigDecimal.valueOf(portionSize.getGrams()), MathContext.DECIMAL32);
  }

  @Override
  public String toString() {
    return this.id + " : " + this.name;
  }
}
