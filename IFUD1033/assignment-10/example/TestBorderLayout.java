/**
 * TestBorderLayout.java  - "Programmering i Java", 4.utgave - 2009-07-01
 *
 * BorderLayout er standard layouthåndterer.
 */

import java.awt.*;
import javax.swing.*;

class BorderLayoutVindu extends JFrame {
  public BorderLayoutVindu(String tittel) {
    setTitle(tittel);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    setLayout(new BorderLayout()); // kan sløyfes, er standard
    Font storSkrift = new Font("SansSerif", Font.BOLD, 20);

    JButton knappEn = new JButton("1");
    knappEn.setFont(storSkrift);
    add(knappEn, BorderLayout.WEST);

    JButton knappTo = new JButton("2");
    knappTo.setFont(storSkrift);
    add(knappTo, BorderLayout.CENTER);

    JButton knappTre = new JButton("3");
    knappTre.setFont(storSkrift);
    add(knappTre, BorderLayout.EAST);

    JButton knappFire = new JButton("4");
    knappFire.setFont(storSkrift);
    add(knappFire, BorderLayout.NORTH);

    JButton knappFem = new JButton("5");
    knappFem.setFont(storSkrift);
    add(knappFem, BorderLayout.SOUTH);
    pack();
  }
}

class TestBorderLayout {
  public static void main(String[] args) {
    BorderLayoutVindu etVindu = new BorderLayoutVindu("BorderLayout");
    etVindu.setVisible(true);
  }
}