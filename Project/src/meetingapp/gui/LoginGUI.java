package meetingapp.gui;

import meetingapp.entity.Employee;
import meetingapp.entity.Login;

import javax.swing.*;
import java.awt.event.*;

/**
 * Created by dmnguyen on 7/7/16.
 */
public class LoginGUI extends JFrame {
    private JPanel loginPanel;
    private JPanel westLabelPanel;
    private JPanel southButtonPanel;
    private JPanel eastFieldPanel;
    private JTextField userNameField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JLabel userNameLabel;
    private JLabel passwordLabel;
    private JLabel loginText;
    private JButton button2;

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }

    public LoginGUI() {
        super("loginPanel");
        userNameField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                login();
            }
        });
        userNameField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                super.keyTyped(e);
            }
        });
        passwordField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                login();
            }
        });
        passwordField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                super.keyTyped(e);
            }
        });
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                login();
            }
        });

    }

    public void login() {
        Employee emp = Login.authenticate(userNameField.getText(), new String(passwordField.getPassword()));
        if (emp != null) {
            if (emp.getIsAdmin()) {
                AdminEmpChoiceGUI aegui = new AdminEmpChoiceGUI(emp);
                aegui.showGUI();
                dispose();
            } else {
                EmployeeGUI egui = new EmployeeGUI(emp);
                egui.showGUI();
                dispose();
            }
        } else {
            //show failed login text
            loginText.setText("Incorrect login!");
        }
    }

    // method is called in meetingapp.MeetingApp to initiate meetingapp.entity.Login User Interface
    public void showGUI() {
        setTitle("meetingapp.entity.Login");
        setContentPane(loginPanel);
        pack();
        setVisible(true);
        setLocationRelativeTo(null);
        setResizable(false);
    }
}
