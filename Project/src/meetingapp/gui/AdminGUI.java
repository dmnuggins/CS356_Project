package meetingapp.gui;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by CesarRecinos on 8/7/2016.
 */
public class AdminGUI extends JFrame{
    private JPanel rootPanel;
    private JPanel employeePanel;
    private JPanel buttonPanel;
    private JButton employeeButton;
    private JPanel meetingPanel;
    private JPanel backPanel;
    private JButton meetingButton;
    private JButton goBack;

    public AdminGUI() {

        super("Admin Menu");
        setContentPane(rootPanel);
        pack();
        setLocationRelativeTo(null);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        employeeButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                EmployeeDisplayAdminGUI empDisplay = new EmployeeDisplayAdminGUI();
                dispose();
            }
        });


        meetingButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {

                RoomDisplayAdminGUI roomDisplay =new RoomDisplayAdminGUI();
                dispose();

            }
        });

        goBack.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {

            }
        });


        setVisible(true);
    }
}
