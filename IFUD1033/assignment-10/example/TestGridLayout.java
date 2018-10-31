/**
 * TestGridLayout.java  - "Programmering i Java", 4.utgave - 2009-07-01
 *
 * Viser bruk av GridLayout for å lage vindu med flere felter til utfylling.
 * Ingen håndtering av data er lagt inn.
 */

import java.awt.*;
import javax.swing.*;

class GridLayoutVindu extends JFrame {
  private JTextField navn = new JTextField(15);
  private JTextField adresse = new JTextField(15);
  private JTextField tlf = new JTextField(15);
  private JTextField ePost = new JTextField(15);

  public GridLayoutVindu(String tittel) {
    setTitle(tittel);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    /*
     * Argumentene til GridLayout() er: antall rader, antall kolonner,
     * horisontal avstand mellom rutene, vertikal avstand mellom rutene
     * de to siste i antall piksler
     */
    setLayout(new GridLayout(4, 2, 5, 5));

    JLabel ledetekst = new JLabel("Navn:", JLabel.RIGHT);
    add(ledetekst);
    add(navn);
    ledetekst = new JLabel("Adresse:", JLabel.RIGHT);
    add(ledetekst);
    add(adresse);
    ledetekst = new JLabel("Tlf.:", JLabel.RIGHT);
    add(ledetekst);
    add(tlf);
    ledetekst = new JLabel("E-post:", JLabel.RIGHT);
    add(ledetekst);
    add(ePost);
    pack();
  }
}

class TestGridLayout {
  public static void main(String[] args) {
    GridLayoutVindu etVindu = new GridLayoutVindu("Eksempel på GridLayout");
    etVindu.setVisible(true);
  }
}