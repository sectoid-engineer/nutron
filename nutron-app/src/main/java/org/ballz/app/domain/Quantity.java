package org.ballz.app.domain;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;

@Valid
public record Quantity(
    @Min(value = 0, message = "Quantity can't be negative")
    @Max(value = 100000, message = "Quantity too large")
    Integer amount,
    Unit unit) {

  public boolean isLargerThan(Quantity other) {
    return this.amount() > other.amount();
  }

  public enum Unit {
    G, MG, UG
  }
}
