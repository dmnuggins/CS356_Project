package meetingapp;

import meetingapp.gui.*;
import meetingapp.db.*;
import meetingapp.entity.*;

/**
 * Created by cthill on 8/6/16.
 */
public class MeetingApp {

    public static void main(String args[]) {
        //Create admin account
        Employee admin = new Employee(0, "Admin", true);
        EmployeeDB.getInstance().save(admin);
        Login adminLogin = new Login(0, "admin", "password");
        LoginDB.getInstance().save(adminLogin);

        Employee user = new Employee(1, "User", false);
        EmployeeDB.getInstance().save(user);
        Login userLogin = new Login(1, "user", "password");
        LoginDB.getInstance().save(userLogin);

        Room room = new Room(101, 5);
        RoomDB.getInstance().save(room);
        Room room1 = new Room(102, 3);
        RoomDB.getInstance().save(room1);
        Room room2 = new Room(103, 10);
        RoomDB.getInstance().save(room2);

        LoginGUI lg = new LoginGUI();
        lg.showGUI();
    }
}
