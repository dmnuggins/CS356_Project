package meetingapp.gui;

import meetingapp.entity.Employee;
import meetingapp.entity.Participant;
import meetingapp.entity.Meeting;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by cthill on 8/11/16.
 */
public class NotifyMeetingOwner extends MeetingAppGUI {

    protected Participant participant;
    private JPanel mainPanel;
    private JButton dismissButton;
    private JTextPane messageTextPane;

    public NotifyMeetingOwner(final Employee employee, final Participant participant) {
        super("Response", employee, false);
        setup(mainPanel);

        this.participant = participant;
        this.participant.setSeenByOwner(true);

        Employee emp = Employee.get(participant.getEmployeeID());
        String empName = emp.getName();
        String accText = "declined";
        if (participant.getAccepted()) {
            accText = "accepted";
        }

        ;
        String meetingDateText = Meeting.get(participant.getMeetingID()).getStart().toString();
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
