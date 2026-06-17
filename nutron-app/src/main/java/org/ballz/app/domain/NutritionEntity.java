package org.ballz.app.domain;

import lombok.RequiredArgsConstructor;

import jakarta.annotation.Nonnull;
import java.util.UUID;

@RequiredArgsConstructor
public abstract class NutritionEntity {
  private final @Nonnull UUID id;
  protected String name;
  protected String note;
  protected @Nonnull Macros macros;
}
