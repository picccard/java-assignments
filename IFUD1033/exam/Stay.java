import java.util.ArrayList;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Stay {
    private ArrayList<Animal> dogs = new ArrayList<Animal>();
    private ArrayList<Animal> cats = new ArrayList<Animal>();
    private final LocalDate fromDate;
    private final LocalDate toDate;

    public Stay(Animal[] animals, LocalDate from, LocalDate to) {
        for (Animal animal : animals) {
            if (animal instanceof Dog) {
                Dog dog = (Dog) animal;
                dogs.add(dog);
            }
            if (animal instanceof Cat) {
                Cat cat = (Cat) animal;
                cats.add(cat);
            }
        }
        this.fromDate = from;
        this.toDate = to;
    }

    public double calculatePrice() {
        double dogCost = calculatePriceForAnimalList(dogs);
        double catCost = calculatePriceForAnimalList(cats);
        long stayDays = ChronoUnit.DAYS.between(fromDate, toDate);
        System.out.println((dogCost + catCost) * stayDays);
        return (dogCost + catCost) * stayDays;
    }

    public double calculatePriceForAnimalList(ArrayList<Animal> animals) {
        double sum = 0;
        for (int i = 0; i < animals.size(); i++) {
            Animal animal = animals.get(i);

            if (i == 0) {
                sum += animal.getStartPrice() + animal.getAddonCost();
            } else if (i == 1 && animal instanceof Dog) {
                sum += (animal.getStartPrice() * 0.75) + animal.getAddonCost();
            } else {
                sum += (animal.getStartPrice() * 0.50) + animal.getAddonCost();
            }
        }
        System.out.println("sum er " + sum);
        return sum;
    }
    public static void main(String[] args) {
        Dog dog1 = new Dog(1, "dog1", false, false, false, 1);
        Dog dog2 = new Dog(2, "dog2", false, false, false, 1);
        Dog dog3 = new Dog(3, "dog3", false, false, false, 1);
        Dog dog4 = new Dog(4, "dog4", false, false, false, 1);
        Dog[] dogs = {dog1, dog2, dog3, dog4};
        LocalDate from = LocalDate.of(2014,10,20);
        LocalDate to = LocalDate.of(2014,10,21);

        Stay stay = new Stay(dogs, from, to);
        stay.calculatePrice();
    }
}
