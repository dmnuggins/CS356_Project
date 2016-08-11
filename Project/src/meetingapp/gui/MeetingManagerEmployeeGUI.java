package meetingapp.gui;

import meetingapp.entity.Employee;

import javax.swing.*;

/**
 * Created by dmnguyen on 8/9/16.
 */
public class MeetingManagerEmployeeGUI extends MeetingAppGUI {

    private JPanel meetingManagerRootPanel;
    private JPanel northPanel;
    private JPanel southPanel;
    private JPanel centerPanel;
    private JButton backButton;
    private JPanel southButtonPanel;
    private JTable ownMeetingTable;
    private JTable participantMeetingTable;
    private JButton cancelMeetingButton;
    private JButton updateMeetingButton;
    private JButton updateStatusButton;
    private JButton meetingInfoButton;
    private JScrollPane ownJScrollPane;
    private JScrollPane partJScrollPane;

    public MeetingManagerEmployeeGUI (final Employee employee) {
        super("Meeting Manager", employee);
        setContentPane(meetingManagerRootPanel);
        pack();

        setVisible(true);
    }
}
