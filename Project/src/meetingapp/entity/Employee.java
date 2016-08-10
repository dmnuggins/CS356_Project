package meetingapp.entity;

import meetingapp.db.*;
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

    public void setLogin(Login login) {
        this.login = login;
    }

    public void save() {
        EmployeeDB.getInstance().save(this);
    }
}
