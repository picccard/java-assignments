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
        new Font("SansSerif", Font.PLAIN, 14),
        new Font("Monospaced", Font.PLAIN, 14),
        new Font("Serif", Font.PLAIN, 14),
        new Font("Dialog", Font.PLAIN, 14),
    };
    private JLabel lblSampleText;

    public FontChangerWindow(String title) {
        int nextRow = 0;
        // Some window properties
        setTitle(title);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        // Makes every element take up the same amount of HORIZONTAL space, e.g. you can click the radioBtn-text the same width as the widest element
        //gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 50, 5, 50); // topSpacing, leftSpacing, bottomSpacing, rightSpacing
        gbc.gridx = 0; // All items will be the first in it's own row

        // Sample-label
        lblSampleText = new JLabel("This is some very long and nice sample text");
        lblSampleText.setHorizontalAlignment(JLabel.CENTER);
        gbc.gridy = nextRow++;
        add(lblSampleText, gbc);

        // RadioButtons for fonts
        ButtonGroup fontBtns = new ButtonGroup();
        for (Font font : fonts) {
            JRadioButton radioBtn = new JRadioButton(font.getFontName());
            radioBtn.addActionListener(this);
            fontBtns.add(radioBtn);
            gbc.gridy = nextRow++;
            add(radioBtn, gbc);
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
