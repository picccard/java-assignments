/**

	Title:	Drink.java
	Date:	06.03.2017
	Author:	Eskil Uhlving Larsen

*/
class Drink {
  private final String name;
  private final double alcoholContent;

  public Drink(String name, double percentage) {
    this.alcoholContent = percentage;
    this.name = name;
  }

  public String getName() {
    return this.name;
  }
  public double getPercentage() {
    return this.alcoholContent;
  }
  public double getGramAlcohol(int mlDrikk) {
    return ((this.alcoholContent/100) * 0.79 * mlDrikk);
  }
  public double findAlcoholConcentrationInBlood(int mlDrink, double bodyWeight, boolean man) {
    double w;
    if (man) {w = 0.68;} else {w = 0.55;}
    return ((this.getGramAlcohol(mlDrink)) / (bodyWeight * w));
  }
}
