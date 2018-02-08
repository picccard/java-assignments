/**

	Title:	Triangle.java
	Date:	22.02.2017
	Author:	Eskil Uhlving Larsen

*/
import static javax.swing.JOptionPane.*;
class Triangle_v2 {
  public static void main(String[] args) {
    String linesRead = showInputDialog("How many lines?");
    int lines = Integer.parseInt(linesRead);
    for (int i=1; i<lines+1; i++) {
      for (int o=1; o<=i; o++) {
        System.out.print("*");
      }
      System.out.println();
    }
  }
}
