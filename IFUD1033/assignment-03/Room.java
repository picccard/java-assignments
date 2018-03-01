/**

	Title:	Room.java
	Date:	28.02.2018
	Author:	Eskil Uhlving Larsen

*/

import java.text.MessageFormat;
import java.lang.StringBuilder;
import java.util.ArrayList;
import java.time.LocalDateTime;


public class Room {
    // roomNR is an uniqe identifier
    // size is the number of people that can fit in the room.
    private final int roomNR;
    private final int size;
    private ArrayList<Reservasjon> reservations;

    public Room(int roomNR, int size) {
        this.roomNR = roomNR;
        this.size = size;
        this.reservations = new ArrayList<Reservasjon>();
    }

    public boolean makeReservation(LocalDateTime fromDate, LocalDateTime toDate, Kunde kunde) {
        for (Reservasjon res : this.reservations) {
            if (res.overlapp(fromDate, toDate)) {
                return false;
            }
        }
        // the ArrayList.add methods returns true if successful
        return this.reservations.add(new Reservasjon(fromDate, toDate, kunde));
    }

    public int getSize() {
        return this.size;
    }

    public int getRoomNR() {
        return this.roomNR;
    }

    public String toString() {
        StringBuilder out = new StringBuilder();
        out.append(MessageFormat.format("Room {0} (size of {1}) got {2} reservations: \n", this.roomNR, this.size, this.reservations.size()));
        for (Reservasjon res : this.reservations) {
            out.append(MessageFormat.format("\t{0} - {1}\n", this.reservations.indexOf(res) + 1, res.toString()));
        }
        return out.toString();
    }

    public static void main(String[] args) {
        Room firstRoom = new Room(404, 7);
        Kunde eskil = new Kunde("Eskil", "46771144");
        Kunde adis = new Kunde("Adis", "91111111");

        // Makes a valid reservation, should return true
        System.out.println(
            firstRoom.makeReservation(
                LocalDateTime.of(2003, 2, 1, 10, 0),
                LocalDateTime.of(2003, 2, 1, 11, 0),
                eskil));
        // this reservation should not be accepted because of overlapp, should return false
        System.out.println(
            firstRoom.makeReservation(
                LocalDateTime.of(2003, 2, 1, 10, 30),
                LocalDateTime.of(2003, 2, 1, 11, 30),
                eskil));
        // another valid reservation
        System.out.println(
            firstRoom.makeReservation(
                LocalDateTime.of(2003, 2, 1, 11, 00),
                LocalDateTime.of(2003, 2, 1, 12, 00),
                adis));
        System.out.println(firstRoom);

        //Testing the return value from ArrayList.add method
        ArrayList<String> te = new ArrayList<String>();
        System.out.println(te.add("te"));
    }
}
