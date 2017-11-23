/**

	Title:	StreetTest.java
	Date:	31.03.2017
	Author:	Eskil Uhlving Larsen

*/
class StreetTest {
  public static void main(String[] args) {
    Street street1 = new Street("Solgaten", 10); // first constructor
    street1.throwGarbage(5, 55);
    street1.throwGarbage(1, 110);
    street1.throwGarbage(3, 600);
    street1.throwGarbage(2, 1500);
    street1.throwGarbage(7, 1090);
    street1.throwGarbage(8, 100);
    street1.throwGarbage(10, 550);
    street1.throwGarbage(5, 1000);
    System.out.println(street1);
    if (street1.twoFullNextToEachOther(false)) {
      System.out.println("Two full next to each other on odd side!");
    }
    if (street1.twoFullNextToEachOther(true)) {
        System.out.println("Two full next to each other on even side!");
    }

    System.out.println("\nMore gargabe gets thrown (880 in bin 3)");
    street1.throwGarbage(3, 880);
    System.out.println(street1);
    if (street1.twoFullNextToEachOther(false)) {
        System.out.println("Two full next to each other on odd side!");
    }
    if (street1.twoFullNextToEachOther(true)) {
        System.out.println("Two full next to each other on even side!");
    }

    System.out.println("\nEmptying odd side of the street.");
    street1.empty(false);
    System.out.println(street1);
    if (street1.twoFullNextToEachOther(false)) {
        System.out.println("Two full next to each other on odd side!");
    }
    if (street1.twoFullNextToEachOther(true)) {
        System.out.println("Two full next to each other on even side!");
    }

    System.out.println("\nMore gargabe gets thrown (100 in bin 2; 880 in bin 3; 1500 in bin 4; 550 in bin 9)");
    street1.throwGarbage(3, 880);
    street1.throwGarbage(9, 550);
    street1.throwGarbage(2, 100);
    street1.throwGarbage(4, 1500);
    System.out.println(street1);
    if (street1.twoFullNextToEachOther(false)) {
      System.out.println("Two full next to each other on odd side!");
    }
    if (street1.twoFullNextToEachOther(true)) {
        System.out.println("Two full next to each other on even side!");
    }

    System.out.println("\nEmptying even side.");
    street1.empty(true);
    System.out.println(street1);
    if (street1.twoFullNextToEachOther(false)) {
      System.out.println("Two full next to each other on odd side!");
    }
    if (street1.twoFullNextToEachOther(true)) {
      System.out.println("Two full next to each other on even side!");
    }

    int[] weights2 = {1, 2, 3, 4, 5, 6};
    Street street2 = new Street("Brattenga", weights2); // second constructor
    System.out.println("\n\n" + street2);
    weights2[3] = 333;
    System.out.println("Deep-copying in the contructor prevents Brattenga from beeing changed:");
    System.out.println(street2);
    int[] weights3 = street2.getWeights();
    weights3[3] = 666;
    System.out.println("Deep-copying in getWeights() prevents Brattenga from beeing changed:");
    System.out.println(street2);
  }
} // StreetTest



/* Kjøring:
Odde siden av Solgaten:
  1: 110
  3: 600
  5: 1055
  7: 1090
  9: 0
Like siden av Solgaten:
  2: 1500
  4: 0
  6: 0
  8: 100
  10: 550
To fulle ved siden av hverandre på odde siden!

Det throwGarbagees mer søppel (880 i dunk 3)
Odde siden av Solgaten:
  1: 110
  3: 1480
  5: 1055
  7: 1090
  9: 0
Like siden av Solgaten:
  2: 1500
  4: 0
  6: 0
  8: 100
  10: 550
To fulle ved siden av hverandre på odde siden!

Tømmer odde siden
Odde siden av Solgaten:
  1: 0
  3: 0
  5: 0
  7: 0
  9: 0
Like siden av Solgaten:
  2: 1500
  4: 0
  6: 0
  8: 100
  10: 550

Det throwGarbagees mer søppel (100 i dunk 2; 880 i dunk 3; 1500 i dunk 4; 550 kg i 9)
Odde siden av Solgaten:
  1: 0
  3: 880
  5: 0
  7: 0
  9: 550
Like siden av Solgaten:
  2: 1600
  4: 1500
  6: 0
  8: 100
  10: 550
To fulle ved siden av hverandre på like siden!

Tømmer like siden
Odde siden av Solgaten:
  1: 0
  3: 880
  5: 0
  7: 0
  9: 550
Like siden av Solgaten:
  2: 0
  4: 0
  6: 0
  8: 0
  10: 0


Odde siden av Brattenga:
  1: 1
  3: 3
  5: 5
Like siden av Brattenga:
  2: 2
  4: 4
  6: 6
Pga. dyp kopiering i konstruktøren blir ikke Brattenga endret:
Odde siden av Brattenga:
  1: 1
  3: 3
  5: 5
Like siden av Brattenga:
  2: 2
  4: 4
  6: 6
Pga. dyp kopiering i getVektene() blir ikke Brattenga endret:
Odde siden av Brattenga:
  1: 1
  3: 3
  5: 5
Like siden av Brattenga:
  2: 2
  4: 4
  6: 6
*/
