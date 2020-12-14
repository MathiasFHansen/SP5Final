package MembershipInfo;

import SQL.DelfinJDBC;

import java.util.ArrayList;
import java.util.Scanner;

public class DelfinSystem
{


    ArrayList<String> firstOptionsInTerminal = new ArrayList<>();

    public DelfinSystem() {

        this.firstOptionsInTerminal.add("Press 1 to Register as member");
        this.firstOptionsInTerminal.add("Press 2 to add a member testswim to the overall records");
        this.firstOptionsInTerminal.add("Press 3 to add competitive swimmers to comepetitive table");
        this.firstOptionsInTerminal.add("Press 4 to add a coach to the coach table");
        this.firstOptionsInTerminal.add("Press 5 to manage payments of members");
        this.firstOptionsInTerminal.add("Press 6 to see full list of missing payments");
        this.firstOptionsInTerminal.add("Press 7 see top 5 of a discipline in tournaments");
    }

    public void options()
    {
        DelfinSystem delfinSystem = new DelfinSystem();

        Scanner input = new Scanner(System.in);
        DelfinJDBC delfinJDBC = new DelfinJDBC();


        System.out.println(delfinSystem.firstOptionsInTerminal.get(0));
        System.out.println(delfinSystem.firstOptionsInTerminal.get(1));
        System.out.println(delfinSystem.firstOptionsInTerminal.get(2));
        System.out.println(delfinSystem.firstOptionsInTerminal.get(3));
        System.out.println(delfinSystem.firstOptionsInTerminal.get(4));
        System.out.println(delfinSystem.firstOptionsInTerminal.get(5));
        System.out.println(delfinSystem.firstOptionsInTerminal.get(6));

        boolean finished = false;
        while (!finished)
        {
            String choice = input.nextLine();
            switch (choice)
            {
                case"1":
                    Member m;
                    m = createNewMember();

                    addMemberToList(m);
                    delfinJDBC.saveMembersToDatabase(m);
                    options();
                    break;
                case "2":
                    delfinJDBC.getMembersFromDB();
                    delfinJDBC.saveSwimTestsToDatabase();
                    options();
                    break;

                case "3":
                    delfinJDBC.getCompetitiveMembersFromDB();
                    delfinJDBC.saveCompetitiveSwimmerToDatabase();
                    options();
                    break;
                case "4":
                    Coach c;
                    c = createNewCoach();

                    addCoachToList(c);
                    delfinJDBC.saveCoachToDatabase(c);
                    options();
                    break;
                case "5":
                    Subscription s;
                    delfinJDBC.getMembersFromDB();
                    System.out.println("Select member from idnumber to manage");
                    s = delfinJDBC.getMembershipStatusFromDB(input.nextInt());
                    delfinJDBC.calculateSubscriptionCost(s);
                    System.out.println("write id of member");
                    delfinJDBC.paySubscriptionPrompt(input.nextInt());
                    options();
                    break;
                case "6":
                    delfinJDBC.getMissingPaymentFromDB();
                    break;
                case "7":
                    System.out.println("Write name of discipline (Backstroke, Crawl, Breaststroke, Butterfly");
                    delfinJDBC.sortTopFiveSwimmer(input.next());
                    options();

            }
        }
    }

    public Member createNewMember()
    {
        Scanner scan = new Scanner(System.in);

        int id = 0;
        int age;
        String firstName;
        String lastName;
        String memberType = null;
        boolean active;
        boolean competitive;



        System.out.println("Set age of member");
        age = scan.nextInt();

        if(age < 18)
        {
            memberType = "Junior";
        }
        else if (age > 18)
        {
            memberType = "Senior";
        }

        System.out.println("Set firstname of member (please no numbers)"); //TODO
        firstName = scan.next();
        
        System.out.println("Set lastname of member (please no numbers)"); //TODO
        lastName = scan.next();
        
        System.out.println("set current status of member (true == active, false == passive");
        active = scan.nextBoolean();

        System.out.println("Set whether the member is a competitive swimmer or not (true == yes, false == no");
        competitive = scan.nextBoolean();

        Member m = new Member(id, age, firstName, lastName, memberType, active, competitive);
        return m;
    }

    public void addMemberToList(Member m)
    {
        if(m.memberList.contains(m))
        {
            System.out.println("do nothing");
            return;
        }
        else
        {
            m.memberList.add(m);
        }
        System.out.println(m.memberList);

    }

    public Coach createNewCoach()
    {
        Scanner scan = new Scanner(System.in);

        int idCoach;
        String firstName;
        String lastName;

        idCoach = 0;

        System.out.println("Set firstname of coach (please no numbers)");
        firstName = scan.next();

        System.out.println("Set lastname of member, please no numbers (please no numbers)"); //TODO
        lastName = scan.next();

        Coach c = new Coach(idCoach, firstName, lastName);
        return c;
    }

    public void addCoachToList(Coach c)
    {
        if(c.coachList.contains(c))
        {
            System.out.println("do nothing");
            return;
        }
        else
        {
            c.coachList.add(c);
        }
        System.out.println(c.coachList);

    }

}
