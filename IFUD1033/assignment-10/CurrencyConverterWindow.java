/**

	Title:         CurrencyConverterWindow.java
	Date:          26.04.2018
    Author:        Eskil Uhlving Larsen

*/

/*  ------------------------------------------
    |     Amount:      |      InputField     |
    |----------------------------------------|
    |               ResultString             |
    |----------------------------------------|
    |      To sek.     |     To NOK.         |
    ------------------------------------------
*/

// 3 rows, 2 collums in each row
// JLabel("Amount") - JTextField
// JLabel("Result") streched over whole row
// JButton("To Swedish kr.") - JButton("To Norwgain kr.")

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.JOptionPane;

class CurrencyConverterWindow extends JFrame implements ActionListener {
    private double inputSum;
    private final int SWE_EXCHANGE_RATE = 2;
    private final double NOR_EXCHANGE_RATE = 1.5;
    private final JLabel lblAmount;
    private JTextField inputField;
    private JLabel lblResult;
    private JButton buttons[];

    public CurrencyConverterWindow() {
        // Frame properties
        setTitle("Currency Converter");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // Using the GridBagLayout
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        // Makes every button take up the same amount of HORIZONTAL space
        gbc.fill = GridBagConstraints.HORIZONTAL;
        // Spacing
        gbc.insets = new Insets(5, 5, 5, 5);

        lblAmount = new JLabel("Amount: ");
        lblAmount.setHorizontalAlignment(JLabel.RIGHT);
        // Sets the row and collum
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(lblAmount, gbc);

        inputField = new JTextField(15);
        inputField.setEditable(true);
        inputField.setHorizontalAlignment(JTextField.LEFT);
        // Sets the row and collum
        gbc.gridx = 1;
        gbc.gridy = 0;
        add(inputField, gbc);

        lblResult = new JLabel("The calculated result will show up here.");
        lblResult.setHorizontalAlignment(JLabel.CENTER);
        // Sets the row and collum
        gbc.gridx = 0;
        gbc.gridy = 1;
        // Makes the label take up 2 collums
        gbc.gridwidth = 2;
        add(lblResult, gbc);

        buttons = new JButton[2];
        buttons[0] = new JButton("To Swedish kr.");
        buttons[0].addActionListener(this);
        buttons[0].setActionCommand("swe");
        buttons[1] = new JButton("To Norwegian kr.");
        buttons[1].addActionListener(this);
        buttons[1].setActionCommand("nor");
        // Back to taking up 1 collum
        gbc.gridwidth = 1;
        //Sets the row and collum
        gbc.gridx = 0;
        gbc.gridy = 2;
        add(buttons[0], gbc);
        gbc.gridx = 1;
        gbc.gridy = 2;
        add(buttons[1], gbc);

        pack();
    }

    public void actionPerformed(ActionEvent e) {
        String titleBar = "Not a number";
        String infoMessage = "Please enter a number";

        // Check if the input is empty
        if (inputField.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, infoMessage, titleBar, JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        // Checks if the input is a number, every number is valid, also negative
        try {
            inputSum = Double.parseDouble(inputField.getText());
        } catch (NumberFormatException nfe) {
            // Input was not a number
            System.out.println("Could not parse input to double");
            nfe.printStackTrace();
            infoMessage = "Could not interp the input as a number.\nPlease try again.";
            JOptionPane.showMessageDialog(null, infoMessage, titleBar, JOptionPane.INFORMATION_MESSAGE);
            lblResult.setText("The calculated result will show up here.");
            return;
        }

        // Gets the actionCommand and changes the result label
        String action = e.getActionCommand();
        switch (action) {
            case "swe" :
                lblResult.setText((inputSum * SWE_EXCHANGE_RATE) + " sek");
                break;
            case "nor" :
                lblResult.setText((inputSum * NOR_EXCHANGE_RATE) + " nok");
                break;
            default :
            System.out.println("action command not recognised, exiting");
            System.exit(0);
        }
    }

    public static void main(String[] args) {
        CurrencyConverterWindow frame = new CurrencyConverterWindow();
        frame.setVisible(true);
    }
}
