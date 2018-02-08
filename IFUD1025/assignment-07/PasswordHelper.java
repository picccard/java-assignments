/**

	Title:	PasswordHelper.java
	Date:	09.03.2017
	Author:	Eskil Uhlving Larsen

*/
class PasswordHelper {
  private String password;

  public PasswordHelper(String suggestion) {
    this.password = this.removeIllegalPasswordChar(suggestion);
  }

  private String removeIllegalPasswordChar(String text) {
    String returnText = "";
    for (int i=0; i<text.length(); i++) {
      if (PasswordChar.isPasswordChar(text.charAt(i))) {
        returnText = returnText + text.charAt(i);
      }
    }
    return returnText;
  }

  public int passwordStrength() {
    int strength = 0;
    int capitalLetter = 0;
    int regularLetter = 0;
    int number = 0;
    int specialCharacter = 0;

    if (this.password.length() >= 8) {
      strength = 1;
    }

    for (int i=0; i<this.password.length(); i++) {
      if (PasswordChar.isUpperLetter(this.password.charAt(i))) {
        capitalLetter = 1;
    } else if (PasswordChar.isLowerLetter(this.password.charAt(i))) {
        regularLetter = 1;
    } else if (PasswordChar.isDigit(this.password.charAt(i))) {
        number = 1;
    } else if (PasswordChar.isSpecialChar(this.password.charAt(i))) {
        specialCharacter = 1;
      }
    }
    strength = strength + capitalLetter + regularLetter + number + specialCharacter;
    return strength;
  }

  public String getPassword() {
    return this.password;
  }
}
