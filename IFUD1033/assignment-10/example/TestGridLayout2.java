/**
 * TestGridLayout2.java  - "Programmering i Java", 4.utgave - 2009-07-01
 *
 * Viser bilder i et rutenett
 */

import java.awt.*;
import javax.swing.*;

class GridLayoutVindu2 extends JFrame {
  public GridLayoutVindu2(String tittel) {
    setTitle(tittel);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    setLayout(new GridLayout(2, 5, 5, 5));

    /*
     * Bildene ligger på underkatalogen Beans, og filene heter
     * T1.gif, T2.gif, osv.
     */
    String bildeFilnavnStart = "Beans/T";
    for (int bildenr = 1; bildenr <= 10; bildenr++) {
      String bildeFilnavn
        = bildeFilnavnStart.concat(bildenr + ".gif");
      ImageIcon ikon = new ImageIcon(bildeFilnavn);
      JButton knapp = new JButton(bildeFilnavn, ikon);
      add(knapp);
    }
    pack();
  }
}

class TestGridLayout2 {
  public static void main(String[] args) {
    GridLayoutVindu2 etVindu = new GridLayoutVindu2("Eksempel på GridLayout");
    etVindu.setVisible(true);
  }
}