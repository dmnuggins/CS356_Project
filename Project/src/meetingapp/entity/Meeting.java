package meetingapp.entity;

import meetingapp.db.EmployeeDB;
import meetingapp.db.EmployeeMeetingDB;
import meetingapp.db.MeetingDB;

import java.util.*;
/**
 * Created by cthill on 8/7/16.
 */
public class Meeting extends Entity{

    protected int roomID;
    protected Date start;
    protected Date end;

    public Meeting(int ID, int roomID, Date start, Date end) {
        super(ID);

        this.roomID = roomID;
        this.start = start;
        this.end = end;
    }

    public int getRoomID() {
        return roomID;
    }

    public Date getStart() {
        return start;
    }

    public Date getEnd() {
        return end;
    }

    protected void save() {
        MeetingDB.getInstance().save(this);
    }

    //returns true if meeting is over
    public boolean isPast() {
        return end.before(new Date());
    }

    //returns true if meeting is currently happening
    public boolean isCurrent() {
        return start.before(new Date()) && end.after(new Date());
    }

    //Get all meeting attendees. Owner is included if includeOwner argument is set
    public List<EmployeeMeeting> getAllInvited(boolean includeOwner, boolean includeNotAccepted) {
        List<EmployeeMeeting> out = new ArrayList<EmployeeMeeting>();

        List<EmployeeMeeting> eml = (List<EmployeeMeeting>)(List<?>) EmployeeMeetingDB.getInstance().loadAll();
        for (int i = 0; i < eml.size(); i++) {
            EmployeeMeeting em = eml.get(i);
            if (em.meetingID == ID && (!em.isOwner || includeOwner) && (em.accepted || includeNotAccepted)) {
                out.add(em);
            }
        }

        return out;
    }

    public List<EmployeeMeeting> getAllAccepted(boolean includeOwner) {
        return getAllInvited(includeOwner, false);
    }

    //get owner of meeting
    public Employee getOwner() {
        List<EmployeeMeeting> eml = (List<EmployeeMeeting>)(List<?>) EmployeeMeetingDB.getInstance().loadAll();
        for (int i = 0; i < eml.size(); i++) {
            EmployeeMeeting em = eml.get(i);
            if (em.meetingID == ID && em.isOwner) {
                return (Employee) EmployeeDB.getInstance().load(em.employeeID);
            }
        }

        return null;
    }

    public List<EmployeeMeeting> getAllSeenInvite() {
        List<EmployeeMeeting> out = new ArrayList<EmployeeMeeting>();

        List<EmployeeMeeting> eml = (List<EmployeeMeeting>)(List<?>) EmployeeMeetingDB.getInstance().loadAll();
        for (int i = 0; i < eml.size(); i++) {
            EmployeeMeeting em = eml.get(i);
            if (em.meetingID == ID && !em.isOwner && em.seen) {
                out.add(em);
            }
        }

        return out;
    }

    public static Meeting get(int id) {
        return (Meeting) MeetingDB.getInstance().load(id);
    }

    public static List<Meeting> getAll() {
        return (List<Meeting>)(List<?>) MeetingDB.getInstance().loadAll();
    }
}
