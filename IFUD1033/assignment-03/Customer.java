/**

	Title:         Customer.java
	Date:          2010-01-16
    Author:        Else Lervik og Stiftelsen TISIP
	Translated by: Eskil Uhlving Larsen (28.02.2018)

*/

/**
 * Contains customer data.
 */

class Customer {
  private final String name;
  private final String phone;

/**
 * Constructor:
 * Both name and phone number must be entered, they can't be empty strings or null.
 */
  public Customer(String name, String phone) {
    if (name == null || name.trim().equals("")) {
      throw new IllegalArgumentException("Name must be entered.");
    }
    if (phone == null || phone.trim().equals("")) {
      throw new IllegalArgumentException("Phone number must be entered.");
    }
    this.name = name.trim();
    this.phone = phone.trim();
  }

  public String getName() {
    return name;
  }

  public String getPhone() {
    return phone;
  }

  public String toString() {
    return name + ", phone " + phone;
  }
}
