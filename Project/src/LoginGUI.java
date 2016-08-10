import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * Created by dmnguyen on 7/7/16.
 */
public class LoginGUI extends JFrame{
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
        Employee emp = Login.authenticate(userNameField.getText(),new String(passwordField.getPassword()));
        if(emp != null) {
            if (emp.isAdmin) {
                AdminEmpChoiceGUI aegui = new AdminEmpChoiceGUI();
                aegui.showGUI();
            } else {
                EmployeeGUI egui = new EmployeeGUI();
                egui.showGUI();
            }
        } else {
            //show failed login text
            loginText.setText("Incorrect login!");
        }
    }

    // method is called in MeetingApp to initiate Login User Interface
    public void showGUI() {
        setTitle("Login");
        setContentPane(new LoginGUI().loginPanel);
        pack();
        setVisible(true);
        setLocationRelativeTo(null);
        setResizable(false);

    }
}
