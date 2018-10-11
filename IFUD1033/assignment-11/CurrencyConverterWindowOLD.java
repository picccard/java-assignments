/**

	Title:         CurrencyConverterWindow.java
	Date:          26.04.2018
    Author:        Eskil Uhlving Larsen

*/

/*
    |----------------------------------------|
    |               InfoLabel                |
    |----------------------------------------|
    | From Currency:    |       To Currency: |
    |----------------------------------------|
    |      US Dollar      |     US Dollar.   |
    |      Euro           |     Euro         |  First choose from currency,
    |      GB Pound       |     GB Pound.    |  Then generate to currency-panel (minus the from currency)
    |      Swedish kr.    |     Swedish kr.  |
    |      Norwegian kr.  |     Norwegian kr.|
    |----------------------------------------|
    | JButton("New Currency.")               |
    |----------------------------------------|
*/

// 3 rows, 2 colums in each row
// JLabel("Amount") - JTextField
// JLabel("Result") streched over whole row
// JButton("To Swedish kr.") - JButton("To Norwgain kr.")

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.JOptionPane;

class CurrencyConverterWindow extends JFrame implements ActionListener {
    String[] currencies = {"NOK", "SEK", "EUR", "USD", "DKK"};
    // Currency[] currency = {
    //     new Currency("Euro", "€", "EUR"),
    //     new Currency("US Dollar", "$", "USD"),
    //     new Currency("Norske kroner", "NOK"),
    //     new Currency("Svenske kroner", "SEK")
    // };



    public CurrencyConverterWindow() {
        setTitle("Currency Converter");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5,5,5,5);  // topSpacing, leftSpacing, bottomSpacing, rightSpacing

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        add(new JLabel("This is an currency exchange."), gbc); //JText Area/Field to wrap non editable
        //add(new JLabel("This is an currency exchange. Please select a currency to convert from."), gbc); //JText Area/Field to wrap non editable


        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        add(new JLabel("From Currency:"), gbc);

        JList<String> fromCurrencyList = new JList<String>(currencies);
        fromCurrencyList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        //fromCurrencyList.setVisibleRowCount(5);
        gbc.gridx = 0;
        gbc.gridy = 2;
        add(new JScrollPane(fromCurrencyList), gbc);


        gbc.gridx = 1;
        gbc.gridy = 1;
        //add(new toCurrencyPanel(), gbc);
        add(new JLabel("To Currency:"), gbc);
        JList<String> toCurrencyList = new JList<String>(currencies);
        toCurrencyList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        //toCurrencyList.setVisibleRowCount(5);
        toCurrencyList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        gbc.gridx = 1;
        gbc.gridy = 2;
        add(new JScrollPane(toCurrencyList), gbc);



        pack();
    }

    public void actionPerformed(ActionEvent e) {
        add(new JLabel("actionPerformed"));
        revalidate();
        repaint();
    }

    // class fromCurrencyPanel extends JPanel implements ActionListener {
    //     public fromCurrencyPanel() {
    //         setLayout(new GridBagLayout());
    //         GridBagConstraints gbc = new GridBagConstraints();
    //         gbc.fill = GridBagConstraints.HORIZONTAL;
    //         gbc.gridx = 0;
    //         gbc.gridy = 0;
    //         add(new JLabel("From Currency:"), gbc);
    //         JList<String> fromCurrencyList = new JList<String>(currencies);
    //         fromCurrencyList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    //         //fromCurrencyList.setVisibleRowCount(5);
    //         gbc.gridx = 0;
    //         gbc.gridy = 1;
    //         add(new JScrollPane(fromCurrencyList), gbc);
    //         pack();
    //     }
    //
    //     public void actionPerformed(ActionEvent e) {
    //
    //     }
    // }

    // class toCurrencyPanel extends JPanel implements ActionListener {
    //     public toCurrencyPanel() {
    //         setLayout(new GridBagLayout());
    //         GridBagConstraints gbc = new GridBagConstraints();
    //         gbc.gridx = 0;
    //         gbc.gridy = 0;
    //         add(new JLabel("To Currency:"), gbc);
    //         JList<String> toCurrencyList = new JList<String>(currencies);
    //         toCurrencyList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    //         //toCurrencyList.setVisibleRowCount(5);
    //         toCurrencyList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    //         gbc.gridx = 0;
    //         gbc.gridy = 1;
    //         add(new JScrollPane(toCurrencyList), gbc);
    //         pack();
    //     }
    //
    //     public void actionPerformed(ActionEvent e) {
    //
    //     }
    // }

    public static void main(String[] args) {
        CurrencyConverterWindow frame = new CurrencyConverterWindow();
        frame.setVisible(true);
    }
}
