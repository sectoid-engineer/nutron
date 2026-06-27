package org.ballz.app.domain;

import lombok.Getter;

import jakarta.annotation.Nonnull;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
public class Meal extends NutronEntity {
    private final LocalDateTime dateTime;
    @Nonnull private final Recipe recipe;

  public Meal(@Nonnull UUID id, @Nonnull Macros macros, LocalDateTime dateTime, Recipe recipe) {
    super(id, macros);
    this.dateTime = dateTime;
    this.recipe = recipe;
  }
}
