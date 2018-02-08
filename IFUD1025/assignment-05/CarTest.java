/**

	Title:	CarTest.java
	Date:	26.02.2017
	Author:	Eskil Uhlving Larsen

*/
class CarTest {
  public static void main(String[] args) {
    Car myCar = new Car("VD-12345", "Volvo", 2002);
    Car yourCar = new Car("BF-98765", "Saab", 1990);
    System.out.println("myCar default");
    System.out.println(myCar.toString());
    System.out.println("yourCar default");
    System.out.println(yourCar.toString());
    myCar.start();
    myCar.accelerate(111);
    yourCar.start();
    yourCar.accelerate(222);
    System.out.println("myCar start+accelerate");
    System.out.println(myCar.toString());
    System.out.println("yourCar start+accelerate");
    System.out.println(yourCar.toString());
    myCar.brake(100);
    yourCar.brake(200);
    System.out.println("myCar brake");
    System.out.println(myCar.toString());
    System.out.println("yourCar brake");
    System.out.println(yourCar.toString());
    myCar.stop();
    yourCar.stop();
    System.out.println("myCar stop");
    System.out.println(myCar.toString());
    System.out.println("yourCar stop");
    System.out.println(yourCar.toString());
  }
}
