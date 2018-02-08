/*

	Title:	TempConverter.java
	Date:	09.02.2017
	Author:	Eskil Uhlving Larsen

*/
import static javax.swing.JOptionPane.*;
class TempConverter {
  public static void main(String[] args) {
    String fahrenheitRead = showInputDialog("How many fahrenheit? ");
    Double fahrenheit = Double.parseDouble(fahrenheitRead);
    Double celsius = (fahrenheit - 32) * 5 / 9;
    String res = fahrenheit + " degrees fahrenheit is " + celsius + " degrees celsius.";
    showMessageDialog(null, res);
  }
}
