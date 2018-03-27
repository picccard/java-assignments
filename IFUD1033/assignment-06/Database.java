/**

	Title:	Database.java
	Date:	20.03.2018
	Author:	Eskil Uhlving Larsen

*/

import java.sql.*;
// Exception for duplicate primary key and
// Exception when foreign key references a primary key that doesn't exist
import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;

public class Database {
    private Connection connection;

    public Database(String dbDriver, String dbName) throws Exception {
        try {
          Class.forName(dbDriver);
          connection = DriverManager.getConnection(dbName);
        } catch (Exception e) {
          DBCleaner.typeMessage(e, "constructor");
          throw e;
        }
    }

    public void closeConnection() {
        DBCleaner.closeConnection(connection);
    }

    //Method should return false of book with ISBN lready exists. Then nothing should happen.
    public boolean regNewBook(Book newBook) {
        String query1 = "insert into boktittel(isbn, forfatter, tittel) values(?, ?, ?);";
        String query2 = "insert into eksemplar(isbn, eks_nr) values (?, 1);";
        PreparedStatement pStatmnt1 = null;
        PreparedStatement pStatmnt2 = null;
        try {
            pStatmnt1 = connection.prepareStatement(query1);
            pStatmnt1.setString(1, newBook.getIsbn());
            pStatmnt1.setString(2, newBook.getAuthor());
            pStatmnt1.setString(3, newBook.getTitle());
            int res1 = pStatmnt1.executeUpdate(); // This might throw MySQLIntegrityConstraintViolationException
            pStatmnt2 = connection.prepareStatement(query2);
            pStatmnt2.setString(1, newBook.getIsbn());
            int res2 = pStatmnt2.executeUpdate();
            return (res1 > 0 && res2 > 0); // If both is ok 1 row is updated in each statement
        } catch (MySQLIntegrityConstraintViolationException e) {
            // Reason: ISBN (primary key) is not uniqe, returning false;
            DBCleaner.typeMessage(e, "regNewBook()");
            return false;
        } catch (Exception e) {
            DBCleaner.typeMessage(e, "regNewBook()");
            return false;
        } finally {
            DBCleaner.closeStatement(pStatmnt1);
            DBCleaner.closeStatement(pStatmnt2);
        }
    }

    public int regNewCopy(String isbn) {
        /*
        This method should registrer a new copy of a title which should be registred beforehand.
        The copy number (eks_nr i the db) should be 1 greater than the existing highest. I do not use autoincrement.

        Method should return 0 if no title with given ISBN exist,
        otherwise should the copy number (eks_nr in db) get returned.
        */

        int newNr = 1; // if there isnt any data in the db
        boolean ok = false;
        int triesCount = 0;
        ResultSet res = null;
        PreparedStatement selectQuery = null;
        PreparedStatement insertQuery = null;

        do {
            try {
                String selectSql = "select max(eks_nr) as maks from     eksemplar";
                selectQuery = connection.prepareStatement(selectSql);
                res = selectQuery.executeQuery();
                /*
                If there isn't any data in the table, max() will return SQL NULL.
                getInt() will parse this to 0, newNr then becomes 1.
                */
                res.next();
                newNr = res.getInt("maks") + 1;
                String insertSql = "insert into eksemplar(isbn, eks_nr) values (?, ?);";
                insertQuery = connection.prepareStatement(insertSql);
                insertQuery.setString(1, isbn);
                insertQuery.setString(2, Integer.toString(newNr));
                System.out.println(insertQuery);
                insertQuery.executeUpdate();
                ok = true;
            } catch (MySQLIntegrityConstraintViolationException e) {
                // Foreign key references a primary key that doesn't exist (no title with isnb exist)
                //DBCleaner.typeMessage(e, "regNyttEksemplar()");
                DBCleaner.typeMessage(e, "No title with isbn " + isbn + " exists.");
                return 0;

            } catch (SQLException e) {
                /*
                If the error is because the eks_nr already exists
                this means someone (a client) have added a copy to the db
                between the two SQL queries.
                To solve this we try to run the query 3 more times.
                This will not happen often, only rare cases.
                */
                if (triesCount < 4) {
                    triesCount++;
                } else {
                    DBCleaner.typeMessage(e, "regNewCopy()");
                    return -1; // RETURN, **unknown** error occured    (finally-block gets executed)
                }
            } finally {
                DBCleaner.closeResSet(res);
                DBCleaner.closeStatement(selectQuery);
                DBCleaner.closeStatement(insertQuery);
            }
        } while (!ok);
        return newNr;
    }

    public boolean loanBook(String isbn, String name, int copyNr) {
        /*
        This method should register a book as loaned.
        The collum "utlånt" for the specimen should be set equal to the
        name of the loaner. Any old loaner value gets overwritten.

        The method should return false if the ISBN and/or copyNr isn't
        valid.
        */

        String query = "update eksemplar set laant_av = ? where isbn = ? and eks_nr = ?";
        PreparedStatement pStatmnt = null;

        try {
            pStatmnt = connection.prepareStatement(query);
            pStatmnt.setString(1, name);
            pStatmnt.setString(2, isbn);
            pStatmnt.setInt(3, copyNr);
            return (pStatmnt.executeUpdate() > 0);
        } catch (SQLException e) {
            DBCleaner.typeMessage(e, "loanBook()");
        } finally {
            DBCleaner.closeStatement(pStatmnt);
        }
        return false;
    }

    public static void main(String[] args) throws Exception {
        String driver = "com.mysql.jdbc.Driver";
        String url = "jdbc:mysql://mysql.stud.xxx.xxxx.xx:port/dbName?user=username&password=pwd";
        url += "&autoReconnect=true&useSSL=false";
        Database db = new Database(driver, url);

        //Some testing of each of the methods.
        System.out.println(db.regNewBook(new Book("1235", "titttel", "fatter")));
        System.out.println(db.regNewCopy("0-596-00123-1"));
        System.out.println(db.loanBook("0-201-50998-X", "alice", 1));

        db.closeConnection();
    }
}
