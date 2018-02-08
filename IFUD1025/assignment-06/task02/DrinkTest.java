/**

	Title:	DrinkTest.java
	Date:	06.03.2017
	Author:	Eskil Uhlving Larsen

*/
import static javax.swing.JOptionPane.*;
class DrinkTest {
  public static void main(String[] args) {
    final String[] OPTIONS = {"wine", "beer", "cognac", "quit"};
    final int WINE = 0;
    final int BEER = 1;
    final int COGNAC = 2;
    final int QUIT = 3;
    boolean man;
    double alcoholContent = 0;

    String weightRead = showInputDialog("Weight");
    //TODO: Test input, throw exception "void weight"
    while (weightRead == null || weightRead.isEmpty()) {
      showMessageDialog(null, "Input a void weight");
      weightRead = showInputDialog("Weight");
    }
    double weight = Double.parseDouble(weightRead);

    int sex = showConfirmDialog(null, "Are you male?", "Sex", YES_NO_OPTION);
    while (sex == -1) {
      showMessageDialog(null, "Chose an option");
      sex = showConfirmDialog(null, "Are you male?", "Sex", YES_NO_OPTION);
    }
    if (sex == YES_OPTION) {
      man = true;
    } else {
      man = false;
    }

    int choise = showOptionDialog(null, "Chose", "Drink", DEFAULT_OPTION, PLAIN_MESSAGE, null,
        OPTIONS, OPTIONS[0]);

    while (choise != QUIT && choise != -1) {
      String mlRead = showInputDialog("How many ml are you gonna drink?");
      int ml = Integer.parseInt(mlRead);
      Drink myDrink = null;
      if (choise == WINE) {
        myDrink = new Drink("Wine", 12);
    } else if (choise == BEER) {
        myDrink = new Drink("Beer", 4.7);
    } else if (choise == COGNAC) {
        myDrink = new Drink("Cognac", 43);
      }

      alcoholContent += myDrink.findAlcoholConcentrationInBlood(ml, weight, man);
      showMessageDialog(null, "Alkoholprosent: " + alcoholContent);
      if (alcoholContent > 0.02) {
        showMessageDialog(null, "You are not allowed to drive in Norway.");
      } else {
        showMessageDialog(null, "You are allowed to drive in Norway.");
      }
      choise = showOptionDialog(null, "Chose", "Drink", DEFAULT_OPTION, PLAIN_MESSAGE, null,
          OPTIONS, OPTIONS[0]);
    }
  }
}
