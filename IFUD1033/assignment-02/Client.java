/**

	Title:	Oppgave1.java
	Date:	28.02.2018
	Translator:	Eskil Uhlving Larsen

*/

import static javax.swing.JOptionPane.*;

/**
 *
 * Program som kan brukes til å prøve ut metodene laget i øving 1.
 *
 * Om det er vanskelig å lese, kan det kanskje være på sin plass å repetere litt:
 *
 * Brukergrensesnittet er lagt til en egen klasse, se kapittel 6.4, side 193.
 * For øvrig er et menystyrt program vist i kapittel 9.6, side 304.
 */

class ClientUI {
  public final String NEW_STUDENT = "New student";
  public final String QUIT = "Quit";
  private String[] OPTIONS = {NEW_STUDENT, QUIT};  // første gang, ingen studenter registrert

  private AssignmentOverview overview;
  public ClientUI(AssignmentOverview overview) {
    this.overview = overview;
  }

  /**
   *
   * Metoden leser inn valget som en streng, og returnerer den.
   * Valget skal være argument til metoden utførValgtOppgave().
   * Hvis programmet skal quitted, returneres null.
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
   * Metode som sørger for at ønsket valg blir utført.
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
   * Metoden registrere ny student.
   * Hvis student med dette navnet allerede eksisterer, skjer ingen registrering.
   * Resultatet av operasjonen skrives ut til brukeren.
   */
  private void regNewStudent() {
    String nameOfNewStud = null;
    do {
      nameOfNewStud = showInputDialog("Oppgi navn: ");
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
     * Metoden registrerer oppgaver for en navngitt student.
     * Brukerinput kontrolleres ved at det må kunne tolkes som et tall.
     * Registreringsmetoden (i klassen Student) kan kaste unntaksobjekt IllegalArgumentException.
     * Dette fanges også opp. I begge tilfeller må brukeren gjenta inntasting inntil ok data.
     * Endelig skrives det ut en melding om antall oppgaver studenten nå har registrert.
     */
    private void acceptAssignments(String studName) {
      String msg = "Number of assignments to accept " + studName +": ";
      int countToBeAccepted = 0;
      boolean registered = false;
      do { // gjentar inntil registrering aksepteres av objektet overview
        try {
          countToBeAccepted = readInt(msg);
          overview.acceptAssignments(studName, countToBeAccepted);  // kan ikke returnere false, pga navn alltid gyldig
          registered = true; // kommer hit bare dersom exception ikke blir kastet
        } catch (IllegalArgumentException e) {  // kommer hit hvis studenter får negativt antall oppgaver
          msg = "You wrote " + countToBeAccepted + ". \nDidn't accept raise-count for " + studName + ". Try again: ";
        }
      } while (!registered);

      msg = "Total accepted assignments for " + studName + " is " + overview.findNrOfAssignments(studName) + ".";
      showMessageDialog(null, msg);
    }

    /* Hjelpemetode som går i løkke inntil brukeren skriver et heltall. */
    private int readInt(String msg) {
      int number = 0;
      boolean ok = false;
      do {  // gjentar inntil brukerinput kan tolkes som tall
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
   * Hovedprogrammet. Går i løkke og lar brukeren gjøre valg.
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