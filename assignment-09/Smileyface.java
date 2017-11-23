/**

	Title:	Smileyface.java
	Date:	25.03.2017
	Author:	Eskil Uhlving Larsen

*/

import java.awt.Graphics;
import java.awt.Color;
import javax.swing.JFrame;
import javax.swing.JPanel;

class SmileyWindow extends JFrame {
  public SmileyWindow(int width, int height, String title) {
    this.setTitle(title);
    this.setSize(width, height); //width, height
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.add(new SmileyDrawing());
    this.setVisible(true);
  }
}

class SmileyDrawing extends JPanel {
  public void paintComponent(Graphics drawingboard) {
    super.paintComponent(drawingboard); //husk denne

    //Hode
    drawingboard.setColor((Color.YELLOW));
    drawingboard.fillOval(20, 20, 200, 200);
    //Venstre øye
    drawingboard.setColor(Color.BLUE);
    drawingboard.fillOval(80, 80, 20, 20);
    //Høyre øye
    drawingboard.fillOval(140, 80, 20, 20);
    //Munn
    drawingboard.drawArc(40, 90, 160, 110, 0, -180);
  }
}

class Smileyface {
  public static void main(String[] args) {
    new SmileyWindow(300, 300, "Smiley");
  }
}
