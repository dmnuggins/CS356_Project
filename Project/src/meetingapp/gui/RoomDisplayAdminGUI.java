package meetingapp.gui;

import meetingapp.entity.*;

import javax.swing.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;


/**
 * Created by CesarRecinos on 8/7/2016.
 */
public class RoomDisplayAdminGUI extends MeetingAppGUI {
    private JPanel rootPanel;
    private JPanel textPanel;
    private JPanel buttonPanel;
    private JButton createRoom;
    private JButton editRoom;
    private JButton cancelButton;
    private JComboBox comboBox1;
    private JPanel labelPanel;


    public RoomDisplayAdminGUI(final Employee employee){
        super("Meeting Rooms", employee);
        setup(rootPanel);

        List<Room> allRooms = Room.getAll();
        for (Room r : allRooms) {
            comboBox1.addItem("Room " + r.getID());
        }


        comboBox1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        createRoom.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new CreateRoomGUI(employee);
                dispose();
            }
        });

        editRoom.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Room selected = allRooms.get(comboBox1.getSelectedIndex());
                new EditRoomGUI(employee, selected);
                dispose();

            }
        });

        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AdminGUI(employee);
                dispose();
            }
        });

        setVisible(true);
    }
}
