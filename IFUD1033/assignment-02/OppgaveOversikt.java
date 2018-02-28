/**

	Title:	OppgaveOversikt.java
	Date:	28.02.2018
	Author:	Eskil Uhlving Larsen

*/

import java.text.MessageFormat;
import java.util.Arrays;

public class OppgaveOversikt {
    private Student[] students;
    private int countStud;

    public OppgaveOversikt() {
        this.students = new Student[5];
        this.countStud = 0;
    }

    public boolean regNyStudent(String name) {
        for (int i = 0; i < this.countStud; i++) {
            if (this.students[i].getName().equals(name)) {
                return false;
            }
        }
        /*for (Student stud : students) {
            if (stud.getName().equals(name)) {
                return false;
            }
        }*/
        if (countStud >= this.students.length) {
            //Expand the studentArray
            Student[] newStudArr = new Student[students.length + 5];
            for (int i = 0; i < students.length; i++) {
                newStudArr[i] = students[i];
            }
            students = newStudArr;
        }
        students[countStud] = new Student(name);
        countStud++;
        return true;
    }

    public int finnAntStud() {
        return countStud;
    }

    public int finnAntOppgaver(String name) {
        for (int i = 0; i < this.countStud; i++) {
            if (this.students[i].getName().equals(name)) {
                return this.students[i].getCountAssignments();
            }
        }
        return -1;
    }

    public boolean økAntOppg(String name, int increase) {
        for (int i = 0; i < this.countStud; i++) {
            if (this.students[i].getName().equals(name)) {
                this.students[i].acceptAssignments(increase);
                return true;
            }
        }
        return false;
    }

    public String[] finnAlleNavn() {
        String[] names = new String[countStud];
        for (int i = 0; i < this.countStud; i++) {
            names[i] = students[i].getName();
        }
        return names;
    }

    public String toString() {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < this.countStud; i++) {
            result.append(MessageFormat.format("{0}: {1}, assignments: {2}\n", (i+1), students[i].getName(), students[i].getCountAssignments()));
        }
        return result.toString();
    }

    /*public static void main(String[] args) {
        OppgaveOversikt oo = new OppgaveOversikt();
        oo.regNyStudent("ole");
        oo.regNyStudent("adis");
        oo.regNyStudent("eskil");
        oo.regNyStudent("magnus");

        System.out.println("Testing findAllStudentNames(): " + Arrays.toString(oo.finnAlleNavn()));
        
        System.out.println("\nTesting toString()\n" + oo.toString());
        
        
        System.out.println("Testing økAntOppg(\"none\", 1): " + oo.økAntOppg("none", 1));
        System.out.println("Testing økAntOppg(\"adis\", 4): " + oo.økAntOppg("adis", 4));
        System.out.println("Testing økAntOppg(\"eskil\", 3): " + oo.økAntOppg("eskil", 3));
        System.out.println("\nTesting toString()\n" + oo.toString());

        System.out.println("Testing findNrOfStud(): " + oo.finnAntStud());
        System.out.println("Testing findNrOfAssignments(\"adis\"): " + oo.finnAntOppgaver("adis"));
        System.out.println("Testing findNrOfAssignments(\"none\"): " + oo.finnAntOppgaver("none"));
    }*/
}
