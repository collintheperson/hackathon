package models;

/**
 * Created by Guest on 8/18/17.
 */
public class Member {
    private String memberName;
    private int badge;
    private int id;

    public Member ( String memberName, int badge) {
        this.memberName=memberName;
        this.badge=badge;
    }

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public int getBadgeNumber() {
        return badge;
    }

    public void setBadgeNumber(int badge) {
        this.badge = badge;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Member member = (Member) o;

        if (badge != member.badge) return false;
        return memberName.equals(member.memberName);
    }

    @Override
    public int hashCode() {
        int result = memberName.hashCode();
        result = 31 * result + badge;
        return result;
    }
}
