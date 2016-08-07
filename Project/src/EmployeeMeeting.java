import java.util.List;
/**
 * Created by cthill on 8/7/16.
 */
public class EmployeeMeeting {

    public int employeeID;
    public int meetingID;
    public boolean owner;

    public EmployeeMeeting(int e, int m, boolean o) {
        employeeID = e;
        meetingID = m;
        owner = o;
    }

    public EmployeeMeeting() {

    }

    public static List<Meeting> getAllMeetings(int employeeID) {
        return null;
    }

    public static List<Employee> getAllEmployees(int employeeID) {
        return null;
    }
}
