package meetingapp.gui;

import meetingapp.entity.Employee;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.TextStyle;
import java.util.Locale;
import java.util.List;

/**
 * Created by cthill on 8/28/16.
 */
public class AvailableTimesGUI extends MeetingAppGUI {

    private JPanel rootPanel;
    private JButton cancelButton;
    private JButton selectButton;
    private JButton leftButton;
    private JButton rightButton;
    private JTable scheduleTable;
    private JLabel errorText;

    private List<Employee> invited;
    private LocalDate originalStartDay = LocalDate.now();
    private LocalDate startDay = LocalDate.now();
    private LocalDateTime selected;
    private boolean[][] availableTimes = new boolean[5][24];

    public AvailableTimesGUI(Employee employee, List<Employee> invited) {
        super("Select a time", employee, false);
        setup(rootPanel);

        this.invited = invited;
        this.invited.add(employee);

        populateTable(startDay);

        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new EmployeeGUI(employee);
                dispose();
            }
        });

        selectButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int col = scheduleTable.getSelectedColumn();
                int row = scheduleTable.getSelectedRow();
                if (col > 0 && !availableTimes[col - 1][row]) {
                    scheduleTable.getModel().setValueAt("free", row, col);
                    selected = startDay.atStartOfDay().plusDays(col - 1).plusHours(row);
                    dispatchEvent(new WindowEvent(window, WindowEvent.WINDOW_CLOSING));
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

        availableTimes = new boolean[5][24];

        for (Employee e : invited) {
            boolean[][] schedule = e.getSchedule(startDay, 5, false);
            for (int i = 0; i < schedule.length; i++) {
                for (int j = 0; j < schedule[i].length; j++) {
                    availableTimes[i][j] |= schedule[i][j];
                }
            }
        }

        for (int row = 0; row < 24; row++) {
            Object[] thisRow = new Object[6];
            for (int col = 0; col < 6; col++) {
                if (col == 0) {
                    thisRow[0] = times[row];
                } else {
                    if (availableTimes[col - 1][row]) {
                        thisRow[col] = "-------";
                    } else {
                        thisRow[col] = "Available!";
                    }
                }
            }
            model.addRow(thisRow);
        }
    }

    public LocalDateTime getSelectedTime() {
        return selected;
    }
}
