/**
 * Class Species with subclasses.
 * Small testclient at the end.
 */

import java.util.ArrayList;
import java.time.LocalDate;

abstract class Species {
    private final String norwegianName;
    private final String latinName;
    private final String latinFamily;

    /**
    * Constructor Species: None of the arguments should be null.
    */
    public Species(String norwegianName, String latinName, String latinFamily) {
        if (norwegianName == null || latinName == null || latinFamily == null) {
            throw new IllegalArgumentException("None of the arguments should be null");
        }
        this.norwegianName = norwegianName.trim();
        this.latinName = latinName.trim();
        this.latinFamily = latinFamily.trim();
    }

    public String getNorwegianName() {
        return norwegianName;
    }

    public String getLatinName() {
        return latinName;
    }

    public String getLatinFamily() {
        return latinFamily;
    }

    public String toString() {
        return "Norwegian name: " + norwegianName + ", latin name and family: " + latinName
            + ", " + latinFamily;
        }
    }

class AnimalsAsIndividuals extends Species {
    private final String kidCount; // e.g. "3-4"
    private final boolean dangerous;
    private ArrayList<Individual> individuals;

    public AnimalsAsIndividuals(String norwegianName, String latinName, String latinFamily, String kidCount, boolean dangerous) {
        super(norwegianName, latinName, latinFamily);
        this.kidCount = kidCount;
        this.dangerous = dangerous;
        this.individuals = new ArrayList<Individual>();
    }

    public String getKidCount() {
        return kidCount;
    }

    public boolean isDangerous() {
        return dangerous;
    }

    public Individual registerIndividual(Individual individual) {
        // Not checking for any info about the individual
        // Same individual can be added the registered n times
        // Two different individuals with the same name can fuck this program up
        if (individual != null) {
            individuals.add(individual);
            return individual;
        }
        return null;
    }

    public Individual findIndividual(String individualname) {
        for (Individual i : individuals) {
            if (i.getIndividualname().equals(individualname)) {
                return i;
            }
        }
        return null;
    }

    public LocalDate findBirthDateIndividual(Individual individual) {
        for (Individual i : individuals) {
            if (i.getIndividualname().equals(individual.getIndividualname())) {
                return i.getBirthDate();
            }
        }
        return null;
    }

    public int findInduvidualsCount() {
        return individuals.size();
    }

    public String toString() {
        String res = super.toString();
        res += ", Animals as individuals, nr of kids: " + kidCount;
        res += (dangerous) ? ", animal is dangerous." : ", animal is not dangerous.";
        res += " " + findInduvidualsCount() + " individuals is registered:";
        for (Individual i : individuals) {
            res += "\nName: " + i.getIndividualname() + ", birthdate: " + i.getBirthDate();
        }
        return res;
    }
}

abstract class AnimalsInGroups extends Species {
    private ArrayList<Group> groups;
    public AnimalsInGroups(String norwegianName, String latinName, String latinFamily) {
        super(norwegianName, latinName, latinFamily);
        groups = new ArrayList<Group>();
    }

    public Group registerGroup(Group group) {
        // Not checking for any info about the individual
        // Same individual can be added the registered n times
        // Two different individuals with the same name can fuck this program up
        if (group != null) {
            groups.add(group);
            return group;
        }
        return null;
    }

    // Returns the first group with given groupName
    public Group findGroup(String groupName) {
        for (Group group : groups) {
            if (group.getGroupName().equals(groupName)) {
                return group;
            }
        }
        return null;
    }

    public int findCountInGroup(Group g) {
        return g.getCount();
    }

    public int findGroupCount() {
        return groups.size();
    }

    public String toString() {
        String res = super.toString();
        res += ", Animals in groups";
        if (groups.size() > 0) {
            res += " on ";
            for (Group g : groups) {
                res += findCountInGroup(g) + "-"; // Isn't the best
            }
            res += "a-group";
        }
        return res;
    }
}

class Bird extends AnimalsInGroups {
    private final String eggCount; // e.g. "2-4"

    public Bird(String norwegianName, String latinName,  String latinFamily, String eggCount) {
        super(norwegianName, latinName, latinFamily);
        this.eggCount = eggCount;
    }

    public String getEggCount() {
        return eggCount;
    }

    public String toString() {
        String res = super.toString();
        res += ", this is birds, egg count:  " + eggCount;
        return res;
    }
}

class Fish extends AnimalsInGroups {
    public Fish(String norwegianName, String latinName,  String latinFamily) {
        super(norwegianName, latinName, latinFamily);
    }

    public String toString() {
        String res = super.toString();
        res += ", this is fish";
        return res;
    }
}

class TestClasstree {
    public static void main(String[] args) {
        AnimalsAsIndividuals bear = new AnimalsAsIndividuals("Bjørn", "XXX", "YYY", "1-2", true);
        System.out.println(bear.getNorwegianName());
        System.out.println(bear.getLatinName());
        System.out.println(bear.getLatinFamily());
        System.out.println(bear.getKidCount());
        System.out.println(bear.isDangerous());
        System.out.println(bear.registerIndividual(new Individual("Boog", LocalDate.of(2018, 4, 14))));
        System.out.println(bear.registerIndividual(new Individual("Winnie-the-Pooh", LocalDate.of(2003, 4, 14))));
        System.out.println(bear.findIndividual("Boog")); // found, same ref as^
        System.out.println(bear.findIndividual("none")); // Not found, null
        System.out.println("individuals count: " + bear.findInduvidualsCount());
        System.out.println("toString(): " + bear);

        Bird bird = new Bird("Hummingbird", "xxx", "yyy", "2-5");
        Group hummingbirdCage1 = new Group("HummingbirdCage1", 4);
        Group hummingbirdCage2 = new Group("HummingbirdCage2", 6);
        bird.registerGroup(hummingbirdCage1);
        bird.registerGroup(hummingbirdCage2);
        System.out.println(bird.findGroup("HummingbirdCage2")); // Retuns the correct group
        System.out.println(bird.findGroup("notAGroup")); // Should not be found, returns null
        System.out.println(bird.getNorwegianName());
        System.out.println(bird.getLatinName());
        System.out.println(bird.getLatinFamily());
        System.out.println(bird.getEggCount());
        System.out.println("toString(): " + bird);

        Fish fish = new Fish("Shark", "ppp", "qqq");
        System.out.println(fish.getNorwegianName());
        System.out.println(fish.getLatinName());
        System.out.println(fish.getLatinFamily());
        System.out.println("toString(): " + fish);
    }
}
