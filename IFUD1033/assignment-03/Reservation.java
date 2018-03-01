/**

	Title:         Reservasjon.java
	Date:          2010-01-16
    Author:        Else Lervik og Stiftelsen TISIP
    Updated by:    Nils Tesdal (2016-01-25)
	Translated by: Eskil Uhlving Larsen (28.02.2018)

*/

/**
 *
 * An object with data about a reservation.
 * Methods for getting data and checking overlap
 * med annen reservasjon.
 */

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

class Reservation {
  private final LocalDateTime fromDate;
  private final LocalDateTime toDate;
  private final Customer customer;

  /**
   * Constructor:
   * fromDate must be before toDate.
   * None of the arguments can be null.
   */
  public Reservation(LocalDateTime fromDate, LocalDateTime toDate, Customer customer) {
    if (fromDate == null || toDate == null) {
      throw new IllegalArgumentException("From-time and/or to-time is null");
    }
    if (fromDate.isAfter(toDate) || fromDate.equals(toDate)) {
      throw new IllegalArgumentException("From-time is equal to or after to-time");
    }
    if (customer == null) {
      throw new IllegalArgumentException("Customer is null");
    }
    this.fromDate = fromDate;
    this.toDate = toDate;
    this.customer = customer;
  }

  public LocalDateTime getFromTime() {
    return this.fromDate;
  }

  public LocalDateTime getToTime() {
    return this.toDate;
  }

  /**
   * Method returns true if timeinterval [checkFromTime, checkToTime] overlap
   * with the timeinterval inside the this.reservationobject [fromDate, toDate].
   * Overlao is not defined if checkFromTime or checkToTime is null.
   * Then a NullPointerException is thrown.
   */
  public boolean overlap(LocalDateTime checkFromTime, LocalDateTime checkToTime) {
    return (checkToTime.isAfter(fromDate) && checkFromTime.isBefore(toDate));
  }

  public String toString() {
    return "Customer: " + customer.getName() + ", phone: " + customer.getPhone() + ", from " +
                       fromDate.format(DateTimeFormatter.ISO_DATE_TIME) + ", to " + toDate.format(DateTimeFormatter.ISO_DATE_TIME);
  }

  /**
   * Tests.
   */
  public static void main(String[] args) {
    Customer c = new Customer("Alice Ross", "12345678");
    System.out.println("Total number of tests: ");
    Reservation r1 = new Reservation(LocalDateTime.of(2003, 2, 1, 10, 0), LocalDateTime.of(2003, 2, 1, 11, 0), c);
    System.out.println(r1);

    Reservation r2 = new Reservation(LocalDateTime.of(2003, 1, 21, 10, 0), LocalDateTime.of(2003, 1, 21, 10, 30), c);
    Reservation r3 = new Reservation(LocalDateTime.of(2003, 2, 1, 11, 30), LocalDateTime.of(2003, 2, 1, 13, 0), c);
    Reservation r4 = new Reservation(LocalDateTime.of(2003, 2, 1, 9, 0), LocalDateTime.of(2003, 2, 1, 11, 0), c);
    if (r1.toString().equals("Customer: Alice Ross, phone: 12345678, from 2003-02-01T10:00:00, to 2003-02-01T11:00:00") &&
        r2.toString().equals("Customer: Alice Ross, phone: 12345678, from 2003-01-21T10:00:00, to 2003-01-21T10:30:00") &&
        r3.toString().equals("Customer: Alice Ross, phone: 12345678, from 2003-02-01T11:30:00, to 2003-02-01T13:00:00") &&
        r4.toString().equals("Customer: Alice Ross, phone: 12345678, from 2003-02-01T09:00:00, to 2003-02-01T11:00:00")) {
          System.out.println("Reservation: Test 1 successful.");
    }

    if (r1.overlap(LocalDateTime.of(2003, 2, 1, 10, 0), LocalDateTime.of(2003, 2, 1, 11, 0)) &&
       !r1.overlap(LocalDateTime.of(2003, 2, 2, 10, 0), LocalDateTime.of(2003, 2, 2, 11, 0)) &&
        r1.overlap(LocalDateTime.of(2003, 2, 1, 10, 30), LocalDateTime.of(2003, 2, 1, 11, 0)) &&
        r1.overlap(LocalDateTime.of(2003, 2, 1, 9, 30), LocalDateTime.of(2003, 2, 1, 10, 30))) {
         System.out.println("Reservation: Test 2 successful.");
    }
  }
}
