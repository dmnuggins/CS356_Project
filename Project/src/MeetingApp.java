/**
 * Created by cthill on 8/6/16.
 */
public class MeetingApp {

    public static void main(String args[]) {
        System.out.println("hello");

        Employee testEmployee = new Employee(1, "Bob Jones 4", true);

        EmployeeDB db = (EmployeeDB) EmployeeDB.getInstance();

        db.save(testEmployee);
        db.save(testEmployee);
        db.save(testEmployee);


        Employee loadTest1 = db.load(0);
        loadTest1.isAdmin = true;
        db.save(loadTest1);
        Employee loadTest2 = db.load(1);
        System.out.println(loadTest1.name);
        System.out.println(loadTest2.name);

    }

}
