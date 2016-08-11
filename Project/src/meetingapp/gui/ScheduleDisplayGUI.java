package meetingapp.gui;

import javax.swing.*;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;
import java.awt.*;

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
    private JTable scheduleTable;

    public ScheduleDisplayGUI() {
        String[] columns = {"Today", "Tomorrow"};

        String[][] data = {{"Today's Meeting", "Tomorrow's Meeting"},
                {"Today's Meeting", "Tomorrow's Meeting"},
                {"Today's Meeting", "Tomorrow's Meeting"},
                {"Today's Meeting", "Tomorrow's Meeting"},
                {"Today's Meeting", "Tomorrow's Meeting"},
                {"Today's Meeting", "Tomorrow's Meeting"},
                {"Today's Meeting", "Tomorrow's Meeting"},
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
        scheduleTable.setPreferredScrollableViewportSize(new Dimension(450,200));
        scheduleTable.setFillsViewportHeight(true);

        JScrollPane jsp = new JScrollPane(scheduleTable);
        southPanel.add(jsp);
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
