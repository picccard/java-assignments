/**

	Title:	RestaurantTest.java
	Date:	07.02.2018
	Author:	Eskil Uhlving Larsen

*/
import static javax.swing.JOptionPane.*;
import java.util.Arrays;
import java.text.MessageFormat;
import java.lang.StringBuilder;
import java.time.format.DateTimeFormatter;
import java.time.Period;

public class RestaurantTest {
    public static void main(String[] args) {
        // Variables
        final String[] OPTIONS = {"reserve table", "find table(s)", "clean table", "info", "change name", "quit"};
        final int RESERVE_TABLE = 0;
        final int FIND_TABLE = 1;
        final int CLEAN_TABLE = 2;
        final int INFO = 3;
        final int CHANGE_NAME = 4;
        final int QUIT = 5;

        // Gets name, date, and nr of tables to create a Restaurant object.
        String resName = showInputDialog("What is the name of the restaurant?");
        // Needs exception-handling, if the format is not right an exception is thrown. is anything below 01-01-1970 allowed?
        String estDate = showInputDialog("What date was it established? (dd-MM-yyyy)");
        String numberOfTablesRead = showInputDialog("How many tables is in the restaurant?");
        int numberOfTables = Integer.parseInt(numberOfTablesRead);
        Restaurant restaurant = new Restaurant(resName, estDate, numberOfTables);
        System.out.println(restaurant.listTables());

        // Loop through menu.
        int choise = QUIT;
        do {
            choise = showOptionDialog(null, "Action", restaurant.getName()+ " since " + restaurant.getEstYear(), DEFAULT_OPTION, PLAIN_MESSAGE, null,
                OPTIONS, OPTIONS[0]);
            switch (choise) {
                case RESERVE_TABLE:
                    String reserveOn = showInputDialog("Who should I reserve the table(s) on?");
                    String numberOfTablesToReserveRead = showInputDialog("How many tables should I reserve?");
                    int numberOfTablesToReserve = Integer.parseInt(numberOfTablesToReserveRead);
                    if (restaurant.reserveTable(reserveOn, numberOfTablesToReserve)) {
                        showMessageDialog(null, "Reservation complete!");
                    } else {
                        showMessageDialog(null, "Reservation failed, not enough tables available.");
                    }
                    break;
                case FIND_TABLE:
                    String reservationName = showInputDialog("Who are the tables reserved on?");
                    int[] result = restaurant.tablesReservedBy(reservationName);
                    if (result.length > 0) {
                        String resultString = Arrays.toString(result);
                        String dialogText = MessageFormat.format("{0} have reserved table(s) {1}.", reservationName, resultString);
                        showMessageDialog(null, dialogText);
                    } else {
                        String dialogText = MessageFormat.format("{0} have no active reservations.", reservationName);
                        showMessageDialog(null, dialogText);
                    }
                    break;
                case CLEAN_TABLE:
                    // Should move exeption-handling into the table-class
                    try {
                        String indexToCleanRead = showInputDialog("What table to clean?");
                        int indexToClean = Integer.parseInt(indexToCleanRead);
                        restaurant.cleanTable(indexToClean);
                    } catch (Exception e) {
                        System.out.println(e);
                    }
                    break;
                case INFO:
                    DateTimeFormatter f = DateTimeFormatter.ofPattern("dd MMMM yyyy");
                    Period age = restaurant.getAge();
                    StringBuilder dialogText = new StringBuilder();
                    dialogText.append("This restaurant is named " + restaurant.getName() + ".\n");
                    dialogText.append("And was established ");
                    dialogText.append(restaurant.getEstDate().format(f) + ".\n\n");
                    dialogText.append("Current age is:\n");
                    dialogText.append(MessageFormat.format("{0} years, {1} months, {2} days", age.getYears(), age.getMonths(), age.getDays()));
                    showMessageDialog(null, dialogText);
                    break;
                case CHANGE_NAME:
                    String newName = showInputDialog("What is the new name?");
                    restaurant.setName(newName);
                    showMessageDialog(null, "Restaurant is now called " + restaurant.getName());
                    break;
            }
            System.out.println(restaurant.listTables());
        } while (choise != QUIT);
    }
}
