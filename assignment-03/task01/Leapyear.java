/*
	Title:	Leapyear.java - oving3
	Date:	15.02.2017
	Author:	Eskil Uhlving Larsen
*/
import static javax.swing.JOptionPane.*;
class Leapyear {
  public static void main(String[] args) {
    String yearRead = showInputDialog("Chose a year: ");
    int year = Integer.parseInt(yearRead);

    if (year%400 == 0 && year%100 == 0) {
        String right = yearRead + " is a leapyear";
        showMessageDialog(null, right);
    } else if (year%100 != 0 && year%4 ==  0) {
      String right = yearRead + " is a leapyear";
      showMessageDialog(null, right);
    } else {
      String wrong = yearRead + " is not a leapyear";
      showMessageDialog(null, wrong);
    }
  }
}
/*
	Checks a year to see if it is a leap year.
*/
