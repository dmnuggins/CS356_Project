package meetingapp.gui;
import meetingapp.entity.Employee;
import meetingapp.entity.Room;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


/**
 * Created by CesarRecinos on 8/9/2016.
 */
public class CreateRoomGUI extends MeetingAppGUI {
    private JPanel rootPanel;
    private JTextField numberField;
    private JTextField capacityField;
    private JLabel roomIDLabel;
    private JLabel capacityLabel;
    private JButton saveButton;
    private JButton cancelButton;
    private JPanel buttonPanel;
    private JPanel gridPanel;
    private JPanel roomPanel;
    private JPanel labelPanel;

    Room room;
    public CreateRoomGUI(final Employee employee){
        super("Create a New Room", employee);
        setup(rootPanel);


        numberField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        capacityField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                room = new Room(Integer.parseInt(numberField.getText()),Integer.parseInt(capacityField.getText()));
                room.setCapacity(Integer.parseInt(capacityField.getText()));
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
