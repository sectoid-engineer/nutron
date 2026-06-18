package org.ballz.app.domain;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

class QuantityTest {

  @Test
  void isLargerThan_withScale() {
    Quantity q1 = new Quantity(BigDecimal.ONE, Quantity.Unit.G);
    Quantity q2 = new Quantity(BigDecimal.ONE, Quantity.Unit.MG);

    assertTrue(q1.isLargerThan(q2));
    assertFalse(q2.isLargerThan(q1));
  }

  @Test
  void isLargerThan_withAmount() {
    Quantity q1 = new Quantity(BigDecimal.TEN, Quantity.Unit.G);
    Quantity q2 = new Quantity(BigDecimal.ONE, Quantity.Unit.G);

    assertTrue(q1.isLargerThan(q2));
    assertFalse(q2.isLargerThan(q1));
  }

  @Test
  void plusNullThrows() {
    Quantity q1 = new Quantity(BigDecimal.TEN, Quantity.Unit.G);
    assertThrows(NullPointerException.class, () -> q1.plus(null));
  }

  @Test
  void plus_sameUnits() {
    Quantity q1 = new Quantity(BigDecimal.TEN, Quantity.Unit.G);
    Quantity q2 = new Quantity(BigDecimal.ONE, Quantity.Unit.G);

    assertThat(q1.plus(q2)).matches(sum -> sum.amount().equals(BigDecimal.valueOf(11)) && sum.unit() == Quantity.Unit.G);
  }

  @Test
  void plus_mixedUnits() {
    Quantity q1 = new Quantity(BigDecimal.TEN, Quantity.Unit.G);
    Quantity q2 = new Quantity(BigDecimal.ONE, Quantity.Unit.MG);

    assertThat(q1.plus(q2)).matches(sum -> sum.amount().equals(BigDecimal.valueOf(10001)) && sum.unit() == Quantity.Unit.MG);
  }

  @Test
  void scale() {
    Quantity q1 = new Quantity(BigDecimal.TEN, Quantity.Unit.G);

    assertThat(q1.scale(BigDecimal.TWO))
        .matches(q2 -> q2.amount().equals(BigDecimal.valueOf(20))
        && q1.unit() == Quantity.Unit.G);
  }


}