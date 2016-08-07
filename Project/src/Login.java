import java.util.List;
/**
 * Created by cthill on 8/7/16.
 */
public class Login {
    public int employeeID;
    public String username;
    public String password;

    public Login() {
    }

    public Login(int id, String u, String p) {
        employeeID = id;
        username = u;
        password = p;
    }

    //checks username and password. Returns employeeID or -1 for not found
    public static Employee authenticate(String u, String p) {
        LoginDB db = LoginDB.getInstance();
        List<Login> loginList = db.loadAll();
        for (int i = 0; i < loginList.size(); i++) {
            Login l = loginList.get(i);
            if (l.username.equals(u) && l.password.equals(p)) {
                EmployeeDB edb = EmployeeDB.getInstance();
                return edb.load(l.employeeID);
            }
        }

        return null;
    }
}
