/*
	Øving 10
	18.04.2018
	Anders Kvanvig
*/
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

class ValutaKalkGrid extends JFrame {
    JTextField belop = new JTextField(15);
    JLabel resultatString = new JLabel("Resultatet av omregningen kommer her.");
    JButton tilSvensk = new JButton("Til Svensk");
    JButton tilNorsk = new JButton("Til Norsk");
    double kurs = 1.0815713; //Kurs fra nok til sek (18.04.18)

    public ValutaKalkGrid() {
        setTitle("Valutakalkulator");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(3,2,5,5)); //(Rader, kolonner, vertikal avstand (pxl), horisontal avstand (pxl))
        JLabel beskrivelse = new JLabel("beløp:", JLabel.RIGHT);
        Knappelytter lytter = new Knappelytter();
        tilSvensk.addActionListener(lytter);
        tilNorsk.addActionListener(lytter);

        add(beskrivelse);
        add(belop);
        add(new JLabel(""));
        add(resultatString);
        add(tilSvensk);
        add(tilNorsk);
        pack();
    }
    //Bruker en intern klasse for å plukke opp ActionEvents, dette gir tilgang til private variabler i klassen over.
    private class Knappelytter implements ActionListener {
        public void actionPerformed(ActionEvent hendelse) {
            JButton valgtKnapp = (JButton) hendelse.getSource(); //Returnerer et object, dette kan castes til typen vi vet det vil være.
            String knapp = valgtKnapp.getText();
            String nyValuta;
            //Sjekker tall gitt
            try {
                double verdi = Double.parseDouble(belop.getText());

                if (knapp.equals(tilSvensk.getText())) {
                    verdi = verdi * kurs;
                    nyValuta = "sek";
                } else {
                    verdi = verdi * (1 / kurs);
                    nyValuta = "nok";
                }
                //Runder av til to desimalplasser
                verdi = ((double)Math.round(verdi * 100)) / 100;
                resultatString.setText(verdi + " " + nyValuta);

            } catch (NumberFormatException nfe) {
                resultatString.setText("Ikke et tall");
            }
        }
    }
}

class valutakalk {
    public static void main(String[] args) {
        ValutaKalkGrid vindu = new ValutaKalkGrid();
        vindu.setVisible(true);
    }
}
