package MembershipInfo;

import java.util.ArrayList;

public class Coach
{
    private int idCoach;
    private String firstName;
    private String lastName;
    public ArrayList<Coach> coachList = new ArrayList<>();

    public Coach(int idCoach, String firstName, String lastName)
    {
        this.idCoach = idCoach;
        this.firstName = firstName;
        this.lastName = lastName;
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

    public ArrayList<Coach> getCoachList()
    {
        return coachList;
    }

    @Override
    public String toString()
    {
        return "Coach:" + "\t" + "Coach id: " + idCoach +  "\t" + firstName + "\t" + lastName;
    }
}
