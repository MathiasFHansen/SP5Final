package MembershipInfo;

import java.util.ArrayList;

public class Member
{
    private int id;
    private int age;
    private String firstName;
    private String lastName;
    private String memberType;
    private boolean active;
    private boolean competitive;

    public ArrayList<Member> memberList = new ArrayList<>();



    public Member(int id, int age, String firstName, String lastName, String memberType, boolean active, boolean competitive)
    {
        this.id = id;
        this.age = age;
        this.firstName = firstName;
        this.lastName = lastName;
        this.memberType = memberType;
        this.active = active;
        this.competitive = competitive;

    }

    public ArrayList<Member> getMemberList()
    {
        return memberList;
    }

    public int getAge()
    {
        return age;
    }

    public void setAge(int age)
    {
        this.age = age;
    }

    public String getFirstName()
    {
        return firstName;
    }

    public void setFirstName(String firstName)
    {
        this.firstName = firstName;
    }

    public String getLastName()
    {
        return lastName;
    }

    public void setLastName(String lastName)
    {
        this.lastName = lastName;
    }

    public String getMemberType()
    {
        return memberType;
    }

    public void setMemberType(String memberType)
    {
        this.memberType = memberType;
    }

    public boolean isActive()
    {
        return active;
    }

    public void setActive(boolean active)
    {
        this.active = active;
    }

    public boolean isCompetitive()
    {
        return competitive;
    }

    public void setCompetitive(boolean competitive)
    {
        this.competitive = competitive;
    }

    public void setMemberList(ArrayList<Member> memberList)
    {
        this.memberList = memberList;
    }

    @Override
    public String toString()
    {
        return "Members: " + "ID " +
                id + "\t" + "Age " +
                age + "," + "\t" +
                firstName + " " + lastName + "," + "\t" +
                memberType + "," + "\t" + "active " +
                active + "," + "\t" + "competitive " +
                competitive  + "\n\n";
    }
}
