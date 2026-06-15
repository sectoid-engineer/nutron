package org.ballz.app.domain;

import lombok.Builder;
import org.ballz.app.exception.NutronArgumentException;

@Builder
public record Macros(
    Quantity cal,
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
}
