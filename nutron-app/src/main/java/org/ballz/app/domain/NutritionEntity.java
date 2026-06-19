package org.ballz.app.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import jakarta.annotation.Nonnull;
import java.util.UUID;

@RequiredArgsConstructor
@Getter
public abstract class NutritionEntity {
  protected final @Nonnull UUID id;
  protected final String name;
  protected final String note;
  protected final @Nonnull Macros macros;

  protected NutritionEntity(UUID id, Macros macros) {
    this(id, null, null, macros);
  }
}
