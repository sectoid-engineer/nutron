package org.ballz.app.domain;

import org.springframework.data.relational.core.mapping.Embedded;

import jakarta.annotation.Nonnull;
import java.util.Map;

public record NutritionValues(
    @Nonnull
    @Embedded(onEmpty = Embedded.OnEmpty.USE_EMPTY, prefix = "macros_")
    Macros macros,
    Map<String, Quantity> micros,
    Map<String, Quantity> other
) {
}
