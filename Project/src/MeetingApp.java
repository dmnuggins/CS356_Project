import java.util.*;
/**
 * Created by cthill on 8/6/16.
 */
public class MeetingApp {

    public static void main(String args[]) {
<<<<<<< HEAD
        LoginGUI gui = new LoginGUI();
        gui.showGUI();
//        EmployeeDB db = EmployeeDB.getInstance();
//        Employee e = new Employee(0, "TestEMP", false);
//        e.reserved = new ArrayList<Date>();
//        db.save(e);
=======
        MeetingDB mdb = MeetingDB.getInstance();
        Meeting m = new Meeting();
        m.id = 0;
        m.room = 1100;
        m.start = new Date(2016, 9, 11, 9, 0, 0);
        m.end = new Date(2016, 9, 11, 10, 0, 0);
        mdb.save(m);
>>>>>>> master

        Meeting load = mdb.load(0);
        System.out.println(load.start);
        System.out.println(load.end);

<<<<<<< HEAD
//        Employee loadTest1 = Login.authenticate("joe", "password");
//
//        System.out.println(loadTest1.name);
//        System.out.println(loadTest1.reserved.size());
=======
        System.out.println(mdb.getNextID());
>>>>>>> master
    }
}
