package models;

/**
 * Created by Guest on 8/18/17.
 */
public class Member {
    private String memberName;
    private int badgeNumber;

    public Member ( String memberName, int badgeNumber) {
        this.memberName=memberName;
        this.badgeNumber=badgeNumber;
    }

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public int getBadgeNumber() {
        return badgeNumber;
    }

    public void setBadgeNumber(int badgeNumber) {
        this.badgeNumber = badgeNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Member member = (Member) o;

        if (badgeNumber != member.badgeNumber) return false;
        return memberName.equals(member.memberName);
    }

    @Override
    public int hashCode() {
        int result = memberName.hashCode();
        result = 31 * result + badgeNumber;
        return result;
    }
}
