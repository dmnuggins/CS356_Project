package meetingapp.gui;

import meetingapp.entity.Employee;
import meetingapp.entity.Participant;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
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
    private JTable meetingTable;
    private JTable participantMeetingTable;
    private JButton cancelMeetingButton;
    private JButton updateMeetingButton;
    private JButton updateStatusButton;
    private JScrollPane ownJScrollPane;
    private JButton meetingInfoButton;

    public MeetingManagerEmployeeGUI (final Employee employee) {
        super("Meeting Manager", employee);
        setContentPane(meetingManagerRootPanel);
        pack();

        DefaultTableModel ownModel = new DefaultTableModel();
        meetingTable.setModel(ownModel);
        ownModel.addColumn("Meeting");
        List<Participant> meetings =  employee.getAllMeetings(true, false);
        for (Participant em : meetings) {
            ownModel.addRow(new Object[] { em.getMeeting().getStart().toLocaleString() } );
        }

        DefaultTableModel invitedModel = new DefaultTableModel();
        participantMeetingTable.setModel(invitedModel);
        invitedModel.addColumn("Meeting");
        List<Participant> invited =  employee.getAllMeetings(false, false);
        for (Participant em : invited) {
            invitedModel.addRow(new Object[] { em.getMeeting().getStart().toLocaleString() } );
        }

        /**
         * When a specific meeting (owned/invited) is selected,
         * the corresponding option buttons will be highlighted
         */
        meetingTable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {

            }
        });

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
