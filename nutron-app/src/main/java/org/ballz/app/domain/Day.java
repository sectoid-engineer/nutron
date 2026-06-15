package org.ballz.app.domain;

import jakarta.annotation.Nonnull;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

public class Day extends Tracked {
    private DayType type;
    private LocalDateTime date;
    private Macros targets;
    private Set<Meal> meals;
    private Cost cost;

  public Day(@Nonnull UUID id) {
    super(id);
  }

  public enum DayType {
    HARD_TRAIN, LIGHT_TRAIN, REST, SEDENTARY
  }
}
