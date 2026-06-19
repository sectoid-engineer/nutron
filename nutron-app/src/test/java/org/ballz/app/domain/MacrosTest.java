package org.ballz.app.domain;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import org.junit.jupiter.api.Test;

class MacrosTest {

  @Test
  void add_withZero() {
    Macros m1 = Macros.builder().build();
    Macros m2 = Macros.builder()
        .cal(100)
        .carb(Quantity.g(100))
        .build();

    assertThat(m1.add(m2)).matches(sum -> sum.cal() == 100 && sum.carb().equals(Quantity.g(100)));
  }

  @Test
  void add_withValues() {
    Macros m1 = Macros.builder()
        .cal(50)
        .carb(Quantity.g(20))
        .protein(Quantity.g(10))
        .build();
    Macros m2 = Macros.builder()
        .cal(100)
        .carb(Quantity.g(100))
        .build();

    assertThat(m1.add(m2)).matches(sum ->
        sum.cal() == 150
        && sum.carb().equals(Quantity.g(120))
        && sum.protein().equals(Quantity.g(10)));
  }

  @Test
  void scale() {
  }
}