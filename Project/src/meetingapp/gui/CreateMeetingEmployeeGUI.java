package meetingapp.gui;

import meetingapp.db.MeetingDB;
import meetingapp.db.ParticipantDB;
import meetingapp.entity.*;

import javax.swing.*;
import java.awt.event.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.ArrayList;
import java.util.List;

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
    private JComboBox roomComboBox;
    private JLabel attendinglabel;
    private JPanel whenPanel;
    private JLabel selectedTimeLabel;
    private JButton manageButton;
    private JButton showAvailableTimesButton;
    private JLabel messageLabel;

    private MeetingAttendeesGUI meetAttendGUI;
    private AvailableTimesGUI availableTimesGUI;

    private List<Employee> invited;
    private List<Room> rooms;
    private LocalDateTime start;


    public CreateMeetingEmployeeGUI(final Employee employee) {
        super("New Meeting", employee);
        setup(rootPanel);

        this.employee = employee;
        invited = new ArrayList<>();

        DefaultComboBoxModel<String> rm = new DefaultComboBoxModel<>();
        rooms = Room.getAll();
        for (Room r : rooms) {
            rm.addElement("Room " + r.getID() + " (" + r.getCapacity() + ")");
        }
        roomComboBox.setModel(rm);

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

        createButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Room selectedRoom = rooms.get(roomComboBox.getSelectedIndex());
                if (invited.size() == 0) {
                    messageLabel.setText("Cannot create meeting with no employees!");
                } else if (start == null) {
                    messageLabel.setText("Please select a start time!");
                } else if (selectedRoom.getCapacity() < invited.size()) {
                    messageLabel.setText("Too many people for room!");
                } else {
                    int nextMeetingID = MeetingDB.getInstance().getNextID();
                    Meeting newMeeting = new Meeting(nextMeetingID, selectedRoom.getID(), LocalDateTime.now());
                    newMeeting.save();

                    //create participant for owner
                    int ownerParticipantID = ParticipantDB.getInstance().getNextID();
                    Participant owner = new Participant(ownerParticipantID, employee.getID(), newMeeting.getID(), true, true, true, true);
                    owner.save();

                    //create participant for each invited
                    for (Employee emp : invited) {
                        int participantID = ParticipantDB.getInstance().getNextID();
                        Participant par = new Participant(participantID, emp.getID(), newMeeting.getID(), false, false, false, false);
                        par.save();
                    }

                    //dispose
                    new EmployeeGUI(employee);
                    dispose();
                }
            }
        });

        showAvailableTimesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (availableTimesGUI == null) {
                    if (invited.size() > 0) {
                        availableTimesGUI = new AvailableTimesGUI(employee, invited);
                        availableTimesGUI.addWindowListener(new WindowAdapter() {
                            @Override
                            public void windowClosing(WindowEvent e) {
                                if (availableTimesGUI.getSelectedTime() != null) {
                                    start = availableTimesGUI.getSelectedTime();
                                    selectedTimeLabel.setText(start.format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.SHORT)));
                                }
                                availableTimesGUI = null;
                                super.windowClosing(e);
                            }
                        });
                    } else {
                        messageLabel.setText("Please select employees first!");
                    }
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
