package org.ballz.app.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import java.util.Objects;

@Valid
public record Quantity(
    @Min(value = 0, message = "Quantity can't be negative")
    @Max(value = 500_000_000, message = "Quantity too large")
    Integer amount,
    Unit unit) {

  public boolean isLargerThan(Quantity other) {
    return this.amount() * this.unit().getScale() > other.amount() + other.unit().getScale();
  }

  public Quantity plus(Quantity additional) {
    Objects.requireNonNull(additional);

    Unit sumUnit = minCommonUnit(this.unit, additional.unit());
    int sumAmount =
        ( this.amount() * this.unit().getScale() + additional.amount() * additional.unit().getScale() )
        / sumUnit.getScale();

    return new Quantity(sumAmount, sumUnit);
  }

  public Quantity times(Quantity factor) {
    Objects.requireNonNull(factor);

    Unit productUnit = minCommonUnit(this.unit, factor.unit());
    int scale = this.unit.getScale() * factor.unit().getScale()
                / productUnit.getScale()  / productUnit.getScale();
    int productAmount = this.amount() * factor.amount() * scale;

    return new Quantity(productAmount, productUnit);
  }

  private Unit minCommonUnit(Unit a, Unit b) {
    if(a == Unit.UG || b == Unit.UG) return Unit.UG;
    else if(a == Unit.MG || b == Unit.MG) return Unit.MG;
    else return Unit.G;
  }

  public int inGrams() {
    if(unit == Unit.G) return amount;
    else return Math.round((float)amount / unit.getScale());
  }

  @RequiredArgsConstructor
  @Getter
  public enum Unit {
    G(1_000_000), MG(1000), UG(1);

    private final int scale;
  }
}
