/**

	Title:	ConferenceCenter.java
	Date:	28.02.2018
	Author:	Eskil Uhlving Larsen

*/
import java.util.ArrayList;
import java.time.LocalDateTime;
import java.text.MessageFormat;
import java.lang.StringBuilder;

public class ConferenceCenter {
    private ArrayList<Room> rooms;

    public ConferenceCenter() {
        this.rooms = new ArrayList<Room>();
    }

    // Alt constructor
    public ConferenceCenter(Room[] rooms) {
        this.rooms = new ArrayList<Room>();
        for (Room r : this.rooms) {
            this.rooms.add(r);
        }
    }

    public boolean regNewRoom(int roomNR, int size) {
        for (Room room : this.rooms) {
            if (room.getRoomNR() == roomNR) {
                // throw new IllegalArgumentException("RoomNR already exist")
                return false;
            }
            if (size < 1 || size > 100) { // Rooms must have a size between 1 and 100
                return false;
                // throw new IllegalArgumentException("size is not valid")
            }
        }

        return this.rooms.add(new Room(roomNR, size));
    }

    public boolean makeReservation(LocalDateTime fromDate, LocalDateTime toDate, int countPersons, Kunde customer) {
        for (Room room : this.rooms) {
            // Loops through every room
            // If the room is big enough
            // attempt to make a reservation on the room, return true if successful.
            // If the room ain't available, the room.makeReservation wil return false
            // And the method will attempt again on the next room big enough
            // If no reservation is posible, return false
            if (room.getSize() >= countPersons) {
                if (room.makeReservation(fromDate, toDate, customer)) {
                    return true;
                }
            }
        }
        // No reservation was made
        return false;
    }

    public boolean makeReservation(LocalDateTime fromDate, LocalDateTime toDate, int countPersons, String name, String phoneNR) {
        Kunde customer = new Kunde(name, phoneNR);
        return this.makeReservation(fromDate, toDate, countPersons, customer);
    }

    public int getRoomCount() {
        return this.rooms.size();
    }

    public Room findRoomFromIndex(int index) {
        return this.rooms.get(index);
        // this might throw IndexOutOfBoundsException
    }

    public Room findRoomFromRoomNR(int roomNR) {
        for (Room room : this.rooms) {
            if (room.getRoomNR() == roomNR) {
                // Found the room
                return room;
            }
        }
        // Didn't find the room
        return null;
    }

    public String toString() {
        StringBuilder out = new StringBuilder();
        out.append(MessageFormat.format("ConferenceCenter have {0} rooms: \n", this.rooms.size()));
        for (Room room : this.rooms) {
            out.append(room.toString());
        }
        return out.toString();
    }

    public static void main(String[] args) {
        // Test all methods

        // Create ConferenceCenter-object
        ConferenceCenter cc = new ConferenceCenter();
        // adding rooms to cc, all should return true
        // except room 202, it's added twice and should return false the 2nd time.
        System.out.println(cc.regNewRoom(201, 4));
        System.out.println(cc.regNewRoom(202, 2));
        System.out.println(cc.regNewRoom(204, 8));
        System.out.println(cc.regNewRoom(202, 6));
        System.out.println(cc.regNewRoom(301, 4));
        // Printing the ConferenceCenter, should call toString
        System.out.println(cc);
        System.out.println("ConferenceCenter room-count: " + cc.getRoomCount());
        // Normal reservation
        System.out.println(cc.makeReservation(
            LocalDateTime.of(2003, 2, 1, 10, 0),
            LocalDateTime.of(2003, 2, 1, 11, 0),
            6,
            "Bob Ross", "91111111"
        ));
        // Overlapping reservaion, also with 6 people, should return false
        System.out.println(cc.makeReservation(
            LocalDateTime.of(2003, 2, 1, 10, 0),
            LocalDateTime.of(2003, 2, 1, 11, 0),
            6,
            "Bob Ross", "91111111"
        ));
        // Same reservation, later time
        System.out.println(cc.makeReservation(
            LocalDateTime.of(2003, 2, 1, 11, 0),
            LocalDateTime.of(2003, 2, 1, 12, 0),
            6,
            "Bob Ross", "91111111"
        ));
        System.out.println(cc.makeReservation(
            LocalDateTime.of(2003, 2, 1, 11, 0),
            LocalDateTime.of(2003, 2, 1, 12, 0),
            4,
            "Alice Ross", "92222222"
        ));
        System.out.println(cc.makeReservation(
            LocalDateTime.of(2003, 2, 1, 11, 0),
            LocalDateTime.of(2003, 2, 1, 12, 0),
            4,
            "Alice Ross", "92222222"
        ));
        System.out.println(cc);
        System.out.println("--------------------------------------------");
        System.out.println("Trying to find room with index 3:");
        System.out.println(cc.findRoomFromIndex(3));
        System.out.println("Trying to find roomNR 202:");
        System.out.println(cc.findRoomFromRoomNR(202));

    }

    // makeReservation should be changed to avoid this...
    // we have two rooms with the size of 8 and 6, in that order.
    // two teams of 6 and 8 make one reservation each, overlapping, in that order.
    // Currently the team of 6 will reserve the room for 8 people making the
    // team unable to make a successfully reservation.
}
