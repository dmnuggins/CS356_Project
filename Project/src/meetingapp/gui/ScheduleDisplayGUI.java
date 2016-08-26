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

        String[] columns = {"", "Monday", "Tuesday", "Wednesday","Thursday","Friday"};
        String[][] times = {{"12:00AM"},{"01:00AM"},{"02:00AM"},{"03:00AM"},{"04:00AM"},{"05:00AM"},{"06:00AM"},
                {"07:00AM"},{"08:00AM"},{"09:00AM"}, {"10:00AM"},{"11:00AM"},{"12:00PM"},{"01:00PM"},{"02:00PM"},
                {"03:00PM"},{"04:00PM"},{"05:00PM"}, {"06:00PM"},{"07:00PM"},{"08:00PM"},{"09:00PM"},{"10:00PM"},
                {"11:00PM"}};

        // establishes table columns
        for(int i=0;i<columns.length;i++) {
            model.addColumn(columns[i]);
        }
        // creates time labels for the day
        // first column is reserved for time slots
        for(int j=0;j<times.length;j++) {
            model.addRow(times[j]);
        }


        
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new EmployeeGUI(employee);
                dispose();
            }
        });


        reserveTimeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        setVisible(true);
    }

}
