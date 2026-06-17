package org.ballz.app.domain;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;

class QuantityTest {

  @Test
  void isLargerThan_withScale() {
    Quantity q1 = new Quantity(1, Quantity.Unit.G);
    Quantity q2 = new Quantity(1, Quantity.Unit.MG);

    assertTrue(q1.isLargerThan(q2));
    assertFalse(q2.isLargerThan(q1));
  }

  @Test
  void isLargerThan_withAmount() {
    Quantity q1 = new Quantity(100, Quantity.Unit.G);
    Quantity q2 = new Quantity(1, Quantity.Unit.G);

    assertTrue(q1.isLargerThan(q2));
    assertFalse(q2.isLargerThan(q1));
  }

  @Test
  void plusNullThrows() {
    assertThrows(NullPointerException.class, () -> new Quantity(10, Quantity.Unit.G).plus(null));
  }

  @Test
  void plus_sameUnits() {
    Quantity q1 = new Quantity(100, Quantity.Unit.G);
    Quantity q2 = new Quantity(10, Quantity.Unit.G);

    assertThat(q1.plus(q2)).matches(sum -> sum.amount() == 110 && sum.unit() == Quantity.Unit.G);
  }

  @Test
  void plus_mixedUnits() {
    Quantity q1 = new Quantity(1, Quantity.Unit.G);
    Quantity q2 = new Quantity(500, Quantity.Unit.MG);

    assertThat(q1.plus(q2)).matches(sum -> sum.amount() == 1500 && sum.unit() == Quantity.Unit.MG);
  }

  @Test
  void times_mixed() {
    Quantity q1 = new Quantity(1, Quantity.Unit.G);
    Quantity q2 = new Quantity(500, Quantity.Unit.MG);

    assertThat(q1.times(q2)).matches(prod -> prod.amount() == 500 && prod.unit() == Quantity.Unit.MG);
  }

  @Test
  void times_mixed2() {
    Quantity q1 = new Quantity(1, Quantity.Unit.UG);
    Quantity q2 = new Quantity(500, Quantity.Unit.MG);

    assertThat(q1.times(q2)).matches(prod -> prod.amount() == 500 && prod.unit() == Quantity.Unit.MG);
  }
}