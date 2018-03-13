/**

	Title:	InputReader.java
	Date:	13.03.2018
	Author:	Eskil Uhlving Larsen

*/

import static javax.swing.JOptionPane.*;
import javax.swing.*;

class InputReader {
  /**
   * Reads the passwordinput from the user.
   * The text is trimmed before it is returned.
   */
  public static String readPassword() {
    JLabel jPassword = new JLabel("Password: "); // simplyfied: http://www.asjava.com/swing/joptionpane-showinputdialog-with-password/
    JTextField password = new JPasswordField();
    Object[] obj = {jPassword, password};
    showConfirmDialog(null, obj, "Please input password for JOptionPane showConfirmDialog", OK_CANCEL_OPTION);
    return password.getText().trim();
  }

  /**
   * Reads text from user. Blank text is not accepted.
   * Text is trimmed before returned.
   */
  public static String readText(String msg) {
    String text = showInputDialog(msg);
    while (text == null || text.trim().equals("")) {
      showMessageDialog(null, "Textfield can't be empty.");
      text = showInputDialog(msg);
    }
    return text.trim();
  }

  /* Helping method that loops untill user inputs an integer. */
  public static int readInt(String msg) {
    int number = 0;
    boolean ok = false;
    do {  // loops until userinput is an integer
      String numberRead = showInputDialog(msg);
      try {
        number = Integer.parseInt(numberRead);
        ok = true;
      } catch (Exception e) {
        showMessageDialog(null, "Can't interpret what you wrote as a number. Try again.");
      }
    } while (!ok);
    return number;
  }
}
