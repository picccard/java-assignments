/**

	Title:	VendingMachine.java - Øving 11
	Date:	17.04.2017
	Author:	Eskil Uhlving Larsen

*/
public class VendingMachine {
  private PriceList pricelist;
  private CoinBox coinbox;

  public VendingMachine(int listSize, int boxSize) {
    this.pricelist = new PriceList(listSize);
    this.coinbox = new CoinBox(boxSize);
  }

  public Raffle play(Player player) {
    coinbox.addCoin(player.getCoin());
    player.pay();
    return new Raffle();
  }

  public void setPrice(String currency, double price) {
    this.pricelist.setPrice(currency, price);
  }

  public boolean isFull() {
    return this.coinbox.isFull();
  }

  public double priceForAGuess(String currency) {
    return this.pricelist.priceForAGuess(currency);
  }

  public int nrOfGuesses(String currency, double verdi) {
    return this.pricelist.nrOfGuesses(currency, verdi);
  }

  public String getPriceList() {
    return this.pricelist.toString();
  }

  public String getCoinBox() {
    return this.coinbox.toString();
  }

  public int getMatches(Raffle raffle, Player player) {
    return raffle.getMatches(player.getGuesses());
  }
}
