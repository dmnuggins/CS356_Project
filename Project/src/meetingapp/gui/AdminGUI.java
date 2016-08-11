package meetingapp.gui;
import meetingapp.entity.Employee;

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

    private Employee employee;

    public AdminGUI(final Employee employee) {

        super("Admin Menu");
        setContentPane(rootPanel);
        pack();
        setLocationRelativeTo(null);

        employeeButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                new EmployeeDisplayAdminGUI(employee);
                dispose();
            }
        });


        meetingButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                new RoomDisplayAdminGUI(employee);
                dispose();
            }
        });

        goBack.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                new AdminEmpChoiceGUI(employee);
                dispose();
            }
        });


        setVisible(true);
    }

}
