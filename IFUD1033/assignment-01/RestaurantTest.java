/**

	Title:	RestaurantTest.java
	Date:	07.02.2018
	Author:	Eskil Uhlving Larsen

*/
import static javax.swing.JOptionPane.*;
public class RestaurantTest {
    public static void main(String[] args) {
        final String[] OPTIONS = {"reserve table", "find table(s)", "clean table", "quit"};
        final int RESERVE_TABLE = 0;
        final int FIND_TABLE = 1;
        final int CLEAN_TABLE = 2;
        final int QUIT = 3;

        String resName = showInputDialog("What is the name of the restaurant?");
        String estDate = showInputDialog("What date was it established? (dd-MM-yyyy)");
        estDate = "11-11-1999"; // Used to skip the date-input when testing.
        String numberOfTablesRead = showInputDialog("How many tables is in the restaurant?");
        int numberOfTables = Integer.parseInt(numberOfTablesRead);
        Restaurant restaurant = new Restaurant(resName, estDate, numberOfTables);
        restaurant.listTables();

        int choise = QUIT;
        do {
            choise = showOptionDialog(null, "Action", restaurant.getName(), DEFAULT_OPTION, PLAIN_MESSAGE, null,
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
                        break;
                    }
                case FIND_TABLE:  // finne alle bordene som er reservert på et bestemt navn
                    //....les inn navn, og kall metode...
                    break;
                case CLEAN_TABLE:
                    try {
                        String indexToCleanRead = showInputDialog("What table to clean?");
                        int indexToClean = Integer.parseInt(indexToCleanRead);
                        restaurant.cleanTable(indexToClean);
                    } catch (Exception e) {
                        System.out.println(e);
                    }
                    break;

            }
            System.out.println(restaurant.listTables());
        } while (choise != QUIT);
        /*
        Brukeren skal kunne velge mellom følgende alternativer:

            reserver bord (les inn navn og antall bord)
            finn hvilke bord en bestemt person har reservert (les inn navn)
            frigi bord (les inn aktuelle bordnummer)
            avslutt

        Sørg også for at metodene som finner alderen og endrer restaurantnavnet blir prøvd ut.
        */
    }
}
