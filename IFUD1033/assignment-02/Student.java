import java.text.MessageFormat;

public class Student {
    private final String name;
    private int countAssignments;
    
    public Student(String name) {
        this.name = name;
        this.countAssignments = 0;
    }

    public Student(String name, int startCount) {
        this.name = name;
        this.countAssignments = startCount;
    }

    public String getName() {
        return this.name;
    }

    public int getCountAssignments() {
        return this.countAssignments;
    }

    public void acceptAssignments(int count) {
        if (count < 0) {
            throw new IllegalArgumentException("Assignment-count must be a positive integer.");
        }

        this.countAssignments += count;
    }

    public String toString() {
        String out = MessageFormat.format("Student: {0}, Assignments: {1}", this.name, this.countAssignments);
        return out;
    }

    public static void main(String[] args) {
        Student eskil = new Student("eskil");
        System.out.println(eskil.toString());
        eskil.acceptAssignments(1);
        System.out.println(eskil.toString());
        
    }
}
