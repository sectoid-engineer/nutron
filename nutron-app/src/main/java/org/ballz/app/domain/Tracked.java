package org.ballz.app.domain;

import lombok.RequiredArgsConstructor;

import jakarta.annotation.Nonnull;
import java.util.UUID;

@RequiredArgsConstructor
public abstract class Tracked {
  private final @Nonnull UUID id;
  private String name;
  private String note;
  private Macros trackedMacros;
}
