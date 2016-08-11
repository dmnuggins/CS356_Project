package meetingapp.gui;

import meetingapp.entity.Employee;

import javax.swing.*;

/**
 * Created by dmnguyen on 8/9/16.
 */
public class MeetingManagerEmployeeGUI extends MeetingAppGUI {

    private JPanel mainPanel;

    public MeetingManagerEmployeeGUI (final Employee employee) {
        super("Meeting Manager", employee);
        setContentPane(mainPanel);
        pack();

        setVisible(true);
    }
}
