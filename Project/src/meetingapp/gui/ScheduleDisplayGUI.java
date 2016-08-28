package meetingapp.gui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.TextStyle;
import java.util.Locale;

import meetingapp.entity.Employee;

/**
 * Created by dmnguyen on 8/9/16.
 */
public class ScheduleDisplayGUI extends MeetingAppGUI{
    private JPanel scheduleDisplayPanel;
    private JPanel southPanel;
    private JPanel northPanel;
    private JPanel buttonPanel;
    private JButton reserveTimeButton;
    private JButton unreserveTimeButton;
    private JButton backButton;
    private JPanel centerPanel;
    private JPanel titlePanel;
    private JTable scheduleTable;
    private JLabel updateText;
    private JButton rightButton;
    private JButton leftButton;
    LocalDate originalStartDay = LocalDate.now();
    LocalDate startDay = LocalDate.now();

    public ScheduleDisplayGUI(final Employee employee) {
        super("Schedule", employee);
        setup(scheduleDisplayPanel);

        populateTable(startDay);
        scheduleTable.getTableHeader().setReorderingAllowed(false);

        
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new EmployeeGUI(employee);
                dispose();
            }
        });


        unreserveTimeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int col = scheduleTable.getSelectedColumn();
                int row = scheduleTable.getSelectedRow();
                if (col > 0) {
                    scheduleTable.getModel().setValueAt("free", row, col);
                    LocalDateTime toUnreserve = startDay.atStartOfDay().plusDays(col - 1).plusHours(row);
                    employee.unreserveDate(toUnreserve);
                    updateText.setText("Schedule Updated!");
                } else {
                    updateText.setText(" ");
                }
                scheduleTable.grabFocus();
            }
        });

        reserveTimeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int col = scheduleTable.getSelectedColumn();
                int row = scheduleTable.getSelectedRow();
                if (col > 0) {
                    scheduleTable.getModel().setValueAt("reserved", row, col);
                    LocalDateTime toReserve = startDay.atStartOfDay().plusDays(col - 1).plusHours(row);
                    employee.reserveDate(toReserve);
                    updateText.setText("Schedule Updated!");
                } else {
                    updateText.setText(" ");
                }
                scheduleTable.grabFocus();
            }
        });

        rightButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startDay = startDay.plusDays(1);
                populateTable(startDay);
                if (startDay.isAfter(originalStartDay)) {
                    leftButton.setEnabled(true);
                }
            }
        });

        leftButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startDay = startDay.minusDays(1);
                populateTable(startDay);
                if (startDay.isEqual(originalStartDay)) {
                    leftButton.setEnabled(false);
                }
            }
        });

        setVisible(true);
    }

    private void populateTable(LocalDate startDay) {
        updateText.setText(" ");

        DefaultTableModel model = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                //all cells false
                return false;
            }
        };
        scheduleTable.setModel(model);
        scheduleTable.setRowHeight(20);

        String[] columns = new String[6];
        columns[0] = "";
        for (int i = 0; i < 5; i++) {
            LocalDate day = startDay.plusDays(i);
            String dayOfWeek = day.getDayOfWeek().getDisplayName(TextStyle.SHORT, Locale.ENGLISH);
            int dayOfMonth = day.getDayOfMonth();
            int month = day.getMonthValue();

            columns[i + 1] = dayOfWeek + ", " + month + "/" + dayOfMonth;
        }

        String[] times = {"12:00AM","01:00AM","02:00AM","03:00AM","04:00AM","05:00AM","06:00AM",
                "07:00AM","08:00AM","09:00AM", "10:00AM","11:00AM","12:00PM","01:00PM","02:00PM",
                "03:00PM","04:00PM","05:00PM", "06:00PM","07:00PM","08:00PM","09:00PM","10:00PM",
                "11:00PM"};

        //add table columns
        for(int i = 0;i < columns.length; i++) {
            model.addColumn(columns[i]);
        }

        boolean[][] reserved = employee.getSchedule(startDay, 5, false);

        for (int row = 0; row < 24; row++) {
            Object[] thisRow = new Object[6];
            for (int col = 0; col < 6; col++) {
                if (col == 0) {
                    thisRow[0] = times[row];
                } else {
                    if (reserved[col - 1][row]) {
                        thisRow[col] = "reserved";
                    } else {
                        thisRow[col] = "free";
                    }
                }
            }
            model.addRow(thisRow);
        }
    }

}
