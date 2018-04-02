/**

	Title:         BasicMember.java
	Date:          02.04.2018
    Author:        Eskil Uhlving Larsen

*/

import java.time.LocalDate;

class BasicMember extends BonusMember {
    public BasicMember(int memberNr, PersonalInfo pers, LocalDate enrolledDate) {
        super(memberNr, pers, enrolledDate);
    }
}
