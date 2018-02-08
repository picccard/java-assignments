/**

	Title:	User.java
	Date:	09.03.2017
	Author:	Eskil Uhlving Larsen

*/
class User {
  private String username;
  private PasswordHelper helper;
  private boolean signedIn;

  public User(String name, String password) {
    this.username = name;
    this.helper = new PasswordHelper(password);
  }

  public void signIn(String user, String password) {
    if (user.equals(this.username)) {
      System.out.println("username ok");
      if (password.equals(this.helper.getPassword())) {
        this.signedIn = true;
        System.out.println("signed in");
      }
    }
  }

  public void signOut() {
    this.signedIn = false;
    System.out.println("signed out");
  }

  public boolean isSignedIn() {
    return this.signedIn;
  }

  public int passwordStrength() {
      return helper.passwordStrength();
  }

  public String getPassword() {
      return this.helper.getPassword();
  }

}
