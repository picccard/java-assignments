/**

	Title:         TestTribune.java
	Date:          09.04.2018
    Author:        Eskil Uhlving Larsen

*/

import java.util.ArrayList;
import java.io.*;

class TestTribune {
    public static void main(String[] args) {
        System.out.println("Total tests: 9");

        Object[] result;
        String[] na = new String[2];
        na[0] = "ole";
        na[1] = "eskil";


        StandingTribune standT = new StandingTribune("standing", 100, 5);

        result = standT.buyTickets(na);
        if (result instanceof StandingTicket[] && result.length == 2) {
            System.out.println("1: ok");
        } else {System.out.println("1: failed");}
        //System.out.println(result);

        result = standT.buyTickets(3);
        if (result instanceof StandingTicket[] && result.length == 3) {
            System.out.println("2: ok");
        } else {System.out.println("2: failed");}
        //System.out.println(result);

        result = standT.buyTickets(99);
        if (result == null) {
            System.out.println("3: ok");
        } else {System.out.println("3: failed");}
        //System.out.println(result);


        SittingTribune sitT = new SittingTribune("sitting", 25, 2, 5);

        result = sitT.buyTickets(na);
        if (result instanceof SittingTicket[] && result.length == 2) {
            System.out.println("4: ok");
        } else {System.out.println("4: failed");}

        result = sitT.buyTickets(5);
        if (result instanceof SittingTicket[] && result.length == 5) {
            System.out.println("5: ok");
        } else {System.out.println("5: failed");}
        //System.out.println(result);


        result = sitT.buyTickets(6);
        if (result == null) {
            System.out.println("6: ok");
        } else {System.out.println("6: failed");}
        //System.out.println(result);


        VIPTribune vT = new VIPTribune("vip", 10, 6, 5);

        result = vT.buyTickets(na);
        if (result instanceof SittingTicket[] && result.length == 2) {
            System.out.println("7: ok");
        } else {System.out.println("7: failed");}
        //System.out.println(result);

        result = vT.buyTickets(1);
        if (result == null) {
            System.out.println("8: ok");
        } else {System.out.println("8: failed");}
        //System.out.println(result);

        result = vT.buyTickets(na);
        if (result instanceof SittingTicket[] && result.length == 2) {
            System.out.println("9: ok");
        } else {System.out.println("9: failed");}
        //System.out.println(result);

        // Put Tribunes into a single arraylist object
        ArrayList<Tribune> tribunes = new ArrayList<Tribune>();
        tribunes.add(standT);
        tribunes.add(sitT);
        tribunes.add(vT);

        //Serialize the arraylist with the tribunes
        try {
            FileOutputStream fileOutputStream = new FileOutputStream("tribunes.ser");
            ObjectOutputStream outStream = new ObjectOutputStream(fileOutputStream);
            outStream.writeObject(tribunes);
            outStream.close();
            System.out.println("\nSerialized done");
        } catch (FileNotFoundException e) {
            System.out.println("Unable to open file");
        } catch (IOException e) {
            e.printStackTrace();
        }

        //De-Serialize
        ArrayList<Tribune> foundTribunes = null;
        try {
            FileInputStream fileInputStream = new FileInputStream("tribunes.ser");
            ObjectInputStream inStream = new ObjectInputStream(fileInputStream);
            foundTribunes = (ArrayList<Tribune>) inStream.readObject();
            inStream.close();
            System.out.println("\nDe-serialize done");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException c) {
            System.out.println("Tribune class not found");
            c.printStackTrace();
         }

        //Final print
        for (Tribune trib : foundTribunes) {
            System.out.println("\n");
            System.out.println(trib);
        }
        // System.out.println("\n");
        // System.out.println(standT + "\n\n");
        // System.out.println(sitT + "\n\n");
        // System.out.println(vT + "\n\n");
    }
}
