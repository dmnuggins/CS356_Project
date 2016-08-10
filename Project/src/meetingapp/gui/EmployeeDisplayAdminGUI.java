package meetingapp.gui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by CesarRecinos on 8/7/2016.
 */
public class EmployeeDisplayAdminGUI extends JFrame{
    private JPanel rootPanel;
    private JPanel textFieldPanel;
    private JPanel buttonPanel;
    private JComboBox comboBox1;
    private JButton createButton;
    private JButton editButton;
    private JButton cancelButton;

    public EmployeeDisplayAdminGUI(){
        super("Employees");
        setContentPane(rootPanel);
        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        comboBox1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        createButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();

                new AdminGUI().setVisible(true);
            }
        });

        setVisible(true);
    }
}
