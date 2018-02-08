/*

	Title:	TimeConverter.java
	Date:	09.02.2017
	Author:	Eskil Uhlving Larsen

*/
class TimeConverter {
  public static void main(String[] args) {
    int hr = 23;
    int mm = 59;
    int ss = 59;
    int totalSec = (hr * 60 * 60) + (mm * 60) + ss;
    System.out.println(hr + " hours, " + mm + " minutes and " + ss + " seconds, is a total of " + totalSec + " seconds.");
  }
}
