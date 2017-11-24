/**

	Title:	Card
	Date:	28.04.2017
	Author:	Eskil Uhlving Larsen
    
*/
public class Card implements Comparable<Card> {
  private final String suit;
  private final int rank;


  public Card(String suit, int rank) {
    this.suit = suit;
    this.rank = rank;
  }

  public int getRank() {
    return this.rank;
  }

  public String getSuit() {
    return this.suit;
  }

  public int compareTo(Card otherCard) {
    int rank1 = this.getRank();
    int rank2 = otherCard.getRank();
    if (Math.abs(rank2 - rank1) < 0.001) {
      return 0;
    } else if (rank1 < rank2) {
      return -1;
    } else {
      return 1;
    }
  }

  public boolean equals(Object otherCard) {
    if (!(otherCard instanceof Card)) {
      return false;
    }
    if (this == otherCard) {
      return true;
    }
    Card kort2 = (Card) otherCard;
    return (this.rank == kort2.getRank());
  }

  public String toString() {
    return (this.suit + " " + this.rank);
  }
}
