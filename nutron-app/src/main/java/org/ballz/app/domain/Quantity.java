package org.ballz.app.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;

@Valid
public record Quantity(
    @Min(value = 0, message = "Quantity can't be negative")
    @Max(value = 500_000_000, message = "Quantity too large")
    BigDecimal amount,
    Unit unit) {

  public static final Quantity ZERO = new Quantity(BigDecimal.ZERO, Unit.G);

  public boolean isLargerThan(Quantity other) {
    return this.amount().multiply(this.unit().getScale()).compareTo(
           other.amount().multiply(other.unit().getScale())) > 0;
  }

  public Quantity plus(Quantity additional) {
    Objects.requireNonNull(additional);

    Unit sumUnit = minCommonUnit(this.unit, additional.unit());
    BigDecimal sumAmount =
        (this.amount().multiply(this.unit().getScale())
            .add(additional.amount().multiply(additional.unit().getScale())))
            .divide(sumUnit.getScale(), RoundingMode.HALF_UP);

    return new Quantity(sumAmount, sumUnit);
  }

  public Quantity scale(BigDecimal factor) {
    Objects.requireNonNull(factor);

    return new Quantity(this.amount.multiply(factor), this.unit);
  }

  private Unit minCommonUnit(Unit a, Unit b) {
    if(a == Unit.UG || b == Unit.UG) return Unit.UG;
    else if(a == Unit.MG || b == Unit.MG) return Unit.MG;
    else return Unit.G;
  }

  public BigDecimal inGrams() {
    if(unit == Unit.G) return amount;
    else return amount.divide(unit.getScale(), RoundingMode.HALF_UP);
  }

  @RequiredArgsConstructor
  @Getter
  public enum Unit {
    G(BigDecimal.valueOf(1_000_000)),
    MG(BigDecimal.valueOf(1000)),
    UG(BigDecimal.valueOf(1));

    private final BigDecimal scale;
  }

  public static Quantity g(int val) {
    return new Quantity(BigDecimal.valueOf(val), Unit.G);
  }

  public static Quantity mg(int val) {
    return new Quantity(BigDecimal.valueOf(val), Unit.MG);
  }

  public static Quantity ug(int val) {
    return new Quantity(BigDecimal.valueOf(val), Unit.UG);
  }
}
