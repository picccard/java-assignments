/**

	Title:         Personregister.java
	Date:          13.04.2018
    Author:        Eskil Uhlving Larsen

*/

import java.util.ArrayList;

class Personregister {
    public static final char PERSON_FINS_IKKE = 'X'; //returverdi hvis person ikke finnes
    private ArrayList<Person> allePersonene = new ArrayList<Person>();

    public Person finnPerson(long birthNr) {
        for (Person p : allePersonene) {
            if (p.getFnr() == birthNr) {
                return p;
            }
        }
        return null;
    }

    public boolean registrerNyPerson(Person newPerson) {
        Person p = finnPerson(newPerson.getFnr());
        if (p == null) { // Person doesn't exist, can be added
            return allePersonene.add(newPerson); // aka true;
        }
        return false;
    }

    public boolean endreLønn(long birthNr, int newPaygrade) {
        Person p = finnPerson(birthNr);
        if (p != null && p instanceof Ansatt) {
            Ansatt emp = (Ansatt) p;
            emp.setLønnstrinn(newPaygrade);
            return true;
        }
        return false;
    }

    public boolean registrerNyttFag(long birthNr, String courseNr) {
        Person p = finnPerson(birthNr);
        if (p != null && p instanceof Student) {
            Student stud = (Student) p;
            stud.registrerNyttFag(courseNr);
            return true;
        }
        return false;
    }

    public boolean settKarakter(long birthNr, String courseNr, char grade) {
        Person p = finnPerson(birthNr);
        if (p != null && p instanceof Student) {
            Student stud = (Student) p;
            stud.settKarakter(courseNr, grade);
            return true;
        }
        return false;
    }

    public char finnKarakter(long birthNr, String courseNr) {
        Person p = finnPerson(birthNr);
        if (p != null && p instanceof Student) {
            Student stud = (Student) p;
            return stud.finnKarakter(courseNr);
        }
        return PERSON_FINS_IKKE;
    }

    public static void main(String[] args) {
        Student student1 = new Student(12106078756L, "Ole Pettersen",
            "Storgt 3, 7001 Trondheim", 1234567L);
        Student student2 = new Student(12125678756L, "Per Hansen",
            "Storgt 13, 7001 Trondheim", 1234557L);
        Ansatt læreren = new Ansatt(15107078056L, "Hanne Hansen",
            "Storgt 13, 7001 Trondheim", 50);
        Personregister registeret = new Personregister();
        registeret.registrerNyPerson(læreren);
        registeret.registrerNyPerson(student1);
        registeret.registrerNyPerson(student2);
        registeret.registrerNyttFag(12106078756L, "HiST-LO172D"); // student1
        registeret.registrerNyttFag(12125678756L, "HiST-LO451D"); // student2
        student2.registrerNyttFag("HiST-LO451D");
        student1.settKarakter("HiST-LO451D", 'C');
        student2.settKarakter("HiST-LO451D", 'B');
        registeret.settKarakter(12106078756L, "HiST-LO445D", 'F');

        char søk1 = registeret.finnKarakter(12125678756L, "HiST-LO172D");  // ugyldig fagnr for denne studenten
        char søk2 = registeret.finnKarakter(12125678756L, "HiST-LO451D");
        char søk3 = registeret.finnKarakter(12106078756L, "HiST-LO451D");
        char søk4 = registeret.finnKarakter(12106078756L, "HiST-LO445D");
        registeret.endreLønn(15107078056L, 52);  // endrer lærerlønnen via registeret

        if (søk1 == 'Y' && søk2 == 'B' && søk3 == 'C' && søk4 == 'F' && læreren.getLønnstrinn() == 52) {
            System.out.println("Testkjøring ok.");
        } else {
            System.out.println("Testkjøring ikke ok. Skal være Y B C F 52, det var: "
                + søk1 + " " + søk2 + " " + søk3 + " " + søk4 + " " + læreren.getLønnstrinn());
      }
    }
}
