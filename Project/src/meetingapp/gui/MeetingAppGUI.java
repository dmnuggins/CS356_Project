package meetingapp.gui;

import meetingapp.entity.Employee;
import meetingapp.entity.Room;
import oracle.jvm.hotspot.jfr.JFR;

import java.awt.Container;
import javax.swing.JFrame;

/**
 * Created by cthill on 8/10/16.
 */
public class MeetingAppGUI extends JFrame {
    protected Employee employee;
    protected Room room;
    protected JFrame window = this;

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
        } else {
            setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        }
        this.employee = employee;
    }

    protected void setup(Container c) {
        setContentPane(c);
        pack();
        setLocationRelativeTo(null);
    }


}
