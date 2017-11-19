/*

	Title:	SecToHHmmSS.java
	Date:	11.02.2017
	Author:	Eskil Uhlving Larsen

*/
import static javax.swing.JOptionPane.*;
class SecToHHmmSS {
  public static void main(String[] args) {
    String secRead = showInputDialog("How many seconds? ");
    Double totalSec = Double.parseDouble(secRead);

	int hr = (int) (totalSec / 60 / 60);  // 1.6 -> 1hr
	int min = (int) (((totalSec / 60 / 60) - hr) * 60); // (1.6-1)*60 = 36min
	int sec = (int) (totalSec-(hr * 60 * 60) - (min * 60)); // 61-(0*60*60)-(1*60) = 1sec

	String res = totalSec + " " + hr + " " + min + " " + sec;

	showMessageDialog(null, res);
  }
}
