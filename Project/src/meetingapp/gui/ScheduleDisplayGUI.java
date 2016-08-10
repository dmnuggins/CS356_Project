package meetingapp.gui;

import javax.swing.*;

/**
 * Created by dmnguyen on 8/9/16.
 */
public class ScheduleDisplayGUI extends JFrame{
    private JPanel scheduleDisplayPanel;

    public void showGUI() {
        setTitle("Schedule");
        setContentPane(scheduleDisplayPanel);
        pack();
        setVisible(true);
        setLocationRelativeTo(null);
        setResizable(false);
    }
}
