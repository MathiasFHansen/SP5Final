import MembershipInfo.DelfinSystem;
import SQL.DelfinJDBC;

public class Main
{



    public static void main(String[] args)
    {
        DelfinJDBC delfinJDBC = new DelfinJDBC();
        DelfinSystem delfinSystem = new DelfinSystem();

        delfinSystem.options();

    }
}
