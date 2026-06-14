package org.ballz.app.domain;

/**
 * @param cal  in tenths of gram
 * @param carb in tenths of gram
 * @param fiber in tenths of gram (is part of carbs, hence cannot be more than carbs)
 * @param sugar in tenths of gram (is part of carbs, hence cannot be more than carbs)
 * @param protein in tenths of gram
 * @param fat in tenths of gram
 * @param satFat in tenths of gram (is part of fat, hence cannot be more than fat)
 * @param transFat in tenths of gram (is part of fat, hence cannot be more than fat)
 */
public record Macros(
    int cal,
    int carb,
    int fiber,
    int sugar,
    int protein,
    int fat,
    int satFat,
    int transFat
) {
}
