/**

	Title:	Table.java
	Date:	06.02.2018
	Author:	Eskil Uhlving Larsen

*/
import java.lang.StringBuilder;
import java.util.ArrayList;

public class Table {
    String[] tables;


    public Table(int numberOfTables) {
        this.tables = new String[numberOfTables];
    }

    // Counts the nr of available tables and returns the nr.
    public int nrOfAvailableTables() {
        int available = 0;
        for (String reservedBy : this.tables) {
            if (reservedBy == null) {
                available++;
            }
        }
        return available;
    }

    // Counts the nr of reserved tables and returns the nr.
    public int nrOfReservedTables() {
        int reserved = 0;
        for (String reservedBy : this.tables) {
            if (reservedBy != null) {
                reserved++;
            }
        }
        return reserved;
    }

    // Reserves a single table on the name given as an argument.
    public boolean reserveTable(String name) {
        if (this.nrOfAvailableTables() >= 1) {
            for (String s : this.tables) {
                if (s == null) {
                    s = name;
                    return true;
                }
            }
        }
        return false;
    }

    // Reserves a set nr of tables for the name given as an argument.
    public boolean reserveTable(String name, int nrOfTables) {
        if (this.nrOfAvailableTables() >= nrOfTables) {
            int nrOfTablesLeft = nrOfTables;

            for (int i = 0; i < this.tables.length; i++) {
                if (nrOfTablesLeft > 0) {
                    if (this.tables[i] == null) {
                        this.tables[i] = name;
                        nrOfTablesLeft--;
                    }
                } else {
                    break;
                }
            }
            return true;
        }
        return false;
    }

    // Checks what tables are reversed by the name parameter
    // and returns an array with the table-indexes.
    public int[] tablesReservedBy(String name) {
        /* Old method, without ArrayList
        int reserved = 0;
        for (int i = 0; i < this.tables.length; i++) {
            if (this.tables[i] != null && this.tables[i].equals(name)) {
                reserved++;
            }
        }
        // Would rather use arrayList, dynamic size (without having to loop through twice)
        int[] tablesReserved = new int[reserved];
        reserved = 0;
        for (int i = 0; i < this.tables.length; i++) {
            if (this.tables[i] != null && this.tables[i].equals(name)) {
                tablesReserved[reserved] = i;
                reserved++;
            }
        }
        return tablesReserved;
        */
        // Method with ArrayList
        ArrayList<Integer> reservedTables = new ArrayList<Integer>();
        for (int i = 0; i < this.tables.length; i++) {
            if (this.tables[i] != null && this.tables[i].equals(name)) {
                reservedTables.add(Integer.valueOf(i));
            }
        }
        int[] result = new int[reservedTables.size()];
        for(int i = 0; i < result.length; i++) {
            result[i] = reservedTables.get(i);
        }
        return result;

    }

    // Sets the table at tableIndex-parameter back to null.
    public boolean cleanTable(int tableIndex) {
        if (this.tables[tableIndex] != null) {
            this.tables[tableIndex] = null;
            return true;
        }
        return false;
    }

    // Logs out table, index and if reserved.
    public String listTables() {
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < this.tables.length; i++) {
            res.append("table" + i + " : " + this.tables[i] + "\n");
        }
        return res.toString();
    }
}
