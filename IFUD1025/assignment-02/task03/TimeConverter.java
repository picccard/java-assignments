/*

	Title:	TimeConverter.java
	Date:	09.02.2017
	Author:	Eskil Uhlving Larsen

*/
import static javax.swing.JOptionPane.*;
class TimeConverter {
  public static void main(String[] args) {
    String antSekunderLest = showInputDialog("Antall sekunder: ");
    Double antSekunder = Double.parseDouble(antSekunderLest);
    Double timer = (antSekunder/60/60);
    Double minutter = (antSekunder/60);
    String utdata = antSekunder + " sekunder er " + minutter + " minutter." + "\n" + antSekunder + " sekunder er " + timer + " timer.";
    showMessageDialog(null, utdata);
  }
}
