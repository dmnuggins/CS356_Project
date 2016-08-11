package meetingapp.gui;

import meetingapp.entity.Employee;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by dmnguyen on 8/7/16.
 * UI will also be displayed alongside another form that will show all the updates
 * and current meetings that the user is a part of, allowing him to reply or manage
 * all that is displayed
 */
public class EmployeeGUI extends MeetingAppGUI{
    private JPanel employeePanel;
    private JPanel northPanel;
    private JButton displayScheduleButton;
    private JButton createMeetingButton;
    private JButton changePasswordButton;
    private JPanel displayButtonPanel;
    private JPanel createButtonPanel;
    private JPanel updateUserButtonPanel;
    private JButton manageMeetingsButton;
    private JButton backButton;
    private JLabel topLabel;

    public EmployeeGUI(final Employee employee) {
        super("Employee Menu", employee);
        setContentPane(employeePanel);
        pack();

        topLabel.setText("Welcome, " + employee.getName());

        displayScheduleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ScheduleDisplayGUI(employee);
                dispose();
            }
        });
        createMeetingButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new CreateMeetingEmployeeGUI(employee);
                dispose();
            }
        });
        changePasswordButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ChangePasswordEmployeeGUI(employee);
                dispose();
            }
        });

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new LoginGUI();
                dispose();
            }
        });

        setVisible(true);
    }
}
