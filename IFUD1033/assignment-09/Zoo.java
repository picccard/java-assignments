/**

	Title:         Zoo.java
	Date:          14.04.2018
    Author:        Eskil Uhlving Larsen

*/

import java.util.ArrayList;
import java.time.LocalDate;

class Zoo {
    private ArrayList<Species> allSpecies;

    public Zoo() {
        this.allSpecies = new ArrayList<Species>();
    }

    public boolean registerNewSpecies(Species newSpecies) {
        if (newSpecies == null) {
            return false;
        }
        Species latSpecies = findSpecies(newSpecies.getLatinName());
        Species norSpecies = findSpecies(newSpecies.getNorwegianName());
        if (latSpecies == null && norSpecies == null) { // newSpecies have a unique latin & norwegian name
            allSpecies.add(newSpecies);
            return true;
        }
        return false; // newSpecies already exists
    }

    public Species findSpecies(String speciesName) { // speciesName can be in norwegian or latin
        for (Species species : allSpecies) {
            if (species.getNorwegianName().equals(speciesName.trim()) || species.getLatinName().equals(speciesName.trim())) {
                return species;
            }
        }
        return null;
    }

    public Species findSpecies(int index) {
        if (index < 0 || index >= allSpecies.size()) {
            return null;
        }
        return allSpecies.get(index);
    }

    public int findSpeciesCount() {
        return allSpecies.size();
    }

    public boolean registerNewIndividual(String speciesName, Individual newIndividual) {
        Species species = findSpecies(speciesName.trim());
        if (species != null && species instanceof AnimalsAsIndividuals) {
            AnimalsAsIndividuals aai = (AnimalsAsIndividuals) species;
            String newIndividualName = newIndividual.getIndividualname();
            Individual i = aai.findIndividual(newIndividualName);
            if (i == null) { // Then the newIndividual got a unique name
                aai.registerIndividual(newIndividual);
                return true;
            }
        }
        return false;
    }

    public Individual findIndividual(String speciesName, String individualname) {
        Species species = findSpecies(speciesName);
        if (species != null && species instanceof AnimalsAsIndividuals) {
            AnimalsAsIndividuals aai = (AnimalsAsIndividuals) species;
            return aai.findIndividual(individualname);
        }
        return null;
    }

    public LocalDate findBirthDateIndividual(String speciesName, String individualname) {
        Individual i = findIndividual(speciesName, individualname);
        if (i != null && i instanceof Individual) {
            return i.getBirthDate();
        }
        return null;
    }

    public boolean registerNewGroup(String speciesName, Group newGroup) {
        Species species = findSpecies(speciesName);
        if (species != null && species instanceof AnimalsInGroups) {
            AnimalsInGroups dig = (AnimalsInGroups) species;
            String newGroupName = newGroup.getGroupName();
            Group g = dig.findGroup(newGroupName);
            if (g == null) { // The newGroup got a uniqua name
                dig.registerGroup(newGroup);
                return true;
            }
        }
        return false;
    }

    public Group findGroup(String speciesName, String groupName) {
        Species species = findSpecies(speciesName);
        if (species != null && species instanceof AnimalsInGroups) {
            AnimalsInGroups dig = (AnimalsInGroups) species;
            return dig.findGroup(groupName);
        }
        return null;
    }

    public int findCountInGroup(Group g) {
        return g.getCount();
    }

    public static void main(String[] args) {
        System.out.println("Total tests: 15 (test 4 got 2 parts)");
        Zoo zoo = new Zoo();
        if (zoo.registerNewSpecies(new Fish("Shark", "ppp", "qqq"))) {
            System.out.println("1: ok");
        } else {System.out.println("1: not ok");}

        if (!zoo.registerNewSpecies(new Fish("Cod", "ppp", "qqq"))) { // false, same lat name
            System.out.println("2: ok");
        } else {System.out.println("2: not ok");}

        if (zoo.registerNewSpecies(new Fish("Cod", "xxx", "qqq"))) {
            System.out.println("3: ok");
        } else {System.out.println("3: not ok");}

        if (!zoo.registerNewSpecies(new Fish("Cod", "kkk", "qqq"))) { // false, same nor name
            System.out.println("4: ok");
        } else {System.out.println("4: not ok");}

        if (zoo.registerNewSpecies(new AnimalsAsIndividuals("Bear", "XXX", "YYY", "1-2", true))) { // bear, to use later
            System.out.println("4,5: ok");
        } else {System.out.println("4,5: not ok");}

        Species species1 = zoo.findSpecies("ppp"); // Shark
        if (species1 instanceof Fish && species1.getNorwegianName().equals("Shark")) {
            System.out.println("5: ok");
        } else {System.out.println("5: not ok");}

        Species species2 = zoo.findSpecies("www"); // Not a species
        if (species2 == null) {
            System.out.println("6: ok");
        } else {System.out.println("6: not ok");}

        Species species3 = zoo.findSpecies(1); // Cod
        if (species1 instanceof Fish && species3.getNorwegianName().equals("Cod")) {
            System.out.println("7: ok");
        } else {System.out.println("7: not ok");}

        if (zoo.findSpeciesCount() == 3) {
            System.out.println("8: ok");
        } else {System.out.println("8: not ok");}

        Individual winnieThePooh = new Individual("Winnie-the-Pooh", LocalDate.of(2018, 2, 14));
        if (zoo.registerNewIndividual("Bear", winnieThePooh)) {
            System.out.println("9: ok");
        } else {System.out.println("9: not ok");}

        if (!zoo.registerNewIndividual("Bear", winnieThePooh)) { // Individual with name "Winnie-the-Pooh" already exists
            System.out.println("10: ok");
        } else {System.out.println("10: not ok");}

        if (zoo.findBirthDateIndividual("Bear", "Winnie-the-Pooh").isEqual(LocalDate.of(2018, 2, 14))) {
            System.out.println("11: ok");
        } else {System.out.println("11: not ok");}

        if (zoo.findBirthDateIndividual("k", "k") == null) { // Non valid specieName or individualname
            System.out.println("12: ok");
        } else {System.out.println("12: not ok");}

        zoo.registerNewSpecies(new Bird("Hummingbird", "uuu", "yyy", "2-5"));
        if (zoo.registerNewGroup("Hummingbird", new Group("HummingbirdCage1", 4))) { // should be true
            System.out.println("13: ok");
        } else {System.out.println("13: not ok");}

        if (!zoo.registerNewGroup("Hummingbird", new Group("HummingbirdCage1", 6))) { // false, groupname is duplicate
            System.out.println("14: ok");
        } else {System.out.println("14: not ok");}

        if (zoo.findCountInGroup(zoo.findGroup("Hummingbird", "HummingbirdCage1")) == 4) {
            System.out.println("15: ok");
        } else {System.out.println("15: not ok");}


        // test-template
        // if (true) {
        //     System.out.println("x: ok");
        // } else {System.out.println("x: not ok");}
    }
}
