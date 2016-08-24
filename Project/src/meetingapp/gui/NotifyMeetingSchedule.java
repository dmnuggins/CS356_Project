package meetingapp.gui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import meetingapp.entity.Employee;
import meetingapp.entity.Participant;
import meetingapp.entity.Meeting;

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
        super("Schedule", employee);
        setup(rootPanel);

        DefaultTableModel model = new DefaultTableModel();
        scheduleTable.setModel(model);
        model.addColumn("Meeting Time");

        List<Participant> meetings = employee.getAllMeetings(false, false);

        int today = new Date().getDate();
        Date twoDays = new Date();
        twoDays.setHours(0);
        twoDays.setMinutes(0);
        twoDays.setSeconds(0);
        twoDays.setDate(twoDays.getDay() + 2);

        for (Participant p : meetings) {
            Meeting m = p.getMeeting();
            if (m.getStart().before(twoDays)) {
                String prefixText = "Today, ";

                if (m.getStart().getDate() != today) {
                    prefixText = "Tomorrow, ";
                }

                model.addRow(new Object[] { m.getStart().getHours() + ":00" });
            }
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
