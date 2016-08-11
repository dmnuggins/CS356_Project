package meetingapp.gui;
import meetingapp.db.EmployeeDB;
import meetingapp.entity.Employee;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * Created by CesarRecinos on 8/7/2016.
 */
public class EmployeeDisplayAdminGUI extends MeetingAppGUI{
    private JPanel rootPanel;
    private JPanel textFieldPanel;
    private JPanel buttonPanel;
    private JComboBox comboBox1;
    private JButton createButton;
    private JButton editButton;
    private JButton cancelButton;

    ArrayList<Employee> allEmployees;

    public EmployeeDisplayAdminGUI(final Employee employee){
        super("Employees", employee);
        setContentPane(rootPanel);
        pack();

        allEmployees = (ArrayList<Employee>) EmployeeDB.getInstance().loadAll();
        allEmployees.remove(employee);

        for (Employee e : allEmployees) {
            comboBox1.addItem(e.getName());
        }

        comboBox1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        createButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new CreateEmployeeGUI(employee);
                dispose();
            }
        });
        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Employee selected = allEmployees.get(comboBox1.getSelectedIndex());
                new EditEmployeeGUI(employee, selected);
                dispose();

            }
        });
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();

                new AdminGUI(employee).setVisible(true);
            }
        });

        setVisible(true);
    }
}
