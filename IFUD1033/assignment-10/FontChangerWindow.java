/**

	Title:         FontChangerWindow.java
	Date:          25.04.2018
    Author:        Eskil Uhlving Larsen

*/

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

class FontChangerWindow extends JFrame implements ActionListener {
    private Font fonts[] = {
        new Font("SansSerif", Font.PLAIN, 12),
        new Font("Monospaced", Font.PLAIN, 12),
        new Font("Serif", Font.PLAIN, 12)
    };
    private JLabel lblSampleText;

    public FontChangerWindow(String title) {
        // Some window properties
        setTitle(title);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize( new Dimension( 230, 150 ) );
        setLayout(new FlowLayout());
        setResizable(false);

        // Sample-label
        lblSampleText = new JLabel("This is some sample text");
        add(lblSampleText);

        // RadioButtons for fonts
        ButtonGroup fontBtns = new ButtonGroup();
        for (Font font : fonts) {
            JRadioButton radioBtn = new JRadioButton(font.getFontName());
            radioBtn.addActionListener(this);
            fontBtns.add(radioBtn);
            add(radioBtn);
        }

        // Change-font-button
        // JButton btnChangeFont = new JButton("Change Font");
        // btnChangeFont.addActionListener(this); //this implements ActionListener
        // add(btnChangeFont);

        pack();
    }

    // Changes the font
    public void actionPerformed(ActionEvent e) {
        JRadioButton srcBtn = (JRadioButton) e.getSource();
        for (Font font : fonts) {
            if (srcBtn.getText().equals(font.getFontName())) {
                lblSampleText.setFont(font);
            }
        }
    }

    // Main loop
    public static void main(String[] args) {
        FontChangerWindow frame = new FontChangerWindow("FontChanger");
        frame.setVisible(true);
    }
}
