/**

	Title:	DBCommunication.java
	Date:	07.03.2018
	Author:	Eskil Uhlving Larsen

*/

// Imports
import java.sql.*;
import java.lang.StringBuilder;
import static javax.swing.JOptionPane.*;


public class DBCommunication {
    // Establises a connection to the db and returns it.
    public Connection getConnection() throws Exception {
        String jbdcDriver = "com.mysql.jdbc.Driver";
        Class.forName(jbdcDriver);

        String username = "root";
        String password = "xxxxxx";
        String url = "jdbc:mysql://url.to.database:port/";
        url += username + "?user=" + username + "&password=" + password;
        Connection conn = DriverManager.getConnection(url);
        //Had to put the user credentials directly into the url for some reason
        //Connection conn = DriverManager.getConnection(url, username, password);
        return conn;
    }

    // Creates a conncetion to the db and executes basicly any SQL-query
    // Used to create the data-tables in db
    public int createTable(String query) {
        Connection conn = null;
        Statement statement = null;
        int rs = 0;

        try {
            conn = getConnection();
            statement = conn.createStatement();
            rs = statement.executeUpdate(query);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                statement.close();
                conn.close();
            } catch (SQLException SQLe) {
                SQLe.printStackTrace();
            }
        }
        return rs;
    }

    // Makes a connection and searches for the spesified ISBN
    // If successful, returns a string with title, author and nr of copies in the db
    // Else returns null
    public String searchISBN(String isbn) throws Exception {
        Connection conn = getConnection();
        PreparedStatement bookSearch = null;
        PreparedStatement countSearch = null;
        StringBuilder sb = new StringBuilder();

        String query1 = "select forfatter, tittel from boktittel where isbn = ?";
        String query2 = "select count(*) antall from eksemplar where isbn = ?";
        bookSearch = conn.prepareStatement(query1);
        bookSearch.setString(1, isbn);
        countSearch = conn.prepareStatement(query2);
        countSearch.setString(1, isbn);
        ResultSet bookSearchResults = bookSearch.executeQuery();
        ResultSet countSearchResults = countSearch.executeQuery();
        boolean found = true;
        if (bookSearchResults.next() == false) {
            found = false;
        } else {
            do {
                String dbForfatter = bookSearchResults.getString("forfatter");
                String dbTittel = bookSearchResults.getString("tittel");
                sb.append("Title: " + dbTittel + "\nBy:   " + dbForfatter);
            } while (bookSearchResults.next());
        }
        if (countSearchResults.next() == false) {
            found = false;
        } else {
            do {
                int dbCount = countSearchResults.getInt("antall");
                sb.append("\nFound " + dbCount + " copies.");
            } while (countSearchResults.next());
        }
        countSearch.close();
        bookSearch.close();
        conn.close();
        if (!found) {
            return null;
        }
        return sb.toString();
    }

    // Makes a connection tries to update the loaner if a specific book copy.
    // Returns the number of rows updated (should be only 1 if successful)
    public int loanCopy(String loaner, String isbn, int copyNr) throws Exception {
        Connection conn = getConnection();
        String query = "update eksemplar set laant_av = ? where isbn = ? and eks_nr = ? and laant_av is null";
        PreparedStatement updateLoaner = null;
        updateLoaner = conn.prepareStatement(query);
        updateLoaner.setString(1, loaner);
        updateLoaner.setString(2, isbn);
        updateLoaner.setInt(3, copyNr);
        int updatedRows = updateLoaner.executeUpdate();
        return updatedRows;
    }

    // Makes a connection and gets the count of tittles, then returns it.
    public int bookCount() throws Exception {
        Connection conn = getConnection();
        String query = "select count(*) antall from boktittel";
        Statement countSearch = conn.createStatement();
        ResultSet countSearchResults = countSearch.executeQuery(query);
        countSearchResults.next();
        int dbCount = countSearchResults.getInt("antall");
        return dbCount;
    }

    // Some tests
    public static void main(String[] args) {
        try {
            DBCommunication dbComm = new DBCommunication();
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
            dbComm.createTable(tableBookTitle);
            dbComm.createTable(tableBookCopy);
            String[] sok = {dbComm.searchISBN("0-201-50998-X"), dbComm.searchISBN("0-596-00123-1"), dbComm.searchISBN("notFound")};
            for (int i = 0; i < 3; i++) {
                if (sok[i] != null) {
                    showMessageDialog(null, sok[i]);
                }
            }
            System.out.println(dbComm.loanCopy("Bob", "1234569", 1));
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
