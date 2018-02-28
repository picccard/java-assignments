/**

	Title:	ConferenceCenter.java
	Date:	28.02.2018
	Author:	Eskil Uhlving Larsen

*/
import java.util.ArrayList;
import java.time.LocalDateTime;

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
        return "";
    }

    public static void main(String[] args) {

    }
}
