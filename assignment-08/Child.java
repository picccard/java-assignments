/**

	Title:	Child.java
	Date:	23.03.2017
	Author:	Eskil Uhlving Larsen

*/
class Child {

  private final String name;
  private char letter;
  private int points = 0;

  public Child(String name) {
    this.name = name;
  }

  public String getName() {
    return this.name;
  }
  public char getLetter() {
    return this.letter;
  }
  public int getPoints() {
    return this.points;
  }

  public void setLetter(char letter) {
    this.letter = letter;
  }

  public void getPoint() {
    this.points += 1;
  }

  public void draw(Deck deck) {
    this.letter = deck.draw();
  }

  public boolean wantSwap(Child otherChild) {
    return (this.letter >= 'M' && otherChild.letter >= 'M');
  }

  public void swap(Child otherChild) {
    char myCard = this.getLetter();
    this.setLetter(otherChild.getLetter());
    otherChild.setLetter(myCard);
  }

  public void compare(Child otherChild) {
      /*
        Earliest letter gets a point.
      */
    if (this.getLetter() > otherChild.getLetter()) {
      otherChild.getPoint();
  } else if (this.getLetter() < otherChild.getLetter()) {
      this.getPoint();
    }
  }
}
