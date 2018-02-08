/**

	Title:	PropertiesSet.java - Øving 12
	Date:	28.04.2017
	Author:	Eskil Uhlving Larsen
    
*/
public class PropertiesSet {
  private static final String[] SUITS = {"clubs", "diamonds", "hearts", "spades" };
  private int minRank;
  private int maxRank;

  public PropertiesSet(int min, int max) {
    //min should not be able to be greater than max, add validation
    this.minRank = min;
    this.maxRank = max;
  }

  public int countRanks() {
    return (this.maxRank - this.minRank + 1);
  }

  public int[] getRanks() {
    int[] rankTable = new int[this.maxRank - this.minRank + 1];
    for (int i = 0; i < this.maxRank - this.minRank + 1; i++) {
      rankTable[i] = (this.minRank + i);
    }
    return rankTable;
  }

  public Card[] getDeck() {
    Card[] tempDeck = new Card[(this.maxRank - this.minRank + 1) * SUITS.length];
    int count = 0; // i + j instead of count?
    for (int i = 0; i < this.maxRank - this.minRank + 1; i++) {
      for (int j = 0; j < SUITS.length; j++) {
        tempDeck[count] = new Card(SUITS[j], this.minRank + i);
        count++;
      }
    }
    return tempDeck;
  }

  public int countCards() {
    return ((this.maxRank - this.minRank + 1) * SUITS.length);
  }

  public int getMinRank() {
    return this.minRank;
  }

  public int getMaxRank() {
    return this.maxRank;
  }
}
