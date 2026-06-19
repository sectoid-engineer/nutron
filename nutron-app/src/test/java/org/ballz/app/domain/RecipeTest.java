package org.ballz.app.domain;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.UUID;

class RecipeTest {

  @Test
  void addIngredient() {

    Recipe recipe = new Recipe(
        UUID.randomUUID(),
        Macros.builder().build(),
        Cost.czk(BigDecimal.ZERO)
    );

    Ingredient chicken = new Ingredient(
        UUID.randomUUID(),
        "Grilled chicken breast",
        "Good for cutting",
        Macros.builder()
            .cal(165)
            .carb(Quantity.ZERO)
            .protein(Quantity.g(31))
            .fat(Quantity.g(4))
            .satFat(Quantity.g(1))
            .build(),
        Ingredient.IngredientType.MEAT,
        Ingredient.CookingState.GRILLED,
        Cost.czk(25),
        Ingredient.PortionSize.HUNDRED_GRAM);
    chicken.getMicronutrients()
        .put("Cholesterol", Quantity.mg(85));
    chicken.getMicronutrients()
        .put("Sodium", Quantity.mg(74));


    Ingredient pepper = new Ingredient(
        UUID.randomUUID(),
        "Bell pepper",
        null,
        Macros.builder()
            .cal(31)
            .carb(Quantity.g(6))
            .protein(Quantity.g(1))
            .fat(Quantity.mg(300))
            .build(),
        Ingredient.IngredientType.VEGGIE,
        Ingredient.CookingState.RAW,
        Cost.czk(20),
        Ingredient.PortionSize.HUNDRED_GRAM);

    pepper.getMicronutrients()
            .put("Sodium", Quantity.mg(4));
    pepper.getMicronutrients()
            .put("Potassium", Quantity.mg(211));


    Recipe recipe2 = recipe.withIngredient(chicken, Quantity.g(250));
    Recipe recipe3 = recipe2.withIngredient(pepper, Quantity.g(200));

    assertThat(recipe3.getSubtotalCost())
        .matches(c -> c.value().equals(BigDecimal.valueOf(45)) && c.currency() == Cost.Currency.CZK);
  }
}