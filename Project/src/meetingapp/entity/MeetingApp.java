package meetingapp.entity;

import meetingapp.db.EmployeeDB;
import meetingapp.db.LoginDB;

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

        LoginGUI lg = new LoginGUI();
        lg.showGUI();

//        meetingapp.db.EmployeeMeetingDB emd = meetingapp.db.EmployeeMeetingDB.getInstance();
//
//        meetingapp.entity.EmployeeMeeting em1 = new meetingapp.entity.EmployeeMeeting();
//        em1.meetingID = 0;
//        em1.employeeID = 0;
//        em1.isOwner = false;
//        em1.ID = 0;
//
//        emd.save(em1);
//
//        meetingapp.entity.EmployeeMeeting eml = emd.load(0);
    }
}
