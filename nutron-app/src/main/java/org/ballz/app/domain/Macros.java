package org.ballz.app.domain;

import lombok.Builder;
import org.ballz.app.exception.NutronArgumentException;

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

  public Macros {
    if(fiber.isLargerThan(carb)) throw new NutronArgumentException("Fiber is part of carbs, cannot be larger than carbs");
    else if(sugar.isLargerThan(carb)) throw new NutronArgumentException("Sugar is part of carbs, cannot be larger than carbs");
    else if(satFat.isLargerThan(fat)) throw new NutronArgumentException("Saturated fat is part of fats, cannot be larger than fat");
    else if(transFat.isLargerThan(fat)) throw new NutronArgumentException("Trans fat is part of fats, cannot be larger than fat");
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

//  public Macros scale(int factor) {
//    if(factor < 0) throw new IllegalArgumentException("attempted to apply negative scale to Macros");

//    return new Macros(
//        this.cal * factor,
//        this.carb * factor,
//        this.fiber * factor,
//        this.sugar * factor,
//        this.protein * factor,
//        this.fat * factor,
//        this.satFat * factor,
//        this.transFat * factor
//    )
//  }
}
