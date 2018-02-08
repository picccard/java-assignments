/**

	Title:	Restaurant.java
	Date:	06.02.2018
	Author:	Eskil Uhlving Larsen

*/
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.Period;
//import java.time.temporal.ChronoUnit;

public class Restaurant {
    private String name;
    private Table tables;
    private final LocalDate estDate;
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

    // Restaurant('MyRestaurant', '21-02-1970', 20)
    public Restaurant(String name, String estDate, int numberOfTables) {
        this.name = name;
        this.estDate = LocalDate.parse(estDate, this.formatter);
        this.tables = new Table(numberOfTables);
    }

    public Restaurant(String name, LocalDate estDate, int numberOfTables) {
        this.name = name;
        this.estDate = estDate;
        this.tables = new Table(numberOfTables);
    }

    public String getName() {
        return this.name;
    }

    public void setName(String newName) {
        this.name = newName;
    }

    public LocalDate getEstDate() {
        return this.estDate;
    }

    public int getEstYear() {
        return this.estDate.getYear();
    }

    public Period getAge() {
        LocalDate today = LocalDate.now();
        Period p = Period.between(this.estDate, today);
        return p;
        //long daysOld = ChronoUnit.DAYS.between(this.estDate, today);
    }

    public int nrOfAvailableTables() {
        return this.tables.nrOfAvailableTables();
    }

    public int nrOfReservedTables() {
        return this.tables.nrOfReservedTables();
    }

    public boolean reserveTable(String name) {
        return this.tables.reserveTable(name);
    }

    public boolean reserveTable(String name, int nrOfTables) {
        return this.tables.reserveTable(name, nrOfTables);
    }

    public int[] tablesReservedBy(String name) {
        return this.tables.tablesReservedBy(name);
    }

    public boolean cleanTable(int tableIndex) {
        return this.tables.cleanTable(tableIndex);
    }

    public String listTables() {
        return this.tables.listTables();
    }
}
