/**

	Title:	Occurrence.java
	Date:	23.02.2017
	Author:	Eskil Uhlving Larsen

*/
import static javax.swing.JOptionPane.*;
class Occurrence {
  public static void main(String[] args) {
    String myText;
    String myCharRead;
    char myChar;

    // http://stackoverflow.com/a/30219789
    // String myCharString = String.valueOf(myChar);
    // int count = text.length() - text.replace(" ", "").length();
    // int count = text.length() - text.replace(myCharString, "").length();

    do {
      myText = showInputDialog("Type a text");
  } while (myText == null || myText.isEmpty());

    do {
      myCharRead = showInputDialog("Choose a char");
    } while (myCharRead == null || myCharRead.isEmpty());

    myChar = myCharRead.charAt(0);
    int count=0;
    for (int i=0; i<myText.length(); i++) {
      if (myText.charAt(i) == myChar) {
        count+=1;
      }
    }

    int wantGuess=1; //showConfirmDialog() no returns 1, yes returns 0
    String guess;
    do {
      wantGuess = showConfirmDialog(null, "Do you want to guess how many " + myChar + "'s I found?");
      if (wantGuess==0) {
        do {
          guess = showInputDialog("Guess how many " + myChar + "'s I found");
        } while (guess == null || guess.isEmpty());
        // Burde bruke try-catch for å forhindre at ikke-tall konverteres til integer
		// With just a space, the program crashes
        int guessInt = Integer.parseInt(guess);
        if (guessInt == count) {
          wantGuess = 1;
        } else {
          showMessageDialog(null, "You guessed " + guessInt + ", but it was wrong");
        }
      }
    } while (wantGuess == 0);

    showMessageDialog(null, "Found " + count + " occurrences of " + myChar);
  }
}
