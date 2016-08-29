package meetingapp.gui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import meetingapp.entity.Employee;
import meetingapp.entity.Participant;
import meetingapp.entity.Meeting;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by dmnguyen on 8/16/16.
 */
public class NotifyMeetingSchedule extends MeetingAppGUI {
    private JPanel rootPanel;
    private JPanel northPanel;
    private JPanel centerPanel;
    private JPanel southPanel;
    private JButton dismissButton;
    private JTable scheduleTable;


    public NotifyMeetingSchedule(final Employee employee) {
        super("Schedule", employee, false);
        setup(rootPanel);

        DefaultTableModel model = new DefaultTableModel();
        scheduleTable.setModel(model);
        model.addColumn("Meeting Time");

        List<Participant> meetings = employee.getAllMeetings(false);
        Collections.sort(meetings);

        LocalDateTime twoDays = LocalDate.now().atStartOfDay().plusDays(2);

        for (Participant p : meetings) {
            Meeting m = p.getMeeting();
            if (m.getStart().isBefore(twoDays) && p.getAccepted()) {
                String prefixText = "Today, ";
                if (m.getStart().getDayOfMonth() != LocalDateTime.now().getDayOfMonth()) {
                    prefixText = "Tomorrow, ";
                }
                model.addRow(new Object[] { prefixText + m.getStartString() });
            }
        }

        if (model.getRowCount() == 0) {
            model.addRow(new Object[] { "No upcoming meetings" });
        }


        dismissButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        setVisible(true);
    }

}
