/**

	Title:         PersonalInfo.java
	Date:          2010-01-18
    Author:        Else Lervik
	Translator:    Eskil Uhlving Larsen (02.04.2018)

*/

/**
 * PersonalInfo.java  2010-01-18
 *
 * Class with personalinformation: firstname, lastname, eMail and password.
 * password can be changed, but must differ from the old.
 * passwordcontroll doen't differ between letter-case.
 */
class PersonalInfo {
  private final String lastname;
  private final String firstname;
  private final String eMail;
  private String password;

  /**
   * Constructor:
   * All data must be given: firstname, lastname, eMail, password
   * None of the data can be null or empty strings.
   */
  public PersonalInfo(String firstname, String lastname, String eMail, String password) {
    if (firstname == null || lastname == null || eMail == null || password == null ||
        firstname.trim().equals("") || lastname.trim().equals("") || eMail.trim().equals("") || password.trim().equals("")) {
          throw new IllegalArgumentException("One or more constructorarguments is null and/or is empty.");
    }
    this.firstname = firstname.trim();
    this.lastname = lastname.trim();
    this.eMail = eMail.trim();
    this.password = password.trim();
  }

  public String getFirstname() {
    return firstname;
  }

  public String getLastname() {
    return lastname;
  }

  public String getEmail() {
    return eMail;
  }

  /**
   * Method returns true of password is correct.
   * The passwordcontroll does not care about letter-case.
   */
  public boolean okPassword(String pwd) {
    return password.equalsIgnoreCase(pwd);
  }

  /**
   * Method changes the password if the new is not equal to the old.
   * Letter-case does not matter.
   *
   * Method returns true if password is changed, else false is returned.
   */
  public boolean changePassword(String old, String newPwd) {
    if (old == null || newPwd == null) {
      return false;
    }
    if (!password.equalsIgnoreCase(old.trim())) {
      return false;
    } else {
      password = newPwd.trim();
      return true;
    }
  }
}
