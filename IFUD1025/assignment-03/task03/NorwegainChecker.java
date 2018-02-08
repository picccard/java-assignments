/*

	Title:	NorwegainChecker.java
	Date:	15.02.2017
	Author:	Eskil Uhlving Larsen

*/
import static javax.swing.JOptionPane.*;
class NorwegainChecker {
  public static void main(String[] args) {
    String text = showInputDialog("Type a text: ");
    /* u for known | k for unknown */
    int u1 = text.toLowerCase().indexOf('c');
    int u2 = text.toLowerCase().indexOf('q');
    int u3 = text.toLowerCase().indexOf('w');
    int u4 = text.toLowerCase().indexOf('x');
    int u5 = text.toLowerCase().indexOf('z');
    //If unknown letter -> known=false
    Boolean known = (u1 == u2 && u2 == u3 && u3 == u4 && u4 == u5);
    int k1 = text.toLowerCase().indexOf('\u00e6');
    int k2 = text.toLowerCase().indexOf('\u00f8');
    int k3 = text.toLowerCase().indexOf('\u00e5');
    //If known letter -> unknown=false
    Boolean unknown = (k1 == k2 && k2 == k3);


    if (known && !unknown) {
      showMessageDialog(null, "Text is simple-norwegian");
    } else {
        showMessageDialog(null, "Text is not simple-norwegian");
    }
  }
}
/*
	Asks for a string.
	Checks if the string contrains norwegian/non-norwegian letters.
	Feedback.
*/
