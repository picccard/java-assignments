/**

	Title:         Ticket.java
	Date:          2010-01-18
    Author:        Else Lervik
    Translator:    Eskil Uhlving Larsen (10.04.2018)

*/
/**
 * Class Ticket with subclasses
 */
abstract class Ticket {
    private final String tribunename;
    private final int price;

    /**
    * Constructor:
    * Tribunename must be given. Price got no requirements.
    */
    public Ticket(String tribunename, int price) {
        if (tribunename == null || tribunename.trim().equals("")) {
            throw new IllegalArgumentException("tribunename must be given.");
        }
        this.tribunename = tribunename.trim();
        this.price = price;
    }

    public String getTribunename() {
        return tribunename;
    }

    public int getPrice() {
        return price;
    }

    public String toString() {
        return "Tribunename: "+ tribunename + " & price: " + price;
    }
}

/**
 * StandingTicket.
 */
class StandingTicket extends Ticket {
    public StandingTicket(String tribunename, int price) {
        super(tribunename, price);
    }
}

/**
 * SittingTicket. Row and seat on the row must be given.
 */
class SittingTicket extends Ticket {
    private final int row;
    private final int seat;

    public SittingTicket(String tribunename, int price, int row, int seat) {
        super(tribunename, price);
        if (row < 0 || seat < 0) {
            throw new IllegalArgumentException("Neither row or seat can be negative.\n"
                                                           + "Values received: " + row + ", " + seat);
        }
    this.row = row;
    this.seat = seat;
    }

    public int getRow() {
        return row;
    }

    public int getSeat() {
        return seat;
    }

    public String toString() {
        String ret = super.toString();
        ret += " row: "+ row + " seat: " + seat;
        return ret;
    }
}
