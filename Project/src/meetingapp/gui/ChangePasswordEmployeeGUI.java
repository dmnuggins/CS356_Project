package meetingapp.gui;

import meetingapp.entity.*;

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

/**
 * Created by dmnguyen on 8/9/16.
 */
public class ChangePasswordEmployeeGUI extends MeetingAppGUI{
    private JPanel updateUserPanel;
    private JPanel westPanel;
    private JPanel eastPanel;
    private JLabel oldPasswordLabel;
    private JLabel newPasswordLabel;
    private JLabel reTypeNewPasswordLabel;
    private JPasswordField oldPasswordField;
    private JPasswordField newPasswordField;
    private JPasswordField reEnterNewPasswordField;
    private JPanel southPanel;
    private JButton changePasswordButton;
    private JLabel feedbackText;
    private JButton backButton;

    public ChangePasswordEmployeeGUI(final Employee employee) {
        super("Change Password", employee);
        setup(updateUserPanel);

        ActionListener tryChangeAL = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tryChange();
            }
        };
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new EmployeeGUI(employee);
                dispose();
            }
        });

        oldPasswordField.addActionListener(tryChangeAL);
        newPasswordField.addActionListener(tryChangeAL);
        reEnterNewPasswordField.addActionListener(tryChangeAL);
        changePasswordButton.addActionListener(tryChangeAL);

        setVisible(true);
    }

    public void tryChange() {
        String old = new String(oldPasswordField.getPassword());
        String newPW = new String(newPasswordField.getPassword());
        String newPW2 = new String(reEnterNewPasswordField.getPassword());

        Login l = employee.getLogin();
        if (old.equals(l.getPassword())) {
            if (newPW.equals(newPW2)) {
                l.setPassword(newPW);
                feedbackText.setForeground(new Color(0, 255, 0));
                feedbackText.setText("Passwords changed!");
            } else {
                feedbackText.setForeground(new Color(255, 0, 0));
                feedbackText.setText("Passwords do not match!");
            }

        } else {
            feedbackText.setForeground(new Color(255, 0, 0));
            feedbackText.setText("Incorrect old password!");
        }
    }
}
