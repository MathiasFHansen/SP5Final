package MembershipInfo;

public class Subscription
{
    private int age;
    private boolean hasPayed;
    private boolean active;

    public Subscription(int age, boolean hasPayed, boolean active)
    {
        this.age = age;
        this.hasPayed = hasPayed;
        this.active = active;
    }

    public int getAge()
    {
        return age;
    }

    public void setAge(int age)
    {
        this.age = age;
    }

    public boolean isHasPayed()
    {
        return hasPayed;
    }

    public void setHasPayed(boolean hasPayed)
    {
        this.hasPayed = hasPayed;
    }

    public boolean isActive()
    {
        return active;
    }

    public void setActive(boolean active)
    {
        this.active = active;
    }

    @Override
    public String toString()
    {
        return "Subscription " +
                "age = " + age +
                ", hasPayed = " + hasPayed +
                ", active = " + active;
    }
}
