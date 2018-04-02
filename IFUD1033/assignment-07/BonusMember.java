/**

	Title:         BonusMember.java
	Date:          02.04.2018
    Author:        Eskil Uhlving Larsen

*/

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

class BonusMember {
    private final int memberNr;
    private final PersonalInfo pers;
    private final LocalDate enrolledDate;
    private int points = 0;

    public BonusMember(int memberNr, PersonalInfo pers, LocalDate enrolledDate) {
        this.memberNr = memberNr;
        this.pers = pers;
        this.enrolledDate = enrolledDate;
    }

    public BonusMember(int memberNr, PersonalInfo pers, LocalDate enrolledDate, int startPoints) {
        this.memberNr = memberNr;
        this.pers = pers;
        this.enrolledDate = enrolledDate;
        this.points = startPoints;
    }

    public int getMemberNr() {
        return memberNr;
    }

    public PersonalInfo getPersonalInfo() {
        return pers;
    }

    public LocalDate getEntrolledDate() {
        return enrolledDate;
    }

    public int getPoints() {
        return points;
    }

    public int findQualifyingPoints(LocalDate date) {
        // Hvordan vite opp oppgraderes til sølv eller gull?
        long daysBetween = ChronoUnit.DAYS.between(enrolledDate, date);
        if (daysBetween > 365) {
            return 0; // Membership is over a year old
        } else {
            return getPoints();
        }
    }

    public boolean okPassword(String password) {
        return pers.okPassword(password);
    }

    public boolean regPoints(int newPoints) {
        // Doens't check if newPoints is negative
        double pointFactor = 1.0;
        String className = this.getClass().getSimpleName();
        switch (className) {
            // default is 1.0, with is already declared
            case "SilverMember" : pointFactor = 1.2;break;
            case "GoldMember" : pointFactor = 1.5;break;
        }
        points += newPoints * pointFactor;
        return true;
    }
}
