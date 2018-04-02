/**

	Title:         SilverMember.java
	Date:          02.04.2018
    Author:        Eskil Uhlving Larsen

*/

import java.time.LocalDate;

class SilverMember extends BonusMember {
    public SilverMember(int memberNr, PersonalInfo pers, LocalDate enrolledDate, int startPoints) {
        super(memberNr, pers, enrolledDate, startPoints);
    }
}
