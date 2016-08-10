package meetingapp.gui;

import meetingapp.entity.Employee;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by dmnguyen on 8/7/16.
 */
public class AdminEmpChoiceGUI extends JFrame{
    private JPanel choicePanel;
    private JPanel northPanel;
    private JButton adminMenuButton;
    private JButton employeeMenuButton;
    private JButton logOutButton;
    private JPanel adminMenuPanel;
    private JPanel employeeMenuPanel;
    private JPanel logOutPanel;

    private Employee employee;

    public AdminEmpChoiceGUI(final Employee employee) {


        this.employee = employee;

        adminMenuButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        employeeMenuButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                EmployeeGUI empgui = new EmployeeGUI(employee);
                empgui.showGUI();
            }
        });
        logOutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }
    public void showGUI() {
        setTitle("Choose Menu Type");
        setContentPane(choicePanel);
        pack();
        setVisible(true);
        setLocationRelativeTo(null);

    }
}
