package meetingapp.entity;

import meetingapp.db.EmployeeDB;
import meetingapp.db.EmployeeMeetingDB;
import meetingapp.db.MeetingDB;

import java.util.*;
/**
 * Created by cthill on 8/7/16.
 */
public class EmployeeMeeting extends Entity {

    protected int employeeID;
    protected int meetingID;
    protected boolean isOwner;
    protected boolean accepted;

    public EmployeeMeeting(int ID, int employeeID, int meetingID, boolean isOwner, boolean accepted) {
        super(ID);

        this.employeeID = employeeID;
        this.meetingID = meetingID;
        this.isOwner = isOwner;
        this.accepted = accepted;
    }

    public int getEmployeeID() {
        return employeeID;
    }

    public void setEmployeeID(int employeeID) {
        this.employeeID = employeeID;
        save();
    }

    public int getMeetingID() {
        return meetingID;
    }

    public void setMeetingID(int meetingID) {
        this.meetingID = meetingID;
        save();
    }

    public boolean getIsOwner() {
        return isOwner;
    }

    public boolean getAccepted() {
        return accepted;
    }

    public void setAccepted(boolean accepted) {
        this.accepted = accepted;
        save();
    }

    public void save() {
        EmployeeMeetingDB.getInstance().save(this);
    }

    //Get all meetings a user is invited to (isOwner = false) or created (isOwner = true). Includes meeting invites not accepted yet
    //if includePast argument is set to true, the method will return meetings that already happened
    public static List<Meeting> getAllMeetings(int employeeID, boolean isOwner, boolean includePast) {
        List<Meeting> l = new ArrayList<Meeting>();

        List<EmployeeMeeting> eml = EmployeeMeetingDB.getInstance().loadAll();
        for (int i = 0; i < eml.size(); i++) {
            EmployeeMeeting em = eml.get(i);
            if (em.employeeID == employeeID && em.isOwner == isOwner) {
                Meeting m = MeetingDB.getInstance().load(em.meetingID);
                if (includePast || m.end.before(new Date())) {
                    l.add(m);
                }
            }
        }

        return l;
    }

    //Get all meeting attendees. Owner is included if includeOwner argument is set
    public static List<Employee> getAllEmployees(int meetingID, boolean includeOwner, boolean includeNotAccepted) {
        List<Employee> l = new ArrayList<Employee>();

        List<EmployeeMeeting> eml = EmployeeMeetingDB.getInstance().loadAll();
        for (int i = 0; i < eml.size(); i++) {
            EmployeeMeeting em = eml.get(i);
            if (em.meetingID == meetingID && (em.isOwner || includeOwner) && (em.accepted || includeNotAccepted)) {
                l.add(EmployeeDB.getInstance().load(em.employeeID));
            }
        }

        return l;
    }

    //get owner of meeting
    public static Employee getOwner(int meetingID) {
        List<EmployeeMeeting> eml = EmployeeMeetingDB.getInstance().loadAll();
        for (int i = 0; i < eml.size(); i++) {
            EmployeeMeeting em = eml.get(i);
            if (em.meetingID == meetingID && em.isOwner) {
                return EmployeeDB.getInstance().load(em.employeeID);
            }
        }

        return null;
    }
}
