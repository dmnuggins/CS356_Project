package meetingapp.gui;

import javax.swing.*;

/**
 * Created by dmnguyen on 8/9/16.
 */
public class NotifEmployeeAcceptDeclineGUI extends JFrame{
    private JPanel acceptDeclinePanel;
    private JPanel northPanel;
    private JPanel southPanel;
    private JTable table1;

    public void showGUI() {
        setTitle("meetingapp.entity.Login");
        setContentPane(acceptDeclinePanel);
        pack();
        setVisible(true);
        setLocationRelativeTo(null);
        setResizable(false);

    }
}
