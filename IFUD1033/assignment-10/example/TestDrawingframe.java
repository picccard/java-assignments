/*
 * TestTegneramme.java  E.L 2004-11-02
*/

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;

class Drawingframe extends JFrame implements MouseListener {
    private ArrayList<Point> allPoints = new ArrayList<Point>();

    public Drawingframe(String title) {
        super(title);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 500);
        DrawingField d = new DrawingField();
        add(d);
        // MouseListen ml = new MouseListen();
        d.addMouseListener( this );
    }

    private class DrawingField extends JPanel {
        public void paintComponent(Graphics window) {
            super.paintComponent(window);
            int radius = 10; // used to center the fillOval on the clicked point
            if (allPoints.size() > 1) { // more than one point is needed to draw
                Point prevPoint = allPoints.get(0);
                window.fillOval(prevPoint.x-(radius/2), prevPoint.y-(radius/2), radius, radius);
                for (int i = 1; i < allPoints.size(); i++) {
                    Point currentPoint = allPoints.get(i);
                    window.fillOval(currentPoint.x-(radius/2), currentPoint.y-(radius/2), radius, radius);
                    window.drawLine(prevPoint.x, prevPoint.y,
                                    currentPoint.x, currentPoint.y);
                    prevPoint = currentPoint;
                }
            }
        }
    }

        public void mouseClicked(MouseEvent event) {
            Point clickedPoint = event.getPoint();
            allPoints.add(clickedPoint);
            System.out.println("Mouse clicked at " + clickedPoint.y + ", " + clickedPoint.y);
            repaint();
        }

        public void mousePressed(MouseEvent event) {}
        public void mouseReleased(MouseEvent event) {}
        public void mouseEntered(MouseEvent event) {}
        public void mouseExited(MouseEvent event) {}
    }

class TestDrawingframe {
    public static void main(String[] args) {
        Drawingframe dFrame = new Drawingframe("DrawingFrame");
        dFrame.setVisible(true);
    }
}
