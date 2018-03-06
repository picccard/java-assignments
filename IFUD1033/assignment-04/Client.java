/**

	Title:	    Client.java
	Date:	    05.03.2018
    Author:     Eskil Uhlving Larsen

*/

import java.util.ArrayList;
import java.io.*;

public class Client {
    public ArrayList<Subject> readFile(String fileName) {
        String line = null;
        ArrayList<Subject> newSubjects = new ArrayList<Subject>();
        try {
            // FileReader reads text in the deault encoding
            // A FileReader should always be wrapped in a BufferedReader
            FileReader fileReader = new FileReader(fileName);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            while ((line = bufferedReader.readLine()) != null) {
                // Should throw exception if line.contains(",\t") returns false, otherwise split
                String[] lineParts = line.split(",");
                // lineParts-index: 1.subCode 2.sunName 3.subCredit 4.5.6.7.etc.subResults
                String subCode = lineParts[0].trim();
                String subName = lineParts[1].trim();
                int subCredit = Integer.parseInt(lineParts[2].trim());
                char[] subResults = new char[lineParts.length - 3];
                for (int i = 3; i < lineParts.length; i++) {
                    subResults[i - 3] = lineParts[i].trim().charAt(0);
                }
                newSubjects.add(new Subject(subCode, subName, subCredit, subResults));
            }
            bufferedReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("Unable to open file {0}" + fileName);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return newSubjects;
    }

    public void serializeArrayList(ArrayList<Subject> toSerialize) {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream("subjects.ser");
            ObjectOutputStream outStream = new ObjectOutputStream(fileOutputStream);
            outStream.writeObject(toSerialize);
            outStream.close();
        } catch (FileNotFoundException e) {
            System.out.println("Unable to open file");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Subject> deSerialize(String fileName) {
        ArrayList<Subject> foundArrayListSubjects = null;
        try {
            FileInputStream fileInputStream = new FileInputStream(fileName);
            ObjectInputStream inStream = new ObjectInputStream(fileInputStream);
            foundArrayListSubjects = (ArrayList<Subject>) inStream.readObject();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } catch (ClassNotFoundException c) {
            System.out.println("Subject class not found");
            c.printStackTrace();
            return null;
         }
         return foundArrayListSubjects;
    }

    public static void main(String[] args) {
        Client client = new Client();

        ArrayList<Subject> subjects = client.readFile("data.txt");
        // List out orginal Subjects before serialize
        /*for (Subject s : subjects) {
            System.out.println(s);
        }*/

        // Serialize the ArrayList
        client.serializeArrayList(subjects);

        // DeSerialize and list out every Subject.toString()
        ArrayList<Subject> readSubjects = client.deSerialize("subjects.ser");
        for (Subject s : readSubjects) {
            System.out.println(s);
        }
    }
}
