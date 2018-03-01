/**

	Title:	    Client.java
	Date:	    01.03.2018
    Author:     Eskil Uhlving Larsen

*/

import static javax.swing.JOptionPane.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.text.MessageFormat;
import java.lang.StringBuilder;

/**
 *
 * Program to test the methods in ConferenceCenter.
 *
 */

class ClientUI {
  public final String NEW_ROOM = "New room";
  public final String MAKE_RESERVATION = "New reservation";
  public final String LIST_ROOMS = "Get room-list";
  public final String FIND_ROOM_INDEX = "Find room from index";
  public final String FIND_ROOM_NR = "Find room from roomnr";
  public final String QUIT = "Quit";
  private String[] OPTIONS = {NEW_ROOM, MAKE_RESERVATION, LIST_ROOMS, FIND_ROOM_NR, FIND_ROOM_INDEX, QUIT};  // the first time, no students exists

  private ConferenceCenter cc;
  public ClientUI(ConferenceCenter cc) {
    this.cc = cc;
  }

  /**
   *
   * The method reads the choise as a string and returns it.
   * The choise is gonna be an argument to the doTask()-method.
   * If the program shall be terminated, null is returned.
   */
  public String readChoise() {
    int countRooms = cc.getRoomCount();
    String choice = (String) showInputDialog(null, "Choose from the list, " + countRooms + " rooms:",  "Conference Center",
             DEFAULT_OPTION, null, OPTIONS, OPTIONS[0]);
    if (QUIT.equals(choice)) {
      choice = null;
    }
    return choice;
  }

  /**
   *
   * Method executing the choice/task..
   */
  public void doTask(String task) {
    if (task != null && !task.equals(QUIT)) {
      if (task.equals(NEW_ROOM)) {
        regNewRoom();
      } else if (task.equals(MAKE_RESERVATION)) {
        makeReservation();
      } else if (task.equals(LIST_ROOMS)) {
          listRooms();
      } else if (task.equals(FIND_ROOM_NR)) {
          findRoomFromRoomNR();
      } else if (task.equals(FIND_ROOM_INDEX)) {
          findRoomFromIndex();
      }
    }
  }

  /**
   *
   * Method registers a new room. .
   * This the room-nr already exists, nothing happens.
   * The result if the operation is shown to the user.
   */
  private void regNewRoom() {
    String msg = "";
    int newRoomNR = 0;
    int newRoomSIZE = 0;
    boolean registered = false;
    do { // repeats untill gjentar inntil registrering aksepteres av objektet overview
      try {
        newRoomNR = readInt("Enter room number:");
        newRoomSIZE = readInt("Enter room size: ");
        cc.regNewRoom(newRoomNR, newRoomSIZE); // This might throw exception
        msg = "Registered room number " + newRoomNR + ".";
        registered = true; // only gets here if none exception-thrown
      } catch (IllegalArgumentException e) {  // gets here is size is illegal or if roomnr already exists.
        msg = e.getMessage();
      }
    } while (!registered);
    showMessageDialog(null, msg);
  }

    /**
     *
     * Method to create a reservation.
     * Userinput for two dates [fromDate, toDate], size of the group, contact info [name, phone].
     * The date parsing might throw an exception.
     * This is caught.
     * At the end the result is shown to the user.
     */
    private void makeReservation() {
      DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
      String readFromDate;
      String readToDate;
      LocalDateTime reservFromDate;
      LocalDateTime reservToDate;
      String resultMsg;
      try {
          readFromDate = showInputDialog("When should the reservation begin?\nformat: yyyy-mm-dd HH:mm");
          readToDate = showInputDialog("When should the reservation end?\nformat: yyyy-mm-dd HH:mm");
          reservFromDate = LocalDateTime.parse(readFromDate, formatter);
          reservToDate = LocalDateTime.parse(readToDate, formatter);
          int reservSize = readInt("How many people should the room fit?");
          String name = showInputDialog("Who is making the reservation?");
          String phoneNR = showInputDialog("On what number can we reach " + name + "?");
          if (cc.makeReservation(reservFromDate, reservToDate, reservSize, name, phoneNR)) {
              resultMsg = MessageFormat.format("Successfully made a reservation for {0} for {1} people, from {2} to {3}.", name, reservSize, readFromDate, readToDate);
          } else {
              resultMsg = "Reservation failed.";
          }
      } catch (Exception e) {
          resultMsg = MessageFormat.format("Date not valid. Aborting.\n{0}", e.getMessage());
      }
      showMessageDialog(null, resultMsg);
  }

    /* Helping method that loops untill user inputs an integer. */
    private int readInt(String msg) {
      int number = 0;
      boolean ok = false;
      do {  // loops until userinput is an integer
        String numberRead = showInputDialog(msg);
        try {
          number = Integer.parseInt(numberRead);
          ok = true;
        } catch (Exception e) {
          showMessageDialog(null, "Can't interpret what you wrote as a number. Try again.");
        }
      } while (!ok);
      return number;
    }

    public void listRooms() {
        StringBuilder sb = new StringBuilder();
        for (Room room : cc.getRooms()) {
            sb.append(MessageFormat.format("Index {0}, room nr {1}, size {2}\n", cc.getRooms().indexOf(room), room.getRoomNR(), room.getSize()));
        }
        showMessageDialog(null, sb.toString());
    }

    public void findRoomFromRoomNR() {
        int roomNR = readInt("Enter room number");
        String msg;
        Room foundRoom = cc.findRoomFromRoomNR(roomNR);
        if (foundRoom != null) {
            msg = foundRoom.toString();
        } else {
            msg = MessageFormat.format("Didnt find room {0}", roomNR);
        }
        showMessageDialog(null, msg);
    }

    public void findRoomFromIndex() {
        int roomIndex = readInt("Enter room index");
        String msg = "";
        try {
            Room foundRoom = cc.findRoomFromIndex(roomIndex);
            msg = foundRoom.toString();
        } catch (IndexOutOfBoundsException e) {
            msg = MessageFormat.format("{0} is not an valid index.", roomIndex);
        }
        showMessageDialog(null, msg);
    }
  }


  /**
   * Main. Loops and lets user make a choise.
   */
  class Client {
    public static void main(String[] args) {

    ConferenceCenter cc = new ConferenceCenter();
    ClientUI clientUI = new ClientUI(cc);

    String task = clientUI.readChoise();
    while (task != null) {
      clientUI.doTask(task);
      task = clientUI.readChoise();
    }

    /* Executes toString() */
    System.out.println("\nInfo about Conference Center: ");
    System.out.println(cc);
  }
}
