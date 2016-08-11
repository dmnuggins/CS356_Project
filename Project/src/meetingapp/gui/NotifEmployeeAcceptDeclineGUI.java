package meetingapp.gui;

import javax.swing.*;

/**
 * Created by dmnguyen on 8/9/16.
 */
public class NotifEmployeeAcceptDeclineGUI extends MeetingAppGUI{
    private JPanel acceptDeclinePanel;
    private JPanel northPanel;
    private JPanel southPanel;
    private JTable table1;

    public NotifEmployeeAcceptDeclineGUI() {
        super("...", null);
        setContentPane(acceptDeclinePanel);
        pack();
        setVisible(true);
    }
}
