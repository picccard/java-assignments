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
    System.out.println(cardPile);  // f�r her indirekte testet PropertiesSet-metoden finnKortstokken() getDeck
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


/* Kj�ring:
Informasjon om egenskapssettet:
Minste val�r: 3
St�rste val�r: 4
Antall val�rer: 2
Alle val�rene: 3 4
Antall kort i kortstokk bestemt av egenskapssettet: 8
En ny bunke med 8 kort:
spar 3 - spar 4 - kl�ver 3 - kl�ver 4 - hjerter 3 - hjerter 4 - ruter 3 - ruter
4 -
Bunken etter stokking:
spar 4 - hjerter 3 - hjerter 4 - kl�ver 3 - ruter 3 - kl�ver 4 - ruter 4 - spar
3 -

Fjerner tre kort fra toppen av bunken, nemlig:
spar 3
ruter 4
kl�ver 4
Bunken med 5 kort:
spar 4 - hjerter 3 - hjerter 4 - kl�ver 3 - ruter 3 -
Etter � ha snudd bunken:
ruter 3 - kl�ver 3 - hjerter 4 - hjerter 3 - spar 4 -
Bunken etter ny stokking:
kl�ver 3 - spar 4 - hjerter 4 - ruter 3 - hjerter 3 -
*/
