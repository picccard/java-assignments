/*
 * TestTegneramme.java  E.L 2004-11-02
*/

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;

class Drawingframe extends JFrame {
    private ArrayList<Point> allPoints = new ArrayList<Point>();

    public Drawingframe(String tittle) {
        super(tittle);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 500);
        DrawingField d = new DrawingField();
        add(d);
        MouseListen ml = new MouseListen();
        d.addMouseListener(ml);
    }

    private class DrawingField extends JPanel {
        public void paintComponent(Graphics window) {
            super.paintComponent(window);
            if (allPoints.size() > 1) { // more than one point is needed to draw
                Point lastPoint = allPoints.get(0);
                for (int i = 1; i < allPoints.size(); i++) {
                    Point currentPoint = allPoints.get(i);
                    window.drawLine(lastPoint.x, lastPoint.y,
                                    currentPoint.x, currentPoint.y);
                    lastPoint = currentPoint;
                }
            }
        }
    }

    private class MouseListen implements MouseListener {
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
}

class TestDrawingframe {
    public static void main(String[] args) {
        Drawingframe dFrame = new Drawingframe("DrawingFrame");
        dFrame.setVisible(true);
    }
}
