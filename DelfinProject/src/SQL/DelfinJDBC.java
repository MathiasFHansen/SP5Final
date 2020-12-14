package SQL;

import MembershipInfo.Coach;
import MembershipInfo.Member;
import MembershipInfo.Subscription;


import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;

public class DelfinJDBC
{
    private Connection conn;
    String JdbcUrl = "jdbc:mysql://localhost/delfinproject?" + "autoReconnect=true&useSSL=false";
    String username = "root";
    String password = "Untbap503rc";

    public DelfinJDBC()
    {
        createConnection();
    }

    public void createConnection()
    {
        try
        {
            conn = DriverManager.getConnection(JdbcUrl, username, password);
        } catch (SQLException throwables)
        {
            throwables.printStackTrace();
        }
    }

    public void saveMembersToDatabase(Member m)
    {
        String sql = "INSERT INTO delfinproject.personinfo (Age, FirstName, LastName, MemberType, Status, Competitive) VALUES (?,?,?,?,?,?)";
        try
        {
            PreparedStatement ps = conn.prepareStatement(sql);

            for (Member mem : m.memberList)
            {
                ps.setInt(1, m.getAge());
                ps.setString(2, m.getFirstName());
                ps.setString(3, m.getLastName());
                ps.setString(4, m.getMemberType());
                ps.setBoolean(5, m.isActive());
                ps.setBoolean(6, m.isCompetitive());


                ps.execute();
            }
        } catch (SQLException throwables)
        {
            throwables.printStackTrace();
        }
    }

    public void saveCoachToDatabase(Coach c)
    {
        String sql = "INSERT INTO delfinproject.coach (FirstName, LastName) VALUES (?,?)";
        try
        {
            PreparedStatement ps = conn.prepareStatement(sql);

            for (Coach coach: c.coachList)
            {
                ps.setString(1, c.getFirstName());
                ps.setString(2, c.getLastName());

                ps.execute();
            }
        } catch (SQLException throwables)
        {
            throwables.printStackTrace();
        }
    }


