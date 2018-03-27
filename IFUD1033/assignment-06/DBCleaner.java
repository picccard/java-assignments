/**

	Title:         DBCleaner.java
	Date:          20.03.2018
    Author:        Else Lervik
	Translator:    Eskil Uhlving Larsen

*/

/**
 *
 * Opprydder.java  - "Programmering i Java", 4.utgave - 2009-07-01
 * Methods to clean up after database usage
 */

import java.sql.*;
public class DBCleaner {
  public static void closeResSet(ResultSet res) {
    try {
      if (res != null) {
        res.close();
      }
    } catch (SQLException e) {
      typeMessage(e, "closeResSet()");
    }
  }

  public static void closeStatement(Statement stm) {
    try {
      if (stm != null) {
        stm.close();
      }
    } catch (SQLException e) {
      typeMessage(e, "closeStatement()");
    }
  }

  public static void closeConnection(Connection conn) {
    try {
      if (conn != null) {
        conn.close();
      }
    } catch (SQLException e) {
      typeMessage(e, "closeConnection()");
    }
  }

  public static void rollback(Connection conn) {
    try {
      if (conn != null && !conn.getAutoCommit()) {
        conn.rollback();
      }
    } catch (SQLException e) {
      typeMessage(e, "rollback()");
    }
  }

  public static void enableAutoCommit(Connection conn) {
    try {
      if (conn != null && !conn.getAutoCommit()) {
        conn.setAutoCommit(true);
      }
    } catch (SQLException e) {
      typeMessage(e, "enableAutoCommit()");
    }
  }

  public static void typeMessage(Exception e, String message) {
    System.err.println("*** Error occurred: " + message + ". ***");
    e.printStackTrace(System.err);
  }
}
