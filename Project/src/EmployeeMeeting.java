import java.util.*;
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

    //Get all meetings a user is invited to (isOwner = false) or create (isOwner = true).
    //if includePast argument is set to true, the method will return meetings that already happened
    public static List<Meeting> getAllMeetings(int employeeID, boolean isOwner, boolean includePast) {
        List<Meeting> l = new ArrayList<Meeting>();

        List<EmployeeMeeting> eml = EmployeeMeetingDB.getInstance().loadAll();
        for (int i = 0; i < eml.size(); i++) {
            EmployeeMeeting em = eml.get(i);
            if (em.employeeID == employeeID && em.isOwner == isOwner) {
                Meeting m = MeetingDB.getInstance().load(em.meetingID);
                if (includePast || m.end.after(new Date())) {
                    l.add(m);
                }
            }
        }

        return l;
    }

    //Get all meeting attendees. Owner is included if includeOwner argument is set
    public static List<Employee> getAllEmployees(int meetingID, boolean includeOwner) {
        List<Employee> l = new ArrayList<Employee>();

        List<EmployeeMeeting> eml = EmployeeMeetingDB.getInstance().loadAll();
        for (int i = 0; i < eml.size(); i++) {
            EmployeeMeeting em = eml.get(i);
            if (em.meetingID == meetingID && (em.isOwner || includeOwner)) {
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

        return l;
    }
}
