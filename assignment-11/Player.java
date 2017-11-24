/**

	Title:	Player.java
	Date:	17.04.2017
	Author:	Eskil Uhlving Larsen

*/
import java.util.Arrays;

public class Player {
  private Coin coin;
  private int[] guesses;

  public Player(int[] guesses, String currencyName, double value) {
    this.guesses = guesses;
    Arrays.sort(this.guesses);
    this.coin = new Coin(currencyName, value);
  }

  public Coin pay() {
    Coin temp = this.coin;
    this.coin = null;
    return temp;
  }

  public int[] getGuesses() {
    return this.guesses;
  }

  public Coin getCoin() {
    return this.coin;
  }

  public String toString() {
    String returnText = "";
    for (int i=0; i<this.guesses.length; i++) {
      returnText += (this.guesses[i] + " - ");
    }
    returnText += this.coin.getCurrency();
    returnText += " ";
    returnText += this.coin.getValue();
    return returnText;
  }
}
