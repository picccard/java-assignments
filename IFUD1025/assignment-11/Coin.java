/**

	Title:	Coin.java
	Date:	17.04.2017
	Author:	Eskil Uhlving Larsen

*/
public class Coin {
  private String currency;
  private double value;

  public Coin(String currency, double value) {
    this.currency = currency;
    this.value = value;
  }

  public double getValue() {
    return this.value;
  }

  public String getCurrency() {
    return this.currency;
  }
}
