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
