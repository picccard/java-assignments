/**

	Title:	ConferenceCenter.java
	Date:	28.02.2018
	Author:	Eskil Uhlving Larsen

*/
import java.util.ArrayList;

public class ConferenceCenter {
    private ArrayList<Room> rooms;

    public ConferenceCenter() {
        this.rooms = new ArrayList<Room>();
    }

    // Alt constructor
    public ConferenceCenter(Room[] rooms) {
        this.rooms = new ArrayList<Room>();
        for (Room r : rooms) {
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
}
