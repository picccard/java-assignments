/**

	Title:	Price.java
	Date:	17.04.2017
	Author:	Eskil Uhlving Larsen

*/
public class Price {
  private String name;
  private double price;

  public Price(String name, double price) {
    this.name = name;
    this.price = price;
  }

  public int nrOfGuesses(double paid) {
    return (int) Math.floor(paid/this.price);
  }

  public String getName() {
    return this.name;
  }

  public double getPrice() {
    return this.price;
  }
}
