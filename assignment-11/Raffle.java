/**

	Title:	Raffle.java
	Date:	17.04.2017
	Author:	Eskil Uhlving Larsen

*/
import java.util.Arrays;
import java.util.Random;
public class Raffle {
  private int[] draw;
  private Random randomGen = new Random();

  public Raffle() {
    draw = new int[5];
    for (int i=0; i<draw.length; i++) {
      draw[i] = randomGen.nextInt(10);
    }
    Arrays.sort(this.draw);
  }

  public int[] getDraw() {
    int[] tempDraw = new int[this.draw.length];
    for (int i=0; i<tempDraw.length; i++) {
      tempDraw[i] = this.draw[i];
    }
    return tempDraw;
  }

  public int getMatches(int[] guesses) {
      // https://stackoverflow.com/questions/29293173/count-number-of-strings-that-match-in-two-arrays-java
      // Check out this later:  https://stackoverflow.com/questions/8098601/java-count-occurrence-of-each-item-in-an-array
      int matchCount = 0;

      for(int i = 0, j = 0;i < this.draw.length && j < guesses.length;) {
          if(draw[i] == guesses[j]) {
              matchCount++;
              i++;
              j++;
          } else if(this.draw[i] < guesses[j]) {
              i++;
          } else {
              j++;
          }
      }
      return matchCount;
  }
}
