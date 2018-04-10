abstract class Tribune {
    private final String tribunename;
    private final int capasity;
    private final int price;

    public Tribune(String name, int cap, int price) {
        this.tribunename = name;
        this.capasity = cap;
        this.price = price;
    }

    public String getTribunename() {
        return tribunename;
    }

    public int getCapasity() {
        return capasity;
    }

    public int getPrice() {
        return price;
    }

    public abstract int findTicketsSoldCount(); // This method gets overloaded in the subclasses.

    public int findIncome() {
        return findTicketsSoldCount() * price;
    }

    public abstract Billett[] buyTickets(int ticketAmount);

    public abstract Billett[] buyTickets(String[] names);

    public String toString() {
        String ret = "Name: " + getTribunename() + "\nCapasity: " + getCapasity() + "\nPrice: " + getPrice() + "\nTicketsSold: " + findTicketsSoldCount() + "\nIncome: " + findIncome();
        return ret;
    }
}

class StandingTribune extends Tribune {
    private int ticketsSold;

    public StandingTribune(String name, int cap, int price) {
        super(name, cap, price);
        ticketsSold = 0;
    }

    public int findTicketsSoldCount() {
        return ticketsSold;
    }

    public Billett[] buyTickets(int ticketAmount) {
        if (getCapasity() < findTicketsSoldCount() + ticketAmount) {
            return null; // Not enough capasity
        }
        Billett[] retTickets = new StaaplassBillett[ticketAmount];
        for (int i = 0; i < ticketAmount; i++) {
            retTickets[i] = new StaaplassBillett(getTribunename(), getPrice());
        }
        ticketsSold += ticketAmount;
        return retTickets;
    }

    public Billett[] buyTickets(String[] names) {
        return buyTickets(names.length);
    }
}

class SittingTribune extends Tribune {
    private int[] countTakenSeats; // size: count rows
    private int seatsPrRow;

    public SittingTribune(String name, int cap, int price, int rows) {
        super(name, cap, price);
        seatsPrRow = cap / rows;
        countTakenSeats = new int[rows];
    }

    public int findTicketsSoldCount() {
        int count = 0;
        for (int c : countTakenSeats) {
            count += c;
        }
        return count;
    }

    // returns a array with the rowNR which holds the most seats available and the nrOfSeatsAvailable
    private int[] findAvailableRow() {
        int[] ret = new int[2]; //int[0] = rowNR, int[1] = seatsAvailable
        for (int i = 0; i < countTakenSeats.length; i++) {
            if (ret[1] < seatsPrRow - countTakenSeats[i]) {
                ret[1] = seatsPrRow - countTakenSeats[i];
                ret[0] = i;
            }
            if (ret[1] == seatsPrRow) {
                // found an empty row, no need to check more
                return ret;
            }
        }
        return ret;
    }

    public Billett[] buyTickets(int ticketAmount) {
        int[] rowInfo = findAvailableRow();
        Billett[] retTickets = new Billett[ticketAmount];
        if (rowInfo[1] < ticketAmount) {
            // Not enough seats available in a single row.
            return null;
        }
        int row = rowInfo[0];
        int nextSeat = seatsPrRow - rowInfo[1]; // eg. 4seatsPrRow-2seats=seat2 is first available (indexed)
        for (int i = 0; i < ticketAmount; i++) {
            nextSeat += i;
            retTickets[i] = new SitteplassBillett(getTribunename(), getPrice(), row, nextSeat);
        }
        countTakenSeats[row] += ticketAmount;
        return retTickets;
    }

    public Billett[] buyTickets(String[] names) {
        return buyTickets(names.length);
    }

    public String toString() {
        String ret = super.toString();
        ret += "\nRows: " + countTakenSeats.length + "\nSeats pr Row: " + (getCapasity() / countTakenSeats.length);
        ret += "\nRowCount:";
        for (int rowCount : countTakenSeats) {
            ret += "\n" + rowCount;
        }
        return ret;
    }
}

class VIPTribune extends SittingTribune {
    private String[][] spectator; // size: count rows * count seats pr row

    public VIPTribune(String name, int cap, int price, int rows) {
        super(name, cap, price, rows);
        spectator = new String[rows][cap/rows]; // seatsPrRow = cap/rows
    }

    public Billett[] buyTickets(int ticketAmount) {
        return null;
    }

    public Billett[] buyTickets(String[] names) {
        Billett[] tickets = super.buyTickets(names.length); // if I change this to just super.buyTickets(names); it wont work, WHY?
        if ((tickets != null) && (tickets.length == names.length)) {
            for (int i = 0; i < tickets.length; i++) {
                SitteplassBillett ticket = (SitteplassBillett) tickets[i];
                spectator[ticket.getRad()][ticket.getPlass()] = names[i];
            }
        } else {
            return null;
        }
        return tickets;
    }

    public String toString() {
        String ret = super.toString();
        ret += "\nTicketHolders:";
        for (int i = 0; i < spectator.length; i++) {
            for (int j = 0; j < spectator[i].length; j++) {
                if (spectator[i][j] != null) {
                    ret += "\n" + i + "," + j + " : " + spectator[i][j];
                }
            }
        }
        return ret;
    }
}