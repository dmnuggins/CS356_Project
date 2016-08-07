import java.util.*;
/**
 * Created by cthill on 8/6/16.
 */
public class MeetingApp {

    public static void main(String args[]) {
        LoginDB db = LoginDB.getInstance();

//        Login l = new Login(0, "joe", "password");
//        db.save(l);
//        Employee loadTest1 = Login.authenticate("joe", "password");
//        Date d = new Date();
//        d.setYear(2016);
//        d.setDate(11);
//        d.setMonth(9);
//        loadTest1.reserveDate(d);

        Employee loadTest1 = Login.authenticate("joe", "password");

        System.out.println(loadTest1.name);
        System.out.println(loadTest1.reserved.size());
    }

}
