package meetingapp.gui;

import javax.swing.*;
import java.awt.event.*;

/**
 * Created by dmnguyen on 8/9/16.
 */
public class ChangePasswordEmployeeGUI extends JFrame{
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
    private JButton backButton;
    private JLabel feedbackText;

    public ChangePasswordEmployeeGUI() {
        super("Change Password");

        ActionListener tryChangeAL = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tryChange();
            }
        };

        oldPasswordField.addActionListener(tryChangeAL);
        newPasswordField.addActionListener(tryChangeAL);
        reEnterNewPasswordField.addActionListener(tryChangeAL);

    }

    public void tryChange() {
        //if (oldPasswordField )
    }

    public void showGUI() {
        setTitle("meetingapp.entity.Login");
        setContentPane(updateUserPanel);
        pack();
        setVisible(true);
        setLocationRelativeTo(null);
        setResizable(false);
    }
}
