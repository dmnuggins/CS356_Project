package meetingapp.entity;

import meetingapp.db.*;

import java.io.IOException;
import java.util.*;
/**
 * Created by cthill on 7/26/16.
 */
public class Employee extends Entity {

    protected String name;
    protected boolean isAdmin;
    protected List<Date> reserved = new ArrayList<Date>(); //reserved days
    protected Login login;



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

    public List<Date> getReserved() {
        return reserved;
    }

    public void reserveDate(Date d) {
        reserved.add(d);
        save();
    }

    public void unReserveDate(Date d) {
        for (int i = 0; i < reserved.size(); i++) {
            Date rd = reserved.get(i);
            if (rd.equals(d)) {
                reserved.remove(i);
                break;
            }
        }
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
                if (includePast || m.end.after(new Date())) {
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
}
