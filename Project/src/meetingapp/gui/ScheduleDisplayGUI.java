package meetingapp.gui;

import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import meetingapp.entity.Employee;

/**
 * Created by dmnguyen on 8/9/16.
 */
public class ScheduleDisplayGUI extends JFrame{
    private JPanel scheduleDisplayPanel;
    private JPanel southPanel;
    private JPanel northPanel;
    private JPanel buttonPanel;
    private JButton setVisibilityButton;
    private JButton reserveTimeButton;
    private JButton unreserveTimeButton;
    private JButton backButton;
    private JPanel tablePanel;
    private JPanel lowerButtonPanel;
    private JTable scheduleTable;

    private Employee employee;

    public ScheduleDisplayGUI(Employee e) {
        super("Schedule");

        employee = e;

        String[] columns = {"Sunday", "Monday", "Tuesday", "Wednesday","Thursday","Friday", "Saturday"};

        String[][] data = {{"event1", "event2", "event3", "event4", "event5", "event6", "event7"},
                {"event1", "event2", "event3", "event4", "event5", "event6", "event7"},
                {"event1", "event2", "event3", "event4", "event5", "event6", "event7"},
                {"event1", "event2", "event3", "event4", "event5", "event6", "event7"},
                {"event1", "event2", "event3", "event4", "event5", "event6", "event7"},
                {"event1", "event2", "event3", "event4", "event5", "event6", "event7"},
                {"event1", "event2", "event3", "event4", "event5", "event6", "event7"},
                {"event1", "event2", "event3", "event4", "event5", "event6", "event7"},
                {"event1", "event2", "event3", "event4", "event5", "event6", "event7"},
                {"event1", "event2", "event3", "event4", "event5", "event6", "event7"},
                {"event1", "event2", "event3", "event4", "event5", "event6", "event7"},
                {"event1", "event2", "event3", "event4", "event5", "event6", "event7"},
                {"event1", "event2", "event3", "event4", "event5", "event6", "event7"},
                {"event1", "event2", "event3", "event4", "event5", "event6", "event7"}
        };

        scheduleTable = new JTable(data, columns) {
            public boolean isCellEditable(int data, int columns) {
                return false;
            }

            public Component prepareRenderer(TableCellRenderer r, int data, int columns) {
                Component c = super.prepareRenderer(r, data, columns);

                if(data % 2 == 0)
                    c.setBackground(Color.WHITE);
                else
                    c.setBackground(Color.LIGHT_GRAY);
                return c;
            }
        };
        scheduleTable.setPreferredScrollableViewportSize(new Dimension(550,300));
        scheduleTable.setFillsViewportHeight(true);

        JScrollPane jsp = new JScrollPane(scheduleTable);
        tablePanel.add(jsp);
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                EmployeeGUI empgui = new EmployeeGUI(employee);
                empgui.showGUI();
                dispose();
            }
        });
    }

    public void showGUI() {
        setTitle("Schedule");
        setContentPane(scheduleDisplayPanel);
        pack();
        setVisible(true);
        setLocationRelativeTo(null);
        setResizable(false);
    }
}
