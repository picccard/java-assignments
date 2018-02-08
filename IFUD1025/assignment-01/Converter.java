/*

	Title:	Converter.java - oving1
	Date:	04.02.2017
	Author:	Eskil Uhlving Larsen

*/
/*
    Converts fahrenheit to celsius and prints both numbers.
*/
class Converter {
  public static void main(String[] args) {
    double fahrenheit = 98;
    double celsius = (fahrenheit - 32) * 5/9;
    System.out.println(fahrenheit + " degrees fahrenheit is " + celsius +" degrees celsius.");
  }
}
