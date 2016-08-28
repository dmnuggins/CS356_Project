package meetingapp.entity;

import meetingapp.db.EmployeeDB;
import meetingapp.db.ParticipantDB;
import meetingapp.db.MeetingDB;

import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.*;
import java.time.*;
/**
 * Created by cthill on 8/7/16.
 */
public class Meeting extends Entity{

    protected int roomID;
    protected LocalDateTime start;
    protected LocalDateTime end;

    public Meeting(int ID, int roomID, LocalDateTime start) {
        super(ID);

        this.roomID = roomID;
        this.start = start.withMinute(0).withSecond(0);

        this.end = this.start.plusHours(1);
    }

    public int getRoomID() {
        return roomID;
    }

    public String getStartString() {
        return start.format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.SHORT));
    }

    public LocalDateTime getStart() {
        return start;
    }

    public void save() {
        MeetingDB.getInstance().save(this);
    }

    //returns true if meeting is over
    public boolean isPast() {
        return end.isBefore(LocalDateTime.now());
    }

    //returns true if meeting is currently happening
    public boolean isCurrent() {
        return start.isBefore(LocalDateTime.now()) && end.isAfter(LocalDateTime.now());
    }

    //Get all meeting attendees. Owner is included if includeOwner argument is set
    public List<Participant> getAllInvited(boolean includeOwner, boolean includeNotAccepted) {
        List<Participant> out = new ArrayList<Participant>();

        List<Participant> eml = (List<Participant>)(List<?>) ParticipantDB.getInstance().loadAll();
        for (int i = 0; i < eml.size(); i++) {
            Participant em = eml.get(i);
            if (em.meetingID == ID && (!em.isOwner || includeOwner) && (em.accepted || includeNotAccepted)) {
                out.add(em);
            }
        }

        return out;
    }

    public List<Participant> getAllAccepted(boolean includeOwner) {
        return getAllInvited(includeOwner, false);
    }

    //get owner of meeting
    public Employee getOwner() {
        List<Participant> eml = (List<Participant>)(List<?>) ParticipantDB.getInstance().loadAll();
        for (int i = 0; i < eml.size(); i++) {
            Participant em = eml.get(i);
            if (em.meetingID == ID && em.isOwner) {
                return (Employee) EmployeeDB.getInstance().load(em.employeeID);
            }
        }

        return null;
    }

    public List<Participant> getAllSeenInvite() {
        List<Participant> out = new ArrayList<Participant>();

        List<Participant> eml = (List<Participant>)(List<?>) ParticipantDB.getInstance().loadAll();
        for (int i = 0; i < eml.size(); i++) {
            Participant em = eml.get(i);
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
