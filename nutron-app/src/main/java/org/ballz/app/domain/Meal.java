package org.ballz.app.domain;

import jakarta.annotation.Nonnull;
import java.time.LocalDateTime;
import java.util.UUID;

public record Meal(
    @Nonnull UUID id,
    String name,
    LocalDateTime dateTime,
    String note,
    Recipe recipe,
    Macros targets
) {
}
