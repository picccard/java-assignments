/*

	Title:	FindC.java - oving3
	Date:	15.02.2017
	Author:	Eskil Uhlving Larsen

*/
import static javax.swing.JOptionPane.*;
class FindC {
  public static void main(String[] args) {
    String text = showInputDialog("Skriv inn en tekst.");
    /*
        Using toLowerCase() to remove case-sens.
    */
    int pos = text.toLowerCase().indexOf('c');
    if (pos == -1) {
      showMessageDialog(null, "Found no 'c' in " + "\"" + text + "\"" + ".");
    } else {
      showMessageDialog(null, "The position to 'c' is : " + pos);
    }
  }
}
/*
	Scans the input for any 'c' and gives feedback.
*/
