/**

	Title:	Street.java
	Date:	31.03.2017
	Author:	Eskil Uhlving Larsen

*/

class Street {
  private final String name;
  private int[] weights;


  Street(String name, int streetLength) {
    this.name = name;
    this.weights = new int[streetLength+1];
  }

  Street(String name, int[] weightTable) {
    this.name = name;
    this.weights = new int[weightTable.length+1];
    for (int i=1; i<this.weights.length; i++) {
      this.weights[i] = weightTable[i-1];
    }
  }

  public String getName() {
    return this.name;
  }

  public int[] getWeights() {
    int[] returnTable = new int[this.weights.length];
    for (int i=0; i<this.weights.length; i++) {
      returnTable[i] = this.weights[i];
    }
    return returnTable;
  }

  public void throwGarbage(int streetNr, int weight) {
    this.weights[streetNr] += weight;
  }

  public boolean isFull(int streetNr) {
    return (this.weights[streetNr] >= 1000);
  }

  public void empty(int streetNr) {
    this.weights[streetNr] = 0;
  }


  public void empty(boolean even) {
    int i;
    if (even) {
      i = 0;
    } else {
        i = 1;
    }
    while (i<this.weights.length) {
      this.weights[i] = 0;
      i+=2;
    }
  }

  public boolean twoFullNextToEachOther(boolean even) {
    if (even) {
      for (int i=2; i<this.weights.length-2; i+=2) {
        if (this.weights[i] > 999 && this.weights[i+2] > 999) {
          return true;
        }
      }
    } else {
      for (int i=1; i<this.weights.length-2; i+=2) {
        if (this.weights[i] > 999 && this.weights[i+2] > 999) {
          return true;
        }
      }
    }
    return false;
  }

  public String toString() {
    String returnString = "";
    returnString += "Odd side of " + this.getName();
    for (int i=1; i<this.weights.length; i+=2) {
      returnString += "\n    " + i + ": " + this.weights[i];
    }
    returnString += "\nLike siden av " + this.getName();
    for (int i=2; i<this.weights.length; i+=2) {
      returnString += "\n    " + i + ": " + this.weights[i];
    }
    return returnString;
  }
}
