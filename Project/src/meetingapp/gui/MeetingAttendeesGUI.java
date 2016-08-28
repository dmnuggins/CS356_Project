package meetingapp.gui;

import meetingapp.entity.Employee;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by CesarRecinos on 8/23/2016.
 */
public class MeetingAttendeesGUI extends MeetingAppGUI {
    private JPanel rootPanel;
    private JButton doneButton;
    private JList attendingList;
    private JButton addButton;
    private JButton removeButton;
    private JList employeeList;

    private List<Employee> attending;
    private List<Employee> all;


    public MeetingAttendeesGUI(Employee employee) {
        super("Meeting Attendees", employee, false);
        setup(rootPanel);

        attending = new ArrayList<>();
        all = Employee.getAll();
        all.remove(employee);

        fillLists(all, attending);

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selected = employeeList.getSelectedIndex();
                if (all.size() > 0 && selected != -1) {
                    attending.add(all.remove(selected));
                    fillLists(all, attending);
                }
            }
        });

        removeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selected = attendingList.getSelectedIndex();
                if (attending.size() > 0 && selected != -1) {
                    all.add(attending.remove(selected));
                    fillLists(all, attending);
                }
            }
        });

        doneButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        setVisible(true);
    }


    private void fillLists(List<Employee> all, List<Employee> attending) {
        DefaultListModel<String> employeeListModel = new DefaultListModel<>();
        DefaultListModel<String> attendingListModel = new DefaultListModel<>();

        for (Employee e : all) {
            employeeListModel.addElement(e.getName());
        }

        for (Employee e : attending) {
            attendingListModel.addElement(e.getName());
        }

        employeeList.setModel(employeeListModel);
        attendingList.setModel(attendingListModel);
    }

    public List<Employee> getAttending() {
        return attending;
    }
}
