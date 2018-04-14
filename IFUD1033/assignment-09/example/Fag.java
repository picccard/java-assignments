/**

	Title:         Fag.java
	Date:
    Author:        Else Lervik
    Translator:    Eskil Uhlving Larsen (xx.0x.2018)

*/
/**
 * KLASSEN FAG.
 * Et objekt av klassen fag inneholder fagnr. og karakter.
 * Det er ikke lagt inn noe kontroll av gyldighet av data,
 * verken for fagnr. eller karakter.
 */
class Fag {
  public static final char KARAKTER_IKKE_SATT = 'S';
  private final String fagnr;
  private char karakter = KARAKTER_IKKE_SATT;

  public Fag(String fagnr) {
    this.fagnr = fagnr;
  }

  public String getFagnr() {
    return fagnr;
  }

  public void setKarakter(char nyKarakter) {
    karakter = nyKarakter;
  }

  public char getKarakter() {
    return karakter;
  }
}
