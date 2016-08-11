package meetingapp.gui;

import meetingapp.entity.*;
import meetingapp.db.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

/**
 * Created by Dylan Nguyen on 8/8/2016.
 */
public class NewMeetingEmployeeGUI extends JFrame{
    private JPanel newMeetingPanel;
    private JPanel northPanel;
    private JPanel southPanel;
    private JPanel titlePanel;
    private JPanel comboBoxPanel;
    private JComboBox roomComboBox;
    private JComboBox startDateComboBox;
    private JComboBox endDateComboBox;
    private JLabel roomLabel;
    private JLabel startDateLabel;
    private JLabel endDateLabel;
    private JButton Create;
    private JButton backButton;

    private Employee employee;

    private ArrayList<Room> allRooms;

    public NewMeetingEmployeeGUI(Employee employee) {
        setTitle("New Meeting");
        setContentPane(newMeetingPanel);
        pack();
        setVisible(true);
        setLocationRelativeTo(null);

        this.employee = employee;
        //get all employees
        allRooms = (ArrayList<Room>) RoomDB.getInstance().loadAll();
        for (int i = 0; i < allRooms.size(); i++) {
            roomComboBox.addItem(Integer.toString(allRooms.get(i).getID()));
        }


        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                back();
            }
        });
    }

    public void back() {
        new EmployeeGUI(employee);
        dispose();
    }
}
