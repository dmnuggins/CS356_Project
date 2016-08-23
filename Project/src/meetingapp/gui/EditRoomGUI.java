package meetingapp.gui;

import meetingapp.entity.Employee;
import meetingapp.entity.Room;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

/**
 * Created by CesarRecinos on 8/16/2016.
 */
public class EditRoomGUI extends MeetingAppGUI{
    private JPanel rootPanel;
    private JLabel capacityLabel;
    private JLabel roomIDLabel;
    private JTextField capacityField;
    private JTextField numberField;
    private JButton saveButton;
    private JButton cancelButton;
    private JButton deleteButton;

    Room editing;

    public EditRoomGUI(final Employee employee, final Room editing) {
        super("Edit Room", employee);
        setup(rootPanel);

        this.editing = editing;

        numberField.setText(editing.getID()+"");
        capacityField.setText(editing.getCapacity()+"");


        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                editing.delete();
                new RoomDisplayAdminGUI(employee);
                dispose();


            }
        });
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                editing.setCapacity(Integer.parseInt(capacityField.getText()));
                new RoomDisplayAdminGUI(employee);
                dispose();
            }
        });
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new RoomDisplayAdminGUI(employee);
                dispose();
            }
        });

        setVisible(true);
    }

}
