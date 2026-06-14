package org.ballz.app.domain;

import java.time.LocalDateTime;
import java.util.UUID;

public class Meal extends Tracked {
    LocalDateTime dateTime;
    Recipe recipe;
    Macros targets;

    public Meal(UUID id) {
      super(id);
    }
}
