package org.ballz.app.domain;

import jakarta.annotation.Nonnull;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

public record Day(
    @Nonnull UUID id,
    String name,
    String note,
    DayType type,
    LocalDateTime date,
    Macros targets,
    Set<Meal> meals,
    Macros totals
) {
  public enum DayType {
    HARD_TRAIN, LIGHT_TRAIN, REST, SEDENTARY
  }
}
