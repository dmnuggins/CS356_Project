package meetingapp.gui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import meetingapp.entity.Employee;

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

        String[] columns = {"Today", "Tomorrow"};

        for(int i=0;i<columns.length;i++) {
            model.addColumn(columns[i]);
        }
        model.addRow(new Object[] {"event1", "event2"});


        dismissButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
    }

}
