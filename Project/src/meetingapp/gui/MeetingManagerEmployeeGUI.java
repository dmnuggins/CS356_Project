package meetingapp.gui;

import meetingapp.entity.Employee;
import meetingapp.entity.EmployeeMeeting;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

/**
 * Created by dmnguyen on 8/9/16.
 */
public class MeetingManagerEmployeeGUI extends MeetingAppGUI {

    private JPanel meetingManagerRootPanel;
    private JPanel northPanel;
    private JPanel southPanel;
    private JPanel centerPanel;
    private JButton backButton;
    private JPanel southButtonPanel;
    private JTable ownMeetingTable;
    private JTable participantMeetingTable;
    private JButton cancelMeetingButton;
    private JButton updateMeetingButton;
    private JButton updateStatusButton;
    private JButton meetingInfoButton;
    private JScrollPane ownJScrollPane;
    private JScrollPane partJScrollPane;

    public MeetingManagerEmployeeGUI (final Employee employee) {
        super("Meeting Manager", employee);
        setContentPane(meetingManagerRootPanel);
        pack();

        DefaultTableModel ownModel = new DefaultTableModel();
        ownMeetingTable.setModel(ownModel);
        ownModel.addColumn("Meeting");
        List<EmployeeMeeting> meetings =  employee.getAllMeetings(true, false);
        for (EmployeeMeeting em : meetings) {
            ownModel.addRow(new Object[] { em.getMeeting().getStart().toLocaleString() } );
        }

        DefaultTableModel invitedModel = new DefaultTableModel();
        participantMeetingTable.setModel(invitedModel);
        invitedModel.addColumn("Meeting");
        List<EmployeeMeeting> invited =  employee.getAllMeetings(false, false);
        for (EmployeeMeeting em : invited) {
            invitedModel.addRow(new Object[] { em.getMeeting().getStart().toLocaleString() } );
        }

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new EmployeeGUI(employee);
                dispose();
            }
        });

        setVisible(true);
    }
}
