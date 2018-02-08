/**

	Title:	Car.java
	Date:	26.02.2017
	Author:	Eskil Uhlving Larsen

*/
class Car {
  private String regnr; // = "ABC 420";
  private String manufacturer; // = "Saab";
  private int modYear; // = 1978;
  private int speed; // = 40;
  private boolean running; // = false;

  public Car(String a, String b, int c) {
    this.regnr = a;
    this.manufacturer = b;
    this.modYear = c;
  }
  public Car() {}

  public String getRegnr() {
    return this.regnr;
  }
  public String getManufacturer() {
    return this.manufacturer;
  }
  public int getModYear() {
    return this.modYear;
  }
  public int getSpeed() {
    return this.speed;
  }
  public boolean isRunning() {
    return this.running;
  }
  public void start() {
    this.speed = 0;
    this.running = true;
  }
  public void stop() {
    this.speed = 0;
    this.running = false;
  }
  public void accelerate(int a) {
    this.speed += a;
  }
  public void brake(int a) {
    this.speed -= a;
  }
  public String toString() {
    return ("regnr: " + this.getRegnr()
      + "\nmanufacturer: " + this.getManufacturer()
      + "\nmodYear: " + this.getModYear()
      + "\nspeed: " + this.getSpeed()
      + "\nrunning: " + this.isRunning() + "\n");
  }

}
