/**

	Title:         Reservasjon.java
	Date:          28.02.2018
	Translator:    Eskil Uhlving Larsen

*/
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Resevasjon.java E.L. 2010-01-16
 * Oppdatert av Nils Tesdal 2016-01-25
 *
 * Et objekt inneholder data om en reservasjon.
 * Operasjoner for å hente ut data, og for å sjekke om overlapp
 * med annen reservasjon.
 */

class Reservasjon {
  private final LocalDateTime fraTid;
  private final LocalDateTime tilTid;
  private final Kunde kunde;

  /**
   * Konstruktør:
   * fraTid må være før tilTid.
   * Ingen av argumentene kan være null.
   */
  public Reservasjon(LocalDateTime fraTid, LocalDateTime tilTid, Kunde kunde) {
    if (fraTid == null || tilTid == null) {
      throw new IllegalArgumentException("Fra-tid og/eller til-tid er null");
    }
    if (fraTid.isAfter(tilTid) || fraTid.equals(tilTid)) {
      throw new IllegalArgumentException("Fra-tid er lik eller etter til-tid");
    }
    if (kunde == null) {
      throw new IllegalArgumentException("Kunde er null");
    }
    this.fraTid = fraTid;
    this.tilTid = tilTid;
    this.kunde = kunde;
  }

  public LocalDateTime getFraTid() {
    return fraTid;
  }

  public LocalDateTime getTilTid() {
    return tilTid;
  }

  /**
   * Metoden returnerer true dersom tidsintervallet [sjekkFraTid, sjekkTilTid] overlapper
   * med det tidsintervallet som er i det reservasjonsobjektet vi er inne i [fraTid, tilTid].
   * Overlapp er ikke definert hvis sjekkFraTid eller sjekkTilTid er null.
   * Da kaster metoden NullPointerException.
   */
  public boolean overlapp(LocalDateTime sjekkFraTid, LocalDateTime sjekkTilTid) {
    return (sjekkTilTid.isAfter(fraTid) && sjekkFraTid.isBefore(tilTid));
  }

  public String toString() {
    return "Kunde: " + kunde.getNavn() + ", tlf: " + kunde.getTlf() + ", fra " +
                       fraTid.format(DateTimeFormatter.ISO_DATE_TIME) + ", til " + tilTid.format(DateTimeFormatter.ISO_DATE_TIME);
  }

  /**
   * Metode som prøver klassen Reservasjon.
   */
  public static void main(String[] args) {
    Kunde k = new Kunde("Anne Hansen", "12345678");
    System.out.println("Totalt antall tester: ");
    Reservasjon r1 = new Reservasjon(LocalDateTime.of(2003, 2, 1, 10, 0), LocalDateTime.of(2003, 2, 1, 11, 0), k);
    System.out.println(r1);

    Reservasjon r2 = new Reservasjon(LocalDateTime.of(2003, 1, 21, 10, 0), LocalDateTime.of(2003, 1, 21, 10, 30), k);
    Reservasjon r3 = new Reservasjon(LocalDateTime.of(2003, 2, 1, 11, 30), LocalDateTime.of(2003, 2, 1, 13, 0), k);
    Reservasjon r4 = new Reservasjon(LocalDateTime.of(2003, 2, 1, 9, 0), LocalDateTime.of(2003, 2, 1, 11, 0), k);
    if (r1.toString().equals("Kunde: Anne Hansen, tlf: 12345678, fra 2003-02-01T10:00:00, til 2003-02-01T11:00:00") &&
        r2.toString().equals("Kunde: Anne Hansen, tlf: 12345678, fra 2003-01-21T10:00:00, til 2003-01-21T10:30:00") &&
        r3.toString().equals("Kunde: Anne Hansen, tlf: 12345678, fra 2003-02-01T11:30:00, til 2003-02-01T13:00:00") &&
        r4.toString().equals("Kunde: Anne Hansen, tlf: 12345678, fra 2003-02-01T09:00:00, til 2003-02-01T11:00:00")) {
          System.out.println("Reservasjon: Test 1 vellykket.");
    }

    if (r1.overlapp(LocalDateTime.of(2003, 2, 1, 10, 0), LocalDateTime.of(2003, 2, 1, 11, 0)) &&
       !r1.overlapp(LocalDateTime.of(2003, 2, 2, 10, 0), LocalDateTime.of(2003, 2, 2, 11, 0)) &&
        r1.overlapp(LocalDateTime.of(2003, 2, 1, 10, 30), LocalDateTime.of(2003, 2, 1, 11, 0)) &&
        r1.overlapp(LocalDateTime.of(2003, 2, 1, 9, 30), LocalDateTime.of(2003, 2, 1, 10, 30))) {
         System.out.println("Reservasjon: Test 2 vellykket.");
    }
  }
}
