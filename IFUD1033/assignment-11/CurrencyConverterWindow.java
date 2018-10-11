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
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

// import javax.swing.JFrame;
// import javax.swing.JList;
// import javax.swing.JScrollPane;

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
        add(new JLabel("This is an currency exchange. ETC ETC ETC ETC ETC"), gbc); //JText Area/Field to wrap non editable
        //add(new JLabel("This is an currency exchange. Please select a currency to convert from."), gbc); //JText Area/Field to wrap non editable


        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        CurrencyList fromCList = new CurrencyList("From Currency:");
        fromCList.setVisible(true);
        add(fromCList, gbc);


        gbc.gridx = 1;
        gbc.gridy = 1;
        CurrencyList toCList = new CurrencyList("To Currency:");
        toCList.setVisible(false);
        add(toCList, gbc);

        pack();
    }

    public void actionPerformed(ActionEvent e) {

    }

    class CurrencyList extends JPanel {
        public CurrencyList(String headline) {
            setLayout(new GridBagLayout());
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.fill = GridBagConstraints.HORIZONTAL;
            gbc.gridx = 0;
            gbc.gridy = 0;
            add(new JLabel(headline), gbc);
            JList<String> currencyList = new JList<String>(currencies);
            currencyList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
            currencyList.setVisibleRowCount(3);

            // ListSelectionListener listSelectionListener = new ListSelectionListener() {
            //     public void valueChanged(ListSelectionEvent listSelectionEvent) {
            //         System.out.println("First index: " + listSelectionEvent.getFirstIndex());
            //         System.out.println(", Last index: " + listSelectionEvent.getLastIndex());
            //     }
            // };
            // currencyList.addListSelectionListener(listSelectionListener);

            currencyList.addListSelectionListener(new ListSelectionListener() {
                public void valueChanged(ListSelectionEvent listSelectionEvent) {
                    //add(new JLabel("actionPerformed"));
                    System.out.println("actionPerformed");
                    toCList.setVisible(true);
                    revalidate();
                    repaint();
                }
            });

            gbc.gridx = 0;
            gbc.gridy = 1;
            add(new JScrollPane(currencyList), gbc);
            pack();
        }

        public void actionPerformed(ActionEvent e) {

        }
    }

    public static void main(String[] args) {
        CurrencyConverterWindow frame = new CurrencyConverterWindow();
        frame.setVisible(true);
    }
}
