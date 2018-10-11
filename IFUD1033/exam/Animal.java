public abstract class Animal { // implements Comparable<Dyr>
    private final int id;
    private final String name;
    private static double startPrice;

    public Animal(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public abstract double getStartPrice();

    public abstract double getAddonCost();
}

class Cat extends Animal {
    private final double startPrice = 170;
    private boolean aloneInGage;

    public Cat(int id, String name, boolean aloneInGage) {
        super(id, name);
        this.aloneInGage = aloneInGage;
    }

    public double getStartPrice() {
        return startPrice;
    }

    public double getAddonCost() {
        double retPrice = 0;
        if (aloneInGage) retPrice += 25;
        return retPrice;
    }
}

class Dog extends Animal {
    private static final int SIMPLE_MEDIC = 1;
    private static final int ADVANCED_MEDIC = 2;
    private final double startPrice = 360;
    private boolean alone;
    private boolean over50kg;
    private boolean specialFood;
    private int medic;

    public Dog(int id, String name, boolean alone, boolean over50kg, boolean specialFood, int medic) {
        super(id, name);
        this.alone = alone;
        this.over50kg = over50kg;
        this.specialFood = specialFood;
        this.medic = medic;
        if (medic < 0 || medic > 2) {
            throw new IllegalArgumentException("Medic should be 0 (no medic), 1 (simple medic) or 2 (advanced medic)");
        }
    }

    public double getStartPrice() {
        return startPrice;
    }

    public double getAddonCost() {
        double retPrice = 0;
        if (alone) retPrice += 100;
        if (over50kg) retPrice += 100;
        if (specialFood) retPrice += 25;
        if (medic == SIMPLE_MEDIC) {
            retPrice += 25;
        } else if (medic == ADVANCED_MEDIC) {
            retPrice += 100;
        }
        return retPrice;
    }
}
