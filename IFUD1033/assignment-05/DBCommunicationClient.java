/**

	Title:	DBCommunicationClient.java
	Date:	13.03.2018
	Author:	Eskil Uhlving Larsen

*/

import java.sql.*;
import static javax.swing.JOptionPane.*;

class DBCommunicationUI {
    // Declares some options
    public final String SEARCH = "Search with ISBN";
    public final String LOAN_BOOK = "Loan a book";
    public final String QUIT = "Quit";
    private String[] OPTIONS = {SEARCH, LOAN_BOOK, QUIT};

    private DBCommunication dbComm;

    public DBCommunicationUI(DBCommunication dbComm) {
        this.dbComm = dbComm;
    }

    /**
     *
     * The method reads the choise as a string and returns it.
     * The choise is gonna be an argument to the doTask()-method.
     * If the program shall be terminated, null is returned.
     */
    public String readChoise() throws Exception {
        int countBooks = dbComm.bookCount();
        String choice = (String) showInputDialog(null, "Choose from the list, " + countBooks + " books:",  "Book Database",
               DEFAULT_OPTION, null, OPTIONS, OPTIONS[0]);
        if (QUIT.equals(choice)) {
        choice = null;
        }
        return choice;
    }

    /**
     *
     * Method executing the choice/task..
     */
    public void doTask(String task) throws Exception {
        if (task != null && !task.equals(QUIT)) {
            if (task.equals(SEARCH)) {
                searchISBN();
            } else if (task.equals(LOAN_BOOK)) {
                loanCopy();
            }
        }
    }

    // Establises a connection to the db and returns it.
    public Connection getConnection() throws Exception {
        return dbComm.getConnection();
    }

    // Creates a conncetion to the db and executes basicly any SQL-query
    // Used to create the data-tables in db
    public int createTable(String query) {
        return dbComm.createTable(query);
    }

    // Asks the user for a ISBN
    // Searches the db for given ISBN
    // Displays the result
    public void searchISBN() throws Exception {
        String isbn = InputReader.readText("Search for an ISBN");
        String result = dbComm.searchISBN(isbn);
        if (result == null) {
            result = "No books found. Try again.";
        }
        showMessageDialog(null, result);
    }

    // Asks user to input loaner, isbn and copyNr
    // Tries to update the loaner of the specific book in db
    // Displays the result.
    public void loanCopy() throws Exception {
        String loaner = InputReader.readText("Type who is loaning the book.");
        String isbn = InputReader.readText("Type the ISBN code of the book.");
        int copyNr = InputReader.readInt("Type what copy of the book " + loaner + " are loaning.");
        int result = dbComm.loanCopy(loaner, isbn, copyNr);
        if (result > 0) {
            showMessageDialog(null, "Successfully updated " + result + " rows.");
        } else {
            showMessageDialog(null, "No rows changed.");
        }
    }
}

class DBCommunicationClient {
    public static void main(String[] args) {
        try {
            DBCommunication dbComm = new DBCommunication();
            DBCommunicationUI dbUI = new DBCommunicationUI(dbComm);
            String tableBookTitle = ""
                + "CREATE TABLE IF NOT EXISTS boktittel ("
                + "isbn VARCHAR(50) NOT NULL,"
                + "forfatter  VARCHAR(50) NOT NULL,"
                + "tittel VARCHAR(50) NOT NULL,"
                + "PRIMARY KEY(isbn));";
            String tableBookCopy = ""
                + "CREATE TABLE IF NOT EXISTS eksemplar ("
                + "isbn VARCHAR(50) NOT NULL,"
                + "eks_nr INTEGER NOT NULL,"
                + "laant_av VARCHAR(50),"
                + "PRIMARY KEY(isbn, eks_nr),"
                + "FOREIGN KEY (isbn) REFERENCES boktittel(isbn));";
            dbUI.createTable(tableBookTitle);
            dbUI.createTable(tableBookCopy);

            // Main loop
            String task = dbUI.readChoise();
            while (task != null) {
              dbUI.doTask(task);
              task = dbUI.readChoise();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
