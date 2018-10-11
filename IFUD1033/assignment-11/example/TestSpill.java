/**
 * TestSpill.java  E.L. 2009-11-03
 *
 * Et rekrangulært spillbrett med ruter presenteres for brukeren.
 * I hver rute står det et tallpar (radnummer, kolonnenummer).
 * Spillet går ut på raskest mulig å trykke på ruter slik at summen
 * av alle valgte tall blir en bestemt verdi. Tiden spilleren bruker måles
 * i sekunder. Det er kun mulig å trykke på en knapp én gang, og
 * dersom summen passeres anses spillet som ikke bestått.
 *
 */
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;

class TrykknappSpill extends JFrame {
  private static final int ANT_RADER = 4;
  private static final int ANT_KOLONNER = 5;
  private static Random tallGen = new Random();
  // Minste mulige sum er lik 0, største mulige 70 */
  private static final int MÅL = tallGen.nextInt(71);
  private static String instruksjon = "Resultatet skal bli " + MÅL;

  private Font uthevetSkrift;
  private JTextArea resultatTekst = new JTextArea(3, 30);

  /**
   * Konstruktøren bygger opp spillbrettet bestående av to paneler
   * og et tekstområde lagt ut iht. BorderLayout.
   */
  public TrykknappSpill(String tittel) {
    super(tittel);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    Font skrift = resultatTekst.getFont();
    uthevetSkrift = new Font(skrift.getName(), Font.BOLD + Font.ITALIC, skrift.getSize());

    setLayout(new BorderLayout());
    add(new Instruksjonspanel(), BorderLayout.NORTH);
    add(new Knappepanel(), BorderLayout.CENTER);
    resultatTekst.setLineWrap(true);
    resultatTekst.setWrapStyleWord(true);
    add(resultatTekst, BorderLayout.SOUTH);
    resultatTekst.setText(instruksjon);
    pack();
  }

  /**
   * Panel med overskrift og spilleregler.
   */
  private class Instruksjonspanel extends JPanel {
    public Instruksjonspanel() {
      setLayout(new BorderLayout());
      JLabel overskrift = new JLabel("Trykknappspill");
      overskrift.setForeground(Color.blue);
      overskrift.setFont(uthevetSkrift);
      add(overskrift, BorderLayout.NORTH);
      JTextArea instruksjon = new JTextArea(5, 30);
      instruksjon.setLineWrap(true);
      instruksjon.setWrapStyleWord(true);
      instruksjon.setText(
        "Ved å trykke på knappene skal du generere et regnestykke der "
        + "summen blir tallet oppgitt under knappene. Tallet som knappen "
        + "representerer er gitt ved summen av radnummer og kolonnenummer. "
        + "Disse to tallene står på knappen. Du kan trykke på hver knapp "
        + "bare en gang. Tiden du bruker fra først knappetrykk måles i "
        + "antall sekunder.");
      add(instruksjon, BorderLayout.CENTER);
      add(new JLabel(" "), BorderLayout.SOUTH); // litt luft
    }
  }

  /**
   * Panel med spillebrettet.
   */
  private class Knappepanel extends JPanel {
    public Knappepanel() {
      Knappelytter lytteren = new Knappelytter();
      JButton[][] knapper = new JButton[ANT_RADER][ANT_KOLONNER];
      setLayout(new GridLayout(ANT_RADER, ANT_KOLONNER, 5, 5));
      for (int i = 0; i < ANT_RADER; i++) {
        for (int j = 0; j < ANT_KOLONNER; j++) {
          knapper[i][j] = new JButton(i + ", " + j);
          add(knapper[i][j]);
          knapper[i][j].addActionListener(lytteren);
        }
      }
    }
  }

  /**
   * Lytter etter brukerens knappetrykk.
   * Oppdaterer summen hittil og sjekker om man er i mål, eller om mål er passert.
   * Hvis man er i mål, skrives tidsforbuket ut. Hvis mål er passert, avbrytes spillet.
   * I alle tilfeller skrives regnestykket så langt vi er kommet, ut.
   */
  private class Knappelytter implements ActionListener {
    private String regnestykke = " = ";
    private int resultat;
    private boolean startet = false;  // settes lik true når første knapp trykkes på
    private long starttid;

    public void actionPerformed(ActionEvent hendelse) {
      if (!startet) {
        startet = true;
        starttid = (new Date()).getTime(); // starttidspunktet
      }
      JButton knapp = (JButton) hendelse.getSource();
      knapp.setEnabled(false); // ikke lov å trykke på samme knapp mer enn én gang
      String tekst = knapp.getText();
      int kommapos = tekst.indexOf(',');
      int verdi = 0;
      try { // må håndtere exceptions i parseInt()
        verdi = Integer.parseInt(tekst.substring(0, kommapos));
        verdi += Integer.parseInt(tekst.substring(kommapos + 2, tekst.length()));
      } catch (NumberFormatException e) {  // men feil skal ikke forekomme
      }
      resultat += verdi;
      if (resultat < MÅL) {
        regnestykke += verdi + " + ";
        resultatTekst.setText(instruksjon + "\nNå er resultatet lik " + resultat
                    + " og regnestykket: " + regnestykke);
      } else if (resultat == MÅL) {  // nå er vi i MÅL!
        regnestykke += verdi;
        long sluttid = (new Date()).getTime();
        double antSek = (sluttid - starttid) / 1000.0;
        resultatTekst.setFont(uthevetSkrift);
        resultatTekst.setText("Mål: " + MÅL + "\nDu er i MÅL på " + antSek + " sekunder! \nRegnestykket: " + regnestykke);
        startet = false;
      } else { // vi har passert MÅL!
        regnestykke += verdi;
        resultatTekst.setFont(uthevetSkrift);
        resultatTekst.setText("Mål: " + MÅL + "\nNå har du passert forbi målet. Ikke bestått! "
            + "\nResultatet lik " + resultat + " og regnestykket: " + regnestykke);
      }
    }
  }
}

class TestSpill {
  public static void main(String[] args) {
    TrykknappSpill etVindu = new TrykknappSpill("Trykknappspill");
    etVindu.setVisible(true);
  }
}
