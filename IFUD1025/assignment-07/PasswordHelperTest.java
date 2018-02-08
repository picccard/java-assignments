/**

	Title:	PasswordHelperTest.java
	Date:	09.03.2017
	Author:	Eskil Uhlving Larsen

*/
class PasswordHelperTest {
  public static void main(String[] args) {
    User myself = new User("Eskil", "test");

    System.out.println(myself.getPassword());
    System.out.println(myself.passwordStrength());

    System.out.println(myself.isSignedIn());
    myself.signIn("Eskil", "test");
    System.out.println(myself.isSignedIn());
    myself.signOut();
    System.out.println(myself.isSignedIn());

  }
}
