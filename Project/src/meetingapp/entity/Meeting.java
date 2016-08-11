package meetingapp.entity;

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

    public void save() {
        MeetingDB.getInstance().save(this);
    }

    //returns attendees of meeting. Includes owner
//    public List<Employee> getAttendees() {
//        return EmployeeMeeting.getAllEmployees(ID, true, true);
//    }

    public Employee getOwner() {
        return EmployeeMeeting.getOwner(ID);
    }

    //returns true if meeting is over
    public boolean isPast() {
        return end.before(new Date());
    }

    //returns true if meeting is currently happening
    public boolean isCurrent() {
        return start.before(new Date()) && end.after(new Date());
    }

}
