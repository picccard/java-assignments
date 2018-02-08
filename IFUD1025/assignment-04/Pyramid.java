/**

	Title:	Pyramid.java
	Date:	22.02.2017
	Author:	Eskil Uhlving Larsen

	Todo:	Check input and if Esc is pushed

*/
import static javax.swing.JOptionPane.*;
public class Pyramid {
 public static void main(String[] args) {
  String linesRead = showInputDialog("How many lines?");
  int lines = Integer.parseInt(linesRead);
  System.out.println();
  for (int i = 1; i <= lines; i++) {
   for (int o = i; o < lines; o++) {
    System.out.print(" ");
   }
   for (int k = 1; k < i * 2; k++) {
    System.out.print("*");
   }
   System.out.println();
  }
  System.out.println();
 }
}
