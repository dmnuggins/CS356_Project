package meetingapp.gui;

import meetingapp.entity.*;
import java.util.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by dmnguyen on 8/9/16.
 */
public class NotifyEmployeeInvites extends MeetingAppGUI {
    private JPanel northPanel;
    private JButton acceptButton;
    private JButton declineButton;
    private JPanel meetingInvitePanel;
    private JLabel dateLabelStart;
    private JList invitedList;
    private JLabel topLabel;

    Participant participant;

    public NotifyEmployeeInvites(final Employee employee, final Participant participant) {
        super("Meeting Invite", employee, false);
        setup(meetingInvitePanel);
        this.setLocation(this.getX() + 250, this.getY());

        this.participant = participant;
        this.participant.setSeen(true);

        //populate text fields
        topLabel.setText("Invite from: " + participant.getMeeting().getOwner().getName());
        Meeting meeting = participant.getMeeting();
        dateLabelStart.setText("Starts: " + meeting.getStartString());
        //dateLabelEnd.setText("Ends: " + meeting.getEnd());

        //build list of attending employees
        ArrayList<Participant> attending = (ArrayList<Participant>) meeting.getAllAccepted(true);
        DefaultListModel<String> lm = new DefaultListModel<String>();
        invitedList.setModel(lm);
        for (Participant e : attending) {
            lm.addElement(Employee.get(e.getEmployeeID()).getName());
        }

        acceptButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                participant.setAccepted(true);
                dispose();
            }
        });

        declineButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        setVisible(true);
        toFront();
    }
}
