package meetingapp.entity;

import meetingapp.db.EmployeeDB;
import meetingapp.db.LoginDB;

import java.util.List;
/**
 * Created by cthill on 8/7/16.
 */
public class Login extends Entity {
    //protected int employeeID; //employeeID = ID
    protected String username;
    protected String password;

    public Login(int ID, String username, String password) {
        super(ID);

        this.username = username;
        this.password = password;
    }

    public int getEmployeeID() {
        return ID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
        save();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
        save();
    }

    public void save() {
        LoginDB.getInstance().save(this);
    }

    //checks username and password. Returns employee
    public static Employee authenticate(String u, String p) {
        LoginDB db = LoginDB.getInstance();
        List<Login> loginList = (List<Login>)(List<?>) db.loadAll();
        for (int i = 0; i < loginList.size(); i++) {
            Login l = loginList.get(i);
            if (l.username.equals(u) && l.password.equals(p)) {
                return (Employee) EmployeeDB.getInstance().load(l.ID);
            }
        }

        return null;
    }

    public static Login get(int id) {
        return (Login) LoginDB.getInstance().load(id);
    }

    public static List<Login> getAll() {
        return (List<Login>)(List<?>) LoginDB.getInstance().loadAll();
    }
}
