package meetingapp.gui;

import meetingapp.entity.Employee;
import meetingapp.entity.Room;

import javax.swing.JFrame;

/**
 * Created by cthill on 8/10/16.
 */
public class MeetingAppGUI extends JFrame {
    protected Employee employee;
    protected Room room;

    public MeetingAppGUI(String title, final Employee employee) {
        super(title);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.employee = employee;
    }

    public MeetingAppGUI(String title, final Employee employee, boolean exitOnClose) {
        super(title);
        setResizable(false);
        setLocationRelativeTo(null);
        if (exitOnClose) {
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        }
        this.employee = employee;
    }

    public MeetingAppGUI(String title, final Room room) {
        super(title);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.room = room;
    }
}
