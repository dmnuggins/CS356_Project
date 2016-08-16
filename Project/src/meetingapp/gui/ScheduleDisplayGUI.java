package meetingapp.gui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import meetingapp.entity.Employee;

/**
 * Created by dmnguyen on 8/9/16.
 */
public class ScheduleDisplayGUI extends MeetingAppGUI{
    private JPanel scheduleDisplayPanel;
    private JPanel southPanel;
    private JPanel northPanel;
    private JPanel buttonPanel;
    private JButton setVisibilityButton;
    private JButton reserveTimeButton;
    private JButton unreserveTimeButton;
    private JButton backButton;
    private JPanel centerPanel;
    private JPanel titlePanel;
    private JTable scheduleTable;
    private JButton confirmScheduleButton;

    public ScheduleDisplayGUI(final Employee employee) {
        super("Schedule", employee);
        setup(scheduleDisplayPanel);

        DefaultTableModel model = new DefaultTableModel();
        scheduleTable.setModel(model);

        String[] columns = {"Monday", "Tuesday", "Wednesday","Thursday","Friday"};

        for(int i=0;i<columns.length;i++) {
            model.addColumn(columns[i]);
        }
        model.addRow(new Object[] {"event1", "event2", "event3", "event4", "event5"});


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
