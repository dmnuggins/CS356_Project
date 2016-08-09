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

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }

    public LoginGUI() {
        super("loginPanel");
        userNameField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

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
                AdminEmpChoiceGUI aegui = new AdminEmpChoiceGUI();
                aegui.showGUI();
//                Employee emp = Login.authenticate(userNameField.getText(),new String(passwordField.getPassword()));
//                System.out.println(emp.name);
//                if(emp.name != null) {
//                    if (emp.isAdmin) {
//                        //give choice of accessing admin/employee UI
//                    } else {

//                    }
//
//                }
            }
        });

    }

    // method is called in MeetingApp to initiate Login User Interface
    public void showGUI() {
        setTitle("Login");
        setContentPane(new LoginGUI().loginPanel);
        pack();
        setVisible(true);
        setLocationRelativeTo(null);

    }
}
