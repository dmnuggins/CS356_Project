import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by dmnguyen on 8/7/16.
 * UI will also be displayed alongside another form that will show all the updates
 * and current meetings that the user is a part of, allowing him to reply or manage
 * all that is displayed
 */
public class EmployeeGUI extends JFrame{
    private JPanel employeePanel;
    private JPanel northPanel;
    private JButton displayScheduleButton;
    private JButton createMeetingButton;
    private JButton updateUserInfoButton;
    private JPanel southPanel;
    private JPanel acceptDeclinePanel;
    private JPanel newMeetingInvitePanel;
    private JPanel meetingSchedulePanel;
    private JTable invitesTabel;
    private JTable accDecTabel;
    private JTable scheduleTable;
    private JPanel buttonPanel;
    private JScrollPane scheduleScrollPane;

    public EmployeeGUI() {
        displayScheduleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        createMeetingButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                NewMeetingGUI nmgui = new NewMeetingGUI();
                nmgui.showGUI();
            }
        });
        updateUserInfoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }

    public void showGUI() {
        setTitle("New Meeting");
        setContentPane(new EmployeeGUI().employeePanel);
        pack();
        setVisible(true);
        setLocationRelativeTo(null);

    }
}
