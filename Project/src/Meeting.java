import java.util.*;
/**
 * Created by cthill on 8/7/16.
 */
public class Meeting {

    public int id;
    public int room;
    public Date start;
    public Date end;

    public Meeting() {

    }

    public Meeting(int r, Date s, Date e) {
        room = r;
        start = s;
        end = e;
    }

    public List<Employee> getAttendees() {
        return EmployeeMeeting.getAllEmployees(id);
    }

    public Employee getOwner() {
        return EmployeeMeeting.getOwner(id);
    }

    //returns true if meeting is over
    public boolean isPast() {
        return end.before(new Date());
    }

    //returns true if meeting is started
    public boolean isCurrent() {
        return start.before(new Date()) && end.after(new Date());
    }

}