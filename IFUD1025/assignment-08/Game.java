/**

	Title:	Game.java
	Date:	23.03.2017
	Author:	Eskil Uhlving Larsen

*/
class Game {
  private Deck deck = new Deck();
  private Child player1;
  private Child player2;

  public Game(String aName, String bName) {
    this.player1 = new Child(aName);
    this.player2 = new Child(bName);
  }

  public String turn() {
    String info = "";
    this.player1.draw(deck);
    info += this.player1.getName() + " drew " + this.player1.getLetter() + ". ";
    this.player2.draw(deck);
    info += this.player2.getName() + " drew " + this.player2.getLetter() + ". ";
    if (this.player1.wantSwap(player2)) {
      this.player1.swap(player2);
      info += "They swapped cards. ";
    } else {
      info += "No swap. ";
    }
    this.player1.compare(player2);
    info += this.player1.getName() + " have " + this.player1.getPoints() + " points. ";
    info += this.player2.getName() + " have " + this.player2.getPoints() + " points. ";
    return info;
  }

  public String finish() {
    String info = "";
    info += "Summary: " + this.player1.getName() + " got " + this.player1.getPoints() + " points";
    info += " and " + this.player2.getName() + " got " + this.player2.getPoints() + " points.";
    return info;
  }
}
