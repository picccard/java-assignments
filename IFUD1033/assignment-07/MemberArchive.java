/**

	Title:         MemberArchive.java
	Date:          02.04.2018
    Author:        Eskil Uhlving Larsen

*/

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Random;

class MemberArchive {
    private ArrayList<BonusMember> members;
    public MemberArchive() {
        this.members = new ArrayList<BonusMember>();
    }

    /*
    findPoints() takes memberNr and password as agruments and returns
    the number of points this member have.
    A negative number is returned if the membernumber is valid and/or
    */
    public int findPoints(int memberNr, String password) {
        for (BonusMember member : members) {
            if (member.getMemberNr() == memberNr && member.okPassword(password)) {
                return member.getPoints();
            }
        }
        return -1; //MemberNr or password is wrong/not found.
    }

    /*
    regPoints() takes memberNr and a point-sum as arguments.
    These new points should be added to the members total sum of points.
    If the memberNr aint valid, false is returned.
    */
    public boolean regPoints(int memberNr, int points) {
        for (BonusMember member : members) {
            if (member.getMemberNr() == memberNr) {
                return member.regPoints(points);
            }
        }
        return false; //memberNr not found.
    }

    /*
    newMember() creates a object of BasicMember and adds this to the memberArchive.
    Every members starts as a basic-member.
    The memberNr is returned.
    */
    public int newMember(PersonalInfo pers, LocalDate enrolledDate) {
        int newMemberNr = getAvailableNr();
        members.add(new BasicMember(newMemberNr, pers, enrolledDate));
        return newMemberNr;
    }

    /*
    This method should get a random integer,
    this nr should not be previous used as memberNr.
    Random is awfull. MemberNr will always be [100, 999]
    */
    private int getAvailableNr() {
        Random random = new Random();
        boolean ok = true;
        int max = 999;
        int min = 100;
        int newMemberNr;

        // this could be rly bad! oh boii
        // random is awfull.
        do {
            newMemberNr = random.nextInt(max - min + 1) + min;
            for (BonusMember member : members) {
                if (member.getMemberNr() == newMemberNr) {
                    ok = false; // Nr already used, start over
                    break;
                }
            }
        } while (!ok);

        return newMemberNr;
    }

    /*
    checkmembers() should loop through all members and upgrade members who qualify for it.
    Basic-members can qualify for silver and gold.
    Silver-members can qualify for gold.
    I use instance.getClass().getSimpleName() to check member-type, could switch to use instanceOf.
    */
    public int checkMembers() {
        int upgradeCount = 0;
        for (int i = 0; i < members.size(); i++) {
            switch (members.get(i).getClass().getSimpleName()) {
                case "BasicMember" :
                    if (members.get(i).findQualifyingPoints(LocalDate.now()) >= 25000) {
                        // Upgrade to silver
                        SilverMember newAcc = new SilverMember(
                        members.get(i).getMemberNr(),
                        members.get(i).getPersonalInfo(),
                        members.get(i).getEntrolledDate(),
                        members.get(i).getPoints());
                        members.set(i, newAcc);
                        System.out.println("Upgraded " + members.get(i).getMemberNr() + " to silver");
                        upgradeCount++;
                    }
                // No break between the two cases, this way an account
                // with enough points can be upgraded to silver and gold in a single run
                // Should maybe move Gold-upgrade above silver,
                // this way we won't get a "double-upgrade"
                case "SilverMember" :
                    if (members.get(i).findQualifyingPoints(LocalDate.now()) >= 75000) {
                        // Upgrade to gold
                        GoldMember newAcc = new GoldMember(
                        members.get(i).getMemberNr(),
                        members.get(i).getPersonalInfo(),
                        members.get(i).getEntrolledDate(),
                        members.get(i).getPoints());
                        members.set(i, newAcc);
                        System.out.println("Upgraded " + members.get(i).getMemberNr() + " to gold");
                        upgradeCount++;
                    }
            }
        }
        return upgradeCount;
    }

    public static void main(String[] args) {
        // Create to PersonalInfo
        PersonalInfo ole = new PersonalInfo("Olsen", "Ole", "ole.olsen@dot.com", "ole");
        PersonalInfo tove = new PersonalInfo("Hansen", "Tove", "tove.hansen@dot.com", "tove");

        // MemberArchive with two new members, saving memberNR for later
        MemberArchive memberArchive = new MemberArchive();
        int oleNR = memberArchive.newMember(ole, LocalDate.of(2018, 2, 15));
        int toveNR = memberArchive.newMember(tove, LocalDate.of(2018, 3, 5));
        System.out.println(oleNR); //should be unique (100-999)
        System.out.println(toveNR);

        System.out.println("Totalt antall tester: 7");
        if (memberArchive.findPoints(oleNR, "ole") == 0) {
            // Ole got a brand new BasicMember-acc with 0 points.
            // memberNR and password should be correct.
            System.out.println("Test 1 ok");
        }

        if (memberArchive.findPoints(toveNR, "tove") == 0) {
            // Same as last test, finds memberNR later in the arraylist
            System.out.println("Test 2 ok");
        }

        if (memberArchive.findPoints(oleNR, "olee") == -1) {
            // valid membernr, wrong password
            System.out.println("Test 3 ok");
        }

        if (memberArchive.findPoints(toveNR, "toove") == -1) {
            // same as last, membernr is later in arraylist
            System.out.println("Test 4 ok");
        }

        if (memberArchive.regPoints(oleNR, 75000) == true && memberArchive.findPoints(oleNR, "ole") == 75000) {
            // points are successfully added.
            System.out.println("Test 5 ok");
        }

        if (!memberArchive.regPoints(120, 5000)) {
            // invalid memberNR, fails and returns false.
            System.out.println("Test 6 ok");
        }

        memberArchive.regPoints(toveNR, 24999+1); // Tove now qualify to upgrade
        if (memberArchive.checkMembers() == 3) {
            System.out.println("Test 7 ok");
        }
    }
}
