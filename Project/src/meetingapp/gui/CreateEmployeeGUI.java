package meetingapp.gui;
import meetingapp.entity.Employee;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by CesarRecinos on 8/9/2016.
 */
public class CreateEmployeeGUI extends MeetingAppGUI{
    private JPanel rootPanel;
    private JPanel employeePanel;
    private JPanel gridPanel;
    private JLabel nameLabel;
    private JLabel usernameLabel;
    private JLabel passwordLabel;
    private JTextField nameField;
    private JTextField usernameField;
    private JCheckBox isAdminCheckBox;
    private JPanel buttonPanel;
    private JButton saveEmployee;
    private JButton cancelButton;
    private JPasswordField passwordField;

    public CreateEmployeeGUI(Employee employee){
        super("Create a New Employee", employee);
        setContentPane(rootPanel);
        pack();

        nameField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        usernameField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        passwordField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        saveEmployee.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();

                //new EmployeeDisplayAdminGUI().setVisible(true);
            }
        });

        setVisible(true);
    }
}
