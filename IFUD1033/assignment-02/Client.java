/**

	Title:	    Client.java (org. Oppgave1.java)
	Date:	    28.02.2018
    Author:     Else Lervik og Stiftelsen TISIP
    Translator: Eskil Uhlving Larsen

*/

import static javax.swing.JOptionPane.*;

/**
 *
 * Program to test the methods in AssignmentOverview.
 *
 */

class ClientUI {
  public final String NEW_STUDENT = "New student";
  public final String QUIT = "Quit";
  private String[] OPTIONS = {NEW_STUDENT, QUIT};  // the first time, no students exists

  private AssignmentOverview overview;
  public ClientUI(AssignmentOverview overview) {
    this.overview = overview;
  }

  /**
   *
   * The method reads the choise as a string and returns it.
   * The choise is gonna be an argument to the doTask()-method.
   * If the program shall be terminated, null is returned.
   */
  public String readChoise() {
    int countStud = overview.findNrOfStud();
    String choice = (String) showInputDialog(null, "Choose from the list, " + countStud + " students:",  "Accepted assignments",
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
      if (task.equals(NEW_STUDENT)) {
        regNewStudent();
      } else {
        acceptAssignments(task);  // task is the name of the student
      }
    }
  }

  /**
   *
   * Method registers a new student. .
   * This the student-name already exists, nothing happens.
   * The result if the operation is shown to the user.
   */
  private void regNewStudent() {
    String nameOfNewStud = null;
    do {
      nameOfNewStud = showInputDialog("Enter name: ");
    } while (nameOfNewStud == null);

    nameOfNewStud = nameOfNewStud.trim();
    if (overview.regNewStudent(nameOfNewStud)) {
      showMessageDialog(null, nameOfNewStud + " is registered.");
      String[] allStudNames = overview.findAllStudentNames();
      String[] newOptions = new String[allStudNames.length + 2];
      for (int i = 0; i < allStudNames.length; i++) {
        newOptions[i] = allStudNames[i];
      }
      newOptions[allStudNames.length] = NEW_STUDENT;
      newOptions[allStudNames.length + 1] = QUIT;
      OPTIONS = newOptions;
      } else  {
        showMessageDialog(null, nameOfNewStud + " is already registered.");
      }
    }

    /**
     *
     * Method increases the number of acceptAssignments for the named student.
     * Userinput interpreted as a number.
     * The redistrationmethod(in the Student class) can throw an IllegalArgumentException.
     * This is caught.
     * At the end it's shown to the user, how many acceptAssignments the student got.
     */
    private void acceptAssignments(String studName) {
      String msg = "Number of assignments to accept " + studName +": ";
      int countToBeAccepted = 0;
      boolean registered = false;
      do { // repeats untill the registration is successful
        try {
          countToBeAccepted = readInt(msg);
          overview.acceptAssignments(studName, countToBeAccepted);  // cant return false, name always correct.
          registered = true; // only gets here if none exception-thrown
      } catch (IllegalArgumentException e) {  // gets here is nr of assignments is negative.
          msg = "You wrote " + countToBeAccepted + ". \nDidn't accept raise-count for " + studName + ". Try again: ";
        }
      } while (!registered);

      msg = "Total accepted assignments for " + studName + " is " + overview.findNrOfAssignments(studName) + ".";
      showMessageDialog(null, msg);
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
          showMessageDialog(null, "Can't interpret what you wrote as a number. Try again. ");
        }
      } while (!ok);
      return number;
    }
  }


  /**
   * Main. Loops and lets user make a choise.
   */
  class Client {
    public static void main(String[] args) {

    AssignmentOverview overview = new AssignmentOverview();
    ClientUI clientUI = new ClientUI(overview);

    String task = clientUI.readChoise();
    while (task != null) {
      clientUI.doTask(task);
      task = clientUI.readChoise();
    }

    /* Executes toString() */
    System.out.println("\nInfo about all the students: ");
    System.out.println(overview);
  }
}
