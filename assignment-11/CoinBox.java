/**

	Title:	CoinBox.java
	Date:	17.04.2017
	Author:	Eskil Uhlving Larsen

*/
public class CoinBox {
  private Coin[] coins;
  private int coinCount;

  public CoinBox(int storrelse) {
    this.coins = new Coin[storrelse];
    this.coinCount = 0;
  }

  public boolean isFull() {
    return (this.coinCount == this.coins.length);
  }

  public void addCoin(Coin coin) {
    if (!this.isFull()) {
      this.coins[coinCount] = coin;
      this.coinCount += 1;
    }
  }

  public String toString() {
    String returnText = "Coins in the box:";
    for (int i=0; i<this.coins.length; i++) {
      if (!(this.coins[i] == null)) {
        returnText += "\n";
        returnText += this.coins[i].getCurrency();
        returnText += " ";
        returnText += this.coins[i].getValue();
      }
    }
    return returnText;
  }
}
