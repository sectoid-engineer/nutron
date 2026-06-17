package org.ballz.app.domain;

import lombok.Getter;

import jakarta.annotation.Nonnull;
import java.util.UUID;

/**
 * Base User configuration:
 * Tracked.macros represents the user's base daily expected macros
 */
@Getter
public class User extends NutritionEntity {

  private final int mealsPerDay;
  private final int metabolicBaseCal;

  public User(@Nonnull UUID id, @Nonnull Macros macros, int mealsPerDay, int metabolicBaseCal) {
    super(id, macros);
    this.mealsPerDay = mealsPerDay;
    this.metabolicBaseCal = metabolicBaseCal;
  }
}
