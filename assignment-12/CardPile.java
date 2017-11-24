/**

	Title:	CardPile.java
	Date:	28.04.2017
	Author:	Eskil Uhlving Larsen

*/
public class CardPile {
  PropertiesSet propertiesSet;
  Card[] cards;
  int cardCount;

  public CardPile(PropertiesSet pSet) {
    this.propertiesSet = pSet;
    this.cards = this.propertiesSet.getDeck();
    this.cardCount = this.cards.length;
  }

  public Card removeTop() {
    Card topCard = this.cards[cardCount - 1];
    this.cardCount -= 1;

    Card[] remainingCards = new Card[cardCount];
    for (int i = remainingCards.length-1; i >= 0; i--) {
      remainingCards[i] = this.cards[i];
    }
    this.cards = remainingCards;
    return topCard;
  }

  public void reverse() {
    /*
    for (int i = 0; i < validData.length / 2; i++) {
        int temp = validData[i];
        validData[i] = validData[validData.length - i - 1];
        validData[validData.length - i - 1] = temp;
    }
    */

    Card[] reversedCards = new Card[cards.length];
    int index = 0;
    for (int i = cards.length-1; i >= 0; i--) {
      reversedCards[index] = this.cards[i];
      index++;
    }
    this.cards = reversedCards;
  }

  public void shuffle() {
    //Fisher-Yates modern shuffle
    //https://en.wikipedia.org/wiki/Fisher-Yates_shuffle#The_modern_algorithm
    java.util.Random r = new java.util.Random();

    for (int i = this.cards.length - 1; i > 0; i--) {
      int index = r.nextInt(i + 1);
      Card temp = this.cards[index];
      this.cards[index] = this.cards[i];
      this.cards[i] = temp;
    }
  }

  public int getCardCount() {
    return this.cardCount;
  }


  public String toString() {
    String returnText = "";
    for (int i = 0; i < this.cards.length; i++) {
      returnText += this.cards[i].getSuit() + " " + this.cards[i].getRank() + " - ";
    }
    return returnText;
  }
}
