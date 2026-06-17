package org.ballz.app.domain;

import lombok.Getter;

import jakarta.annotation.Nonnull;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

/**
 * Day: set of meals.
 * targets is the macros that the User should eat in this day
 * Tracked.macros is the current actual amount of Macros eaten so far
 */
@Getter
public class Day extends NutritionEntity {
    private final DayType type;
    private final LocalDateTime date;
    private final Macros targets;
    private final Set<Meal> meals;
    private final Cost cost;

  public Day(@Nonnull UUID id, @Nonnull Macros macros, DayType type, LocalDateTime date, Macros targets, Set<Meal> meals, Cost cost) {
    super(id, macros);
    this.type = type;
    this.date = date;
    this.targets = targets;
    this.meals = meals;
    this.cost = cost;
  }

  public enum DayType {
    HARD_TRAIN, LIGHT_TRAIN, REST, SEDENTARY
  }
}
