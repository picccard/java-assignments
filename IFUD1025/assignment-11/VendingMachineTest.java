import static javax.swing.JOptionPane.*;
/**
* VendingMachineTest
*/
class VendingMachineTest {
 public static void main(String[] args) {
 /* Creates the vending machine:
 *  The price can be set for 200 currencies and
 *  the 5 coins can fit in the machine.
 */
 VendingMachine vendingMachine = new VendingMachine(200, 5);
 /* Sets the price for three currencies: EUR, USD og NOK. */
 final String[] CURRENCIES = {"EUR", "USD", "NOK"}; // 3 items
 final double[] PRICES = {0.12, 0.16, 1.0}; // also 3 items
 for (int i = 0; i < CURRENCIES.length; i++) {
 vendingMachine.setPrice(CURRENCIES[i], PRICES[i]);
 }/*
 * Så lenge bøssen ikke er full, vil følgende store løkke gå.
 * I løkken leser vi inn mynten og tippingen,
 * kaller Automats spill()-metode, og
 * skriver ut trekningen og antall rette.
 */
 do {
 /* Read the coin. */
 int currency = showOptionDialog(null,
 vendingMachine.getPriceList() + "\n\nYour coin's currency:",
 "Input a coin (part 1)",
 DEFAULT_OPTION,
 PLAIN_MESSAGE,
 null,
 CURRENCIES,
 CURRENCIES[0]);
 String currencyName = CURRENCIES[currency];
 String valueRead = showInputDialog(null,
 "Every guess costs "
 + vendingMachine.priceForAGuess(currencyName) + " "
 + currencyName + "."
 + "\n\nYour coin's value in " + currencyName + ":",
 "Input a coin (part 2)",
 QUESTION_MESSAGE);
 double value = Double.parseDouble(valueRead);
 /* Reads the guesses. */
 int nrOfGuesses = vendingMachine.nrOfGuesses(currencyName,
 value);
 int[] guesses = new int[nrOfGuesses];
 final String[] DIGITOPTIONS // litt stygt med "0"... "9" direkte
 = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9"};
 for (int i = 0; i < nrOfGuesses; i++) {
 guesses[i] = showOptionDialog(null,
 "Tipp et tall:",
 "Tipping (tall nr " + (i + 1) + " av " + nrOfGuesses + ")",
 DEFAULT_OPTION,
 PLAIN_MESSAGE,
 null,
 DIGITOPTIONS,
 DIGITOPTIONS[0]);
 }
 /* Creates a player and prints player-info. */
 Player player = new Player(guesses, currencyName, value);
 System.out.println("Your data is: " + player);
 /* Plays a turn and prints the results. */
 Raffle raffle = vendingMachine.play(player);
 int[] raffleNumbers = raffle.getDraw();
 System.out.print("The following numbers was chosen: ");
 for (int j = 0; j < raffleNumbers.length; j++) {
 System.out.print(raffleNumbers[j] + " - ");
 }
 System.out.println("\nYou have "
 + vendingMachine.getMatches(raffle, player) + " right guesses");
 System.out.println("--------------------------------------------");
} while (!vendingMachine.isFull());
 /* Prints the content of the full coinbox. */
 System.out.println(vendingMachine.getCoinBox());
 }
} // VendingMachineTest
