package org.ballz.app.domain;

import jakarta.annotation.Nonnull;

public record Tag(
  @Nonnull String name
){
  @Override
  public @Nonnull String toString() {
    return name;
  }
}
