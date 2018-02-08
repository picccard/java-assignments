/*

	Title:	GuessingGame.java
	Date:	09.02.2017
	Author:	Eskil Uhlving Larsen

*/
import static javax.swing.JOptionPane.*;
class GuessingGame {
  public static void main(String[] args) {
    int secretInt = 5;
    char secretChar = 'U';
    String secretString = "java";
    showMessageDialog(null, "An secret integer, char and string is chosen. Guess what they are!");
    String guessIntRead = showInputDialog("Guess an integer.");
    int guessInt = Integer.parseInt(guessIntRead);
    String guessCharRead = showInputDialog("Guess a char.");
    char guessChar = guessCharRead.charAt(0);
    String guessString = showInputDialog("Guess a word.");
    String res1 = "An secret integer, char and string is chosen.";
    String res2 = "Secret number was " + secretInt + ". You guessed " + guessInt + ". Result: " + (secretInt == guessInt);
    String res3 = "Secret char was " + secretChar + ". You guessed " + guessChar + ". Result: " + (secretChar == guessChar);
    String res4 = "Secret word was " + secretString + ". You guessed " + guessString + ". Result: " + (secretString.equals(guessString));
    String res = (res1 + "\n" + res2 + "\n" + res3 + "\n" + res4);
    showMessageDialog(null, res);
  }
}
