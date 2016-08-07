/**
 * Created by cthill on 8/7/16.
 */
public class Login {
    public int employeeID;
    public String username;
    public String password;

    public Login() {
    }

    public Login(String u, String p) {
        username = u;
        password = p;
    }

    //checks username and password. Returns employeeID or -1 for not found
    public static int Authenticate(String u, String p) {
        return -1;
    }
}
