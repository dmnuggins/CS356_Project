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
        return null;
    }

}
