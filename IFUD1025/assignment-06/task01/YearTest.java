/**

	Title:	YearTest.java
	Date:	06.03.2017
	Author:	Eskil Uhlving Larsen

*/
import static javax.swing.JOptionPane.*;

class YearTest {
  public static void main(String[] args) {
    String yearNrRead = showInputDialog("What year?");
    int yearNr = Integer.parseInt(yearNrRead);
    Year ar = new Year(yearNr);

    String text = "The year is " + ar.getYear()
        + "\nLast year was " + ar.lastYear()
        + "\nThe next year is " + ar.nextYear()
        + "\nThree years ago was " + ar.afterSomeYears(-3)
        + "\nIn three years it's gonna be " + ar.afterSomeYears(3);
    if (ar.isLeapyear()) {
      text += "\n" + yearNr + " is a leap year";
    } else {
      text += "\n" + yearNr + " is not a leap year";
    }
    showMessageDialog(null, text);
  }
} // YearTest
