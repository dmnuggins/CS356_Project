package meetingapp.gui;

import meetingapp.db.RoomDB;
import meetingapp.entity.*;

import javax.swing.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;


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

    public RoomDisplayAdminGUI(final Employee employee){
        super("Meeting Rooms", employee);
        setContentPane(rootPanel);
        pack();

        ArrayList<Room> allRooms = (ArrayList<Room>) RoomDB.getInstance().loadAll();
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

            }
        });

        editRoom.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

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
