/**

	Title:         Ansatt.java
	Date:
    Author:        Else Lervik
    Translator:    Eskil Uhlving Larsen (xx.0x.2018)

*/

/**
 * KLASSEN ANSATT.
 * Et ansattobjekt er et personobjekt med tilleggsinformasjon om lønnstrinn.
 */
class Ansatt extends Person {
  private int lønnstrinn;

  public Ansatt(long fnr, String navn, String adresse, int lønnstrinn) {
    super(fnr, navn, adresse);
    this.lønnstrinn = lønnstrinn;
  }

  public int getLønnstrinn() {
    return lønnstrinn;
  }

  public void setLønnstrinn(int nyttLønnstrinn) {
    lønnstrinn = nyttLønnstrinn;
  }
}
