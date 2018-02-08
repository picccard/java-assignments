/**

	Title:	CardTest
	Date:	28.04.2017
	Author:	Eskil Uhlving Larsen

*/
public class CardTest {
  public static void main(String[] args) {
    Card[] cards = new Card[10];
    cards[0] = new Card("hjerter", 6);
    cards[1] = new Card("hjerter", 3);
    cards[2] = new Card("hjerter", 9);
    cards[3] = new Card("hjerter", 7);
    cards[4] = new Card("hjerter", 4);
    cards[5] = new Card("hjerter", 3);
    cards[6] = new Card("hjerter", 2);
    cards[7] = new Card("hjerter", 1);
    cards[8] = new Card("hjerter", 5);
    cards[9] = new Card("hjerter", 7);

    for (Card card : cards) {
      System.out.print(card.getRank() + " ");
    }

    java.util.Arrays.sort(cards);
    System.out.println();
    System.out.println();

    for (Card card : cards) {
      System.out.print(card.getRank() + " ");
    }
  }
}
