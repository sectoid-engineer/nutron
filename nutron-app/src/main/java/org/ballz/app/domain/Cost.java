package org.ballz.app.domain;

public record Cost(
    int value,
    Currency currency
) {

  public enum Currency {
    CZK, EUR, USD
  }

  public static Cost czk(int value) {
    return new Cost(value, Currency.CZK);
  }

  public static Cost eur(int value) {
    return new Cost(value, Currency.EUR);
  }

  public static Cost usd(int value) {
    return new Cost(value, Currency.USD);
  }
}
