import java.util.ArrayList;

public class Customer {
    private final int customernr;
    private final String name;
    private ArrayList<Animal> animals = new ArrayList<Animal>();
    private ArrayList<Stay> stays = new ArrayList<Stay>();

    public Customer(int customernr, String name) {
        this.customernr = customernr;
        this.name = name;
    }

    public boolean addAnimal(Animal newAnimal) {
        return animals.add(newAnimal);
    }
}
