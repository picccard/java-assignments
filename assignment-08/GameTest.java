/**

	Title:	GameTest.java
	Date:	23.03.2017
	Author:	Eskil Uhlving Larsen

*/
class GameTest {
  public static void main(String[] args) {
    String player1Name = javax.swing.JOptionPane.showInputDialog("Who is player1?");
    String player2Name = javax.swing.JOptionPane.showInputDialog("Who is player2?");
    int turns = Integer.parseInt(javax.swing.JOptionPane.showInputDialog("How many turns?:"));

    Game game = new Game(player1Name, player2Name);

    for (int i=0;i<turns;i++) {
      System.out.println(game.turn());
    }
    System.out.println(game.finish());
  }
}
