import java.util.ArrayList;
import java.util.List;
/**
 * Created by cthill on 8/7/16.
 */
public class EmployeeMeeting {

    public int ID;
    public int employeeID;
    public int meetingID;
    public boolean isOwner;

    public EmployeeMeeting(int e, int m, boolean o) {
        employeeID = e;
        meetingID = m;
        isOwner = o;
    }

    public EmployeeMeeting() {

    }

    public static List<Meeting> getAllMeetings(int employeeID) {
        List<Meeting> l = new ArrayList<Meeting>();

        List<EmployeeMeeting> eml = EmployeeMeetingDB.getInstance().loadAll();
        for (int i = 0; i < eml.size(); i++) {
            EmployeeMeeting em = eml.get(i);
            if (em.employeeID == employeeID) {
                l.add(MeetingDB.getInstance().load(em.meetingID));
            }
        }

        return l;
    }

    public static List<Employee> getAllEmployees(int meetingID) {
        List<Employee> l = new ArrayList<Employee>();

        List<EmployeeMeeting> eml = EmployeeMeetingDB.getInstance().loadAll();
        for (int i = 0; i < eml.size(); i++) {
            EmployeeMeeting em = eml.get(i);
            if (em.meetingID == meetingID) {
                l.add(EmployeeDB.getInstance().load(em.employeeID));
            }
        }

        return l;
    }
}
