package meetingapp.gui;

import meetingapp.db.MeetingDB;
import meetingapp.entity.*;
import java.util.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by dmnguyen on 8/9/16.
 */
public class NotifEmployeeInvitesGUI extends MeetingAppGUI {
    private JPanel northPanel;
    private JButton acceptButton;
    private JButton declineButton;
    private JPanel meetingInvitePanel;
    private JLabel dateLabelStart;
    private JLabel dateLabelEnd;
    private JList invitedList;

    EmployeeMeeting meeting;

    public NotifEmployeeInvitesGUI(final Employee employee, final EmployeeMeeting meeting) {
        super("Meeting Invite", employee, false);
        setContentPane(meetingInvitePanel);
        pack();

        this.meeting = meeting;
        meeting.setSeen(true);

        Meeting m = MeetingDB.getInstance().load(meeting.getMeetingID());
        dateLabelStart.setText("Starts: " + m.getStart().toLocaleString());
        dateLabelEnd.setText("Ends: " + m.getEnd().toLocaleString());

        ArrayList<Employee> attending = (ArrayList<Employee>) EmployeeMeeting.getAllEmployees(meeting.getMeetingID(), true, false);

        DefaultListModel<String> lm = new DefaultListModel<String>();
        invitedList.setModel(lm);
        
        for (Employee e : attending) {
            lm.addElement(e.getName());
        }

        acceptButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                meeting.setAccepted(true);
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
