package meetingapp.gui;

import meetingapp.entity.Employee;
import meetingapp.entity.Meeting;
import meetingapp.entity.Participant;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

/**
 * Created by CesarRecinos on 8/25/2016.
 */
public class MeetingInfoGUI extends MeetingAppGUI {
    private JPanel rootPanel;
    private JPanel labelPanel;
    private JLabel meetingLabel;
    private JPanel buttonPanel;
    private JButton backButton;
    private JLabel meetingTimeLabel;
    private JTable invitedTable;

    public MeetingInfoGUI(Employee employee, Meeting m) {
        super("Meeting Info", employee, false);
        setup(rootPanel);

        meetingTimeLabel.setText(m.getStartString());

        DefaultTableModel tm = new DefaultTableModel();
        tm.addColumn("Employee");
        tm.addColumn("Accepted");

        List<Participant> invited = m.getAllInvited(true, true);
        for (Participant p : invited) {
            String acceptedText = "no";
            if (p.getAccepted()) {
                acceptedText = "yes";
            }
            tm.addRow(new Object[] { p.getEmployee().getName(), acceptedText });
        }
        invitedTable.setModel(tm);

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        setVisible(true);
    }
}
