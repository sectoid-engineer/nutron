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
  private final Map<Ingredient, Quantity> ingredientsQuantities = new HashMap<>();
  private final Map<String, Quantity> subtotalMicronutrients = new HashMap<>();
  private final Map<String, Quantity> subtotalOtherValues = new HashMap<>();
  private Cost subtotalCost;
  private final Set<Tag> tags = new HashSet<>();

  public Recipe(UUID id) {
    super(id);
  }
}
