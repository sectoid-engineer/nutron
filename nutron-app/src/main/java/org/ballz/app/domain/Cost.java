package org.ballz.app.domain;

import java.math.BigDecimal;

public record Cost(
    BigDecimal value,
    Currency currency
) {

  public enum Currency {
    CZK, EUR, USD
  }

  public static Cost czk(int val) {
    return new Cost(BigDecimal.valueOf(val), Currency.CZK);
  }

  public static Cost czk(BigDecimal value) {
    return new Cost(value, Currency.CZK);
  }

  public static Cost eur(BigDecimal value) {
    return new Cost(value, Currency.EUR);
  }

  public static Cost usd(BigDecimal value) {
    return new Cost(value, Currency.USD);
  }

  public Cost plus(Cost additional) {
    if(additional == null) throw new NullPointerException("Attempted to add null Cost");
    else if(additional.currency() != this.currency) throw new IllegalArgumentException("Attempted to add Costs with mismatching currencies");

    return new Cost(this.value.add(additional.value()), this.currency);
  }

  public Cost scale(BigDecimal factor) {
    if(factor == null || factor.compareTo(BigDecimal.ZERO) < 0) throw new IllegalArgumentException("Wrong argument to Cost scaling method");

    return new Cost(this.value.multiply(factor), this.currency);
  }
}
