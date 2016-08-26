package meetingapp.entity;

import meetingapp.db.*;

import java.io.IOException;
import java.time.*;
import java.util.*;
/**
 * Created by cthill on 7/26/16.
 */
public class Employee extends Entity {

    protected String name;
    protected boolean isAdmin;
    protected List<LocalDateTime> reserved = new ArrayList<LocalDateTime>(); //reserved days
    protected Login login;
    public boolean NotifiedOfUpcoming;


    public Employee(int ID, String name, boolean isAdmin) {

        super(ID);
        this.name = name;
        this.isAdmin = isAdmin;

        login = (Login) LoginDB.getInstance().load(ID);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        save();
    }

    public boolean getIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(boolean isAdmin) {
        this.isAdmin = isAdmin;
        save();
    }

    public List<LocalDateTime> getReserved() {
        return reserved;
    }

    public void reserveDate(LocalDateTime d) {
        reserved.add(d);
        save();
    }

    public void unreserveDate(LocalDateTime d) {
        reserved.remove(d);
//        for (int i = 0; i < reserved.size(); i++) {
//            LocalDateTime rd = reserved.get(i);
//            if (rd.equals(d)) {
//                reserved.remove(i);
//                break;
//            }
//        }
        save();
    }

    public Login getLogin() {
        return login;
    }

    public void setLogin(Login login){
        this.login = login;
    }

    public void save() {
        EmployeeDB.getInstance().save(this);
    }

    //Get all meetings a user is invited to (isOwner = false) or created (isOwner = true). Includes meeting invites not accepted yet
    //if includePast argument is set to true, the method will return meetings that already happened
    public List<Participant> getAllMeetings(boolean isOwner, boolean includePast) {
        List<Participant> out = new ArrayList<Participant>();

        List<Participant> eml = (List<Participant>)(List<?>) ParticipantDB.getInstance().loadAll();
        for (Participant em : eml) {
            if (em.employeeID == ID && em.isOwner == isOwner) {
                Meeting m = (Meeting) MeetingDB.getInstance().load(em.meetingID);
                if (includePast || !m.isPast()) {
                    out.add(em);
                }
            }
        }

        return out;
    }

    public static Employee get(int id) {
        return (Employee) EmployeeDB.getInstance().load(id);
    }

    public static List<Employee> getAll() {
        return (List<Employee>)(List<?>) EmployeeDB.getInstance().loadAll();
    }

    public void delete() {
        try {
            EmployeeDB.getInstance().eraseRecord(this.getID());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean[][] getSchedule(LocalDate startDay, int numDays, boolean includeMeetings) {
        List<LocalDateTime> blockedTimes = new ArrayList<>();

        //get list of meetings
        if (includeMeetings) {
            List<Participant> allMeetings = this.getAllMeetings(true, true);
            for (Participant p : allMeetings) {
                LocalDateTime meetingStart = p.getMeeting().getStart();
                //exclude meetings before startDay
                if (!meetingStart.isBefore(startDay.atStartOfDay())) {
                    //exclude meetings after startDay + numDays
                    if (!meetingStart.isAfter(startDay.plusDays(numDays).atStartOfDay())) {
                        blockedTimes.add(meetingStart);
                    }
                }
            }
        }

        //get list of employee schedule reserved times
        for (LocalDateTime r : reserved) {
            if (!r.isBefore(startDay.atStartOfDay())) {
                //exclude meetings after startDay + numDays
                if (!r.isAfter(startDay.plusDays(numDays).atStartOfDay())) {
                    blockedTimes.add(r);
                }
            }
        }
        
        boolean[][] sched = new boolean[numDays][24];

        //compute sched
        for (LocalDateTime d : blockedTimes) {
            int day = Period.between(startDay, d.toLocalDate()).getDays();
            int hour = d.getHour();
            sched[day][hour] = true;
        }

        return sched;
    }
}
