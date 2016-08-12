package meetingapp.gui;

import meetingapp.entity.Employee;
import meetingapp.entity.EmployeeMeeting;
import meetingapp.entity.Meeting;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by cthill on 8/11/16.
 */
public class NotifyMeetingOwner extends MeetingAppGUI {

    protected EmployeeMeeting employeeMeeting;
    private JPanel mainPanel;
    private JButton dismissButton;
    private JTextPane messageTextPane;

    public NotifyMeetingOwner(final Employee employee, final EmployeeMeeting employeeMeeting) {
        super("Response", employee, false);
        setContentPane(mainPanel);
        pack();

        this.employeeMeeting = employeeMeeting;
        this.employeeMeeting.setSeenByOwner(true);

        Employee emp = Employee.get(employeeMeeting.getEmployeeID());
        String empName = emp.getName();
        String accText = "declined";
        if (employeeMeeting.getAccepted()) {
            accText = "accepted";
        }

        ;
        String meetingDateText = Meeting.get(employeeMeeting.getMeetingID()).getStart().toLocaleString();
        messageTextPane.setText(empName + " has " + accText + " your meeting invite for " + meetingDateText + ".");

        dismissButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        setVisible(true);
    }
}
