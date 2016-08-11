package meetingapp.gui;

import meetingapp.entity.*;
import meetingapp.db.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

/**
 * Created by Dylan Nguyen on 8/8/2016.
 */
public class CreateMeetingEmployeeGUI extends MeetingAppGUI{
    private JPanel newMeetingPanel;
    private JPanel northPanel;
    private JPanel southPanel;
    private JPanel titlePanel;
    private JPanel comboBoxPanel;
    private JComboBox roomComboBox;
    private JLabel roomLabel;
    private JButton Create;
    private JButton backButton;
    private JTable inviteListTable;
    private JButton removeEmployeesButton;
    private JButton changeTimeButton;
    private JButton addEmployeesButton;
    private JLabel meetingDateLabel;

    private ArrayList<Room> allRooms;

    public CreateMeetingEmployeeGUI(final Employee employee) {
        super("New Meeting", employee);
        setContentPane(newMeetingPanel);
        pack();


        this.employee = employee;
        //get all employees
        allRooms = (ArrayList<Room>) RoomDB.getInstance().loadAll();
        for (int i = 0; i < allRooms.size(); i++) {
            roomComboBox.addItem(Integer.toString(allRooms.get(i).getID()));
        }

        DefaultTableModel model = new DefaultTableModel();
        inviteListTable.setModel(model);

        model.addColumn("Name");
        model.addRow(new Object[] { employee.getName() });

        meetingDateLabel.setText(new Date().toLocaleString());

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
