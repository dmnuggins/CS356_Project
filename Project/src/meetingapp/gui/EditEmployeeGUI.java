package meetingapp.gui;
import meetingapp.entity.Employee;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by CesarRecinos on 8/9/2016.
 */
public class EditEmployeeGUI extends JFrame{
    private JPanel rootPanel;
    private JPanel employeePanel;
    private JPanel gridPanel;
    private JLabel nameLabel;
    private JLabel userNameLabel;
    private JLabel passwordLabel;
    private JCheckBox isAdminCheckBox;
    private JTextField nameField;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JPanel deletePanel;
    private JButton deleteButton;
    private JPanel buttonPanel;
    private JButton saveButton;
    private JButton cancelButton;

    Employee employee;
    Employee editing;

    public EditEmployeeGUI(final Employee employee, final Employee editing){
        super("Edit Employee");
        setContentPane(rootPanel);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);

        this.editing = editing;
        nameField.setText(editing.getName());
        usernameField.setText(editing.getLogin().getUsername());
        passwordField.setText(editing.getLogin().getPassword());
        isAdminCheckBox.setSelected(editing.getIsAdmin());


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


        isAdminCheckBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });


        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });


        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                editing.setIsAdmin(isAdminCheckBox.isSelected());
                editing.setName(nameField.getText());
                editing.getLogin().setPassword(new String(passwordField.getPassword()));
                editing.getLogin().setUsername(usernameField.getText());
            }
        });


        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new EmployeeDisplayAdminGUI(employee);
                dispose();
            }
        });
    }
}
