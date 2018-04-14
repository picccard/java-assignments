/**

	Title:         Ansatt.java
	Date:
    Author:        Else Lervik
    Translator:    Eskil Uhlving Larsen (xx.0x.2018)

*/

/**
 * KLASSEN ANSATT.
 * Et ansattobjekt er et personobjekt med tilleggsinformasjon om l�nnstrinn.
 */
class Ansatt extends Person {
  private int l�nnstrinn;

  public Ansatt(long fnr, String navn, String adresse, int l�nnstrinn) {
    super(fnr, navn, adresse);
    this.l�nnstrinn = l�nnstrinn;
  }

  public int getL�nnstrinn() {
    return l�nnstrinn;
  }

  public void setL�nnstrinn(int nyttL�nnstrinn) {
    l�nnstrinn = nyttL�nnstrinn;
  }
}