    public ArrayList<Coach> getCoachFromDB()
    {
        ArrayList<Coach> tmp = new ArrayList<>();
        try
        {

            PreparedStatement pstm;
            String sql = "select * from delfinproject.coach";
            pstm = conn.prepareStatement(sql);
            ResultSet resultSet;

            resultSet = pstm.executeQuery(sql);

            while (resultSet.next())
            {
                Coach coach = new Coach(
                        resultSet.getInt("idCoach"),
                        resultSet.getString("FirstName"),
                        resultSet.getString("LastName")
                );
                tmp.add(coach);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        System.out.println(tmp);
        return tmp;
    }

    public ArrayList<Member> getMembersFromDB()
    {
        ArrayList<Member> tmp = new ArrayList<>();
        try
        {

            PreparedStatement pstm;
            String sql = "select * from delfinproject.personinfo";
            pstm = conn.prepareStatement(sql);
            ResultSet resultSet;

            resultSet = pstm.executeQuery(sql);

            while (resultSet.next())
            {
                //OBS! The age parameter of the member object here is changed to the databasetables ID.
                Member member = new Member(
                        resultSet.getInt("idPersonInfo"),
                        resultSet.getInt("Age"),
                        resultSet.getString("FirstName"),
                        resultSet.getString("LastName"),
                        resultSet.getString("MemberType"),
                        resultSet.getBoolean("Status"),
                        resultSet.getBoolean("Competitive")
                );
                tmp.add(member);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        System.out.println(tmp);
        return tmp;
    }

    public ArrayList<Member> getCompetitiveMembersFromDB()
    {
        ArrayList<Member> tmp = new ArrayList<>();
        try
        {

            PreparedStatement pstm;
            String sql = "select * from delfinproject.personinfo WHERE Competitive = 1";
            pstm = conn.prepareStatement(sql);
            ResultSet resultSet;

            resultSet = pstm.executeQuery(sql);

            while (resultSet.next())
            {

                Member member = new Member(
                        resultSet.getInt("idPersonInfo"),
                        resultSet.getInt("Age"),
                        resultSet.getString("FirstName"),
                        resultSet.getString("LastName"),
                        resultSet.getString("MemberType"),
                        resultSet.getBoolean("Status"),
                        resultSet.getBoolean("Competitive")
                );
                tmp.add(member);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        System.out.println(tmp);
        return tmp;
    }



    public void saveSwimTestsToDatabase()
    {
        String sql = "INSERT INTO delfinproject.swimtraining (SwimmerID, TrainingTime, Date) VALUES (?,?,?)";
        Scanner input = new Scanner(System.in);
        try
        {
            PreparedStatement ps = conn.prepareStatement(sql);

            System.out.println("Choose swimmer from ID to add in swimtraining");
            ps.setInt(1,input.nextInt());
            System.out.println("Write swimmers swimtime like (mm.ss etc.) ");
            ps.setString(2,input.next());
            System.out.println("Write date of the swimtime like (DD-MM-YYYY)");
            ps.setString(3,input.next());
            System.out.println("Swimtime added to list");
            ps.execute();



        } catch (SQLException throwables)
        {
            throwables.printStackTrace();
        }
    }


    public void saveCompetitiveSwimmerToDatabase()
    {
        String sql = "INSERT INTO delfinproject.competition (SwimmerID, Tournament, Placement, Time, Team, CoachID, Discipline) VALUES (?,?,?,?,?,?,?)";
        Scanner input = new Scanner(System.in);
        try
        {
            PreparedStatement ps = conn.prepareStatement(sql);


            System.out.println("Choose swimmer from ID to add in swimtraining");
            int var = Integer.parseInt(input.nextLine());
            ps.setInt(1,var);

            System.out.println("Write tournament/competition name: (please no numbers)");
            ps.setString(2,input.nextLine());

            System.out.println("Write placement number of swimmer");
            ps.setInt(3,input.nextInt());

            System.out.println("Write swimmers swimtime like (mm.ss etc.) ");
            ps.setString(4,input.next());

            System.out.println("Write teamnumber of swimmer (below 18 years old = false, above 18 = true");
            ps.setBoolean(5, input.nextBoolean());

            getCoachFromDB();
            System.out.println("Choose coach based on idnumber");
            ps.setInt(6,input.nextInt());

            System.out.println("Write the discipline the swimmer is participating in (Crawl, Butterfly, Breaststroke, Backstroke)  (please no numbers)");
            ps.setString(7,input.next());

            System.out.println("Swimmer added to list");
            ps.execute();

        } catch (SQLException throwables)
        {
            throwables.printStackTrace();
        }
    }

    public Subscription getMembershipStatusFromDB(int id) {

        //ArrayList<Subscription> tmp = new ArrayList<>();
        Subscription sub = new Subscription(0, false, false);

        try {
            String sql = "SELECT * FROM delfinproject.personinfo WHERE idPersonInfo = ?";
            PreparedStatement ps;
            ps = conn.prepareStatement(sql);
            ps.setInt(1,id);


            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {

                sub.setAge(resultSet.getInt("Age"));
                sub.setHasPayed(resultSet.getBoolean("HasPayed"));
                sub.setActive(resultSet.getBoolean("Status"));


//                Subscription subby = new Subscription(
//                resultSet.getInt("Age"),
//                resultSet.getBoolean("HasPayed"),
//                resultSet.getBoolean("Status"));

            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        System.out.println(sub);
        return sub;
    }

    public int calculateSubscriptionCost(Subscription s) {
        int subscription;
        if (s.isActive() == true) {
            if (s.getAge() < 18) {
                subscription = 1000;
            } else if (s.getAge() > 18 && s.getAge() < 60) {
                subscription = 1600;
            } else {                    // age > 60
                subscription = 1200;      //1600 minus 25%
            }
        } else {
            subscription = 500;           //passive member
        }
        System.out.println("subscription cost " + subscription + " DKK");
        return subscription;
    }

    public void paySubscriptionPrompt(int id)
    {
        Scanner input = new Scanner(System.in);
        String sql = "UPDATE delfinproject.personinfo SET HasPayed = ? WHERE idPersonInfo = ?";
        PreparedStatement ps = null;
        Boolean hasPayed;


        try
        {
            ps = conn.prepareStatement(sql);
            ps.setInt(2,id);

            System.out.println("Did this member pay?  (y or n)");
            char choice = input.nextLine().charAt(0);
            if(choice == 'y' || choice == 'Y')
            {

                //hasPayed  = input.nextBoolean();
                //System.out.println("write true to change in db table and false if else");
                ps.setBoolean(1,true);
                System.out.println("subscription status updated");
                ps.executeUpdate();
            }
            else if(choice == 'n' || choice == 'N')
            {
                System.out.println("this member has not payed his subscription, remember to pay next time");
            }
        }
        catch (SQLException throwables)
        {
            throwables.printStackTrace();
        }
    }

    public void getMissingPaymentFromDB() {
        String sql = "SELECT * FROM delfinproject.personinfo WHERE HasPayed = 0";

        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql);
            ResultSet resultSet = ps.executeQuery();

            while (resultSet.next()) {

                int id = resultSet.getInt("idPersonInfo");
                String firstName = resultSet.getString("firstName");
                String lastName = resultSet.getString("LastName");
                String hasPayed = resultSet.getString("HasPayed");

                System.out.println("id: " + id + " " + firstName + " " + lastName + "has payed: " + hasPayed);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void sortTopFiveSwimmer(String s) {
        String sql = "SELECT * FROM delfinproject.competition WHERE Discipline = (?) ORDER BY Time asc LIMIT 5";

        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql);

            ps.setString(1,s);
            ResultSet resultSet = ps.executeQuery();

            while (resultSet.next()) {

                int id = resultSet.getInt("SwimmerID");
                double time = resultSet.getDouble("Time");
                String discipline = resultSet.getString("Discipline");


                System.out.println("id: " + id + " Time " + time + " Discipline " + discipline);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
