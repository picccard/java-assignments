/**

	Title:         Individual.java
	Date:          14.04.2018
    Author:        Eskil Uhlving Larsen

*/

import java.time.LocalDate;

class Individual {
    private final String individualname;
    private final LocalDate birthDate; // e.g. LocalDate.of(2008, 2, 10)

    public Individual(String individualname, LocalDate birthDate) {
        this.individualname = individualname;
        this.birthDate = birthDate;
    }

    public String getIndividualname() {
        return individualname;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }
}
