package meetingapp;

import meetingapp.gui.*;
import meetingapp.db.*;
import meetingapp.entity.*;
import java.util.*;

/**
 * Created by cthill on 8/6/16.
 */
public class MeetingApp {

    public static void main(String args[]) {
        //Create accounts (only needs to be done once)
        CreateAccount(0, "Admin", "admin", "password", true);
        CreateAccount(1, "Christian Hill", "cthill", "password", false);
        CreateAccount(2, "Dylan Nguyen", "dmnguyen", "password", false);
        CreateAccount(3, "Cesar Recinos", "carecinos", "password", false);
        CreateAccount(4, "User", "user", "password", false);
        CreateAccount(5, "Bob Jones", "bob", "password", false);
        CreateAccount(6, "John Smith", "john", "password", false);


        Date start = new Date();
        start.setTime(1473475105000L);
        Date end = new Date();
        end.setTime(1473478705000L);
        Meeting meet = new Meeting(0, 101, start, end);
        MeetingDB.getInstance().save(meet);

        EmployeeMeetingDB.getInstance().save(new EmployeeMeeting(0, 0, 0, true, true, true, true));
        EmployeeMeetingDB.getInstance().save(new EmployeeMeeting(1, 1, 0, false, false, false, false));
        EmployeeMeetingDB.getInstance().save(new EmployeeMeeting(2, 2, 0, false, false, false, false));
        EmployeeMeetingDB.getInstance().save(new EmployeeMeeting(3, 3, 0, false, false, false, false));
        EmployeeMeetingDB.getInstance().save(new EmployeeMeeting(4, 4, 0, false, false, false, false));
        EmployeeMeetingDB.getInstance().save(new EmployeeMeeting(5, 5, 0, false, true, true, true));
        EmployeeMeetingDB.getInstance().save(new EmployeeMeeting(6, 6, 0, false, true, true, false));

        Room room = new Room(101, 5);
        RoomDB.getInstance().save(room);
        Room room1 = new Room(102, 3);
        RoomDB.getInstance().save(room1);
        Room room2 = new Room(103, 10);
        RoomDB.getInstance().save(room2);

        new LoginGUI();
    }

    public static void CreateAccount(int id, String name, String user, String pass, boolean admin) {
        EmployeeDB.getInstance().save(new Employee(id, name, admin));
        LoginDB.getInstance().save(new Login(id, user, pass));
    }
}
