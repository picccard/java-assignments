/**

	Title:	AssignmentOverview.java
	Date:	28.02.2018
	Author:	Eskil Uhlving Larsen

*/

import java.text.MessageFormat;
import java.util.Arrays;
import java.util.ArrayList;

public class AssignmentOverview {
    ArrayList<Student> students;
    private int countStud;

    // Constructor
    public AssignmentOverview() {
        this.students = new ArrayList<Student>();
        this.countStud = 0;
    }

    // Method to register a new student. Also increases the countStud variable.
    public boolean regNewStudent(String name) {
        for (Student stud : this.students) {
            if (stud.getName().equals(name)) {
                return false;
            }
        }
        students.add(new Student(name));
        countStud++;
        return true;
    }

    // Method to get the nr of students.
    public int findNrOfStud() {
        return this.students.size();
    }

    // Tries to look up how many acceptAssignments the student got.
    public int findNrOfAssignments(String name) {
        for (Student stud : this.students) {
            if (stud.getName().equals(name)) {
                return stud.getCountAssignments();
            }
        }
        return -1;
    }

    // Increases the nr of acceptAssignments for a specific student.
    public boolean acceptAssignments(String name, int increase) {
        for (Student stud : students) {
            if (stud.getName().equals(name)) {
                stud.acceptAssignments(increase);
                return true;
            }
        }
        return false;
    }

    // Returns an array with the names of all the students.
    public String[] findAllStudentNames() {
        ArrayList<String> names = new ArrayList<String>();
        for (Student stud : this.students) {
            names.add(stud.getName());
        }
        return names.toArray(new String[names.size()]);
    }

    // toString
    // Format - {index+1}: {student-name}, assignments {NrOfAssignments}
    public String toString() {
        StringBuilder result = new StringBuilder();
        for (Student stud : this.students) {
            result.append(MessageFormat.format("{0}: {1}, assignments: {2}\n", this.students.indexOf(stud) + 1, stud.getName(), stud.getCountAssignments()));
        }
        return result.toString();
    }

    /*public static void main(String[] args) {
        OppgaveOversikt oo = new OppgaveOversikt();
        oo.regNewStudent("ole");
        oo.regNewStudent("adis");
        oo.regNewStudent("eskil");
        oo.regNewStudent("magnus");

        System.out.println("Testing findAllStudentNames(): " + Arrays.toString(oo.findAllStudentNames()));

        System.out.println("\nTesting toString()\n" + oo.toString());


        System.out.println("Testing acceptAssignments(\"none\", 1): " + oo.acceptAssignments("none", 1));
        System.out.println("Testing acceptAssignments(\"adis\", 4): " + oo.acceptAssignments("adis", 4));
        System.out.println("Testing acceptAssignments(\"eskil\", 3): " + oo.acceptAssignments("eskil", 3));
        System.out.println("\nTesting toString()\n" + oo.toString());

        System.out.println("Testing findNrOfStud(): " + oo.findNrOfStud());
        System.out.println("Testing findNrOfAssignments(\"adis\"): " + oo.findNrOfAssignments("adis"));
        System.out.println("Testing findNrOfAssignments(\"none\"): " + oo.findNrOfAssignments("none"));
    }*/
}
