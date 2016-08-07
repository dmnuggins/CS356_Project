import java.util.*;
/**
 * Created by cthill on 7/26/16.
 */
public class Employee {
    public int ID;
    public String name;
    public boolean isAdmin;
    public List<Date> reserved; //reserved days


    public Employee() {
    }

    public Employee(int id, String n, boolean admin, List<Date> res) {
        ID = id;
        name = n;
        isAdmin = admin;
        reserved = res;
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

    public void save() {
        EmployeeDB.getInstance().save(this);
    }
}
