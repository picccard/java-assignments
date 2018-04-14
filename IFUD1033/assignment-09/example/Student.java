/**

	Title:         Student.java
	Date:
    Author:        Else Lervik
    Translator:    Eskil Uhlving Larsen (xx.0x.2018)

*/
/**
 * KLASSEN STUDENT.
 * Et studentobjekt er et personobjekt med tilleggsinformasjon om
 * studentnummer og alle fagene studenten har tatt eller skal ta.
 * Listen med fag kan utvides.
 * Det er videre mulig å registrere og hente ut karakteren i et bestemt fag.
 */
class Student extends Person {
  public static final char FAGNR_IKKE_REGISTRERT = 'Y';

  private final long studnr;
  private java.util.ArrayList<Fag> alleFagene = new java.util.ArrayList<Fag>();

  public Student(long fnr, String navn, String adresse, long studnr) {
    super(fnr, navn, adresse);
    this.studnr = studnr;
  }

  public long getStudnr() {
    return studnr;
  }

 /**
  * Metoden tar fagnummer som argument.
  * Den registrerer nytt fag med dette nummeret, dersom det ikke er registrert fra før.
  * Metoden returnerer en referanse til fagobjektet, uansett om det er nytt eller ikke.
  */
  public Fag registrerNyttFag(String fagnr) {
    Fag f = finnFag(fagnr);
    if (f == null) { // intet fag med dette fagnr er registrert fra før
      Fag nyttFag = new Fag(fagnr);
      alleFagene.add(nyttFag);
      return nyttFag;
    }
    return f;
  }

  /**
   * Metoden finner karakteren i et bestemt fag.
   */
  public char finnKarakter(String fagnr) {
    for (Fag etFag : alleFagene) {
      String fnr = etFag.getFagnr();
      if (fnr.equals(fagnr)) {
        return etFag.getKarakter();
      }
    }
    return FAGNR_IKKE_REGISTRERT; // har gått gjennom alle og ikke funnet faget
  }

  /**
   * Metoden registrerer karakter i et bestemt fag.
   * Det er ingen kontroll av at karakteren er gyldig.
   * Hvis fag med den oppgitte koden ikke er registrert fra før,
   * så blir det registrert.
   */
  public void settKarakter(String fagnr, char nyKarakter) {
    Fag f = registrerNyttFag(fagnr); // registrering hvis ikke registrert fra før
    f.setKarakter(nyKarakter);
  }

  /**
   * Metoden leter opp fag med det oppgitte nummer.
   * Returnerer null hvis fag ikke finnes.
   */
  public Fag finnFag(String fagnr) {
    for (Fag etFag : alleFagene) {
      String fnr = etFag.getFagnr();
      if (fnr.equals(fagnr)) {
        return etFag;
      }
    }
    return null; // har gått gjennom alle og ikke funnet faget
  }
}
