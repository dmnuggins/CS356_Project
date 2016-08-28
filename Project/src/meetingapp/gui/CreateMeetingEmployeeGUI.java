package meetingapp.gui;

import meetingapp.entity.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;

/**
 * Created by Dylan Nguyen on 8/8/2016.
 */
public class CreateMeetingEmployeeGUI extends MeetingAppGUI{
    private JPanel rootPanel;
    private JButton createButton;
    private JButton cancelButton;
    private JPanel labelPanel;
    private JPanel buttonPanel;
    private JPanel contentLabPan;
    private JPanel attendingPanel;
    private JList invitedList;
    private JPanel roomPanel;
    private JLabel roomlabel;
    private JComboBox comboBox1;
    private JLabel attendinglabel;
    private JPanel whenPanel;
    private JLabel dayLabel;
    private JComboBox comboBox2;
    private JLabel startLabel;
    private JComboBox comboBox3;
    private JButton manageButton;
    private MeetingAttendeesGUI meetAttendGUI;

    private List<Employee> invited;


    public CreateMeetingEmployeeGUI(final Employee employee) {
        super("New Meeting", employee);
        setup(rootPanel);

        this.employee = employee;
        invited = new ArrayList<>();

        manageButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (meetAttendGUI == null) {
                    meetAttendGUI = new MeetingAttendeesGUI(employee, invited);

                    meetAttendGUI.addWindowListener(new WindowAdapter() {
                        @Override
                        public void windowClosing(WindowEvent e) {
                            //read out employees from other window
                            invited = meetAttendGUI.getAttending();
                            populateInvitedList(invited);
                            meetAttendGUI = null;

                            //continue closing window
                            super.windowClosing(e);
                        }
                    });
                }
            }
        });

        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new EmployeeGUI(employee);
                dispose();
            }
        });

        setVisible(true);
    }

    private void populateInvitedList(List<Employee> invited) {
        DefaultListModel model = new DefaultListModel();
        for (Employee e : invited) {
            model.addElement(e.getName());
        }
        invitedList.setModel(model);
    }

}
