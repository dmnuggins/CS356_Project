import java.util.*;
/**
 * Created by cthill on 8/6/16.
 */
public class MeetingApp {

    public static void main(String args[]) {
        MeetingDB mdb = MeetingDB.getInstance();
        Meeting m = new Meeting();
        m.id = 0;
        m.room = 1100;
        m.start = new Date(2016, 9, 11, 9, 0, 0);
        m.end = new Date(2016, 9, 11, 10, 0, 0);
        mdb.save(m);

        Meeting load = mdb.load(0);
        System.out.println(load.start);
        System.out.println(load.end);

        System.out.println(mdb.getNextID());
    }
}
