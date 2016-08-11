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
    protected boolean seen;
    protected boolean seenByOwner;

    public EmployeeMeeting(int ID, int employeeID, int meetingID, boolean isOwner, boolean accepted, boolean seen, boolean seenByOwner) {
        super(ID);

        this.employeeID = employeeID;
        this.meetingID = meetingID;
        this.isOwner = isOwner;
        this.accepted = accepted;
        this.seen = seen;
        this.seenByOwner = seenByOwner;
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

    public boolean getSeen() {
        return seen;
    }

    public void setSeen(boolean seen) {
        this.seen = seen;
        save();
    }

    public boolean getSeenByOwner() {
        return seenByOwner;
    }

    public void setSeenByOwner(boolean seenByOwner) {
        this.seenByOwner = seenByOwner;
    }

    public void save() {
        EmployeeMeetingDB.getInstance().save(this);
    }

    //Get all meetings a user is invited to (isOwner = false) or created (isOwner = true). Includes meeting invites not accepted yet
    //if includePast argument is set to true, the method will return meetings that already happened
    public static List<EmployeeMeeting> getAllMeetings(int employeeID, boolean isOwner, boolean includePast) {
        List<EmployeeMeeting> out = new ArrayList<EmployeeMeeting>();

        List<EmployeeMeeting> eml = EmployeeMeetingDB.getInstance().loadAll();
        for (EmployeeMeeting em : eml) {
            if (em.employeeID == employeeID && em.isOwner == isOwner) {
                Meeting m = MeetingDB.getInstance().load(em.meetingID);
                if (includePast || m.end.after(new Date())) {
                    out.add(em);
                }
            }
        }

        return out;
    }

    //Get all meeting attendees. Owner is included if includeOwner argument is set
    public static List<EmployeeMeeting> getAllEmployees(int meetingID, boolean includeOwner, boolean includeNotAccepted) {
        List<EmployeeMeeting> out = new ArrayList<EmployeeMeeting>();

        List<EmployeeMeeting> eml = EmployeeMeetingDB.getInstance().loadAll();
        for (int i = 0; i < eml.size(); i++) {
            EmployeeMeeting em = eml.get(i);
            if (em.meetingID == meetingID && (!em.isOwner || includeOwner) && (em.accepted || includeNotAccepted)) {
                out.add(em);
            }
        }

        return out;
    }


    public static List<EmployeeMeeting> getResponded(int meetingID, boolean includeOwner) {
        List<EmployeeMeeting> out = new ArrayList<EmployeeMeeting>();

        List<EmployeeMeeting> eml = EmployeeMeetingDB.getInstance().loadAll();
        for (int i = 0; i < eml.size(); i++) {
            EmployeeMeeting em = eml.get(i);
            if (em.meetingID == meetingID && (!em.isOwner || includeOwner) && em.seen) {
                out.add(em);
            }
        }

        return out;
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
