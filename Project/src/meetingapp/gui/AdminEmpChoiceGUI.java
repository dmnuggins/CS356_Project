package meetingapp.gui;

import meetingapp.entity.Employee;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by dmnguyen on 8/7/16.
 */
public class AdminEmpChoiceGUI extends MeetingAppGUI{
    private JPanel choicePanel;
    private JPanel northPanel;
    private JButton adminMenuButton;
    private JButton employeeMenuButton;
    private JButton logOutButton;
    private JPanel adminMenuPanel;
    private JPanel employeeMenuPanel;
    private JPanel logOutPanel;

    public AdminEmpChoiceGUI(final Employee employee) {
        super("Choose Menu Type", employee);
        setContentPane(choicePanel);
        pack();

        this.employee = employee;

        adminMenuButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AdminGUI(employee);
                dispose();
            }
        });
        employeeMenuButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new EmployeeGUI(employee);
                dispose();
            }
        });
        logOutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new LoginGUI();
                dispose();
            }
        });

        setVisible(true);
    }
}
