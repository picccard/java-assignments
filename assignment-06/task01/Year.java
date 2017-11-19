/**

	Title:	Year.java
	Date:	06.03.2017
	Author:	Eskil Uhlving Larsen

*/
/*

   ___________________________________
  | Year                             |
  |----------------------------------|
  | -int year {readonly}             |
  |----------------------------------|
  | +Aar(int year)                   |
  | +int getYear()                   |
  | +int lastYear()                  |
  | +int nextYear()                  |
  | +int afterSomeYears(int tall)    |
  | +boolean isLeapyear()            |
  |__________________________________|


*/

class Year {
  private int year;

  public Year(int a) {
    this.year = a;
  }

  public int getYear() {
    return this.year;
  }
  public int lastYear() {
    return (this.year - 1);
  }
  public int nextYear() {
    return (this.year + 1);
  }
  public int afterSomeYears(int a) {
    return (this.year + a);
  }
  public boolean isLeapyear() {
    if (this.year%400 == 0 && this.year%100 == 0) {
        return true;
    } else if (this.year%100 != 0 && this.year%4 ==  0) {
      return true;
    } else {
      return false;
    }
  }
}
