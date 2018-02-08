/**

	Title:	Table.java
	Date:	06.02.2018
	Author:	Eskil Uhlving Larsen

*/
import java.lang.StringBuilder;
public class Table {
    String[] tables;

    public Table(int numberOfTables) {
        this.tables = new String[numberOfTables];
    }

    public int nrOfAvailableTables() {
        int available = 0;
        for (String reservedBy : this.tables) {
            if (reservedBy == null) {
                available++;
            }
        }
        return available;
    }

    public int nrOfReservedTables() {
        int reserved = 0;
        for (String reservedBy : this.tables) {
            if (reservedBy != null) {
                reserved++;
            }
        }
        return reserved;
    }

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

    public int[] tablesReservedBy(String name) {
        int reserved = 0;
        for (int i = 0; i < this.tables.length; i++) {
            if (this.tables[i] != null && this.tables[i].equals(name)) {
                reserved++;
            }
        }
        // Would rather use arrayList, dynamic size
        int[] tablesReserved = new int[reserved];
        reserved = 0;
        for (int i = 0; i < this.tables.length; i++) {
            if (this.tables[i] != null && this.tables[i].equals(name)) {
                tablesReserved[reserved] = i;
                reserved++;
            }
        }
        return tablesReserved;
    }

    public boolean cleanTable(int tableIndex) {
        if (this.tables[tableIndex] != null) {
            this.tables[tableIndex] = null;
            return true;
        }
        return false;
    }

    public String listTables() {
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < this.tables.length; i++) {
            res.append("table" + i + " : " + this.tables[i] + "\n");
        }
        return res;
    }
}
