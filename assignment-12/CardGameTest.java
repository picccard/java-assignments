/**
  * CardGameTest
  *
  */

class CardGameTest {
  public static void main(String[] args) {
    PropertiesSet propertiesSet = new PropertiesSet(3, 4);
    System.out.println("Info from propertiesset:");
    System.out.println("Minimum rank: " + propertiesSet.getMinRank());
    System.out.println("Maximum rank: " + propertiesSet.getMaxRank());
    System.out.println("Number of ranks: " + propertiesSet.countRanks());
    String result = "All ranks: ";
    int[] ranks = propertiesSet.getRanks();
    for (int i = 0; i < propertiesSet.countRanks(); i++) {
      result += ranks[i] + " ";
    }
    System.out.println(result);

    System.out.println("Number of cards in the deck, decided by the propertiesset: "
        + propertiesSet.countCards());

    CardPile cardPile = new CardPile(propertiesSet);
    System.out.println("A new pile with " + cardPile.getCardCount() + " cards:");
    System.out.println(cardPile);  // får her indirekte testet PropertiesSet-metoden finnKortstokken() getDeck
    cardPile.shuffle();
    System.out.println("Cardpile after shuffle:");
    System.out.println(cardPile);

    System.out.println("\nRemoves three cards from the top of the pile, these cards:");
    System.out.println(cardPile.removeTop());
    System.out.println(cardPile.removeTop());
    System.out.println(cardPile.removeTop());

    System.out.println("Cardpile with " + cardPile.getCardCount() + " cards:");
    System.out.println(cardPile);

    System.out.println("After reversing the cardPile:");
    cardPile.reverse();
    System.out.println(cardPile);

    cardPile.shuffle();
    System.out.println("Cardpile after another shuffle:");
    System.out.println(cardPile);
  }
} // CardGameTest


/* Kjøring:
Informasjon om egenskapssettet:
Minste valør: 3
Største valør: 4
Antall valører: 2
Alle valørene: 3 4
Antall kort i kortstokk bestemt av egenskapssettet: 8
En ny bunke med 8 kort:
spar 3 - spar 4 - kløver 3 - kløver 4 - hjerter 3 - hjerter 4 - ruter 3 - ruter
4 -
Bunken etter stokking:
spar 4 - hjerter 3 - hjerter 4 - kløver 3 - ruter 3 - kløver 4 - ruter 4 - spar
3 -

Fjerner tre kort fra toppen av bunken, nemlig:
spar 3
ruter 4
kløver 4
Bunken med 5 kort:
spar 4 - hjerter 3 - hjerter 4 - kløver 3 - ruter 3 -
Etter å ha snudd bunken:
ruter 3 - kløver 3 - hjerter 4 - hjerter 3 - spar 4 -
Bunken etter ny stokking:
kløver 3 - spar 4 - hjerter 4 - ruter 3 - hjerter 3 -
*/
