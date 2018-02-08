/*
 * Deck
 */
class Deck {
  private static final java.util.Random DECK = new java.util.Random(); // Se delkapittel 8.4 og s. 275
  private static final int UPPER_A_NR_VALUE = (int) 'A'; // A's tallverdi i Unicode-tegnsettet (s. 62, 921)
  private static final int COUNT_LETTERS = 26; // f.o.m. A t.o.m. Z

  public char draw() {
    int nr = DECK.nextInt(COUNT_LETTERS) + UPPER_A_NR_VALUE;
    return (char) nr;
  }
} // Kortstokk
