import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by dmnguyen on 8/7/16.
 * UI will also be displayed alongside another form that will show all the updates
 * and current meetings that the user is a part of, allowing him to reply or manage
 * all that is displayed
 */
public class EmployeeGUI {
    private JPanel EmployeePanel;
    private JPanel northPanel;
    private JButton displayScheduleButton;
    private JButton createMeetingButton;
    private JButton updateUserInfoButton;

    public EmployeeGUI() {
        displayScheduleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        createMeetingButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        updateUserInfoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }
}
