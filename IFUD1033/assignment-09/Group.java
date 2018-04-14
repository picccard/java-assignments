/**

	Title:         Group.java
	Date:          14.04.2018
    Author:        Eskil Uhlving Larsen

*/

class Group {
    private String groupName;
    private int count;

    public Group(String groupName) {
        this.groupName = groupName;
        this.count = 0;
    }

    public Group(String groupName, int count) {
        this.groupName = groupName;
        this.count = count;
    }

    public String getGroupName() {
        return groupName;
    }

    public int getCount() {
        return count;
    }
}
