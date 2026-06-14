package org.ballz.app.domain;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

/**
 * Combination of Ingredients ready to be eaten.
 */
public class Recipe extends Tracked {
  private final Map<Ingredient, Integer> ingredientsQuantities = new HashMap<>();
  private final Map<String, Integer> subtotalMicronutrients = new HashMap<>();
  private final Map<String, Integer> subtotalOtherValues = new HashMap<>();
  private int subtotalCost;
  private Currency currency;
  private final Set<Tag> tags = new HashSet<>();

  public Recipe(UUID id) {
    super(id);
  }
}
