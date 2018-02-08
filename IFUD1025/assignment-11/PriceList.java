/**

	Title:	PriceList.java
	Date:	17.04.2017
	Author:	Eskil Uhlving Larsen

*/
public class PriceList {
  private Price[] prices;
  private int priceCount;

  public PriceList(int size) {
    this.prices = new Price[size];
    this.priceCount = 0;
  }

  public void setPrice(String currency, double price) {
    this.prices[this.priceCount] = new Price(currency, price);
    this.priceCount += 1;
  }

  public int nrOfGuesses(String currency, double sum) {
    for (int i=0; i<this.prices.length; i++) {
      if (this.prices[i].getName().equals(currency)) {
        return (this.prices[i].nrOfGuesses(sum));
      }
    }
    return -1; // Retuns -1 if the currency wasn't found
  }

  public double priceForAGuess(String currency) {
    for (int i=0; i<this.prices.length; i++) {
      if (this.prices[i].getName().equals(currency)) {
        return this.prices[i].getPrice();
      }
    }
    return -1;
  }

  public String toString() {
    String returnText = "Price per guess:";
    for (int i=0; i<this.prices.length; i++) {
      if (!(this.prices[i] == null)) {
        returnText += "\n";
        returnText += this.prices[i].getPrice();
        returnText += " ";
        returnText += this.prices[i].getName();
      }
    }
    return returnText;
  }
}
