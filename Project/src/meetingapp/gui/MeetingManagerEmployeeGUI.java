package meetingapp.gui;

import meetingapp.entity.Employee;
import meetingapp.entity.Meeting;
import meetingapp.entity.Participant;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
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
    private JScrollPane ownJScrollPane;
    private JButton cancelMeetingButton;
    private JLabel messageLabel;
    private JButton meetingInfoButton;

    List<Participant> meetings;

    public MeetingManagerEmployeeGUI (final Employee employee) {
        super("Meeting Manager", employee);
        setup(meetingManagerRootPanel);

        meetings = employee.getAllMeetings(false);
        Collections.sort(meetings);
        fillTable(meetings);

        cancelMeetingButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (meetings.size() > 0) {
                    Participant p = meetings.get(meetingTable.getSelectedRow());
                    Meeting m = p.getMeeting();
                    List<Participant> allInMeeting = m.getAllInvited(true, true);
                    for (Participant par : allInMeeting) {
                        par.delete();
                    }
                    m.delete();

                    meetings.remove(p);
                    fillTable(meetings);
                    messageLabel.setText("Meeting canceled!");
                }
            }
        });

        meetingInfoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (meetings.size() > 0) {
                    new MeetingInfoGUI(employee, meetings.get(meetingTable.getSelectedRow()).getMeeting());
                }
            }
        });

        /**
         * When a specific meeting (owned/invited) is selected,
         * the corresponding option buttons will be highlighted
         */
        meetingTable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (meetings.size() > 0 && e.getLastIndex() >= 0 && e.getLastIndex() < meetings.size()) {
                    Participant p = meetings.get(e.getLastIndex());
                    cancelMeetingButton.setEnabled(p.getIsOwner());
                    meetingInfoButton.setEnabled(true);
                } else {
                    cancelMeetingButton.setEnabled(false);
                    meetingInfoButton.setEnabled(false);
                }
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

    public void fillTable(List<Participant> meets) {
        DefaultTableModel model = new DefaultTableModel();
        meetingTable.setModel(model);
        model.addColumn("Meeting");

        for (Participant p : meets) {
            Meeting m = p.getMeeting();
            String prefixText = "Today, ";
            if (m.getStart().getDayOfMonth() != LocalDateTime.now().getDayOfMonth()) {
                prefixText = "Tomorrow, ";
            }

            String ownerString = "participant";
            if (p.getIsOwner()) {
                ownerString = "owner";
            }

            model.addRow(new Object[] { prefixText + m.getStartString(), ownerString });
        }

        if (model.getRowCount() == 0) {
            model.addRow(new Object[] { "No meetings to show" });
        }
    }
}
