package meetingapp.gui;
import meetingapp.entity.Employee;
import meetingapp.db.EmployeeDB;

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

    Employee saving;

    public CreateEmployeeGUI(final Employee employee){
        super("Create a New Employee", employee);
        setup(rootPanel);

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
                //int ID = EmployeeDB.getInstance().getNextID();
                saving = new Employee(EmployeeDB.getInstance().getNextID(), nameField.getText(),isAdminCheckBox.isSelected());
                saving.getLogin().setPassword(new String(passwordField.getPassword()));
                saving.getLogin().setUsername(usernameField.getText());
                new EmployeeDisplayAdminGUI(employee);
                dispose();

            }
        });
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new EmployeeDisplayAdminGUI(employee);
            }
        });

        setVisible(true);
        isAdminCheckBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }
}
