package org.ballz.app.domain;

import lombok.Builder;
import org.ballz.app.exception.NutronArgumentException;

import java.math.BigDecimal;
import java.util.Objects;

@Builder
public record Macros(
    int cal,
    Quantity carb,
    Quantity fiber,
    Quantity sugar,
    Quantity protein,
    Quantity fat,
    Quantity satFat,
    Quantity transFat
) {








  public Macros(int cal,
                Quantity carb,
                Quantity fiber,
                Quantity sugar,
                Quantity protein,
                Quantity fat,
                Quantity satFat,
                Quantity transFat) {
    this.cal = cal;
    this.carb = carb != null ? carb : Quantity.ZERO;

    this.fiber = fiber != null ? fiber : Quantity.ZERO;
    if(this.fiber.isLargerThan(this.carb)) throw new NutronArgumentException("Fiber is part of carbs, cannot be larger than carbs");

    this.sugar = sugar != null ? sugar : Quantity.ZERO;
    if(this.sugar.isLargerThan(this.carb)) throw new NutronArgumentException("Sugar is part of carbs, cannot be larger than carbs");

    this.protein = protein != null ? protein : Quantity.ZERO;
    this.fat = fat != null ? fat : Quantity.ZERO;

    this.satFat = satFat != null ? satFat : Quantity.ZERO;
    if(this.satFat.isLargerThan(this.fat)) throw new NutronArgumentException("Saturated fat is part of fats, cannot be larger than fat");

    this.transFat = transFat != null ? transFat : Quantity.ZERO;
    if(this.transFat.isLargerThan(this.fat)) throw new NutronArgumentException("Trans fat is part of fats, cannot be larger than fat");
  }

  public Macros add(Macros additional) {
    Objects.requireNonNull(additional);

    return new Macros(
        this.cal + additional.cal(),
        this.carb.plus(additional.carb()),
        this.fiber.plus(additional.fiber()),
        this.sugar.plus(additional.sugar()),
        this.protein.plus(additional.protein()),
        this.fat.plus(additional.fat()),
        this.satFat.plus(additional.satFat()),
        this.transFat.plus(additional.transFat())
    );
  }

  public Macros scale(BigDecimal factor) {
    if(factor == null || factor.compareTo(BigDecimal.ZERO) < 0) throw new IllegalArgumentException("Macros can't be scaled by null or negative value");

    return new Macros(
      factor.multiply(BigDecimal.valueOf(cal)).intValue(), //narrowing conversion OK for calories
      carb.scale(factor),
      fiber.scale(factor),
      sugar.scale(factor),
      protein.scale(factor),
      fat.scale(factor),
      satFat.scale(factor),
      transFat.scale(factor)
    );
  }
}
